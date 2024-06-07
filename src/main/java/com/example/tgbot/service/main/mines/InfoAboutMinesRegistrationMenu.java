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
public class InfoAboutMinesRegistrationMenu implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private final UserService userService;

    public InfoAboutMinesRegistrationMenu(BotActions botActions, UrlService urlService, UserService userService, PromoService promoService) {
        this.botActions = botActions;
        this.urlService = urlService;
        this.userService = userService;
        this.promoService = promoService;
    }
    @Override
    public void execute(long chatId, CallbackQuery callbackQuery) {
        User currentUser = userService.getInfoAboutUserByChatID(chatId);
        if(currentUser.getIsVerify()){
            if(userService.userIsRussian(currentUser)){
                botActions.sendMessageWithCallbackQuery(chatId,"Вы уже зарегистрированы",callbackQuery);
            }
            else {
                botActions.sendMessageWithCallbackQuery(chatId,"You already registered",callbackQuery);
            }

        }
        else {

            if (userService.userIsRussian(currentUser)){
                botActions.sendPhoto(chatId,"/imagesrussian/mines/registration.jpg");
            }
            else {
                botActions.sendPhoto(chatId,"/imagesenglish/mines/registration.jpg");
            }
            String answer = "";

            if (userService.userIsRussian(currentUser)) {
                answer = "💸 1. Для начала зарегистрируйтесь на сайте нажав кнопку 1WIN\n\n"
                        + "💸 2. Введите промокод `" + promoService.getOneWinPromo(currentUser) + "` при регистрации.\n\n"
                        + "💸 3. После успешной регистрации ваша учетная запись будет автоматически проверена системой, и вы получите сообщение в боте об успешной регистрации.\n"
                        + "Если вы зарегистрировались, но не получили сообщение, вы можете вручную проверить свой ID.\n\n"
                        + "Если возникнут проблемы, обратитесь в поддержку: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. ВАЖНАЯ ИНФОРМАЦИЯ!\n"
                        + "Если у вас уже есть аккаунт на этом сайте, просто зарегистрируйте новый аккаунт, используя новый адрес электронной почты. Помните, на этом сайте вы можете указать любой номер телефона, он ничего не даёт. Самое важное — ваша электронная почта!";
            }

            if (userService.userIsEnglish(currentUser)) {
                answer = "💸 1. Start by registering on the website by clicking the 1WIN button\n\n"
                        + "💸 2. Enter the promo code `" + promoService.getOneWinPromo(currentUser) + "` during registration.\n\n"
                        + "💸 3. After successful registration, your account will be automatically verified by the system, and you will receive a message in the bot about successful registration.\n"
                        + "If you registered but did not receive a message, you can manually check your ID.\n\n"
                        + "If there are any issues, contact support: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. IMPORTANT INFORMATION!\n"
                        + "If you already have an account on this site, simply register a new account using a new email address. Remember, you can enter any phone number on this site, it doesn't matter. The most important thing is your email!";
            }

            if (userService.userIsHindi(currentUser)) {
                answer = "💸 1. वेबसाइट पर 1WIN बटन पर क्लिक करके पंजीकरण करें\n\n"
                        + "💸 2. पंजीकरण के दौरान प्रोमो कोड `" + promoService.getOneWinPromo(currentUser) + "` दर्ज करें।\n\n"
                        + "💸 3. सफल पंजीकरण के बाद, आपकी खाता स्वचालित रूप से सिस्टम द्वारा सत्यापित की जाएगी, और आपको सफल पंजीकरण के बारे में बॉट में एक संदेश प्राप्त होगा।\n"
                        + "यदि आपने पंजीकरण किया लेकिन संदेश नहीं मिला, तो आप अपना ID मैन्युअल रूप से जांच सकते हैं।\n\n"
                        + "किसी भी समस्या के लिए, समर्थन से संपर्क करें: [SUPPORT](" + urlService.getSupportLink() + ")।\n\n"
                        + "💸 4. महत्वपूर्ण जानकारी!\n"
                        + "यदि आपके पास पहले से इस साइट पर एक खाता है, तो एक नया ईमेल पता का उपयोग करके बस एक नया खाता पंजीकृत करें। याद रखें, इस साइट पर आप कोई भी फ़ोन नंबर दर्ज कर सकते हैं, इसका कोई महत्व नहीं है। सबसे महत्वपूर्ण है आपकी ईमेल!";
            }

            if (userService.userIsBrazilian(currentUser)) {
                answer = "💸 1. Comece registrando-se no site clicando no botão 1WIN\n\n"
                        + "💸 2. Insira o código promocional `" + promoService.getOneWinPromo(currentUser) + "` durante o registro.\n\n"
                        + "💸 3. Após o registro bem-sucedido, sua conta será automaticamente verificada pelo sistema, e você receberá uma mensagem no bot sobre o registro bem-sucedido.\n"
                        + "Se você se registrou mas não recebeu uma mensagem, pode verificar seu ID manualmente.\n\n"
                        + "Se houver algum problema, entre em contato com o suporte: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. INFORMAÇÕES IMPORTANTES!\n"
                        + "Se você já tem uma conta neste site, basta registrar uma nova conta usando um novo endereço de e-mail. Lembre-se, você pode inserir qualquer número de telefone neste site, isso não importa. O mais importante é o seu e-mail!";
            }

            if (userService.userIsSpanish(currentUser)) {
                answer = "💸 1. Comience registrándose en el sitio web haciendo clic en el botón 1WIN\n\n"
                        + "💸 2. Ingrese el código promocional `" + promoService.getOneWinPromo(currentUser) + "` durante el registro.\n\n"
                        + "💸 3. Después del registro exitoso, su cuenta será verificada automáticamente por el sistema, y recibirá un mensaje en el bot sobre el registro exitoso.\n"
                        + "Si se registró pero no recibió un mensaje, puede verificar su ID manualmente.\n\n"
                        + "Si hay algún problema, comuníquese con el soporte: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. INFORMACIÓN IMPORTANTE!\n"
                        + "Si ya tiene una cuenta en este sitio, simplemente registre una nueva cuenta utilizando una nueva dirección de correo electrónico. Recuerde, puede ingresar cualquier número de teléfono en este sitio, no importa. Lo más importante es su correo electrónico!";
            }

            if (userService.userIsUzbek(currentUser)) {
                answer = "💸 1. Saytda ro'yxatdan o'tish uchun 1WIN tugmasini bosing\n\n"
                        + "💸 2. Ro'yxatdan o'tishda promo kodini kiriting `" + promoService.getOneWinPromo(currentUser) + "`.\n\n"
                        + "💸 3. Muvaffaqiyatli ro'yxatdan o'tgandan so'ng, sizning hisobingiz tizim tomonidan avtomatik ravishda tekshiriladi va ro'yxatdan o'tganligingiz haqida botda xabar olasiz.\n"
                        + "Agar ro'yxatdan o'tgan bo'lsangiz, lekin xabar olmagan bo'lsangiz, ID raqamingizni qo'lda tekshirishingiz mumkin.\n\n"
                        + "Agar muammolar bo'lsa, qo'llab-quvvatlash xizmatiga murojaat qiling: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. MUHIM MA'LUMOT!\n"
                        + "Agar sizda allaqachon ushbu saytda hisob mavjud bo'lsa, yangi elektron pochta manzilidan foydalanib yangi hisobni ro'yxatdan o'tkazing. Esda tuting, ushbu saytda har qanday telefon raqamini kiritishingiz mumkin, bu ahamiyatga ega emas. Eng muhimi, elektron pochtangiz!";
            }

            if (userService.userIsAzerbaijani(currentUser)) {
                answer = "💸 1. Saytda qeydiyyatdan keçmək üçün 1WIN düyməsini basın\n\n"
                        + "💸 2. Qeydiyyatdan keçərkən promo kodunu daxil edin `" + promoService.getOneWinPromo(currentUser) + "`.\n\n"
                        + "💸 3. Uğurlu qeydiyyatdan sonra hesabınız sistem tərəfindən avtomatik olaraq yoxlanılacaq və qeydiyyatınızın uğurlu olduğunu bildirən mesaj alacaqsınız.\n"
                        + "Əgər qeydiyyatdan keçmisinizsə, lakin mesaj almamısınızsa, ID-nizi əl ilə yoxlaya bilərsiniz.\n\n"
                        + "Hər hansı bir problem varsa, dəstək xidmətinə müraciət edin: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. MÜHÜM MƏLUMAT!\n"
                        + "Əgər bu saytda artıq hesabınız varsa, yeni e-poçt ünvanı istifadə edərək sadəcə yeni hesab yaradın. Unutmayın ki, bu saytda istənilən telefon nömrəsini daxil edə bilərsiniz, bu vacib deyil. Ən vacibi, elektron poçtunuzdur!";
            }

            if (userService.userIsTurkish(currentUser)) {
                answer = "💸 1. Sitede kayıt olmak için 1WIN butonuna tıklayın\n\n"
                        + "💸 2. Kayıt sırasında promosyon kodunu girin `" + promoService.getOneWinPromo(currentUser) + "`.\n\n"
                        + "💸 3. Başarılı kayıt sonrası hesabınız sistem tarafından otomatik olarak doğrulanacak ve başarılı kayıt hakkında botta bir mesaj alacaksınız.\n"
                        + "Kayıt oldunuz ancak mesaj almadıysanız, ID'nizi manuel olarak kontrol edebilirsiniz.\n\n"
                        + "Herhangi bir sorun olursa, destekle iletişime geçin: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. ÖNEMLİ BİLGİLER!\n"
                        + "Bu sitede zaten bir hesabınız varsa, yeni bir e-posta adresi kullanarak yeni bir hesap oluşturun. Unutmayın, bu sitede herhangi bir telefon numarası girebilirsiniz, bu önemli değil. En önemli olan, e-posta adresinizdir!";
            }

            if (userService.userIsPortuguese(currentUser)) {
                answer = "💸 1. Comece registrando-se no site clicando no botão 1WIN\n\n"
                        + "💸 2. Insira o código promocional `" + promoService.getOneWinPromo(currentUser) + "` durante o registro.\n\n"
                        + "💸 3. Após o registro bem-sucedido, sua conta será automaticamente verificada pelo sistema, e você receberá uma mensagem no bot sobre o registro bem-sucedido.\n"
                        + "Se você se registrou mas não recebeu uma mensagem, pode verificar seu ID manualmente.\n\n"
                        + "Se houver algum problema, entre em contato com o suporte: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. INFORMAÇÕES IMPORTANTES!\n"
                        + "Se você já tem uma conta neste site, basta registrar uma nova conta usando um novo endereço de e-mail. Lembre-se, você pode inserir qualquer número de telefone neste site, isso não importa. O mais importante é o seu e-mail!";
            }

            if (userService.userIsArabic(currentUser)) {
                answer = "💸 1. ابدأ بالتسجيل على الموقع بالنقر على زر 1WIN\n\n"
                        + "💸 2. أدخل رمز العرض الترويجي `" + promoService.getOneWinPromo(currentUser) + "` أثناء التسجيل.\n\n"
                        + "💸 3. بعد التسجيل الناجح، سيتم التحقق من حسابك تلقائيًا بواسطة النظام، وستتلقى رسالة في البوت حول التسجيل الناجح.\n"
                        + "إذا قمت بالتسجيل ولكن لم تتلق رسالة، يمكنك التحقق من معرفك يدويًا.\n\n"
                        + "إذا كانت هناك أي مشكلات، فاتصل بالدعم: [SUPPORT](" + urlService.getSupportLink() + ").\n\n"
                        + "💸 4. معلومات هامة!\n"
                        + "إذا كان لديك بالفعل حساب على هذا الموقع، فقط قم بتسجيل حساب جديد باستخدام عنوان بريد إلكتروني جديد. تذكر، يمكنك إدخال أي رقم هاتف على هذا الموقع، لا يهم. الأهم هو بريدك الإلكتروني!";
            }


            InlineKeyboardMarkup inlineKeyboardMarkup = MinesKeyboard.menuWithCancelButtonAndLinkToOneWin(urlService.getOneWinUrl(currentUser),currentUser.getLanguage());
            botActions.sendMessageWithInlineKeyboardAndParseMARKDOWN(chatId, answer, inlineKeyboardMarkup);
            //Отключил так как бот теперь не смотрит на айди
//            userService.enableAwaitingMode(chatId);
        }
        botActions.handleCallbackQuery(callbackQuery);
    }
}