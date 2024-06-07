package com.example.tgbot.service.main.aviator;


import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.AviatorKeyboard;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class InfoAboutAviatorDepositMenu implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private final UserService userService;

    public InfoAboutAviatorDepositMenu(BotActions botActions, UrlService urlService, UserService userService, PromoService promoService) {
        this.botActions = botActions;
        this.urlService = urlService;
        this.userService = userService;
        this.promoService = promoService;
    }
    @Override
    public void execute(long chatId, CallbackQuery callbackQuery) {
        User currentUser = userService.getInfoAboutUserByChatID(chatId);
        if(currentUser.getIsDeposit()){
            if(userService.userIsRussian(currentUser)){
                botActions.sendMessageWithCallbackQuery(chatId,"Вы уже сделали депозит",callbackQuery);
            }
            else {
                botActions.sendMessageWithCallbackQuery(chatId,"You have already made a deposit.",callbackQuery);
            }

        }
        else {
            String answer = "";
            switch (currentUser.getLanguage()) {
                case "russian":
                    answer = "🌐 Для доступа к сигналам вам нужно сделать первый депозит.\n\n"
                            + "✦ От депозита зависит ваш LVL (уровень) в боте, статус и вероятность успеха сигнала. Чем больше депозит, тем выше ваш LVL в боте, а чем выше ваш уровень в боте, тем больше сигналов с высокой вероятностью успеха вы будете получать.\n\n"
                            + "✦ Активируйте свой счет, сделав первый депозит. Эти средства будут зачислены на ВАШ СЧЕТ, после чего вы сможете использовать их для игры и, что самое главное, выигрыша.\n\n"
                            + "● После внесения первого депозита нажмите кнопку \"🔎 Проверить депозит\".";
                    break;
                case "english":
                    answer = "🌐 To access the signals, you need to make your first deposit.\n\n"
                            + "✦ Your LVL (level) in the bot, status, and the probability of signal success depend on the deposit. The larger the deposit, the higher your LVL in the bot, and the higher your level in the bot, the more signals with a high probability of success you will receive.\n\n"
                            + "✦ Activate your account by making the first deposit. These funds will be credited to YOUR ACCOUNT, after which you can use them to play and, most importantly, win.\n\n"
                            + "● After making the first deposit, press the \"🔎 Check deposit button\".";
                    break;
                case "hindi":
                    answer = "🌐 संकेत प्राप्त करने के लिए, आपको अपनी पहली जमा राशि करनी होगी।\n\n"
                            + "✦ आपके जमा पर बॉट में आपका LVL (स्तर), स्थिति और संकेत सफलता की संभावना निर्भर करती है। जितनी बड़ी जमा, उतना ही उच्च आपका बॉट में LVL होगा, और जितना ही उच्च आपका स्तर बॉट में होगा, उतने ही अधिक संकेत आपको उच्च सफलता की संभावना के साथ मिलेंगे।\n\n"
                            + "✦ अपना खाता सक्रिय करें पहली जमा राशि करके। ये धनराशि आपके खाते में जमा हो जाएगी, जिसके बाद आप इसे खेलने और, सबसे महत्वपूर्ण, जीतने के लिए उपयोग कर सकते हैं।\n\n"
                            + "● पहली जमा राशि करने के बाद, \"🔎 जमा राशि की जांच करें\" बटन दबाएं।";
                    break;
                case "brazilian":
                    answer = "🌐 Para acessar os sinais, você precisa fazer seu primeiro depósito.\n\n"
                            + "✦ Seu LVL (nível) no bot, status e a probabilidade de sucesso do sinal dependem do depósito. Quanto maior o depósito, maior seu LVL no bot, e quanto maior seu nível no bot, mais sinais com alta probabilidade de sucesso você receberá.\n\n"
                            + "✦ Ative sua conta fazendo o primeiro depósito. Esses fundos serão creditados na SUA CONTA, após o que você poderá usá-los para jogar e, o mais importante, ganhar.\n\n"
                            + "● Após fazer o primeiro depósito, pressione o botão \"🔎 Verificar depósito\".";
                    break;
                case "spanish":
                    answer = "🌐 Para acceder a las señales, necesita hacer su primer depósito.\n\n"
                            + "✦ Su LVL (nivel) en el bot, el estado y la probabilidad de éxito de la señal dependen del depósito. Cuanto mayor sea el depósito, mayor será su LVL en el bot, y cuanto mayor sea su nivel en el bot, más señales con alta probabilidad de éxito recibirá.\n\n"
                            + "✦ Active su cuenta haciendo el primer depósito. Estos fondos se acreditarán en SU CUENTA, después de lo cual podrá usarlos para jugar y, lo más importante, ganar.\n\n"
                            + "● Después de hacer el primer depósito, presione el botón \"🔎 Verificar depósito\".";
                    break;
                case "uzbek":
                    answer = "🌐 Signal olish uchun, avval hisobingizni to'ldirishingiz kerak.\n\n"
                            + "✦ Hisobingizdagi depozit miqdoriga qarab botdagi LVL (darajangiz), holatingiz va signal muvaffaqiyat ehtimoli belgilanadi. Katta depozit - yuqori daraja va ko'proq muvaffaqiyatli signal.\n\n"
                            + "✦ Hisobingizni to'ldirib, faollashtiring. Bu mablag'lar HISOBINGIZGA o'tkaziladi, keyin siz ularni o'yin uchun va g'alaba uchun ishlatishingiz mumkin.\n\n"
                            + "● Birinchi depozitni amalga oshirgandan so'ng, \"🔎 Depozitni tekshirish\" tugmasini bosing.";
                    break;
                case "azerbaijani":
                    answer = "🌐 Siqnallara çıxış əldə etmək üçün ilk depozitinizi etməlisiniz.\n\n"
                            + "✦ Botdakı LVL (səviyyəniz), statusunuz və siqnalın uğur ehtimalı depozitdən asılıdır. Daha böyük depozit, daha yüksək səviyyə və daha çox uğurlu siqnal.\n\n"
                            + "✦ İlk depozit edərək hesabınızı aktivləşdirin. Bu vəsaitlər HESABINIZA köçürüləcək və siz onları oynamaq və qazanmaq üçün istifadə edə bilərsiniz.\n\n"
                            + "● İlk depozit etdikdən sonra \"🔎 Depoziti yoxlayın\" düyməsini basın.";
                    break;
                case "turkish":
                    answer = "🌐 Sinyallere erişmek için ilk depozitonuzu yapmanız gerekiyor.\n\n"
                            + "✦ Botta LVL (seviyeniz), durumunuz ve sinyal başarı olasılığı depozit miktarına bağlıdır. Daha büyük depozito, daha yüksek seviye ve daha fazla başarılı sinyal.\n\n"
                            + "✦ İlk depoziti yaparak hesabınızı etkinleştirin. Bu fonlar HESABINIZA yatırılacak ve ardından bunları oynamak ve kazanmak için kullanabilirsiniz.\n\n"
                            + "● İlk depozit yaptıktan sonra \"🔎 Depoziti Kontrol Et\" düğmesine basın.";
                    break;
                case "portuguese":
                    answer = "🌐 Para acessar os sinais, você precisa fazer seu primeiro depósito.\n\n"
                            + "✦ Seu LVL (nível) no bot, status e a probabilidade de sucesso do sinal dependem do depósito. Quanto maior o depósito, maior seu LVL no bot, e quanto maior seu nível no bot, mais sinais com alta probabilidade de sucesso você receberá.\n\n"
                            + "✦ Ative sua conta fazendo o primeiro depósito. Esses fundos serão creditados na SUA CONTA, após o que você poderá usá-los para jogar e, o mais importante, ganhar.\n\n"
                            + "● Após fazer o primeiro depósito, pressione o botão \"🔎 Verificar depósito\".";
                    break;
                case "arabic":
                    answer = "🌐 للوصول إلى الإشارات، تحتاج إلى إجراء الإيداع الأول.\n\n"
                            + "✦ يعتمد مستوى LVL الخاص بك في البوت، والحالة، واحتمالية نجاح الإشارة على الإيداع. كلما كان الإيداع أكبر، زاد مستوى LVL الخاص بك في البوت، وكلما زاد مستواك في البوت، زادت الإشارات التي تتلقى احتمالية نجاح عالية.\n\n"
                            + "✦ قم بتفعيل حسابك عن طريق إجراء الإيداع الأول. سيتم إضافة هذه الأموال إلى حسابك، وبعد ذلك يمكنك استخدامها للعب والفوز.\n\n"
                            + "● بعد إجراء الإيداع الأول، اضغط على زر \"🔎 تحقق من الإيداع\".";
                    break;
                default:
                    answer = "🌐 To access the signals, you need to make your first deposit.\n\n"
                            + "✦ Your LVL (level) in the bot, status, and the probability of signal success depend on the deposit. The larger the deposit, the higher your LVL in the bot, and the higher your level in the bot, the more signals with a high probability of success you will receive.\n\n"
                            + "✦ Activate your account by making the first deposit. These funds will be credited to YOUR ACCOUNT, after which you can use them to play and, most importantly, win.\n\n"
                            + "● After making the first deposit, press the \"🔎 Check deposit button\".";
                    break;
            }

            InlineKeyboardMarkup inlineKeyboardMarkup = AviatorKeyboard.menuWithCheckTheDeposit(currentUser.getLanguage());
            if (userService.userIsRussian(currentUser)){
                botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/imagesrussian/aviator/deposit.jpg", inlineKeyboardMarkup);
            }
            else {
                botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/imagesenglish/aviator/deposit.jpg", inlineKeyboardMarkup);

            }         }
        botActions.handleCallbackQuery(callbackQuery);
    }
}