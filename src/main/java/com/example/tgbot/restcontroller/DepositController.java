package com.example.tgbot.restcontroller;

import com.example.tgbot.entity.User;
import com.example.tgbot.service.BotTokenService;
import com.example.tgbot.service.DepositService;
import com.example.tgbot.service.RegistrationService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
@PropertySource("/application.properties")
public class DepositController {

    @Value("${bot.token}")
    private String botToken;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Autowired
    DepositService depositService;

    @Autowired
    UserService userService;

    @Autowired
    BotTokenService botTokenService;


    @GetMapping("/deposit/{playerId}/{amount}/{country}")
    public ResponseEntity<String> getDeposit(@PathVariable String playerId,
                                             @PathVariable double amount,
                                             @PathVariable String country){

        try {
            firstUserDepositIn1Win(playerId,amount,country);
            userService.setIsDepositedIsTrue(playerId);
            depositService.saveDeposit(playerId);
            User currentPlayer = userService.getByOneWinId(Long.valueOf(playerId));
            sendMessageAboutSuccessfulFirstDepositToUser(String.valueOf(currentPlayer.getChatId()),currentPlayer);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok("Пользователь с ID: " + playerId + " Сделал первый депозит");
    }

    @GetMapping("/redeposit/{playerId}/{amount}/{country}")
    public ResponseEntity<String> getReDeposit(@PathVariable String playerId,
                                               @PathVariable double amount,
                                               @PathVariable String country){
        log.info("Пользователь с ID: " + playerId + " сделал повторный депозит " + amount );
        try {
            userReDepositIn1Win(playerId,amount,country);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok("Пользователь с ID: " + playerId + " Сделал повторный депозит");
    }

    public void userReDepositIn1Win(String playerId, double amount, String country) {
        try {
            // Формирование текста с использованием поддерживаемых HTML тегов
            String text = "🌟 Первый депозит! 🎉\n\n" +
                    "🆔 ID пользователя: <code>" + playerId + "</code>\n" +
                    "🌍 Страна: " + country + "\n" +
                    "💸 Сумма депозита: " + amount + "$";

            // Кодирование текста для URL
            text = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());

            // Формирование URL с параметром parse_mode=HTML
            String url = "https://api.telegram.org/bot" + botTokenService.getTokenForPostbackBot() +
                    "/sendMessage?chat_id=" + botTokenService.getChatId() + "&text=" + text + "&parse_mode=HTML";

            // Отправка GET запроса
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET() // GET запрос
                    .build();

            // Получение ответа от сервера
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Логирование ответа от сервера
            System.out.println("Статус код: " + response.statusCode());
            System.out.println("Ответ: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Обработка ошибок запроса и кодирования
        }
    }






    public void firstUserDepositIn1Win(String playerId, double amount, String country) {
        try {
            // Формирование текста с использованием поддерживаемых HTML тегов
            String text = "🌟 Депозит! 🎉\n\n" +
                    "🆔 ID пользователя: <code>" + playerId + "</code>\n" +
                    "🌍 Страна: " + country + "\n" +
                    "💸 Сумма депозита: " + amount + "$";

            // Кодирование текста для URL
            text = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());

            // Формирование URL с параметром parse_mode=HTML
            String url = "https://api.telegram.org/bot" + botTokenService.getTokenForPostbackBot() +
                    "/sendMessage?chat_id=" + botTokenService.getChatId() + "&text=" + text + "&parse_mode=HTML";

            // Отправка GET запроса
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET() // GET запрос
                    .build();

            // Получение ответа от сервера
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Логирование ответа от сервера
            System.out.println("Статус код: " + response.statusCode());
            System.out.println("Ответ: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Обработка ошибок запроса и кодирования
        }
    }

    public void sendMessageAboutSuccessfulFirstDepositToUser(String chatId,User currentUser) {
        try {
            // Формирование текста и кнопок
            String text = "";
            String replyMarkup = "";
            switch (currentUser.getLanguage()) {
                case "russian":
                    text = "Вы успешно сделали первый депозит! 🎉 Теперь у вас есть доступ к сигналам. Время начинать зарабатывать! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Выбрать игру 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "english":
                    text = "You have successfully made your first deposit! 🎉 Now you have access to the signals. Time to start earning! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Choose The game 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "hindi":
                    text = "आपने सफलतापूर्वक पहली जमा कर दी है! 🎉 अब आपके पास सिग्नल तक पहुंच है। कमाई शुरू करने का समय! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"खेल चुनें 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "brazilian":
                    text = "Você fez com sucesso o seu primeiro depósito! 🎉 Agora você tem acesso aos sinais. Hora de começar a ganhar! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Escolha o jogo 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "spanish":
                    text = "¡Has hecho con éxito tu primer depósito! 🎉 Ahora tienes acceso a las señales. ¡Hora de empezar a ganar! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Elige el juego 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "uzbek":
                    text = "Siz birinchi depozitni muvaffaqiyatli amalga oshirdingiz! 🎉 Endi sizda signallarga kirish mavjud. Pul ishlashni boshlash vaqti! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"O'yinni tanlang 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "azerbaijani":
                    text = "Siz ilk depozitinizi uğurla etdiniz! 🎉 İndi siqnallara girişiniz var. Pul qazanmağa başlama vaxtı! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Oyunu seçin 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "turkish":
                    text = "İlk depozitonuzu başarıyla yaptınız! 🎉 Artık sinyallere erişiminiz var. Kazanmaya başlama zamanı! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Oyunu seç 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "portuguese":
                    text = "Você fez com sucesso o seu primeiro depósito! 🎉 Agora você tem acesso aos sinais. Hora de começar a ganhar! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Escolha o jogo 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "arabic":
                    text = "لقد قمت بعمل أول إيداع بنجاح! 🎉 الآن لديك إمكانية الوصول إلى الإشارات. حان وقت البدء في الكسب! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"اختر اللعبة 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                default:
                    text = "You have successfully made your first deposit! 🎉 Now you have access to the signals. Time to start earning! 💸";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Choose The game 📈\",\"callback_data\":\"choose_games_menu\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
            }


            // Создание тела запроса
            String requestBody = "chat_id=" + URLEncoder.encode(chatId, StandardCharsets.UTF_8) +
                    "&text=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                    "&reply_markup=" + replyMarkup;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.telegram.org/bot" + botToken + "/sendMessage"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody)) // Использование POST запроса
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Логирование ответа от сервера
            System.out.println("Статус код: " + response.statusCode());
            System.out.println("Ответ: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Обработка ошибок запроса и кодирования
        }
    }


}
