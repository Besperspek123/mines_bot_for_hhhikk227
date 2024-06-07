package com.example.tgbot.service.admin;

import com.example.tgbot.entity.User;
import com.example.tgbot.exception.AccessDeniedException;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithBroadcastTextAndBroadcasterChatId;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class SendMessageToUsersCommand implements CommandWithBroadcastTextAndBroadcasterChatId {

    private static final int THREAD_POOL_SIZE = 10; // Задайте количество потоков в пуле
    private static final int MESSAGES_PER_SECOND_LIMIT = 30; // Лимит сообщений в секунду

    private final UserService userService;
    private final BotActions botActions;
    private final List<User> users;
    private final Map<String, String> photoCache = new HashMap<>();

    public SendMessageToUsersCommand(BotActions botActions, UserService userService, List<User> users) {
        this.botActions = botActions;
        this.userService = userService;
        this.users = users;
    }

    @Override
    public void execute(String broadcastText, long broadcasterChatID) {
        if (!userService.getInfoAboutUserByChatID(broadcasterChatID).getIsAdministrator()) {
            botActions.sendMessageWithoutCallbackQuery(broadcasterChatID, "У вас нету прав на выполнение этой команды");
            throw new AccessDeniedException("У вас нету прав на выполнение этой команды");
        }
        List<User> usersList = users;
        AtomicInteger successfulMessagesCount = new AtomicInteger();
        AtomicInteger failedMessagesCount = new AtomicInteger();

        ScheduledExecutorService newExecutorService = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);

        AtomicInteger currentMessagesCount = new AtomicInteger();

        CompletableFuture<Void> allMessages = CompletableFuture.allOf(
                usersList.stream()
                        .map(user -> CompletableFuture.runAsync(() -> {
                            try {
                                if (botActions.sendBroadcastMessage(user.getChatId(), broadcastText, user.getUsername())) {
                                    successfulMessagesCount.getAndIncrement();
                                } else {
                                    failedMessagesCount.getAndIncrement();
                                }
                            }
                            catch (Exception e) {
                                log.error("Error:",e);
                            }

                            int messagesCount = currentMessagesCount.incrementAndGet();
                            if (messagesCount % MESSAGES_PER_SECOND_LIMIT == 0) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    log.error("Ошибка при ожидании перед отправкой следующего сообщения", e);
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }, newExecutorService))
                        .toArray(CompletableFuture[]::new)
        );

        try {
            allMessages.join();
        } catch (CompletionException e) {
            log.error("Ошибка при выполнении рассылки", e.getCause());
        } finally {
            newExecutorService.shutdown();
        }

        botActions.sendMessageWithoutCallbackQuery(broadcasterChatID, "Рассылок отправлено: " + successfulMessagesCount + "\n"
                + "Рассылок не было отправлено: " + failedMessagesCount);
    }

    @Override
    public void execute(long broadcasterChatID, String broadcastText, byte[] photoBytes) {
        if (!userService.getInfoAboutUserByChatID(broadcasterChatID).getIsAdministrator()) {
            botActions.sendMessageWithoutCallbackQuery(broadcasterChatID, "У вас нету прав на выполнение этой команды");
            throw new AccessDeniedException("У вас нету прав на выполнение этой команды");
        }

        List<User> usersList = users;
        AtomicInteger successfulMessagesCount = new AtomicInteger();
        AtomicInteger failedMessagesCount = new AtomicInteger();

        ScheduledExecutorService newExecutorService = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);

        AtomicInteger currentMessagesCount = new AtomicInteger();

        // Загрузить фотографию и получить URL
        String photoUrl = botActions.uploadPhoto(broadcasterChatID, photoBytes);
        if (photoUrl == null) {
            botActions.sendMessageWithoutCallbackQuery(broadcasterChatID, "Не удалось загрузить фотографию");
            return;
        }

        CompletableFuture<Void> allMessages = CompletableFuture.allOf(
                usersList.stream()
                        .map(user -> CompletableFuture.runAsync(() -> {
                            try {
                                if (botActions.sendBroadcastMessageWithPhotoUrl(user.getChatId(), broadcastText, photoUrl)) {
                                    successfulMessagesCount.getAndIncrement();
                                } else {
                                    failedMessagesCount.getAndIncrement();
                                }
                            } catch (Exception e) {
                                log.error("Error:",e);
                            }

                            int messagesCount = currentMessagesCount.incrementAndGet();
                            if (messagesCount % MESSAGES_PER_SECOND_LIMIT == 0) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    log.error("Ошибка при ожидании перед отправкой следующего сообщения", e);
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }, newExecutorService))
                        .toArray(CompletableFuture[]::new)
        );

        try {
            allMessages.join();
        } catch (CompletionException e) {
            log.error("Ошибка при выполнении рассылки", e.getCause());
        } finally {
            newExecutorService.shutdown();
        }

        botActions.sendMessageWithoutCallbackQuery(broadcasterChatID, "Рассылок отправлено: " + successfulMessagesCount + "\n"
                + "Рассылок не было отправлено: " + failedMessagesCount);
    }

}