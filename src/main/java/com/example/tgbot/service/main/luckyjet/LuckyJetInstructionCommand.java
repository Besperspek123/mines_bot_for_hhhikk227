package com.example.tgbot.service.main.luckyjet;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.LuckyJetKeyboard;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class LuckyJetInstructionCommand implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private UserService userService;

    public LuckyJetInstructionCommand(BotActions botActions, UrlService urlService, PromoService promoService, UserService userService) {
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
            botActions.sendPhoto(chatId,"/imagesrussian/luckyjet/luckyjet_logo.jpg");
        }
        else {
            botActions.sendPhoto(chatId,"/imagesenglish/luckyjet/luckyjet_logo.jpg");
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
                            "🟢 1. Перейти в раздел 1win games и выбрать игру ✈ 'Lucky Jet'.\n\n" +
                            "🟢 2. Выставить минимальную ставку. Это важно для управления риском!\n\n" +
                            "🟢 3. Запросить сигнал в боте и ставить по сигналам из бота.\n\n" +
                            "🟢 4. При неудачном сигнале советуем удвоить (Х²) ставку, чтобы полностью перекрыть потерю при следующем сигнале. \n\n" +
                            "   ⚠Важный нюанс: удваивать ставку подходит не всегда. Бывают прогнозы с высоким коэффициентом, и если удваивать каждый проигрыш, есть шанс остаться без денег. Так что запомните истину:\n" +
                            "   - Если коэффициент в сигнале 5 или меньше, вы можете удваивать ставку каждый проигрыш.\n" +
                            "   - Если коэффициент от 5 до 10, вы можете делать 2-3 проигрышных ставки по одной ставке и удваивать ставку,\n" +
                            "     и делать соответственно так же 2-3 проигрышных ставки и так пока не получится выиграть.\n" +
                            "   - Если коэффициент больше 10, играйте по одной небольшой ставке, так как в таких ставках выигрыш может быть от 20-60х от ставки. 😉\n\n" +
                            "   Попробуйте сегодня и увидьте, как ваш капитал растет с помощью нашего бота! 💹";
                } else {
                    answer = "*Бот основан и обучен на кластере нейросети bitsGap🧠*\n\n" +
                            "Для тренировки бота было сыграно более 10 000 игр 🎰\n" +
                            "В данный момент пользователи бота успешно делают в день 15-25% от своего капитала! 💰\n\n" +
                            "На текущий момент бот по сей день проходит проверки и исправления! Точность бота составляет 87%!\n\n" +
                            "Для получения максимального профита следуйте следующей инструкции:\n\n" +
                            "🟢 1. Зарегистрировать аккаунт через нашего бота, по кнопке 'Зарегистрироваться' под сообщением, без этого вы не сможете получить доступ к сигналам.\n\n" +
                            "🟢 2. Пополнить баланс своего аккаунта.\n\n" +
                            "🟢 3. Перейти в раздел 1win games и выбрать игру ✈ 'Lucky Jet'.\n\n" +
                            "🟢 4. Выставить минимальную ставку. Это важно для управления риском!\n\n" +
                            "🟢 5. Запросить сигнал в боте и ставить по сигналам из бота.\n\n" +
                            "🟢 6. При неудачном сигнале советуем удвоить (Х²) ставку, чтобы полностью перекрыть потерю при следующем сигнале. \n\n" +
                            "   ⚠Важный нюанс: удваивать ставку подходит не всегда. Бывают прогнозы с высоким коэффициентом, и если удваивать каждый проигрыш, есть шанс остаться без денег. Так что запомните истину:\n" +
                            "   - Если коэффициент в сигнале 5 или меньше, вы можете удваивать ставку каждый проигрыш.\n" +
                            "   - Если коэффициент от 5 до 10, вы можете делать 2-3 проигрышных ставки по одной ставке и удваивать ставку,\n" +
                            "     и делать соответственно так же 2-3 проигрышных ставки и так пока не получится выиграть.\n" +
                            "   - Если коэффициент больше 10, играйте по одной небольшой ставке, так как в таких ставках выигрыш может быть от 20-60х от ставки. 😉\n\n" +
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
                            "🟢 1. Go to the 1win games section and select the game ✈ 'Lucky Jet'.\n\n" +
                            "🟢 2. Set the minimum bet. This is important for risk management!\n\n" +
                            "🟢 3. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                            "🟢 4. If a signal fails, we recommend doubling (X²) the bet to completely cover the loss in the next signal. \n\n" +
                            "   ⚠Important note: Doubling the bet is not always suitable. There are predictions with high coefficients, and if you double each loss, there's a chance of running out of money. So remember the truth:\n" +
                            "   - If the coefficient in the signal is 5 or less, you can double the bet on each loss.\n" +
                            "   - If the coefficient is between 5 and 10, you can afford 2-3 losing bets at one bet and double the bet,\n" +
                            "     and do the same for another 2-3 losing bets until you win.\n" +
                            "   - If the coefficient is more than 10, play with a small bet, as such bets can yield a win from 20-60x the bet. 😉\n\n" +
                            "   Try it today and see how your capital grows with our bot! 💹";
                } else {
                    answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                            "The bot has been trained with more than 10,000 games 🎰\n" +
                            "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                            "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                            "To maximize your profits, follow these instructions:\n\n" +
                            "🟢 1. Register an account through our bot by clicking the 'Register' button under the message, without this you will not be able to access the signals.\n\n" +
                            "🟢 2. Top up your account balance.\n\n" +
                            "🟢 3. Go to the 1win games section and select the game ✈ 'Lucky Jet'.\n\n" +
                            "🟢 4. Set the minimum bet. This is important for risk management!\n\n" +
                            "🟢 5. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                            "🟢 6. If a signal fails, we recommend doubling (X²) the bet to completely cover the loss in the next signal. \n\n" +
                            "   ⚠Important note: Doubling the bet is not always suitable. There are predictions with high coefficients, and if you double each loss, there's a chance of running out of money. So remember the truth:\n" +
                            "   - If the coefficient in the signal is 5 or less, you can double the bet on each loss.\n" +
                            "   - If the coefficient is between 5 and 10, you can afford 2-3 losing bets at one bet and double the bet,\n" +
                            "     and do the same for another 2-3 losing bets until you win.\n" +
                            "   - If the coefficient is more than 10, play with a small bet, as such bets can yield a win from 20-60x the bet. 😉\n\n" +
                            "   Try it today and see how your capital grows with our bot! 💹";
                }
                break;

            case "hindi":
                if (user.getIsVerify()) {
                    answer = "*बॉट न्यूरल नेटवर्क क्लस्टर bitsGap🧠 पर आधारित और प्रशिक्षित है*\n\n" +
                            "बॉट को 10,000 से अधिक खेलों के साथ प्रशिक्षित किया गया है 🎰\n" +
                            "वर्तमान में, बॉट उपयोगकर्ता प्रतिदिन अपने पूंजी का 15-25% सफलतापूर्वक कमा रहे हैं! 💰\n\n" +
                            "वर्तमान में, बॉट अभी भी जाँच और समायोजन के अधीन है! बॉट की सटीकता 87% है!\n\n" +
                            "अपने मुनाफे को अधिकतम करने के लिए, इन निर्देशों का पालन करें:\n\n" +
                            "🟢 1. 1win गेम्स अनुभाग में जाएं और खेल ✈ 'Lucky Jet' चुनें।\n\n" +
                            "🟢 2. न्यूनतम दांव सेट करें। यह जोखिम प्रबंधन के लिए महत्वपूर्ण है!\n\n" +
                            "🟢 3. बॉट में एक सिग्नल का अनुरोध करें और बॉट के सिग्नल के आधार पर दांव लगाएं।\n\n" +
                            "🟢 4. यदि एक सिग्नल विफल होता है, तो हम अनुशंसा करते हैं कि आप अगले सिग्नल में पूरी हानि को कवर करने के लिए दांव को दोगुना (X²) करें। \n\n" +
                            "   ⚠महत्वपूर्ण नोट: दांव को दोगुना करना हमेशा उपयुक्त नहीं होता है। उच्च गुणांक वाले अनुमान होते हैं, और यदि आप हर हानि को दोगुना करते हैं, तो पैसे खत्म होने का मौका होता है। तो सच्चाई को याद रखें:\n" +
                            "   - यदि सिग्नल में गुणांक 5 या उससे कम है, तो आप हर हानि पर दांव को दोगुना कर सकते हैं।\n" +
                            "   - यदि गुणांक 5 और 10 के बीच है, तो आप एक दांव पर 2-3 हार सकते हैं और दांव को दोगुना कर सकते हैं,\n" +
                            "     और दूसरे 2-3 हारने वाले दांव के लिए भी ऐसा ही करें जब तक आप जीतते नहीं।\n" +
                            "   - यदि गुणांक 10 से अधिक है, तो छोटे दांव के साथ खेलें, क्योंकि ऐसे दांव से आपको दांव का 20-60x जीत मिल सकता है। 😉\n\n" +
                            "   आज इसे आजमाएं और देखें कि आपके पूंजी कैसे हमारे बॉट के साथ बढ़ती है! 💹";
                } else {
                    answer = "*बॉट न्यूरल नेटवर्क क्लस्टर bitsGap🧠 पर आधारित और प्रशिक्षित है*\n\n" +
                            "बॉट को 10,000 से अधिक खेलों के साथ प्रशिक्षित किया गया है 🎰\n" +
                            "वर्तमान में, बॉट उपयोगकर्ता प्रतिदिन अपने पूंजी का 15-25% सफलतापूर्वक कमा रहे हैं! 💰\n\n" +
                            "वर्तमान में, बॉट अभी भी जाँच और समायोजन के अधीन है! बॉट की सटीकता 87% है!\n\n" +
                            "अपने मुनाफे को अधिकतम करने के लिए, इन निर्देशों का पालन करें:\n\n" +
                            "🟢 1. हमारे बॉट के माध्यम से एक खाता पंजीकृत करें, संदेश के नीचे 'पंजीकृत करें' बटन पर क्लिक करके, इसके बिना आप सिग्नल तक पहुंचने में असमर्थ होंगे।\n\n" +
                            "🟢 2. अपने खाते का बैलेंस टॉप अप करें।\n\n" +
                            "🟢 3. 1win गेम्स अनुभाग में जाएं और खेल ✈ 'Lucky Jet' चुनें।\n\n" +
                            "🟢 4. न्यूनतम दांव सेट करें। यह जोखिम प्रबंधन के लिए महत्वपूर्ण है!\n\n" +
                            "🟢 5. बॉट में एक सिग्नल का अनुरोध करें और बॉट के सिग्नल के आधार पर दांव लगाएं।\n\n" +
                            "🟢 6. यदि एक सिग्नल विफल होता है, तो हम अनुशंसा करते हैं कि आप अगले सिग्नल में पूरी हानि को कवर करने के लिए दांव को दोगुना (X²) करें। \n\n" +
                            "   ⚠महत्वपूर्ण नोट: दांव को दोगुना करना हमेशा उपयुक्त नहीं होता है। उच्च गुणांक वाले अनुमान होते हैं, और यदि आप हर हानि को दोगुना करते हैं, तो पैसे खत्म होने का मौका होता है। तो सच्चाई को याद रखें:\n" +
                            "   - यदि सिग्नल में गुणांक 5 या उससे कम है, तो आप हर हानि पर दांव को दोगुना कर सकते हैं।\n" +
                            "   - यदि गुणांक 5 और 10 के बीच है, तो आप एक दांव पर 2-3 हार सकते हैं और दांव को दोगुना कर सकते हैं,\n" +
                            "     और दूसरे 2-3 हारने वाले दांव के लिए भी ऐसा ही करें जब तक आप जीतते नहीं।\n" +
                            "   - यदि गुणांक 10 से अधिक है, तो छोटे दांव के साथ खेलें, क्योंकि ऐसे दांव से आपको दांव का 20-60x जीत मिल सकता है। 😉\n\n" +
                            "   आज इसे आजमाएं और देखें कि आपके पूंजी कैसे हमारे बॉट के साथ बढ़ती है! 💹";
                }
                break;

            case "brazilian":
                if (user.getIsVerify()) {
                    answer = "*O bot é baseado e treinado no cluster de redes neurais bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot fazem com sucesso 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Vá para a seção de jogos 1win e selecione o jogo ✈ 'Lucky Jet'.\n\n" +
                            "🟢 2. Defina a aposta mínima. Isso é importante para a gestão de risco!\n\n" +
                            "🟢 3. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 4. Se um sinal falhar, recomendamos dobrar (X²) a aposta para cobrir completamente a perda no próximo sinal. \n\n" +
                            "   ⚠Nota importante: Dobrar a aposta nem sempre é adequado. Existem previsões com coeficientes altos e, se você dobrar cada perda, há uma chance de ficar sem dinheiro. Então lembre-se da verdade:\n" +
                            "   - Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "   - Se o coeficiente estiver entre 5 e 10, você pode suportar 2-3 perdas em uma aposta e dobrar a aposta,\n" +
                            "     e fazer o mesmo para mais 2-3 perdas até ganhar.\n" +
                            "   - Se o coeficiente for maior que 10, jogue com uma aposta pequena, pois essas apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                            "   Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                } else {
                    answer = "*O bot é baseado e treinado no cluster de redes neurais bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot fazem com sucesso 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Registre uma conta através do nosso bot clicando no botão 'Registrar' abaixo da mensagem, sem isso você não poderá acessar os sinais.\n\n" +
                            "🟢 2. Recarregue o saldo da sua conta.\n\n" +
                            "🟢 3. Vá para a seção de jogos 1win e selecione o jogo ✈ 'Lucky Jet'.\n\n" +
                            "🟢 4. Defina a aposta mínima. Isso é importante para a gestão de risco!\n\n" +
                            "🟢 5. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 6. Se um sinal falhar, recomendamos dobrar (X²) a aposta para cobrir completamente a perda no próximo sinal. \n\n" +
                            "   ⚠Nota importante: Dobrar a aposta nem sempre é adequado. Existem previsões com coeficientes altos e, se você dobrar cada perda, há uma chance de ficar sem dinheiro. Então lembre-se da verdade:\n" +
                            "   - Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "   - Se o coeficiente estiver entre 5 e 10, você pode suportar 2-3 perdas em uma aposta e dobrar a aposta,\n" +
                            "     e fazer o mesmo para mais 2-3 perdas até ganhar.\n" +
                            "   - Se o coeficiente for maior que 10, jogue com uma aposta pequena, pois essas apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                            "   Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                }
                break;

            case "spanish":
                if (user.getIsVerify()) {
                    answer = "*El bot está basado y entrenado en el clúster de redes neuronales bitsGap🧠*\n\n" +
                            "El bot ha sido entrenado con más de 10,000 juegos 🎰\n" +
                            "Actualmente, los usuarios del bot hacen con éxito el 15-25% de su capital por día! 💰\n\n" +
                            "En este momento, el bot aún está pasando por verificaciones y ajustes! La precisión del bot es del 87%!\n\n" +
                            "Para maximizar sus ganancias, siga estas instrucciones:\n\n" +
                            "🟢 1. Vaya a la sección de juegos 1win y seleccione el juego ✈ 'Lucky Jet'.\n\n" +
                            "🟢 2. Configure la apuesta mínima. ¡Esto es importante para la gestión de riesgos!\n\n" +
                            "🟢 3. Solicite una señal en el bot y realice apuestas basadas en las señales del bot.\n\n" +
                            "🟢 4. Si una señal falla, recomendamos duplicar (X²) la apuesta para cubrir completamente la pérdida en la siguiente señal. \n\n" +
                            "   ⚠Nota importante: Duplicar la apuesta no siempre es adecuado. Hay predicciones con coeficientes altos y, si duplica cada pérdida, existe la posibilidad de quedarse sin dinero. Así que recuerde la verdad:\n" +
                            "   - Si el coeficiente en la señal es 5 o menos, puede duplicar la apuesta en cada pérdida.\n" +
                            "   - Si el coeficiente está entre 5 y 10, puede permitirse 2-3 pérdidas en una apuesta y duplicar la apuesta,\n" +
                            "     y hacer lo mismo para otras 2-3 pérdidas hasta ganar.\n" +
                            "   - Si el coeficiente es mayor de 10, juegue con una apuesta pequeña, ya que tales apuestas pueden generar una ganancia de 20-60x la apuesta. 😉\n\n" +
                            "   ¡Inténtelo hoy y vea cómo su capital crece con nuestro bot! 💹";
                } else {
                    answer = "*El bot está basado y entrenado en el clúster de redes neuronales bitsGap🧠*\n\n" +
                            "El bot ha sido entrenado con más de 10,000 juegos 🎰\n" +
                            "Actualmente, los usuarios del bot hacen con éxito el 15-25% de su capital por día! 💰\n\n" +
                            "En este momento, el bot aún está pasando por verificaciones y ajustes! La precisión del bot es del 87%!\n\n" +
                            "Para maximizar sus ganancias, siga estas instrucciones:\n\n" +
                            "🟢 1. Registre una cuenta a través de nuestro bot haciendo clic en el botón 'Registrar' debajo del mensaje, sin esto no podrá acceder a las señales.\n\n" +
                            "🟢 2. Recargue el saldo de su cuenta.\n\n" +
                            "🟢 3. Vaya a la sección de juegos 1win y seleccione el juego ✈ 'Lucky Jet'.\n\n" +
                            "🟢 4. Configure la apuesta mínima. ¡Esto es importante para la gestión de riesgos!\n\n" +
                            "🟢 5. Solicite una señal en el bot y realice apuestas basadas en las señales del bot.\n\n" +
                            "🟢 6. Si una señal falla, recomendamos duplicar (X²) la apuesta para cubrir completamente la pérdida en la siguiente señal. \n\n" +
                            "   ⚠Nota importante: Duplicar la apuesta no siempre es adecuado. Hay predicciones con coeficientes altos y, si duplica cada pérdida, existe la posibilidad de quedarse sin dinero. Así que recuerde la verdad:\n" +
                            "   - Si el coeficiente en la señal es 5 o menos, puede duplicar la apuesta en cada pérdida.\n" +
                            "   - Si el coeficiente está entre 5 y 10, puede permitirse 2-3 pérdidas en una apuesta y duplicar la apuesta,\n" +
                            "     y hacer lo mismo para otras 2-3 pérdidas hasta ganar.\n" +
                            "   - Si el coeficiente es mayor de 10, juegue con una apuesta pequeña, ya que tales apuestas pueden generar una ganancia de 20-60x la apuesta. 😉\n\n" +
                            "   ¡Inténtelo hoy y vea cómo su capital crece con nuestro bot! 💹";
                }
                break;

            case "uzbek":
                if (user.getIsVerify()) {
                    answer = "*Bot bitsGap🧠 neyron tarmog'i klasteriga asoslangan va o'qitilgan*\n\n" +
                            "Bot 10,000 dan ortiq o'yinlar bilan o'qitilgan 🎰\n" +
                            "Hozirda, bot foydalanuvchilari har kuni kapitalining 15-25% ni muvaffaqiyatli qilishmoqda! 💰\n\n" +
                            "Hozirda, bot hali ham tekshirish va sozlashdan o'tmoqda! Botning aniqligi 87%!\n\n" +
                            "Foydangizni maksimal darajada oshirish uchun ushbu ko'rsatmalarga amal qiling:\n\n" +
                            "🟢 1. 1win o'yinlar bo'limiga o'ting va ✈ 'Lucky Jet' o'yinini tanlang.\n\n" +
                            "🟢 2. Minimal garovni qo'ying. Bu xavfni boshqarish uchun muhim!\n\n" +
                            "🟢 3. Botda signal so'rang va bot signallariga asoslangan garovlar qo'ying.\n\n" +
                            "🟢 4. Agar signal muvaffaqiyatsiz bo'lsa, keyingi signalda to'liq yo'qotishni qoplash uchun garovni ikki barobar oshirishni (X²) tavsiya etamiz. \n\n" +
                            "   ⚠Muhim eslatma: Garovni ikki barobar oshirish har doim ham mos kelmaydi. Yuqori koeffitsientli bashoratlar bor va agar har bir yo'qotishni ikki barobar oshirsangiz, pulingiz tugashi ehtimoli bor. Shuning uchun haqiqatni eslab qoling:\n" +
                            "   - Agar signalda koeffitsient 5 yoki undan kam bo'lsa, har bir yo'qotishda garovni ikki barobar oshirishingiz mumkin.\n" +
                            "   - Agar koeffitsient 5 va 10 orasida bo'lsa, siz bir garovda 2-3 yo'qotishni qabul qilishingiz va garovni ikki barobar oshirishingiz mumkin,\n" +
                            "     va yana 2-3 yo'qotishni qilish va nihoyat g'alaba qozonish uchun shu tarzda davom eting.\n" +
                            "   - Agar koeffitsient 10 dan yuqori bo'lsa, kichik garov bilan o'ynang, chunki bunday garovlar garovning 20-60x g'alabasini keltirishi mumkin. 😉\n\n" +
                            "   Bugun sinab ko'ring va kapitalingiz bizning bot bilan qanday o'sayotganini ko'ring! 💹";
                } else {
                    answer = "*Bot bitsGap🧠 neyron tarmog'i klasteriga asoslangan va o'qitilgan*\n\n" +
                            "Bot 10,000 dan ortiq o'yinlar bilan o'qitilgan 🎰\n" +
                            "Hozirda, bot foydalanuvchilari har kuni kapitalining 15-25% ni muvaffaqiyatli qilishmoqda! 💰\n\n" +
                            "Hozirda, bot hali ham tekshirish va sozlashdan o'tmoqda! Botning aniqligi 87%!\n\n" +
                            "Foydangizni maksimal darajada oshirish uchun ushbu ko'rsatmalarga amal qiling:\n\n" +
                            "🟢 1. Xabar ostidagi 'Ro'yxatdan o'tish' tugmasini bosish orqali bizning bot orqali hisob qaydnomangizni ro'yxatdan o'tkazing, shunda siz signallarga kirishingiz mumkin emas.\n\n" +
                            "🟢 2. Hisobingiz balansini to'ldiring.\n\n" +
                            "🟢 3. 1win o'yinlar bo'limiga o'ting va ✈ 'Lucky Jet' o'yinini tanlang.\n\n" +
                            "🟢 4. Minimal garovni qo'ying. Bu xavfni boshqarish uchun muhim!\n\n" +
                            "🟢 5. Botda signal so'rang va bot signallariga asoslangan garovlar qo'ying.\n\n" +
                            "🟢 6. Agar signal muvaffaqiyatsiz bo'lsa, keyingi signalda to'liq yo'qotishni qoplash uchun garovni ikki barobar oshirishni (X²) tavsiya etamiz. \n\n" +
                            "   ⚠Muhim eslatma: Garovni ikki barobar oshirish har doim ham mos kelmaydi. Yuqori koeffitsientli bashoratlar bor va agar har bir yo'qotishni ikki barobar oshirsangiz, pulingiz tugashi ehtimoli bor. Shuning uchun haqiqatni eslab qoling:\n" +
                            "   - Agar signalda koeffitsient 5 yoki undan kam bo'lsa, har bir yo'qotishda garovni ikki barobar oshirishingiz mumkin.\n" +
                            "   - Agar koeffitsient 5 va 10 orasida bo'lsa, siz bir garovda 2-3 yo'qotishni qabul qilishingiz va garovni ikki barobar oshirishingiz mumkin,\n" +
                            "     va yana 2-3 yo'qotishni qilish va nihoyat g'alaba qozonish uchun shu tarzda davom eting.\n" +
                            "   - Agar koeffitsient 10 dan yuqori bo'lsa, kichik garov bilan o'ynang, chunki bunday garovlar garovning 20-60x g'alabasini keltirishi mumkin. 😉\n\n" +
                            "   Bugun sinab ko'ring va kapitalingiz bizning bot bilan qanday o'sayotganini ko'ring! 💹";
                }
                break;

            case "azerbaijani":
                if (user.getIsVerify()) {
                    answer = "*Bot bitsGap🧠 neyron şəbəkə klasterinə əsaslanır və təlim keçmişdir*\n\n" +
                            "Bot 10,000-dən çox oyunla təlim keçmişdir 🎰\n" +
                            "Hazırda, bot istifadəçiləri gündə kapitalının 15-25%-ni uğurla qazanırlar! 💰\n\n" +
                            "Hazırda, bot hələ də yoxlamalar və tənzimləmələrdən keçir! Botun dəqiqliyi 87%-dir!\n\n" +
                            "Gəlirinizi maksimuma çatdırmaq üçün bu təlimatları izləyin:\n\n" +
                            "🟢 1. 1win oyunlar bölməsinə keçin və ✈ 'Lucky Jet' oyununu seçin.\n\n" +
                            "🟢 2. Minimum mərc qoyun. Bu, risk idarəetməsi üçün vacibdir!\n\n" +
                            "🟢 3. Botda bir siqnal istəyin və bot siqnallarına əsasən mərc edin.\n\n" +
                            "🟢 4. Bir siqnal uğursuz olarsa, növbəti siqnalda tam itkini örtmək üçün mərcinizi ikiqat artırmağı (X²) tövsiyə edirik. \n\n" +
                            "   ⚠Vacib qeyd: Mərcini ikiqat artırmaq həmişə uyğun deyil. Yüksək əmsallı proqnozlar var və hər bir itkini ikiqat artırarsanız, pulunuz bitə bilər. Həqiqəti xatırlayın:\n" +
                            "   - Siqnalda əmsal 5 və ya daha azdırsa, hər bir itkini ikiqat artırabilirsiniz.\n" +
                            "   - Əmsal 5 ilə 10 arasındadırsa, bir mərcdə 2-3 itkiyə dözə bilərsiniz və mərcinizi ikiqat artırabilirsiniz,\n" +
                            "     və başqa 2-3 itkiyə qədər qazanmaq üçün bunu edə bilərsiniz.\n" +
                            "   - Əmsal 10-dan böyükdürsə, kiçik mərc ilə oynayın, çünki belə mərclər mərcin 20-60x qazanmasını təmin edə bilər. 😉\n\n" +
                            "   Bu gün sınayın və kapitalınızın botumuzla necə artdığını görün! 💹";
                } else {
                    answer = "*Bot bitsGap🧠 neyron şəbəkə klasterinə əsaslanır və təlim keçmişdir*\n\n" +
                            "Bot 10,000-dən çox oyunla təlim keçmişdir 🎰\n" +
                            "Hazırda, bot istifadəçiləri gündə kapitalının 15-25%-ni uğurla qazanırlar! 💰\n\n" +
                            "Hazırda, bot hələ də yoxlamalar və tənzimləmələrdən keçir! Botun dəqiqliyi 87%-dir!\n\n" +
                            "Gəlirinizi maksimuma çatdırmaq üçün bu təlimatları izləyin:\n\n" +
                            "🟢 1. Mesajın altındakı 'Qeydiyyatdan keç' düyməsinə basaraq, botumuz vasitəsilə bir hesabı qeydiyyatdan keçirin, onsuz siqnallara daxil ola bilməyəcəksiniz.\n\n" +
                            "🟢 2. Hesab balansınızı artırın.\n\n" +
                            "🟢 3. 1win oyunlar bölməsinə keçin və ✈ 'Lucky Jet' oyununu seçin.\n\n" +
                            "🟢 4. Minimum mərc qoyun. Bu, risk idarəetməsi üçün vacibdir!\n\n" +
                            "🟢 5. Botda bir siqnal istəyin və bot siqnallarına əsasən mərc edin.\n\n" +
                            "🟢 6. Bir siqnal uğursuz olarsa, növbəti siqnalda tam itkini örtmək üçün mərcinizi ikiqat artırmağı (X²) tövsiyə edirik. \n\n" +
                            "   ⚠Vacib qeyd: Mərcini ikiqat artırmaq həmişə uyğun deyil. Yüksək əmsallı proqnozlar var və hər bir itkini ikiqat artırarsanız, pulunuz bitə bilər. Həqiqəti xatırlayın:\n" +
                            "   - Siqnalda əmsal 5 və ya daha azdırsa, hər bir itkini ikiqat artırabilirsiniz.\n" +
                            "   - Əmsal 5 ilə 10 arasındadırsa, bir mərcdə 2-3 itkiyə dözə bilərsiniz və mərcinizi ikiqat artırabilirsiniz,\n" +
                            "     və başqa 2-3 itkiyə qədər qazanmaq üçün bunu edə bilərsiniz.\n" +
                            "   - Əmsal 10-dan böyükdürsə, kiçik mərc ilə oynayın, çünki belə mərclər mərcin 20-60x qazanmasını təmin edə bilər. 😉\n\n" +
                            "   Bu gün sınayın və kapitalınızın botumuzla necə artdığını görün! 💹";
                }
                break;

            case "turkish":
                if (user.getIsVerify()) {
                    answer = "*Bot bitsGap🧠 sinir ağı kümesinde eğitilmiş ve temellendirilmiştir*\n\n" +
                            "Bot, 10.000'den fazla oyun ile eğitilmiştir 🎰\n" +
                            "Şu anda, bot kullanıcıları günlük olarak sermayelerinin %15-25'ini başarıyla kazanmaktadır! 💰\n\n" +
                            "Şu anda, bot hala kontrollerden ve ayarlamalardan geçmektedir! Botun doğruluğu %87'dir!\n\n" +
                            "Karlarınızı maksimize etmek için şu talimatları izleyin:\n\n" +
                            "🟢 1. 1win oyunları bölümüne gidin ve ✈ 'Lucky Jet' oyununu seçin.\n\n" +
                            "🟢 2. Minimum bahsi ayarlayın. Bu, risk yönetimi için önemlidir!\n\n" +
                            "🟢 3. Bottan bir sinyal talep edin ve botun sinyallerine dayalı olarak bahis yapın.\n\n" +
                            "🟢 4. Bir sinyal başarısız olursa, bir sonraki sinyalde kaybı tamamen kapatmak için bahsinizi ikiye katlamayı (X²) öneririz. \n\n" +
                            "   ⚠Önemli not: Bahsi ikiye katlamak her zaman uygun değildir. Yüksek katsayılı tahminler vardır ve her kaybı ikiye katlarsanız, paranızın tükenme olasılığı vardır. Bu gerçeği unutmayın:\n" +
                            "   - Sinyaldeki katsayı 5 veya daha az ise, her kaybı ikiye katlayabilirsiniz.\n" +
                            "   - Katsayı 5 ile 10 arasında ise, bir bahiste 2-3 kaybı karşılayabilir ve bahsi ikiye katlayabilirsiniz,\n" +
                            "     ve kazanmak için başka 2-3 kaybı karşılayana kadar bunu yapabilirsiniz.\n" +
                            "   - Katsayı 10'dan büyükse, küçük bahisle oynayın, çünkü böyle bahisler bahsin 20-60x kazancını sağlayabilir. 😉\n\n" +
                            "   Bugün deneyin ve sermayenizin botumuzla nasıl büyüdüğünü görün! 💹";
                } else {
                    answer = "*Bot bitsGap🧠 sinir ağı kümesinde eğitilmiş ve temellendirilmiştir*\n\n" +
                            "Bot, 10.000'den fazla oyun ile eğitilmiştir 🎰\n" +
                            "Şu anda, bot kullanıcıları günlük olarak sermayelerinin %15-25'ini başarıyla kazanmaktadır! 💰\n\n" +
                            "Şu anda, bot hala kontrollerden ve ayarlamalardan geçmektedir! Botun doğruluğu %87'dir!\n\n" +
                            "Karlarınızı maksimize etmek için şu talimatları izleyin:\n\n" +
                            "🟢 1. Mesajın altında bulunan 'Kaydol' düğmesine tıklayarak botumuz aracılığıyla bir hesap kaydedin, bu olmadan sinyallere erişemezsiniz.\n\n" +
                            "🟢 2. Hesap bakiyenizi doldurun.\n\n" +
                            "🟢 3. 1win oyunları bölümüne gidin ve ✈ 'Lucky Jet' oyununu seçin.\n\n" +
                            "🟢 4. Minimum bahsi ayarlayın. Bu, risk yönetimi için önemlidir!\n\n" +
                            "🟢 5. Bottan bir sinyal talep edin ve botun sinyallerine dayalı olarak bahis yapın.\n\n" +
                            "🟢 6. Bir sinyal başarısız olursa, bir sonraki sinyalde kaybı tamamen kapatmak için bahsinizi ikiye katlamayı (X²) öneririz. \n\n" +
                            "   ⚠Önemli not: Bahsi ikiye katlamak her zaman uygun değildir. Yüksek katsayılı tahminler vardır ve her kaybı ikiye katlarsanız, paranızın tükenme olasılığı vardır. Bu gerçeği unutmayın:\n" +
                            "   - Sinyaldeki katsayı 5 veya daha az ise, her kaybı ikiye katlayabilirsiniz.\n" +
                            "   - Katsayı 5 ile 10 arasında ise, bir bahiste 2-3 kaybı karşılayabilir ve bahsi ikiye katlayabilirsiniz,\n" +
                            "     ve kazanmak için başka 2-3 kaybı karşılayana kadar bunu yapabilirsiniz.\n" +
                            "   - Katsayı 10'dan büyükse, küçük bahisle oynayın, çünkü böyle bahisler bahsin 20-60x kazancını sağlayabilir. 😉\n\n" +
                            "   Bugün deneyin ve sermayenizin botumuzla nasıl büyüdüğünü görün! 💹";
                }
                break;

            case "portuguese":
                if (user.getIsVerify()) {
                    answer = "*O bot é baseado e treinado no cluster de redes neurais bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot fazem com sucesso 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Vá para a seção de jogos 1win e selecione o jogo ✈ 'Lucky Jet'.\n\n" +
                            "🟢 2. Defina a aposta mínima. Isso é importante para a gestão de risco!\n\n" +
                            "🟢 3. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 4. Se um sinal falhar, recomendamos dobrar (X²) a aposta para cobrir completamente a perda no próximo sinal. \n\n" +
                            "   ⚠Nota importante: Dobrar a aposta nem sempre é adequado. Existem previsões com coeficientes altos e, se você dobrar cada perda, há uma chance de ficar sem dinheiro. Então lembre-se da verdade:\n" +
                            "   - Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "   - Se o coeficiente estiver entre 5 e 10, você pode suportar 2-3 perdas em uma aposta e dobrar a aposta,\n" +
                            "     e fazer o mesmo para mais 2-3 perdas até ganhar.\n" +
                            "   - Se o coeficiente for maior que 10, jogue com uma aposta pequena, pois essas apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                            "   Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                } else {
                    answer = "*O bot é baseado e treinado no cluster de redes neurais bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot fazem com sucesso 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Registre uma conta através do nosso bot clicando no botão 'Registrar' abaixo da mensagem, sem isso você não poderá acessar os sinais.\n\n" +
                            "🟢 2. Recarregue o saldo da sua conta.\n\n" +
                            "🟢 3. Vá para a seção de jogos 1win e selecione o jogo ✈ 'Lucky Jet'.\n\n" +
                            "🟢 4. Defina a aposta mínima. Isso é importante para a gestão de risco!\n\n" +
                            "🟢 5. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 6. Se um sinal falhar, recomendamos dobrar (X²) a aposta para cobrir completamente a perda no próximo sinal. \n\n" +
                            "   ⚠Nota importante: Dobrar a aposta nem sempre é adequado. Existem previsões com coeficientes altos e, se você dobrar cada perda, há uma chance de ficar sem dinheiro. Então lembre-se da verdade:\n" +
                            "   - Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "   - Se o coeficiente estiver entre 5 e 10, você pode suportar 2-3 perdas em uma aposta e dobrar a aposta,\n" +
                            "     e fazer o mesmo para mais 2-3 perdas até ganhar.\n" +
                            "   - Se o coeficiente for maior que 10, jogue com uma aposta pequena, pois essas apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                            "   Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                }
                break;

            case "arabic":
                if (user.getIsVerify()) {
                    answer = "*البوت مبني ومدرب على عنقود الشبكة العصبية bitsGap🧠*\n\n" +
                            "تم تدريب البوت على أكثر من 10,000 لعبة 🎰\n" +
                            "حاليًا، يحقق مستخدمو البوت بنجاح 15-25% من رأس مالهم يوميًا! 💰\n\n" +
                            "في الوقت الحالي، لا يزال البوت يخضع للفحص والتعديلات! دقة البوت هي 87%!\n\n" +
                            "لزيادة أرباحك إلى الحد الأقصى، اتبع هذه التعليمات:\n\n" +
                            "🟢 1. انتقل إلى قسم ألعاب 1win واختر اللعبة ✈ 'Lucky Jet'.\n\n" +
                            "🟢 2. قم بتعيين الحد الأدنى للرهان. هذا مهم لإدارة المخاطر!\n\n" +
                            "🟢 3. اطلب إشارة في البوت وقم بوضع رهانات بناءً على إشارات البوت.\n\n" +
                            "🟢 4. إذا فشلت إشارة، نوصي بمضاعفة الرهان (X²) لتغطية الخسارة بالكامل في الإشارة التالية. \n\n" +
                            "   ⚠ملاحظة هامة: مضاعفة الرهان ليست دائمًا مناسبة. هناك تنبؤات بمعاملات عالية، وإذا قمت بمضاعفة كل خسارة، فهناك احتمال نفاد المال. لذا تذكر الحقيقة:\n" +
                            "   - إذا كان المعامل في الإشارة 5 أو أقل، يمكنك مضاعفة الرهان في كل خسارة.\n" +
                            "   - إذا كان المعامل بين 5 و 10، يمكنك تحمل 2-3 رهانات خاسرة في رهان واحد ومضاعفة الرهان،\n" +
                            "     وتكرار نفس الشيء لرهانات خاسرة أخرى حتى تفوز.\n" +
                            "   - إذا كان المعامل أكبر من 10، العب برهان صغير، لأن هذه الرهانات يمكن أن تحقق ربحًا يتراوح بين 20-60 ضعف الرهان. 😉\n\n" +
                            "   جربه اليوم وشاهد كيف ينمو رأس مالك مع البوت الخاص بنا! 💹";
                } else {
                    answer = "*البوت مبني ومدرب على عنقود الشبكة العصبية bitsGap🧠*\n\n" +
                            "تم تدريب البوت على أكثر من 10,000 لعبة 🎰\n" +
                            "حاليًا، يحقق مستخدمو البوت بنجاح 15-25% من رأس مالهم يوميًا! 💰\n\n" +
                            "في الوقت الحالي، لا يزال البوت يخضع للفحص والتعديلات! دقة البوت هي 87%!\n\n" +
                            "لزيادة أرباحك إلى الحد الأقصى، اتبع هذه التعليمات:\n\n" +
                            "🟢 1. قم بتسجيل حساب من خلال البوت الخاص بنا بالنقر على زر 'تسجيل' أسفل الرسالة، بدون هذا لن تتمكن من الوصول إلى الإشارات.\n\n" +
                            "🟢 2. قم بتعبئة رصيد حسابك.\n\n" +
                            "🟢 3. انتقل إلى قسم ألعاب 1win واختر اللعبة ✈ 'Lucky Jet'.\n\n" +
                            "🟢 4. قم بتعيين الحد الأدنى للرهان. هذا مهم لإدارة المخاطر!\n\n" +
                            "🟢 5. اطلب إشارة في البوت وقم بوضع رهانات بناءً على إشارات البوت.\n\n" +
                            "🟢 6. إذا فشلت إشارة، نوصي بمضاعفة الرهان (X²) لتغطية الخسارة بالكامل في الإشارة التالية. \n\n" +
                            "   ⚠ملاحظة هامة: مضاعفة الرهان ليست دائمًا مناسبة. هناك تنبؤات بمعاملات عالية، وإذا قمت بمضاعفة كل خسارة، فهناك احتمال نفاد المال. لذا تذكر الحقيقة:\n" +
                            "   - إذا كان المعامل في الإشارة 5 أو أقل، يمكنك مضاعفة الرهان في كل خسارة.\n" +
                            "   - إذا كان المعامل بين 5 و 10، يمكنك تحمل 2-3 رهانات خاسرة في رهان واحد ومضاعفة الرهان،\n" +
                            "     وتكرار نفس الشيء لرهانات خاسرة أخرى حتى تفوز.\n" +
                            "   - إذا كان المعامل أكبر من 10، العب برهان صغير، لأن هذه الرهانات يمكن أن تحقق ربحًا يتراوح بين 20-60 ضعف الرهان. 😉\n\n" +
                            "   جربه اليوم وشاهد كيف ينمو رأس مالك مع البوت الخاص بنا! 💹";
                }
                break;
        }


        InlineKeyboardMarkup inlineKeyboardMarkup;
        if(user.getIsVerify()){
            inlineKeyboardMarkup = LuckyJetKeyboard.mainForInstructionIfUserIsRegistered(oneWinUrl,user.getLanguage());
        }
        else {
            inlineKeyboardMarkup = LuckyJetKeyboard.mainForInstructionIfUserIsNotRegistered(oneWinUrl,user.getLanguage());
        }
        botActions.sendMessageWithInlineKeyboardAndParseMARKDOWN(chatId, answer, inlineKeyboardMarkup);
        botActions.handleCallbackQuery(callbackQuery);
    }
}