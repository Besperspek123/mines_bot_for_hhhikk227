package com.example.tgbot.service.main.luckyjet;


import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.LuckyJetKeyboard;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class InfoAboutLuckyJetDepositMenu implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private final UserService userService;

    public InfoAboutLuckyJetDepositMenu(BotActions botActions, UrlService urlService, UserService userService, PromoService promoService) {
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
                    answer = "🌐 सिग्नल प्राप्त करने के लिए, आपको अपनी पहली जमा करनी होगी।\n\n"
                            + "✦ आपके बॉट में LVL (स्तर), स्थिति, और सिग्नल की सफलता की संभावना जमा पर निर्भर करती है। जितनी बड़ी जमा, उतना ही उच्च आपका बॉट में LVL होगा, और जितना ऊँचा आपका स्तर होगा, उतने ही अधिक उच्च सफलता संभावना वाले सिग्नल आपको प्राप्त होंगे।\n\n"
                            + "✦ अपनी खाता सक्रिय करें पहली जमा करके। ये धनराशि आपके खाते में जमा हो जाएगी, जिसके बाद आप उन्हें खेलने के लिए और सबसे महत्वपूर्ण, जीतने के लिए उपयोग कर सकते हैं।\n\n"
                            + "● पहली जमा करने के बाद, \"🔎 जमा जांचें\" बटन दबाएं।";
                    break;
                case "brazilian":
                    answer = "🌐 Para acessar os sinais, você precisa fazer seu primeiro depósito.\n\n"
                            + "✦ Seu LVL (nível) no bot, status e a probabilidade de sucesso do sinal dependem do depósito. Quanto maior o depósito, maior será seu LVL no bot, e quanto maior seu nível no bot, mais sinais com alta probabilidade de sucesso você receberá.\n\n"
                            + "✦ Ative sua conta fazendo o primeiro depósito. Esses fundos serão creditados na SUA CONTA, após o que você poderá usá-los para jogar e, o mais importante, ganhar.\n\n"
                            + "● Após fazer o primeiro depósito, pressione o botão \"🔎 Verificar depósito\".";
                    break;
                case "spanish":
                    answer = "🌐 Para acceder a las señales, necesitas hacer tu primer depósito.\n\n"
                            + "✦ Tu LVL (nivel) en el bot, el estado y la probabilidad de éxito de la señal dependen del depósito. Cuanto mayor sea el depósito, mayor será tu LVL en el bot, y cuanto mayor sea tu nivel en el bot, más señales con alta probabilidad de éxito recibirás.\n\n"
                            + "✦ Activa tu cuenta haciendo el primer depósito. Estos fondos serán acreditados en TU CUENTA, después de lo cual podrás usarlos para jugar y, lo más importante, ganar.\n\n"
                            + "● Después de hacer el primer depósito, presiona el botón \"🔎 Verificar depósito\".";
                    break;
                case "uzbek":
                    answer = "🌐 Belgilarni olish uchun siz birinchi omonatingizni qilishingiz kerak.\n\n"
                            + "✦ Botdagi LVL (daraja), holat va signal muvaffaqiyatining ehtimoli omonatga bog'liq. Omonat qancha katta bo'lsa, botdagi LVLingiz shuncha yuqori bo'ladi va botdagi darajangiz qanchalik yuqori bo'lsa, shuncha yuqori muvaffaqiyat ehtimoliga ega signallarni olasiz.\n\n"
                            + "✦ Birinchi omonatni qilish orqali hisobingizni faollashtiring. Bu mablag'lar HISOBINGIZGA o'tkaziladi, shundan so'ng siz ularni o'ynash va, eng muhimi, yutish uchun ishlatishingiz mumkin.\n\n"
                            + "● Birinchi omonatni qilganingizdan so'ng, \"🔎 Omonatni tekshirish\" tugmasini bosing.";
                    break;
                case "azerbaijani":
                    answer = "🌐 Siqnallara giriş etmək üçün ilk depozitinizi etməlisiniz.\n\n"
                            + "✦ Botdakı LVL (səviyyə), status və siqnalın uğur ehtimalı depozitdən asılıdır. Depozit nə qədər böyük olarsa, botdakı LVLiniz o qədər yüksək olacaq və botdakı səviyyəniz nə qədər yüksək olarsa, daha yüksək uğur ehtimalı olan siqnalları alacaqsınız.\n\n"
                            + "✦ Hesabınızı ilk depozit edərək aktivləşdirin. Bu vəsaitlər SİZİN HESABINIZA köçürüləcək, sonra siz onları oynamaq və ən əsası, qazanmaq üçün istifadə edə bilərsiniz.\n\n"
                            + "● İlk depozitinizi etdikdən sonra, \"🔎 Depoziti yoxlayın\" düyməsini basın.";
                    break;
                case "turkish":
                    answer = "🌐 Sinyallere erişmek için, ilk depozitonuzu yapmanız gerekiyor.\n\n"
                            + "✦ Botta LVL (seviye), durum ve sinyal başarısı olasılığı depozit miktarına bağlıdır. Depozit ne kadar büyükse, botta LVL'niz o kadar yüksek olur ve botta seviyeniz ne kadar yüksekse, daha yüksek başarı olasılığına sahip sinyaller alırsınız.\n\n"
                            + "✦ İlk depozit yaparak hesabınızı etkinleştirin. Bu fonlar HESABINIZA yatırılacak, ardından onları oynamak ve en önemlisi kazanmak için kullanabilirsiniz.\n\n"
                            + "● İlk depoziti yaptıktan sonra, \"🔎 Depoziti Kontrol Et\" düğmesine basın.";
                    break;
                case "portuguese":
                    answer = "🌐 Para acessar os sinais, você precisa fazer seu primeiro depósito.\n\n"
                            + "✦ Seu LVL (nível) no bot, status e a probabilidade de sucesso do sinal dependem do depósito. Quanto maior o depósito, maior será seu LVL no bot, e quanto maior seu nível no bot, mais sinais com alta probabilidade de sucesso você receberá.\n\n"
                            + "✦ Ative sua conta fazendo o primeiro depósito. Esses fundos serão creditados na SUA CONTA, após o que você poderá usá-los para jogar e, o mais importante, ganhar.\n\n"
                            + "● Após fazer o primeiro depósito, pressione o botão \"🔎 Verificar depósito\".";
                    break;
                case "arabic":
                    answer = "🌐 للوصول إلى الإشارات، تحتاج إلى إجراء أول إيداع.\n\n"
                            + "✦ يعتمد مستوى LVL الخاص بك في الروبوت، والحالة، واحتمالية نجاح الإشارة على الإيداع. كلما زاد الإيداع، زاد مستوى LVL الخاص بك في الروبوت، وكلما زاد مستوى الخاص بك في الروبوت، زادت الإشارات ذات الاحتمالية العالية للنجاح التي ستتلقاها.\n\n"
                            + "✦ قم بتنشيط حسابك عن طريق إجراء أول إيداع. سيتم تحويل هذه الأموال إلى حسابك، بعد ذلك يمكنك استخدامها للعب والفوز، وهو الأهم.\n\n"
                            + "● بعد إجراء أول إيداع، اضغط على زر \"🔎 التحقق من الإيداع\".";
                    break;
                default:
                    answer = "🌐 To access the signals, you need to make your first deposit.\n\n"
                            + "✦ Your LVL (level) in the bot, status, and the probability of signal success depend on the deposit. The larger the deposit, the higher your LVL in the bot, and the higher your level in the bot, the more signals with a high probability of success you will receive.\n\n"
                            + "✦ Activate your account by making the first deposit. These funds will be credited to YOUR ACCOUNT, after which you can use them to play and, most importantly, win.\n\n"
                            + "● After making the first deposit, press the \"🔎 Check deposit button\".";
                    break;
            }
            InlineKeyboardMarkup inlineKeyboardMarkup = LuckyJetKeyboard.menuWithCheckTheDeposit(currentUser.getLanguage());
            botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/images"+currentUser.getLanguage()+"/luckyjet/deposit.jpg", inlineKeyboardMarkup);

            if (userService.userIsRussian(currentUser)){
                botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/imagesrussian/luckyjet/deposit.jpg", inlineKeyboardMarkup);
            }
            else {
                botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/imagesenglish/luckyjet/deposit.jpg", inlineKeyboardMarkup);

            }

        }
        botActions.handleCallbackQuery(callbackQuery);
    }
}