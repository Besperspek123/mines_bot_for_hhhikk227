package com.example.tgbot.service.main.coinflip;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.CoinFlipKeyboard;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class CoinInstructionCommand implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private UserService userService;

    public CoinInstructionCommand(BotActions botActions, UrlService urlService, PromoService promoService, UserService userService) {
        this.botActions = botActions;
        this.urlService = urlService;
        this.promoService = promoService;
        this.userService = userService;
    }
    @Override
    public void execute(long chatId, CallbackQuery callbackQuery) {
        User user = userService.getInfoAboutUserByChatID(chatId);
        String oneWinUrl = urlService.getOneWinUrl(userService.getInfoAboutUserByChatID(chatId));
        if (userService.userIsRussian(user) || userService.userIsAzerbaijani(user) || userService.userIsUzbek(user)) {
            botActions.sendPhoto(chatId,"/imagesrussian/coinflip/coinflip_logo.jpg");
        }
        else {
            botActions.sendPhoto(chatId,"/imagesenglish/coinflip/coinflip_logo.jpg");
        }
        String answer = "";


        switch (user.getLanguage()) {
            case "russian":
                if (user.getIsVerify()) {
                    answer = "*Бот основан и обучен на кластере нейросети bitsGap🧠*\n\n" +
                            "Для тренировки бота было сыграно более 10 000 игр 🎰\n" +
                            "В данный момент пользователи бота успешно делают в день 15-25% от своего капитала! 💰\n\n" +
                            "На текущий момент бот по сей день проходит проверки и исправления! Точность бота составляет 87%!\n\n" +
                            "Для получения максимального профита следуйте следующей инструкции:\n\n" +
                            "🟢 1. Перейти в раздел 1win games и выбрать игру 'Coinflip'.\n\n" +
                            "🟢 2. Выберите сторону монеты (орёл или решка). Это важно для вашей ставки!\n\n" +
                            "🟢 3. Запросить сигнал в боте и ставить по сигналам из бота.\n\n" +
                            "🟢 4. При неудачном сигнале советуем удвоить (Х²) ставку, чтобы попытаться восстановить предыдущие потери.\n\n" +
                            "   ⚠ Важно: Помните, что каждая игра имеет 50/50 шансов, и стратегия удвоения может быть рискованной. Играйте ответственно и управляйте своим банкроллом осторожно.\n\n" +
                            "   Попробуйте сегодня и увидьте, как ваш капитал растет с помощью нашего бота! 💹";
                } else {
                    answer = "*Бот основан и обучен на кластере нейросети bitsGap🧠*\n\n" +
                            "Для тренировки бота было сыграно более 10 000 игр 🎰\n" +
                            "В данный момент пользователи бота успешно делают в день 15-25% от своего капитала! 💰\n\n" +
                            "На текущий момент бот по сей день проходит проверки и исправления! Точность бота составляет 87%!\n\n" +
                            "Для получения максимального профита следуйте следующей инструкции:\n\n" +
                            "🟢 1. Зарегистрировать аккаунт через нашего бота, по кнопке 'Зарегистрироваться' под сообщением, без этого вы не сможете получить доступ к сигналам.\n\n" +
                            "🟢 2. Пополнить баланс своего аккаунта.\n\n" +
                            "🟢 3. Перейти в раздел 1win games и выбрать игру 'Coinflip'.\n\n" +
                            "🟢 4. Выберите сторону монеты (орёл или решка). Это важно для вашей ставки!\n\n" +
                            "🟢 5. Запросить сигнал в боте и ставить по сигналам из бота.\n\n" +
                            "🟢 6. При неудачном сигнале советуем удвоить (Х²) ставку, чтобы попытаться восстановить предыдущие потери.\n\n" +
                            "   ⚠ Важно: Помните, что каждая игра имеет 50/50 шансов, и стратегия удвоения может быть рискованной. Играйте ответственно и управляйте своим банкроллом осторожно.\n\n" +
                            "   Попробуйте сегодня и увидьте, как ваш капитал растет с помощью нашего бота! 💹";
                }
                break;
            case "english":
                if (user.getIsVerify()) {
                    answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                            "The bot has been trained with more than 10,000 games 🎰\n" +
                            "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                            "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                            "To maximize your profits, follow these instructions:\n\n" +
                            "🟢 1. Go to the 1win games section and select the game 'Coinflip'.\n\n" +
                            "🟢 2. Choose a side of the coin (heads or tails). This is crucial for your betting strategy!\n\n" +
                            "🟢 3. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                            "🟢 4. If a signal fails, we recommend doubling (X²) the bet to attempt to recover previous losses.\n\n" +
                            "   ⚠ Important: Remember that each game has a 50/50 chance, and the doubling strategy can be risky. Play responsibly and manage your bankroll cautiously.\n\n" +
                            "   Try it today and see how your capital grows with our bot! 💹";
                } else {
                    answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                            "The bot has been trained with more than 10,000 games 🎰\n" +
                            "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                            "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                            "To maximize your profits, follow these instructions:\n\n" +
                            "🟢 1. Register an account through our bot by clicking the 'Register' button under the message, without this you will not be able to access the signals.\n\n" +
                            "🟢 2. Top up your account balance.\n\n" +
                            "🟢 3. Go to the 1win games section and select the game 'Coinflip'.\n\n" +
                            "🟢 4. Choose a side of the coin (heads or tails). This is crucial for your betting strategy!\n\n" +
                            "🟢 5. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                            "🟢 6. If a signal fails, we recommend doubling (X²) the bet to attempt to recover previous losses.\n\n" +
                            "   ⚠ Important: Remember that each game has a 50/50 chance, and the doubling strategy can be risky. Play responsibly and manage your bankroll cautiously.\n\n" +
                            "   Try it today and see how your capital grows with our bot! 💹";
                }
                break;
            case "hindi":
                if (user.getIsVerify()) {
                    answer = "*बॉट bitsGap न्यूरल नेटवर्क क्लस्टर पर आधारित और प्रशिक्षित है🧠*\n\n" +
                            "बॉट को प्रशिक्षित करने के लिए 10,000 से अधिक खेल खेले गए 🎰\n" +
                            "वर्तमान में, बॉट उपयोगकर्ता अपने पूंजी का प्रतिदिन 15-25% सफलतापूर्वक कमा रहे हैं! 💰\n\n" +
                            "इस समय, बॉट अभी भी जांच और समायोजन के दौर से गुजर रहा है! बॉट की सटीकता 87% है!\n\n" +
                            "अपने मुनाफे को अधिकतम करने के लिए, इन निर्देशों का पालन करें:\n\n" +
                            "🟢 1. 1win गेम्स अनुभाग में जाएं और 'Coinflip' गेम चुनें।\n\n" +
                            "🟢 2. सिक्के की एक साइड चुनें (हेड्स या टेल्स)। यह आपकी सट्टेबाजी की रणनीति के लिए महत्वपूर्ण है!\n\n" +
                            "🟢 3. बॉट में एक संकेत का अनुरोध करें और बॉट के संकेतों के आधार पर दांव लगाएं।\n\n" +
                            "🟢 4. यदि एक संकेत विफल होता है, तो हम पिछले नुकसान की वसूली के लिए दांव को दोगुना (X²) करने की सलाह देते हैं।\n\n" +
                            "   ⚠ महत्वपूर्ण: याद रखें कि प्रत्येक गेम में 50/50 का मौका होता है, और दोगुना करने की रणनीति जोखिम भरी हो सकती है। जिम्मेदारी से खेलें और अपने बैंक रोल को सावधानीपूर्वक प्रबंधित करें।\n\n" +
                            "   आज ही आज़माएं और देखें कि आपके पूंजी हमारे बॉट के साथ कैसे बढ़ती है! 💹";
                } else {
                    answer = "*बॉट bitsGap न्यूरल नेटवर्क क्लस्टर पर आधारित और प्रशिक्षित है🧠*\n\n" +
                            "बॉट को प्रशिक्षित करने के लिए 10,000 से अधिक खेल खेले गए 🎰\n" +
                            "वर्तमान में, बॉट उपयोगकर्ता अपने पूंजी का प्रतिदिन 15-25% सफलतापूर्वक कमा रहे हैं! 💰\n\n" +
                            "इस समय, बॉट अभी भी जांच और समायोजन के दौर से गुजर रहा है! बॉट की सटीकता 87% है!\n\n" +
                            "अपने मुनाफे को अधिकतम करने के लिए, इन निर्देशों का पालन करें:\n\n" +
                            "🟢 1. हमारे बॉट के माध्यम से खाता पंजीकृत करें, संदेश के नीचे 'रजिस्टर' बटन पर क्लिक करें, इसके बिना आप संकेतों तक पहुंच प्राप्त नहीं कर पाएंगे।\n\n" +
                            "🟢 2. अपने खाते की शेष राशि को टॉप अप करें।\n\n" +
                            "🟢 3. 1win गेम्स अनुभाग में जाएं और 'Coinflip' गेम चुनें।\n\n" +
                            "🟢 4. सिक्के की एक साइड चुनें (हेड्स या टेल्स)। यह आपकी सट्टेबाजी की रणनीति के लिए महत्वपूर्ण है!\n\n" +
                            "🟢 5. बॉट में एक संकेत का अनुरोध करें और बॉट के संकेतों के आधार पर दांव लगाएं।\n\n" +
                            "🟢 6. यदि एक संकेत विफल होता है, तो हम पिछले नुकसान की वसूली के लिए दांव को दोगुना (X²) करने की सलाह देते हैं।\n\n" +
                            "   ⚠ महत्वपूर्ण: याद रखें कि प्रत्येक गेम में 50/50 का मौका होता है, और दोगुना करने की रणनीति जोखिम भरी हो सकती है। जिम्मेदारी से खेलें और अपने बैंक रोल को सावधानीपूर्वक प्रबंधित करें।\n\n" +
                            "   आज ही आज़माएं और देखें कि आपके पूंजी हमारे बॉट के साथ कैसे बढ़ती है! 💹";
                }
                break;
            case "brazilian":
                if (user.getIsVerify()) {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot fazem com sucesso 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Vá para a seção de jogos 1win e selecione o jogo 'Coinflip'.\n\n" +
                            "🟢 2. Escolha um lado da moeda (coroa ou cara). Isso é crucial para sua estratégia de apostas!\n\n" +
                            "🟢 3. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 4. Se um sinal falhar, recomendamos dobrar (X²) a aposta para tentar recuperar perdas anteriores.\n\n" +
                            "   ⚠ Importante: Lembre-se de que cada jogo tem uma chance de 50/50, e a estratégia de dobrar pode ser arriscada. Jogue com responsabilidade e gerencie seu bankroll com cautela.\n\n" +
                            "   Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                } else {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot fazem com sucesso 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Registre uma conta através do nosso bot clicando no botão 'Registrar' sob a mensagem, sem isso você não poderá acessar os sinais.\n\n" +
                            "🟢 2. Recarregue o saldo da sua conta.\n\n" +
                            "🟢 3. Vá para a seção de jogos 1win e selecione o jogo 'Coinflip'.\n\n" +
                            "🟢 4. Escolha um lado da moeda (coroa ou cara). Isso é crucial para sua estratégia de apostas!\n\n" +
                            "🟢 5. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 6. Se um sinal falhar, recomendamos dobrar (X²) a aposta para tentar recuperar perdas anteriores.\n\n" +
                            "   ⚠ Importante: Lembre-se de que cada jogo tem uma chance de 50/50, e a estratégia de dobrar pode ser arriscada. Jogue com responsabilidade e gerencie seu bankroll com cautela.\n\n" +
                            "   Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                }
                break;
            case "spanish":
                if (user.getIsVerify()) {
                    answer = "*El bot está basado y entrenado en el clúster de redes neuronales bitsGap🧠*\n\n" +
                            "El bot ha sido entrenado con más de 10,000 juegos 🎰\n" +
                            "Actualmente, los usuarios del bot hacen con éxito el 15-25% de su capital por día! 💰\n\n" +
                            "En este momento, el bot todavía está pasando por verificaciones y ajustes! La precisión del bot es del 87%!\n\n" +
                            "Para maximizar sus ganancias, siga estas instrucciones:\n\n" +
                            "🟢 1. Vaya a la sección de juegos de 1win y seleccione el juego 'Coinflip'.\n\n" +
                            "🟢 2. Elija un lado de la moneda (cara o cruz). ¡Esto es crucial para su estrategia de apuestas!\n\n" +
                            "🟢 3. Solicite una señal en el bot y haga apuestas basadas en las señales del bot.\n\n" +
                            "🟢 4. Si una señal falla, recomendamos duplicar (X²) la apuesta para intentar recuperar las pérdidas anteriores.\n\n" +
                            "   ⚠ Importante: Recuerde que cada juego tiene una probabilidad de 50/50, y la estrategia de duplicar puede ser arriesgada. Juegue con responsabilidad y gestione su bankroll con cuidado.\n\n" +
                            "   ¡Pruébelo hoy y vea cómo crece su capital con nuestro bot! 💹";
                } else {
                    answer = "*El bot está basado y entrenado en el clúster de redes neuronales bitsGap🧠*\n\n" +
                            "El bot ha sido entrenado con más de 10,000 juegos 🎰\n" +
                            "Actualmente, los usuarios del bot hacen con éxito el 15-25% de su capital por día! 💰\n\n" +
                            "En este momento, el bot todavía está pasando por verificaciones y ajustes! La precisión del bot es del 87%!\n\n" +
                            "Para maximizar sus ganancias, siga estas instrucciones:\n\n" +
                            "🟢 1. Registre una cuenta a través de nuestro bot haciendo clic en el botón 'Registrar' debajo del mensaje, sin esto no podrá acceder a las señales.\n\n" +
                            "🟢 2. Recargue el saldo de su cuenta.\n\n" +
                            "🟢 3. Vaya a la sección de juegos de 1win y seleccione el juego 'Coinflip'.\n\n" +
                            "🟢 4. Elija un lado de la moneda (cara o cruz). ¡Esto es crucial para su estrategia de apuestas!\n\n" +
                            "🟢 5. Solicite una señal en el bot y haga apuestas basadas en las señales del bot.\n\n" +
                            "🟢 6. Si una señal falla, recomendamos duplicar (X²) la apuesta para intentar recuperar las pérdidas anteriores.\n\n" +
                            "   ⚠ Importante: Recuerde que cada juego tiene una probabilidad de 50/50, y la estrategia de duplicar puede ser arriesgada. Juegue con responsabilidad y gestione su bankroll con cuidado.\n\n" +
                            "   ¡Pruébelo hoy y vea cómo crece su capital con nuestro bot! 💹";
                }
                break;
            case "uzbek":
                if (user.getIsVerify()) {
                    answer = "*Bot bitsGap neyron tarmoq klasterida asoslangan va o'qitilgan🧠*\n\n" +
                            "Botni o'qitish uchun 10,000 dan ortiq o'yinlar o'tkazildi 🎰\n" +
                            "Hozirda bot foydalanuvchilari o'z kapitalining 15-25% ni har kuni muvaffaqiyatli qilmoqdalar! 💰\n\n" +
                            "Hozirda bot hali ham tekshirish va sozlash jarayonidan o'tmoqda! Botning aniqligi 87%!\n\n" +
                            "O'zingizning daromadingizni maksimal qilish uchun ushbu ko'rsatmalarga rioya qiling:\n\n" +
                            "🟢 1. 1win o'yinlari bo'limiga o'ting va 'Coinflip' o'yinini tanlang.\n\n" +
                            "🟢 2. Tangada bir tomonini tanlang (head yoki tail). Bu sizning tikish strategiyangiz uchun muhim!\n\n" +
                            "🟢 3. Botda signalni so'rang va botning signallari asosida tikish qiling.\n\n" +
                            "🟢 4. Agar bir signal muvaffaqiyatsiz bo'lsa, biz oldingi yo'qotishlarni tiklashga harakat qilish uchun tikishni ikki baravar (X²) qilishni tavsiya qilamiz.\n\n" +
                            "   ⚠ Muhim: Har bir o'yinning 50/50 imkoniyati borligini unutmang, va ikki baravar qilish strategiyasi xavfli bo'lishi mumkin. Mas'uliyat bilan o'ynang va bankrollni ehtiyotkorlik bilan boshqaring.\n\n" +
                            "   Bugun sinab ko'ring va kapitalingizning bizning bot bilan qanday o'sishini ko'ring! 💹";
                } else {
                    answer = "*Bot bitsGap neyron tarmoq klasterida asoslangan va o'qitilgan🧠*\n\n" +
                            "Botni o'qitish uchun 10,000 dan ortiq o'yinlar o'tkazildi 🎰\n" +
                            "Hozirda bot foydalanuvchilari o'z kapitalining 15-25% ni har kuni muvaffaqiyatli qilmoqdalar! 💰\n\n" +
                            "Hozirda bot hali ham tekshirish va sozlash jarayonidan o'tmoqda! Botning aniqligi 87%!\n\n" +
                            "O'zingizning daromadingizni maksimal qilish uchun ushbu ko'rsatmalarga rioya qiling:\n\n" +
                            "🟢 1. Bizning bot orqali hisobni ro'yxatdan o'tkazing, xabarning pastida 'Ro'yxatdan o'tish' tugmasini bosish orqali, buning yordamida siz signallarga kirishingiz mumkin bo'lmaydi.\n\n" +
                            "🟢 2. Hisobingiz balansini to'ldiring.\n\n" +
                            "🟢 3. 1win o'yinlari bo'limiga o'ting va 'Coinflip' o'yinini tanlang.\n\n" +
                            "🟢 4. Tangada bir tomonini tanlang (head yoki tail). Bu sizning tikish strategiyangiz uchun muhim!\n\n" +
                            "🟢 5. Botda signalni so'rang va botning signallari asosida tikish qiling.\n\n" +
                            "🟢 6. Agar bir signal muvaffaqiyatsiz bo'lsa, biz oldingi yo'qotishlarni tiklashga harakat qilish uchun tikishni ikki baravar (X²) qilishni tavsiya qilamiz.\n\n" +
                            "   ⚠ Muhim: Har bir o'yinning 50/50 imkoniyati borligini unutmang, va ikki baravar qilish strategiyasi xavfli bo'lishi mumkin. Mas'uliyat bilan o'ynang va bankrollni ehtiyotkorlik bilan boshqaring.\n\n" +
                            "   Bugun sinab ko'ring va kapitalingizning bizning bot bilan qanday o'sishini ko'ring! 💹";
                }
                break;
            case "azerbaijani":
                if (user.getIsVerify()) {
                    answer = "*Bot bitsGap neyron şəbəkə klasterinə əsaslanır və öyrədilib🧠*\n\n" +
                            "Botu öyrətmək üçün 10,000-dən çox oyun oynanılıb 🎰\n" +
                            "Hal-hazırda bot istifadəçiləri gündə öz kapitalının 15-25% -ni uğurla qazanırlar! 💰\n\n" +
                            "Hal-hazırda bot hələ də yoxlanış və tənzimləmə mərhələsindən keçir! Botun dəqiqliyi 87% -dir!\n\n" +
                            "Gəlirinizi maksimum etmək üçün bu təlimatlara əməl edin:\n\n" +
                            "🟢 1. 1win oyunlar bölməsinə keçin və 'Coinflip' oyununu seçin.\n\n" +
                            "🟢 2. Pullun bir tərəfini seçin (head ya da tail). Bu, mərc strategiyanız üçün vacibdir!\n\n" +
                            "🟢 3. Botda bir siqnal sorğulayın və botun siqnalları əsasında mərc edin.\n\n" +
                            "🟢 4. Bir siqnal uğursuz olarsa, əvvəlki zərərləri bərpa etmək üçün mərcin miqdarını (X²) iki qat artırmağı tövsiyə edirik.\n\n" +
                            "   ⚠ Vacib: Hər oyunun 50/50 şansı olduğunu unutmayın və iki qat artırma strategiyası riskli ola bilər. Məsuliyyətlə oynayın və bankrollunuzu diqqətlə idarə edin.\n\n" +
                            "   Bugün sınayın və kapitalınızın bizim botla necə artdığını görün! 💹";
                } else {
                    answer = "*Bot bitsGap neyron şəbəkə klasterinə əsaslanır və öyrədilib🧠*\n\n" +
                            "Botu öyrətmək üçün 10,000-dən çox oyun oynanılıb 🎰\n" +
                            "Hal-hazırda bot istifadəçiləri gündə öz kapitalının 15-25% -ni uğurla qazanırlar! 💰\n\n" +
                            "Hal-hazırda bot hələ də yoxlanış və tənzimləmə mərhələsindən keçir! Botun dəqiqliyi 87% -dir!\n\n" +
                            "Gəlirinizi maksimum etmək üçün bu təlimatlara əməl edin:\n\n" +
                            "🟢 1. Botumuz vasitəsilə hesab qeydiyyatdan keçirin, mesajın altındakı 'Qeydiyyat' düyməsini basın, bununla siqnallara daxil ola bilməyəcəksiniz.\n\n" +
                            "🟢 2. Hesabınızın balansını artırın.\n\n" +
                            "🟢 3. 1win oyunlar bölməsinə keçin və 'Coinflip' oyununu seçin.\n\n" +
                            "🟢 4. Pullun bir tərəfini seçin (head ya da tail). Bu, mərc strategiyanız üçün vacibdir!\n\n" +
                            "🟢 5. Botda bir siqnal sorğulayın və botun siqnalları əsasında mərc edin.\n\n" +
                            "🟢 6. Bir siqnal uğursuz olarsa, əvvəlki zərərləri bərpa etmək üçün mərcin miqdarını (X²) iki qat artırmağı tövsiyə edirik.\n\n" +
                            "   ⚠ Vacib: Hər oyunun 50/50 şansı olduğunu unutmayın və iki qat artırma strategiyası riskli ola bilər. Məsuliyyətlə oynayın və bankrollunuzu diqqətlə idarə edin.\n\n" +
                            "   Bugün sınayın və kapitalınızın bizim botla necə artdığını görün! 💹";
                }
                break;
            case "turkish":
                if (user.getIsVerify()) {
                    answer = "*Bot, bitsGap sinir ağı kümesi üzerinde eğitilmiştir🧠*\n\n" +
                            "Bot, 10.000'den fazla oyunla eğitildi 🎰\n" +
                            "Şu anda bot kullanıcıları, sermayelerinin günde %15-25'ini başarıyla kazanmaktadır! 💰\n\n" +
                            "Şu anda bot hala kontrollerden ve ayarlamalardan geçiyor! Botun doğruluğu %87!\n\n" +
                            "Karınızı en üst düzeye çıkarmak için bu talimatları izleyin:\n\n" +
                            "🟢 1. 1win oyunlar bölümüne gidin ve 'Coinflip' oyununu seçin.\n\n" +
                            "🟢 2. Paranın bir tarafını seçin (yazı veya tura). Bu, bahis stratejiniz için önemlidir!\n\n" +
                            "🟢 3. Bottan bir sinyal isteyin ve botun sinyallerine dayanarak bahis yapın.\n\n" +
                            "🟢 4. Bir sinyal başarısız olursa, önceki kayıpları telafi etmek için bahsi ikiye katlamayı (X²) öneririz.\n\n" +
                            "   ⚠ Önemli: Her oyunun 50/50 şansı olduğunu unutmayın ve ikiye katlama stratejisi riskli olabilir. Sorumlu bir şekilde oynayın ve paranızı dikkatle yönetin.\n\n" +
                            "   Bugün deneyin ve sermayenizin botumuzla nasıl büyüdüğünü görün! 💹";
                } else {
                    answer = "*Bot, bitsGap sinir ağı kümesi üzerinde eğitilmiştir🧠*\n\n" +
                            "Bot, 10.000'den fazla oyunla eğitildi 🎰\n" +
                            "Şu anda bot kullanıcıları, sermayelerinin günde %15-25'ini başarıyla kazanmaktadır! 💰\n\n" +
                            "Şu anda bot hala kontrollerden ve ayarlamalardan geçiyor! Botun doğruluğu %87!\n\n" +
                            "Karınızı en üst düzeye çıkarmak için bu talimatları izleyin:\n\n" +
                            "🟢 1. Botumuz aracılığıyla bir hesap kaydettirin, mesajın altında 'Kaydol' düğmesine tıklayın, bu olmadan sinyallere erişemeyeceksiniz.\n\n" +
                            "🟢 2. Hesap bakiyenizi doldurun.\n\n" +
                            "🟢 3. 1win oyunlar bölümüne gidin ve 'Coinflip' oyununu seçin.\n\n" +
                            "🟢 4. Paranın bir tarafını seçin (yazı veya tura). Bu, bahis stratejiniz için önemlidir!\n\n" +
                            "🟢 5. Bottan bir sinyal isteyin ve botun sinyallerine dayanarak bahis yapın.\n\n" +
                            "🟢 6. Bir sinyal başarısız olursa, önceki kayıpları telafi etmek için bahsi ikiye katlamayı (X²) öneririz.\n\n" +
                            "   ⚠ Önemli: Her oyunun 50/50 şansı olduğunu unutmayın ve ikiye katlama stratejisi riskli olabilir. Sorumlu bir şekilde oynayın ve paranızı dikkatle yönetin.\n\n" +
                            "   Bugün deneyin ve sermayenizin botumuzla nasıl büyüdüğünü görün! 💹";
                }
                break;
            case "portuguese":
                if (user.getIsVerify()) {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot fazem com sucesso 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Vá para a seção de jogos 1win e selecione o jogo 'Coinflip'.\n\n" +
                            "🟢 2. Escolha um lado da moeda (cara ou coroa). Isso é crucial para sua estratégia de apostas!\n\n" +
                            "🟢 3. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 4. Se um sinal falhar, recomendamos dobrar (X²) a aposta para tentar recuperar perdas anteriores.\n\n" +
                            "   ⚠ Importante: Lembre-se de que cada jogo tem uma chance de 50/50, e a estratégia de dobrar pode ser arriscada. Jogue com responsabilidade e gerencie seu bankroll com cautela.\n\n" +
                            "   Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                } else {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot fazem com sucesso 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Registre uma conta através do nosso bot clicando no botão 'Registrar' sob a mensagem, sem isso você não poderá acessar os sinais.\n\n" +
                            "🟢 2. Recarregue o saldo da sua conta.\n\n" +
                            "🟢 3. Vá para a seção de jogos 1win e selecione o jogo 'Coinflip'.\n\n" +
                            "🟢 4. Escolha um lado da moeda (cara ou coroa). Isso é crucial para sua estratégia de apostas!\n\n" +
                            "🟢 5. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 6. Se um sinal falhar, recomendamos dobrar (X²) a aposta para tentar recuperar perdas anteriores.\n\n" +
                            "   ⚠ Importante: Lembre-se de que cada jogo tem uma chance de 50/50, e a estratégia de dobrar pode ser arriscada. Jogue com responsabilidade e gerencie seu bankroll com cautela.\n\n" +
                            "   Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                }
                break;
            case "arabic":
                if (user.getIsVerify()) {
                    answer = "*يعتمد الروبوت على مجموعة شبكة عصبية bitsGap🧠*\n\n" +
                            "تم تدريب الروبوت بأكثر من 10,000 لعبة 🎰\n" +
                            "حاليًا، يحقق مستخدمو الروبوت 15-25٪ من رأس مالهم يوميًا! 💰\n\n" +
                            "في الوقت الحالي، لا يزال الروبوت يخضع لفحوصات وتعديلات! دقة الروبوت 87%!\n\n" +
                            "لزيادة أرباحك إلى أقصى حد، اتبع هذه التعليمات:\n\n" +
                            "🟢 1. انتقل إلى قسم ألعاب 1win واختر لعبة 'Coinflip'.\n\n" +
                            "🟢 2. اختر جانب العملة (وجه أو ظهر). هذا مهم لاستراتيجية الرهان الخاصة بك!\n\n" +
                            "🟢 3. اطلب إشارة في الروبوت وضع الرهانات بناءً على إشارات الروبوت.\n\n" +
                            "🟢 4. إذا فشلت الإشارة، نوصي بمضاعفة الرهان (X²) لمحاولة استعادة الخسائر السابقة.\n\n" +
                            "   ⚠ مهم: تذكر أن كل لعبة لها فرصة 50/50، واستراتيجية المضاعفة قد تكون محفوفة بالمخاطر. العب بمسؤولية وقم بإدارة رأس المال الخاص بك بحذر.\n\n" +
                            "   جربها اليوم وشاهد كيف ينمو رأس المال الخاص بك مع روبوتنا! 💹";
                } else {
                    answer = "*يعتمد الروبوت على مجموعة شبكة عصبية bitsGap🧠*\n\n" +
                            "تم تدريب الروبوت بأكثر من 10,000 لعبة 🎰\n" +
                            "حاليًا، يحقق مستخدمو الروبوت 15-25٪ من رأس مالهم يوميًا! 💰\n\n" +
                            "في الوقت الحالي، لا يزال الروبوت يخضع لفحوصات وتعديلات! دقة الروبوت 87%!\n\n" +
                            "لزيادة أرباحك إلى أقصى حد، اتبع هذه التعليمات:\n\n" +
                            "🟢 1. قم بتسجيل حساب من خلال الروبوت الخاص بنا بالنقر فوق الزر 'تسجيل' أسفل الرسالة، بدون ذلك لن تتمكن من الوصول إلى الإشارات.\n\n" +
                            "🟢 2. قم بشحن رصيد حسابك.\n\n" +
                            "🟢 3. انتقل إلى قسم ألعاب 1win واختر لعبة 'Coinflip'.\n\n" +
                            "🟢 4. اختر جانب العملة (وجه أو ظهر). هذا مهم لاستراتيجية الرهان الخاصة بك!\n\n" +
                            "🟢 5. اطلب إشارة في الروبوت وضع الرهانات بناءً على إشارات الروبوت.\n\n" +
                            "🟢 6. إذا فشلت الإشارة، نوصي بمضاعفة الرهان (X²) لمحاولة استعادة الخسائر السابقة.\n\n" +
                            "   ⚠ مهم: تذكر أن كل لعبة لها فرصة 50/50، واستراتيجية المضاعفة قد تكون محفوفة بالمخاطر. العب بمسؤولية وقم بإدارة رأس المال الخاص بك بحذر.\n\n" +
                            "   جربها اليوم وشاهد كيف ينمو رأس المال الخاص بك مع روبوتنا! 💹";
                }
                break;
            default:
                if (user.getIsVerify()) {
                    answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                            "The bot has been trained with more than 10,000 games 🎰\n" +
                            "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                            "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                            "To maximize your profits, follow these instructions:\n\n" +
                            "🟢 1. Go to the 1win games section and select the game 'Coinflip'.\n\n" +
                            "🟢 2. Choose a side of the coin (heads or tails). This is crucial for your betting strategy!\n\n" +
                            "🟢 3. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                            "🟢 4. If a signal fails, we recommend doubling (X²) the bet to attempt to recover previous losses.\n\n" +
                            "   ⚠ Important: Remember that each game has a 50/50 chance, and the doubling strategy can be risky. Play responsibly and manage your bankroll cautiously.\n\n" +
                            "   Try it today and see how your capital grows with our bot! 💹";
                } else {
                    answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                            "The bot has been trained with more than 10,000 games 🎰\n" +
                            "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                            "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                            "To maximize your profits, follow these instructions:\n\n" +
                            "🟢 1. Register an account through our bot by clicking the 'Register' button under the message, without this you will not be able to access the signals.\n\n" +
                            "🟢 2. Top up your account balance.\n\n" +
                            "🟢 3. Go to the 1win games section and select the game 'Coinflip'.\n\n" +
                            "🟢 4. Choose a side of the coin (heads or tails). This is crucial for your betting strategy!\n\n" +
                            "🟢 5. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                            "🟢 6. If a signal fails, we recommend doubling (X²) the bet to attempt to recover previous losses.\n\n" +
                            "   ⚠ Important: Remember that each game has a 50/50 chance, and the doubling strategy can be risky. Play responsibly and manage your bankroll cautiously.\n\n" +
                            "   Try it today and see how your capital grows with our bot! 💹";
                }
                break;
        }




        InlineKeyboardMarkup inlineKeyboardMarkup;
        if(user.getIsVerify()){
            inlineKeyboardMarkup = CoinFlipKeyboard.mainForInstructionIfUserIsRegistered(oneWinUrl,user.getLanguage());
        }
        else {
            inlineKeyboardMarkup = CoinFlipKeyboard.mainForInstructionIfUserIsNotRegistered(oneWinUrl,user.getLanguage());
        }
        botActions.sendMessageWithInlineKeyboardAndParseMARKDOWN(chatId, answer, inlineKeyboardMarkup);
        botActions.handleCallbackQuery(callbackQuery);
    }
}