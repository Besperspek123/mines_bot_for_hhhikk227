package com.example.tgbot.service.main.mines;


import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.MinesKeyboard;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Slf4j
public class InfoAboutMinesDepositMenu implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private final UserService userService;

    public InfoAboutMinesDepositMenu(BotActions botActions, UrlService urlService, UserService userService, PromoService promoService) {
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
            if(currentUser.getLanguage().equals("russian")){
                answer = "🌐 Для доступа к сигналам вам нужно сделать первый депозит.\n\n"
                        + "✦ От депозита зависит ваш LVL (уровень) в боте, статус и вероятность успеха сигнала. Чем больше депозит, тем выше ваш LVL в боте, а чем выше ваш уровень в боте, тем больше сигналов с высокой вероятностью успеха вы будете получать.\n\n"
                        + "✦ Активируйте свой счет, сделав первый депозит. Эти средства будут зачислены на ВАШ СЧЕТ, после чего вы сможете использовать их для игры и, что самое главное, выигрыша.\n\n"
                        + "● После внесения первого депозита нажмите кнопку \"🔎 Проверить депозит\".";
            }
            if(currentUser.getLanguage().equals("english")){
                answer = "🌐 To access the signals, you need to make your first deposit.\n\n"
                        + "✦ Your LVL (level) in the bot, status, and the probability of signal success depend on the deposit. The larger the deposit, the higher your LVL in the bot, and the higher your level in the bot, the more signals with a high probability of success you will receive.\n\n"
                        + "✦ Activate your account by making the first deposit. These funds will be credited to YOUR ACCOUNT, after which you can use them to play and, most importantly, win.\n\n"
                        + "● After making the first deposit, press the \"🔎 Check deposit button\".";
            }
            if(currentUser.getLanguage().equals("hindi")){
                answer = "🌐 संकेतों तक पहुंचने के लिए, आपको अपनी पहली जमा करनी होगी।\n\n"
                        + "✦ आपकी जमा राशि पर आपके बॉट का LVL (स्तर), स्थिति और संकेत की सफलता की संभावना निर्भर करती है। जितनी बड़ी जमा, उतना ही ऊँचा आपका बॉट में LVL होगा, और जितना ऊँचा आपका स्तर, उतने अधिक संकेत उच्च सफलता की संभावना के साथ प्राप्त होंगे।\n\n"
                        + "✦ खाता सक्रिय करने के लिए पहली जमा करें। ये धनराशि आपके खाते में जमा हो जाएगी, जिसके बाद आप इसे खेलने और सबसे महत्वपूर्ण, जीतने के लिए उपयोग कर सकते हैं।\n\n"
                        + "● पहली जमा करने के बाद, \"🔎 जमा की जाँच करें\" बटन दबाएं।";
            }
            if(currentUser.getLanguage().equals("brazilian")){
                answer = "🌐 Para acessar os sinais, você precisa fazer seu primeiro depósito.\n\n"
                        + "✦ Seu LVL (nível) no bot, status e a probabilidade de sucesso do sinal dependem do depósito. Quanto maior o depósito, maior será seu LVL no bot, e quanto maior seu nível no bot, mais sinais com alta probabilidade de sucesso você receberá.\n\n"
                        + "✦ Ative sua conta fazendo o primeiro depósito. Esses fundos serão creditados em SUA CONTA, após o que você poderá usá-los para jogar e, o mais importante, ganhar.\n\n"
                        + "● Após fazer o primeiro depósito, pressione o botão \"🔎 Verificar depósito\".";
            }
            if(currentUser.getLanguage().equals("spanish")){
                answer = "🌐 Para acceder a las señales, necesitas hacer tu primer depósito.\n\n"
                        + "✦ Tu LVL (nivel) en el bot, el estado y la probabilidad de éxito de la señal dependen del depósito. Cuanto mayor sea el depósito, mayor será tu LVL en el bot, y cuanto mayor sea tu nivel en el bot, más señales con alta probabilidad de éxito recibirás.\n\n"
                        + "✦ Activa tu cuenta haciendo el primer depósito. Estos fondos serán acreditados en TU CUENTA, después de lo cual podrás usarlos para jugar y, lo más importante, ganar.\n\n"
                        + "● Después de hacer el primer depósito, presiona el botón \"🔎 Verificar depósito\".";
            }
            if(currentUser.getLanguage().equals("uzbek")){
                answer = "🌐 Belgilarga kirish uchun birinchi depozitni amalga oshirishingiz kerak.\n\n"
                        + "✦ Depozit miqdori botdagi LVL (daraja), status va signal muvaffaqiyati ehtimoliga bog'liq. Katta depozit qanchalik katta bo'lsa, botdagi LVLingiz shunchalik yuqori bo'ladi va botdagi darajangiz qanchalik yuqori bo'lsa, muvaffaqiyat ehtimoli yuqori bo'lgan ko'proq signallarni qabul qilasiz.\n\n"
                        + "✦ Hisobingizni faollashtirish uchun birinchi depozitni amalga oshiring. Ushbu mablag'lar HISOBINGIZGA kiritiladi, shundan so'ng ularni o'yin uchun va eng muhimi, g'alaba qozonish uchun ishlatishingiz mumkin.\n\n"
                        + "● Birinchi depozitni amalga oshirganingizdan so'ng, \"🔎 Depozitni tekshirish\" tugmasini bosing.";
            }
            if(currentUser.getLanguage().equals("azerbaijani")){
                answer = "🌐 Siqnallara giriş üçün ilk depozitinizi etməlisiniz.\n\n"
                        + "✦ Depozitiniz botdakı LVL (səviyyə), status və siqnalın uğur ehtimalına təsir edir. Depozit nə qədər böyükdürsə, botdakı LVL-niz bir o qədər yüksəkdir və botdakı səviyyəniz nə qədər yüksəkdirsə, daha yüksək uğur ehtimalı olan siqnalları qəbul edirsiniz.\n\n"
                        + "✦ Hesabınızı aktivləşdirmək üçün ilk depozitinizi edin. Bu vəsaitlər HESABINIZA köçürüləcək, bundan sonra onları oyun üçün və ən əsası qazanmaq üçün istifadə edə bilərsiniz.\n\n"
                        + "● İlk depozitinizi etdikdən sonra, \"🔎 Depoziti yoxlayın\" düyməsini basın.";
            }
            if(currentUser.getLanguage().equals("turkish")){
                answer = "🌐 Sinyallere erişmek için ilk depozitonuzu yapmanız gerekiyor.\n\n"
                        + "✦ Botdaki LVL (seviye), durum ve sinyal başarı olasılığı depozit miktarına bağlıdır. Depozit ne kadar büyükse, botdaki LVL'iniz o kadar yüksek olur ve botdaki seviyeniz ne kadar yüksekse, yüksek başarı olasılığı olan sinyaller o kadar fazla alırsınız.\n\n"
                        + "✦ Hesabınızı etkinleştirmek için ilk depozitonuzu yapın. Bu fonlar HESABINIZA aktarılacak, ardından onları oyun için ve en önemlisi, kazanmak için kullanabilirsiniz.\n\n"
                        + "● İlk depozitonuzu yaptıktan sonra \"🔎 Depoziti kontrol et\" düğmesine basın.";
            }
            if(currentUser.getLanguage().equals("portuguese")){
                answer = "🌐 Para acessar os sinais, você precisa fazer seu primeiro depósito.\n\n"
                        + "✦ Seu LVL (nível) no bot, status e a probabilidade de sucesso do sinal dependem do depósito. Quanto maior o depósito, maior será seu LVL no bot, e quanto maior seu nível no bot, mais sinais com alta probabilidade de sucesso você receberá.\n\n"
                        + "✦ Ative sua conta fazendo o primeiro depósito. Esses fundos serão creditados em SUA CONTA, após o que você poderá usá-los para jogar e, o mais importante, ganhar.\n\n"
                        + "● Após fazer o primeiro depósito, pressione o botão \"🔎 Verificar depósito\".";
            }
            if(currentUser.getLanguage().equals("arabic")){
                answer = "🌐 للوصول إلى الإشارات، تحتاج إلى إجراء إيداعك الأول.\n\n"
                        + "✦ يعتمد LVL (المستوى) الخاص بك في الروبوت، والحالة، واحتمالية نجاح الإشارة على الإيداع. كلما كان الإيداع أكبر، كان LVL الخاص بك في الروبوت أعلى، وكلما زاد مستواك في الروبوت، كلما استلمت إشارات ذات احتمالية نجاح عالية.\n\n"
                        + "✦ قم بتنشيط حسابك عن طريق إجراء الإيداع الأول. سيتم إضافة هذه الأموال إلى حسابك، وبعد ذلك يمكنك استخدامها للعب والأهم من ذلك، للفوز.\n\n"
                        + "● بعد إجراء الإيداع الأول، اضغط على زر \"🔎 التحقق من الإيداع\".";
            }

            InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.menuWithCheckTheDeposit(currentUser.getLanguage());
            if (userService.userIsRussian(currentUser)){
                botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/imagesrussian/mines/deposit.jpg", inlineKeyboardMarkup);
            }
            else {
                botActions.sendMessageWithPhotoAndKeyboard(chatId,answer, "/imagesenglish/mines/deposit.jpg", inlineKeyboardMarkup);

            }
        }
        botActions.handleCallbackQuery(callbackQuery);
    }
}