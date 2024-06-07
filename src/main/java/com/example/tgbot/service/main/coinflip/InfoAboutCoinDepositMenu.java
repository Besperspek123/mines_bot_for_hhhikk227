package com.example.tgbot.service.main.coinflip;


import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.CoinFlipKeyboard;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class InfoAboutCoinDepositMenu implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private final UserService userService;

    public InfoAboutCoinDepositMenu(BotActions botActions, UrlService urlService, UserService userService, PromoService promoService) {
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
                    answer = "🌐 संकेत प्राप्त करने के लिए, आपको पहली जमा करनी होगी।\n\n"
                            + "✦ आपकी जमा राशि पर निर्भर करेगा आपका LVL (स्तर) बॉट में, स्थिति और संकेत की सफलता की संभावना। जितनी बड़ी जमा राशि होगी, उतना ही उच्च आपका LVL बॉट में होगा, और जितना उच्च आपका स्तर होगा, उतने ही अधिक संकेत आपको मिलेंगे जिनकी सफलता की संभावना अधिक होगी।\n\n"
                            + "✦ अपना खाता सक्रिय करें, पहली जमा करके। ये धनराशि आपके खाते में जमा हो जाएगी, जिसके बाद आप इन्हें खेलने के लिए और सबसे महत्वपूर्ण, जीतने के लिए उपयोग कर सकेंगे।\n\n"
                            + "● पहली जमा करने के बाद, \"🔎 जमा की जांच करें\" बटन दबाएं।";
                    break;
                case "brazilian":
                    answer = "🌐 Para acessar os sinais, você precisa fazer seu primeiro depósito.\n\n"
                            + "✦ Seu LVL (nível) no bot, status e a probabilidade de sucesso do sinal dependem do depósito. Quanto maior o depósito, maior será seu LVL no bot e, quanto maior seu nível no bot, mais sinais com alta probabilidade de sucesso você receberá.\n\n"
                            + "✦ Ative sua conta fazendo o primeiro depósito. Esses fundos serão creditados na SUA CONTA, após o qual você poderá usá-los para jogar e, mais importante, ganhar.\n\n"
                            + "● Após fazer o primeiro depósito, pressione o botão \"🔎 Verificar depósito\".";
                    break;
                case "spanish":
                    answer = "🌐 Para acceder a las señales, necesita hacer su primer depósito.\n\n"
                            + "✦ Su LVL (nivel) en el bot, estado y la probabilidad de éxito del señal dependen del depósito. Cuanto mayor sea el depósito, mayor será su LVL en el bot, y cuanto mayor sea su nivel en el bot, más señales con alta probabilidad de éxito recibirá.\n\n"
                            + "✦ Active su cuenta haciendo el primer depósito. Estos fondos serán acreditados en SU CUENTA, después de lo cual podrá usarlos para jugar y, lo más importante, ganar.\n\n"
                            + "● Después de hacer el primer depósito, presione el botón \"🔎 Verificar depósito\".";
                    break;
                case "uzbek":
                    answer = "🌐 Signallarni olish uchun, birinchi depozitni amalga oshirishingiz kerak.\n\n"
                            + "✦ Depozitdan sizning LVL (daraja), holat va signal muvaffaqiyat ehtimoli bog'liq. Depozit qancha katta bo'lsa, sizning LVL botda shuncha yuqori bo'ladi va darajangiz yuqori bo'lsa, muvaffaqiyat ehtimoli yuqori bo'lgan signallar ko'proq bo'ladi.\n\n"
                            + "✦ Hisobingizni birinchi depozitni amalga oshirib faollashtiring. Bu mablag'lar sizning HISOBingizga o'tkaziladi, shundan so'ng siz ularni o'yin uchun va eng muhimi, yutish uchun ishlatishingiz mumkin bo'ladi.\n\n"
                            + "● Birinchi depozitni amalga oshirgandan so'ng, \"🔎 Depozitni tekshirish\" tugmasini bosing.";
                    break;
                case "azerbaijani":
                    answer = "🌐 Siqnallar əldə etmək üçün ilk depozitinizi etməlisiniz.\n\n"
                            + "✦ Depozitdən sizin LVL (səviyyə) botda, status və siqnalın uğur ehtimalı asılıdır. Depozit nə qədər böyük olsa, botda LVL-iniz bir o qədər yüksək olar və səviyyəniz yüksək olduqca, uğur ehtimalı yüksək olan siqnallar daha çox olur.\n\n"
                            + "✦ Hesabınızı ilk depozitinizi edərək aktivləşdirin. Bu vəsaitlər SİZİN HESABINIZA köçürüləcək, bundan sonra onları oynamaq üçün və ən əsası, udmaq üçün istifadə edə bilərsiniz.\n\n"
                            + "● İlk depoziti etdikdən sonra, \"🔎 Depoziti yoxlayın\" düyməsini basın.";
                    break;
                case "turkish":
                    answer = "🌐 Sinyallere erişmek için ilk para yatırmanızı yapmanız gerekir.\n\n"
                            + "✦ Bot'taki LVL (seviye) niz, durumunuz ve sinyalin başarı olasılığı yatırılan paraya bağlıdır. Yatırılan para ne kadar büyük olursa, bot'taki LVL'niz o kadar yüksek olur ve seviyeniz yüksek olduğunda, başarı olasılığı yüksek olan sinyaller alırsınız.\n\n"
                            + "✦ Hesabınızı ilk para yatırarak etkinleştirin. Bu fonlar HESABINIZA yatırılacak, ardından bunları oynamak ve en önemlisi kazanmak için kullanabilirsiniz.\n\n"
                            + "● İlk parayı yatırdıktan sonra, \"🔎 Depoziti Kontrol Et\" düğmesine basın.";
                    break;
                case "portuguese":
                    answer = "🌐 Para acessar os sinais, você precisa fazer seu primeiro depósito.\n\n"
                            + "✦ Seu LVL (nível) no bot, status e a probabilidade de sucesso do sinal dependem do depósito. Quanto maior o depósito, maior será seu LVL no bot e, quanto maior seu nível no bot, mais sinais com alta probabilidade de sucesso você receberá.\n\n"
                            + "✦ Ative sua conta fazendo o primeiro depósito. Esses fundos serão creditados na SUA CONTA, após o qual você poderá usá-los para jogar e, mais importante, ganhar.\n\n"
                            + "● Após fazer o primeiro depósito, pressione o botão \"🔎 Verificar depósito\".";
                    break;
                case "arabic":
                    answer = "🌐 للوصول إلى الإشارات، تحتاج إلى إجراء أول إيداع.\n\n"
                            + "✦ يعتمد مستوى LVL (المستوى) الخاص بك في البوت، الحالة، واحتمالية نجاح الإشارة على الإيداع. كلما كان الإيداع أكبر، كلما ارتفع مستوى LVL الخاص بك في البوت، وكلما ارتفع مستواك في البوت، كلما زادت الإشارات التي تتلقى مع احتمال نجاح عالي.\n\n"
                            + "✦ قم بتنشيط حسابك عن طريق إجراء الإيداع الأول. سيتم إضافة هذه الأموال إلى حسابك، بعد ذلك يمكنك استخدامها للعب والأهم من ذلك، للفوز.\n\n"
                            + "● بعد إجراء الإيداع الأول، اضغط على الزر \"🔎 التحقق من الإيداع\".";
                    break;
                default:
                    answer = "🌐 To access the signals, you need to make your first deposit.\n\n"
                            + "✦ Your LVL (level) in the bot, status, and the probability of signal success depend on the deposit. The larger the deposit, the higher your LVL in the bot, and the higher your level in the bot, the more signals with a high probability of success you will receive.\n\n"
                            + "✦ Activate your account by making the first deposit. These funds will be credited to YOUR ACCOUNT, after which you can use them to play and, most importantly, win.\n\n"
                            + "● After making the first deposit, press the \"🔎 Check deposit button\".";
                    break;
            }

            InlineKeyboardMarkup inlineKeyboardMarkup = CoinFlipKeyboard.menuWithCheckTheDeposit(currentUser.getLanguage());
            if (userService.userIsRussian(currentUser)){
                botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/imagesrussian/coinflip/deposit.jpg", inlineKeyboardMarkup);
            }
            else {
                botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/imagesenglish/coinflip/deposit.jpg", inlineKeyboardMarkup);

            }
        }
        botActions.handleCallbackQuery(callbackQuery);
    }
}