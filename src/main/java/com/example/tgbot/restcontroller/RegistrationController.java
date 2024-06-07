package com.example.tgbot.restcontroller;

import com.example.tgbot.entity.User;
import com.example.tgbot.service.BotTokenService;
import com.example.tgbot.service.RegistrationService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class RegistrationController {

    @Value("${bot.token}")
    private String botToken;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Autowired
    RegistrationService registrationService;

    @Autowired
    UserService userService;

    @Autowired
    BotTokenService botTokenService;

    @GetMapping("/registration/{playerId}/{chatId}/{country}")
    public ResponseEntity<String> receiveData(@PathVariable String playerId,@PathVariable String chatId,@PathVariable String country) {
        try {
            registrationService.saveRegistration(playerId);
            registerUserIn1Win(playerId,country);

                User currentUser = userService.getInfoAboutUserByChatID(Long.valueOf(chatId));
                if(currentUser != null){
                    currentUser.setOneWinId(Long.valueOf(playerId));
                    currentUser.setIsVerify(true);
                    currentUser.setAwaitingMode(false);
                    userService.saveUser(currentUser);
                    sendMessageAboutSuccessfulRegistrationToUserIn(chatId,currentUser);
                }




        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok("Пользователь с ID: " + playerId + " зарегистрировался в 1win");
    }


    public void registerUserIn1Win(String playerId, String country) {
        try {
            // Формирование текста с использованием поддерживаемых HTML тегов
            String text = "🎉 Новая Регистрация! 🌍\n\n" +
                    "🆔 ID: <code>" + playerId + "</code>\n" +
                    "🌐 Страна: " + country + "\n";

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
    public void sendMessageAboutSuccessfulRegistrationToUserIn(String chatId,User currentUser) {

        try {
            // Формирование текста и кнопок
            String text = "";
            String replyMarkup = "";
            switch (currentUser.getLanguage()) {
                case "russian":
                    text = "Вы успешно зарегистрированы! 🎉 Теперь, чтобы получить доступ к сигналам, вам нужно пополнить баланс вашего игрового аккаунта, " +
                            "который вы только что зарегистрировали. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Как пополнить баланс? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "english":
                    text = "You have successfully registered! 🎉 Now, to access the signals, you just need to replenish the balance " +
                            "of your gaming account that you have just registered. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"How to top up? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "hindi":
                    text = "आप सफलतापूर्वक पंजीकृत हो गए हैं! 🎉 अब, सिग्नल तक पहुंचने के लिए, आपको बस अपने गेमिंग खाते का बैलेंस " +
                            "पुनः भरना होगा जिसे आपने अभी पंजीकृत किया है। 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"बैलेंस कैसे भरें? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "brazilian":
                    text = "Você se registrou com sucesso! 🎉 Agora, para acessar os sinais, você só precisa recarregar o saldo " +
                            "da sua conta de jogo que acabou de registrar. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Como recarregar? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "spanish":
                    text = "¡Te has registrado con éxito! 🎉 Ahora, para acceder a las señales, solo necesitas recargar el saldo " +
                            "de tu cuenta de juego que acabas de registrar. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"¿Cómo recargar el saldo? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "uzbek":
                    text = "Siz muvaffaqiyatli ro'yxatdan o'tdingiz! 🎉 Endi signallarga kirish uchun, faqatgina o'yin hisobingiz balansini " +
                            "to'ldirishingiz kerak. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Balansni qanday to'ldirish mumkin? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "azerbaijani":
                    text = "Siz uğurla qeydiyyatdan keçdiniz! 🎉 İndi siqnallara daxil olmaq üçün, sadəcə qeydiyyatdan keçdiyiniz oyun hesabınızın balansını " +
                            "doldurmanız kifayətdir. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Balansı necə doldurmaq olar? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "turkish":
                    text = "Başarıyla kayıt oldunuz! 🎉 Artık sinyallere erişmek için, sadece yeni kaydettiğiniz oyun hesabınızın bakiyesini " +
                            "yenilemeniz yeterli. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Bakiye nasıl yenilenir? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "portuguese":
                    text = "Você se registrou com sucesso! 🎉 Agora, para acessar os sinais, você só precisa recarregar o saldo " +
                            "da sua conta de jogo que acabou de registrar. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"Como recarregar? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                case "arabic":
                    text = "لقد سجلت بنجاح! 🎉 الآن، للوصول إلى الإشارات، ما عليك سوى إعادة شحن رصيد " +
                            "حساب الألعاب الذي قمت بتسجيله للتو. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"كيف تعبئة الرصيد؟ 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
                            StandardCharsets.UTF_8.toString());
                    break;
                default:
                    text = "You have successfully registered! 🎉 Now, to access the signals, you just need to replenish the balance " +
                            "of your gaming account that you have just registered. 💳";
                    replyMarkup = URLEncoder.encode(
                            "{\"inline_keyboard\":[[{\"text\":\"How to top up? 💰\",\"callback_data\":\"mines_give_signal\"}]]}",
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
