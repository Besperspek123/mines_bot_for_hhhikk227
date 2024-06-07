package com.example.tgbot.service.main.aviator;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.AviatorKeyboard;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class AviatorInstructionCommand implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private UserService userService;

    public AviatorInstructionCommand(BotActions botActions, UrlService urlService, PromoService promoService, UserService userService) {
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
            botActions.sendPhoto(chatId,"/imagesrussian/aviator/aviator_logo.jpg");
        }
        else {
            botActions.sendPhoto(chatId,"/imagesenglish/aviator/aviator_logo.jpg");
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
                            "🟢 1. Перейти в раздел 1win games и выбрать игру ✈ 'Aviator'.\n\n" +
                            "🟢 2. Выставить минимальную ставку. Это важно для управления риском!\n\n" +
                            "🟢 3. Запросить сигнал в боте и ставить по сигналам из бота.\n\n" +
                            "🟢 4. При неудачном сигнале советуем удвоить (Х²) ставку, чтобы полностью перекрыть потерю при следующем сигнале. \n\n" +
                            "⚠Важный нюанс: удваивать ставку подходит не всегда. Бывают прогнозы с высоким коэффициентом, и если удваивать каждый проигрыш, есть шанс остаться без денег. Так что запомните истину:\n" +
                            "- Если коэффициент в сигнале 5 или меньше, вы можете удваивать ставку каждый проигрыш.\n" +
                            "- Если коэффициент от 5 до 10, вы можете делать 2-3 проигрышных ставки по одной ставке и удваивать ставку,\n" +
                            "и делать соответственно так же 2-3 проигрышных ставки и так пока не получится выиграть.\n" +
                            "- Если коэффициент больше 10, играйте по одной небольшой ставке, так как в таких ставках выигрыш может быть от 20-60х от ставки. 😉\n\n" +
                            "Попробуйте сегодня и увидьте, как ваш капитал растет с помощью нашего бота! 💹";
                } else {
                    answer = "*Бот основан и обучен на кластере нейросети bitsGap🧠*\n\n" +
                            "Для тренировки бота было сыграно более 10 000 игр 🎰\n" +
                            "В данный момент пользователи бота успешно делают в день 15-25% от своего капитала! 💰\n\n" +
                            "На текущий момент бот по сей день проходит проверки и исправления! Точность бота составляет 87%!\n\n" +
                            "Для получения максимального профита следуйте следующей инструкции:\n\n" +
                            "🟢 1. Зарегистрировать аккаунт через нашего бота, по кнопке 'Зарегистрироваться' под сообщением, без этого вы не сможете получить доступ к сигналам.\n\n" +
                            "🟢 2. Пополнить баланс своего аккаунта.\n\n" +
                            "🟢 3. Перейти в раздел 1win games и выбрать игру ✈ 'Aviator'.\n\n" +
                            "🟢 4. Выставить минимальную ставку. Это важно для управления риском!\n\n" +
                            "🟢 5. Запросить сигнал в боте и ставить по сигналам из бота.\n\n" +
                            "🟢 6. При неудачном сигнале советуем удвоить (Х²) ставку, чтобы полностью перекрыть потерю при следующем сигнале. \n\n" +
                            "⚠Важный нюанс: удваивать ставку подходит не всегда. Бывают прогнозы с высоким коэффициентом, и если удваивать каждый проигрыш, есть шанс остаться без денег. Так что запомните истину:\n" +
                            "- Если коэффициент в сигнале 5 или меньше, вы можете удваивать ставку каждый проигрыш.\n" +
                            "- Если коэффициент от 5 до 10, вы можете делать 2-3 проигрышных ставки по одной ставке и удваивать ставку,\n" +
                            "и делать соответственно так же 2-3 проигрышных ставки и так пока не получится выиграть.\n" +
                            "- Если коэффициент больше 10, играйте по одной небольшой ставке, так как в таких ставках выигрыш может быть от 20-60х от ставки. 😉\n\n" +
                            "Попробуйте сегодня и увидьте, как ваш капитал растет с помощью нашего бота! 💹";
                }
                break;
            case "english":
                if (user.getIsVerify()) {
                    answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                            "The bot has been trained with more than 10,000 games 🎰\n" +
                            "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                            "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                            "To maximize your profits, follow these instructions:\n\n" +
                            "🟢 1. Go to the 1win games section and select the game ✈ 'Aviator'.\n\n" +
                            "🟢 2. Set the minimum bet. This is important for risk management!\n\n" +
                            "🟢 3. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                            "🟢 4. If a signal fails, we recommend doubling (X²) the bet to completely cover the loss in the next signal. \n\n" +
                            "⚠Important note: Doubling the bet is not always suitable. There are predictions with high coefficients, and if you double each loss, there's a chance of running out of money. So remember the truth:\n" +
                            "- If the coefficient in the signal is 5 or less, you can double the bet on each loss.\n" +
                            "- If the coefficient is between 5 and 10, you can afford 2-3 losing bets at one bet and double the bet,\n" +
                            "and do the same for another 2-3 losing bets until you win.\n" +
                            "- If the coefficient is more than 10, play with a small bet, as such bets can yield a win from 20-60x the bet. 😉\n\n" +
                            "Try it today and see how your capital grows with our bot! 💹";
                } else {
                    answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                            "The bot has been trained with more than 10,000 games 🎰\n" +
                            "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                            "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                            "To maximize your profits, follow these instructions:\n\n" +
                            "🟢 1. Register an account through our bot by clicking the 'Register' button under the message, without this you will not be able to access the signals.\n\n" +
                            "🟢 2. Top up your account balance.\n\n" +
                            "🟢 3. Go to the 1win games section and select the game ✈ 'Aviator'.\n\n" +
                            "🟢 4. Set the minimum bet. This is important for risk management!\n\n" +
                            "🟢 5. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                            "🟢 6. If a signal fails, we recommend doubling (X²) the bet to completely cover the loss in the next signal. \n\n" +
                            "⚠Important note: Doubling the bet is not always suitable. There are predictions with high coefficients, and if you double each loss, there's a chance of running out of money. So remember the truth:\n" +
                            "- If the coefficient in the signal is 5 or less, you can double the bet on each loss.\n" +
                            "- If the coefficient is between 5 and 10, you can afford 2-3 losing bets at one bet and double the bet,\n" +
                            "and do the same for another 2-3 losing bets until you win.\n" +
                            "- If the coefficient is more than 10, play with a small bet, as such bets can yield a win from 20-60x the bet. 😉\n\n" +
                            "Try it today and see how your capital grows with our bot! 💹";
                }
                break;
            case "hindi":
                if (user.getIsVerify()) {
                    answer = "*बॉट बिट्सगैप न्यूरल नेटवर्क क्लस्टर पर आधारित और प्रशिक्षित है🧠*\n\n" +
                            "बॉट को प्रशिक्षित करने के लिए 10,000 से अधिक खेल खेले गए हैं 🎰\n" +
                            "वर्तमान में, बॉट उपयोगकर्ता प्रतिदिन अपने पूंजी का 15-25% सफलतापूर्वक कमाते हैं! 💰\n\n" +
                            "इस समय, बॉट अभी भी जाँच और समायोजन के दौर से गुजर रहा है! बॉट की सटीकता 87% है!\n\n" +
                            "अपने मुनाफे को अधिकतम करने के लिए, इन निर्देशों का पालन करें:\n\n" +
                            "🟢 1. 1win गेम्स अनुभाग में जाएं और 'Aviator' खेल चुनें।\n\n" +
                            "🟢 2. न्यूनतम दांव सेट करें। यह जोखिम प्रबंधन के लिए महत्वपूर्ण है!\n\n" +
                            "🟢 3. बॉट में संकेत का अनुरोध करें और बॉट के संकेतों के आधार पर दांव लगाएं।\n\n" +
                            "🟢 4. यदि कोई संकेत विफल हो जाता है, तो हम अगले संकेत में पूरी तरह से नुकसान को कवर करने के लिए दांव को दोगुना (X²) करने की सलाह देते हैं।\n\n" +
                            "⚠महत्वपूर्ण नोट: दांव को दोगुना करना हमेशा उपयुक्त नहीं होता है। उच्च गुणांक वाले पूर्वानुमान होते हैं, और यदि आप प्रत्येक हार पर दांव को दोगुना करते हैं, तो पैसे खत्म होने का एक मौका होता है। इसलिए सत्य को याद रखें:\n" +
                            "- यदि संकेत में गुणांक 5 या उससे कम है, तो आप प्रत्येक हार पर दांव को दोगुना कर सकते हैं।\n" +
                            "- यदि गुणांक 5 से 10 के बीच है, तो आप एक दांव पर 2-3 हार वाले दांव कर सकते हैं और दांव को दोगुना कर सकते हैं,\n" +
                            "और तब तक 2-3 हार वाले दांव कर सकते हैं जब तक आप जीत नहीं जाते।\n" +
                            "- यदि गुणांक 10 से अधिक है, तो एक छोटे दांव के साथ खेलें, क्योंकि ऐसे दांवों में जीत का गुणांक 20-60x हो सकता है। 😉\n\n" +
                            "आज ही आजमाएं और देखें कि आपका पूंजी हमारे बॉट की मदद से कैसे बढ़ता है! 💹";
                } else {
                    answer = "*बॉट बिट्सगैप न्यूरल नेटवर्क क्लस्टर पर आधारित और प्रशिक्षित है🧠*\n\n" +
                            "बॉट को प्रशिक्षित करने के लिए 10,000 से अधिक खेल खेले गए हैं 🎰\n" +
                            "वर्तमान में, बॉट उपयोगकर्ता प्रतिदिन अपने पूंजी का 15-25% सफलतापूर्वक कमाते हैं! 💰\n\n" +
                            "इस समय, बॉट अभी भी जाँच और समायोजन के दौर से गुजर रहा है! बॉट की सटीकता 87% है!\n\n" +
                            "अपने मुनाफे को अधिकतम करने के लिए, इन निर्देशों का पालन करें:\n\n" +
                            "🟢 1. हमारे बॉट के माध्यम से एक खाता पंजीकृत करें, संदेश के नीचे 'रजिस्टर करें' बटन पर क्लिक करके, इसके बिना आप संकेतों तक पहुँच प्राप्त नहीं कर पाएंगे।\n\n" +
                            "🟢 2. अपने खाते का बैलेंस टॉप अप करें।\n\n" +
                            "🟢 3. 1win गेम्स अनुभाग में जाएं और 'Aviator' खेल चुनें।\n\n" +
                            "🟢 4. न्यूनतम दांव सेट करें। यह जोखिम प्रबंधन के लिए महत्वपूर्ण है!\n\n" +
                            "🟢 5. बॉट में संकेत का अनुरोध करें और बॉट के संकेतों के आधार पर दांव लगाएं।\n\n" +
                            "🟢 6. यदि कोई संकेत विफल हो जाता है, तो हम अगले संकेत में पूरी तरह से नुकसान को कवर करने के लिए दांव को दोगुना (X²) करने की सलाह देते हैं।\n\n" +
                            "⚠महत्वपूर्ण नोट: दांव को दोगुना करना हमेशा उपयुक्त नहीं होता है। उच्च गुणांक वाले पूर्वानुमान होते हैं, और यदि आप प्रत्येक हार पर दांव को दोगुना करते हैं, तो पैसे खत्म होने का एक मौका होता है। इसलिए सत्य को याद रखें:\n" +
                            "- यदि संकेत में गुणांक 5 या उससे कम है, तो आप प्रत्येक हार पर दांव को दोगुना कर सकते हैं।\n" +
                            "- यदि गुणांक 5 से 10 के बीच है, तो आप एक दांव पर 2-3 हार वाले दांव कर सकते हैं और दांव को दोगुना कर सकते हैं,\n" +
                            "और तब तक 2-3 हार वाले दांव कर सकते हैं जब तक आप जीत नहीं जाते।\n" +
                            "- यदि गुणांक 10 से अधिक है, तो एक छोटे दांव के साथ खेलें, क्योंकि ऐसे दांवों में जीत का गुणांक 20-60x हो सकता है। 😉\n\n" +
                            "आज ही आजमाएं और देखें कि आपका पूंजी हमारे बॉट की मदद से कैसे बढ़ता है! 💹";
                }
                break;
            case "brazilian":
                if (user.getIsVerify()) {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot ganham 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Vá para a seção de jogos 1win e selecione o jogo ✈ 'Aviator'.\n\n" +
                            "🟢 2. Defina a aposta mínima. Isso é importante para o gerenciamento de risco!\n\n" +
                            "🟢 3. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 4. Se um sinal falhar, recomendamos dobrar a aposta (X²) para cobrir completamente a perda no próximo sinal.\n\n" +
                            "⚠Nota importante: Dobrar a aposta nem sempre é adequado. Há previsões com altos coeficientes, e se você dobrar cada perda, há uma chance de ficar sem dinheiro. Então lembre-se da verdade:\n" +
                            "- Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "- Se o coeficiente estiver entre 5 e 10, você pode fazer 2-3 apostas perdedoras em uma aposta e dobrar a aposta,\n" +
                            "e fazer o mesmo por mais 2-3 apostas perdedoras até ganhar.\n" +
                            "- Se o coeficiente for maior que 10, jogue com uma pequena aposta, pois essas apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                            "Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                } else {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot ganham 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Registre uma conta através do nosso bot clicando no botão 'Registrar' abaixo da mensagem, sem isso você não poderá acessar os sinais.\n\n" +
                            "🟢 2. Recarregue o saldo da sua conta.\n\n" +
                            "🟢 3. Vá para a seção de jogos 1win e selecione o jogo ✈ 'Aviator'.\n\n" +
                            "🟢 4. Defina a aposta mínima. Isso é importante para o gerenciamento de risco!\n\n" +
                            "🟢 5. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 6. Se um sinal falhar, recomendamos dobrar a aposta (X²) para cobrir completamente a perda no próximo sinal.\n\n" +
                            "⚠Nota importante: Dobrar a aposta nem sempre é adequado. Há previsões com altos coeficientes, e se você dobrar cada perda, há uma chance de ficar sem dinheiro. Então lembre-se da verdade:\n" +
                            "- Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "- Se o coeficiente estiver entre 5 e 10, você pode fazer 2-3 apostas perdedoras em uma aposta e dobrar a aposta,\n" +
                            "e fazer o mesmo por mais 2-3 apostas perdedoras até ganhar.\n" +
                            "- Se o coeficiente for maior que 10, jogue com uma pequena aposta, pois essas apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                            "Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                }
                break;
            case "spanish":
                if (user.getIsVerify()) {
                    answer = "*El bot está basado y entrenado en el clúster de redes neuronales de bitsGap🧠*\n\n" +
                            "El bot ha sido entrenado con más de 10,000 juegos 🎰\n" +
                            "Actualmente, los usuarios del bot ganan con éxito el 15-25% de su capital por día! 💰\n\n" +
                            "En este momento, el bot aún está pasando por verificaciones y ajustes! ¡La precisión del bot es del 87%!\n\n" +
                            "Para maximizar sus ganancias, siga estas instrucciones:\n\n" +
                            "🟢 1. Vaya a la sección de juegos de 1win y seleccione el juego ✈ 'Aviator'.\n\n" +
                            "🟢 2. Establezca la apuesta mínima. ¡Esto es importante para la gestión de riesgos!\n\n" +
                            "🟢 3. Solicite una señal en el bot y haga apuestas basadas en las señales del bot.\n\n" +
                            "🟢 4. Si una señal falla, recomendamos duplicar (X²) la apuesta para cubrir completamente la pérdida en la siguiente señal.\n\n" +
                            "⚠Nota importante: Duplicar la apuesta no siempre es adecuado. Hay predicciones con altos coeficientes, y si duplica cada pérdida, hay una posibilidad de quedarse sin dinero. Así que recuerde la verdad:\n" +
                            "- Si el coeficiente en la señal es 5 o menos, puede duplicar la apuesta en cada pérdida.\n" +
                            "- Si el coeficiente está entre 5 y 10, puede permitirse 2-3 apuestas perdedoras en una apuesta y duplicar la apuesta,\n" +
                            "y hacer lo mismo por otras 2-3 apuestas perdedoras hasta ganar.\n" +
                            "- Si el coeficiente es más de 10, juegue con una apuesta pequeña, ya que tales apuestas pueden rendir una ganancia de 20-60x la apuesta. 😉\n\n" +
                            "¡Pruébelo hoy y vea cómo crece su capital con nuestro bot! 💹";
                } else {
                    answer = "*El bot está basado y entrenado en el clúster de redes neuronales de bitsGap🧠*\n\n" +
                            "El bot ha sido entrenado con más de 10,000 juegos 🎰\n" +
                            "Actualmente, los usuarios del bot ganan con éxito el 15-25% de su capital por día! 💰\n\n" +
                            "En este momento, el bot aún está pasando por verificaciones y ajustes! ¡La precisión del bot es del 87%!\n\n" +
                            "Para maximizar sus ganancias, siga estas instrucciones:\n\n" +
                            "🟢 1. Registre una cuenta a través de nuestro bot haciendo clic en el botón 'Registrar' debajo del mensaje, sin esto no podrá acceder a las señales.\n\n" +
                            "🟢 2. Recargue el saldo de su cuenta.\n\n" +
                            "🟢 3. Vaya a la sección de juegos de 1win y seleccione el juego ✈ 'Aviator'.\n\n" +
                            "🟢 4. Establezca la apuesta mínima. ¡Esto es importante para la gestión de riesgos!\n\n" +
                            "🟢 5. Solicite una señal en el bot y haga apuestas basadas en las señales del bot.\n\n" +
                            "🟢 6. Si una señal falla, recomendamos duplicar (X²) la apuesta para cubrir completamente la pérdida en la siguiente señal.\n\n" +
                            "⚠Nota importante: Duplicar la apuesta no siempre es adecuado. Hay predicciones con altos coeficientes, y si duplica cada pérdida, hay una posibilidad de quedarse sin dinero. Así que recuerde la verdad:\n" +
                            "- Si el coeficiente en la señal es 5 o menos, puede duplicar la apuesta en cada pérdida.\n" +
                            "- Si el coeficiente está entre 5 y 10, puede permitirse 2-3 apuestas perdedoras en una apuesta y duplicar la apuesta,\n" +
                            "y hacer lo mismo por otras 2-3 apuestas perdedoras hasta ganar.\n" +
                            "- Si el coeficiente es más de 10, juegue con una apuesta pequeña, ya que tales apuestas pueden rendir una ganancia de 20-60x la apuesta. 😉\n\n" +
                            "¡Pruébelo hoy y vea cómo crece su capital con nuestro bot! 💹";
                }
                break;
            case "uzbek":
                if (user.getIsVerify()) {
                    answer = "*Bot bitsGap neyron tarmoq klasteriga asoslangan va o'qitilgan🧠*\n\n" +
                            "Bot 10,000 dan ortiq o'yinlar bilan o'qitilgan 🎰\n" +
                            "Hozirda bot foydalanuvchilari har kuni kapitalining 15-25% ni muvaffaqiyatli qilib olishmoqda! 💰\n\n" +
                            "Hozirgi paytda bot hali ham tekshiruvlar va sozlamalar bosqichida! Botning aniqligi 87%!\n\n" +
                            "Foydangizni maksimal darajada oshirish uchun ushbu ko'rsatmalarga amal qiling:\n\n" +
                            "🟢 1. 1win o'yinlari bo'limiga o'ting va ✈ 'Aviator' o'yinini tanlang.\n\n" +
                            "🟢 2. Minimal tikishni qo'ying. Bu xavfni boshqarish uchun muhim!\n\n" +
                            "🟢 3. Botda signal so'rang va botning signallari asosida tikish qo'ying.\n\n" +
                            "🟢 4. Agar signal muvaffaqiyatsiz bo'lsa, keyingi signalda zararlarni to'liq qoplash uchun tikishni ikki barobar (X²) oshirishni tavsiya qilamiz.\n\n" +
                            "⚠Muhim eslatma: Tikishni ikki barobar oshirish har doim ham mos emas. Yuqori koeffitsientli prognozlar bor va har bir yutqazishda tikishni ikki barobar oshirsangiz, pulingiz tugashi ehtimoli bor. Shunday ekan, haqiqatni yodda tuting:\n" +
                            "- Agar signaldagi koeffitsient 5 yoki undan kam bo'lsa, har bir yutqazishda tikishni ikki barobar oshirishingiz mumkin.\n" +
                            "- Agar koeffitsient 5 dan 10 gacha bo'lsa, bir tikishda 2-3 marta yutqazish va tikishni ikki barobar oshirish imkoniyatiga ega bo'lishingiz mumkin,\n" +
                            "va yana 2-3 marta yutqazish va g'alaba qozongunga qadar shunday qilishingiz mumkin.\n" +
                            "- Agar koeffitsient 10 dan yuqori bo'lsa, kichik tikish bilan o'ynang, chunki bunday tikishlarda yutuq tikishdan 20-60x gacha bo'lishi mumkin. 😉\n\n" +
                            "Bugun sinab ko'ring va kapitalingiz botimiz yordamida qanday oshayotganini ko'ring! 💹";
                } else {
                    answer = "*Bot bitsGap neyron tarmoq klasteriga asoslangan va o'qitilgan🧠*\n\n" +
                            "Bot 10,000 dan ortiq o'yinlar bilan o'qitilgan 🎰\n" +
                            "Hozirda bot foydalanuvchilari har kuni kapitalining 15-25% ni muvaffaqiyatli qilib olishmoqda! 💰\n\n" +
                            "Hozirgi paytda bot hali ham tekshiruvlar va sozlamalar bosqichida! Botning aniqligi 87%!\n\n" +
                            "Foydangizni maksimal darajada oshirish uchun ushbu ko'rsatmalarga amal qiling:\n\n" +
                            "🟢 1. Xabar ostidagi 'Ro'yxatdan o'tish' tugmasini bosish orqali bot orqali hisob oching, aks holda signallarga kirish imkoni bo'lmaydi.\n\n" +
                            "🟢 2. Hisob balansingizni to'ldiring.\n\n" +
                            "🟢 3. 1win o'yinlari bo'limiga o'ting va ✈ 'Aviator' o'yinini tanlang.\n\n" +
                            "🟢 4. Minimal tikishni qo'ying. Bu xavfni boshqarish uchun muhim!\n\n" +
                            "🟢 5. Botda signal so'rang va botning signallari asosida tikish qo'ying.\n\n" +
                            "🟢 6. Agar signal muvaffaqiyatsiz bo'lsa, keyingi signalda zararlarni to'liq qoplash uchun tikishni ikki barobar (X²) oshirishni tavsiya qilamiz.\n\n" +
                            "⚠Muhim eslatma: Tikishni ikki barobar oshirish har doim ham mos emas. Yuqori koeffitsientli prognozlar bor va har bir yutqazishda tikishni ikki barobar oshirsangiz, pulingiz tugashi ehtimoli bor. Shunday ekan, haqiqatni yodda tuting:\n" +
                            "- Agar signaldagi koeffitsient 5 yoki undan kam bo'lsa, har bir yutqazishda tikishni ikki barobar oshirishingiz mumkin.\n" +
                            "- Agar koeffitsient 5 dan 10 gacha bo'lsa, bir tikishda 2-3 marta yutqazish va tikishni ikki barobar oshirish imkoniyatiga ega bo'lishingiz mumkin,\n" +
                            "va yana 2-3 marta yutqazish va g'alaba qozongunga qadar shunday qilishingiz mumkin.\n" +
                            "- Agar koeffitsient 10 dan yuqori bo'lsa, kichik tikish bilan o'ynang, chunki bunday tikishlarda yutuq tikishdan 20-60x gacha bo'lishi mumkin. 😉\n\n" +
                            "Bugun sinab ko'ring va kapitalingiz botimiz yordamida qanday oshayotganini ko'ring! 💹";
                }
                break;
            case "azerbaijani":
                if (user.getIsVerify()) {
                    answer = "*Bot bitsGap neyron şəbəkə klasterinə əsaslanır və təlim keçmişdir🧠*\n\n" +
                            "Bot 10.000-dən çox oyun ilə təlim keçmişdir 🎰\n" +
                            "Hazırda bot istifadəçiləri gündəlik kapitalının 15-25%-ni uğurla qazanırlar! 💰\n\n" +
                            "Hazırda bot hələ də yoxlamalar və düzəlişlər mərhələsindədir! Botun dəqiqliyi 87%-dir!\n\n" +
                            "Gəlirinizi maksimum artırmaq üçün bu təlimatlara əməl edin:\n\n" +
                            "🟢 1. 1win oyunları bölməsinə keçin və ✈ 'Aviator' oyununu seçin.\n\n" +
                            "🟢 2. Minimum mərc təyin edin. Bu, risk idarəçiliyi üçün vacibdir!\n\n" +
                            "🟢 3. Botda siqnal tələb edin və botun siqnalları əsasında mərc edin.\n\n" +
                            "🟢 4. Siqnal uğursuz olarsa, növbəti siqnalda zərəri tam örtmək üçün mərcinizi iki dəfə (X²) artırmağı tövsiyə edirik.\n\n" +
                            "⚠Vacib qeyd: Mərcinizi iki dəfə artırmaq həmişə uyğun deyil. Yüksək koeffisiyentli proqnozlar var və hər itkidə mərcinizi iki dəfə artırırsınızsa, pulunuz tükənə bilər. Beləliklə, həqiqəti yadda saxlayın:\n" +
                            "- Siqnalda koeffisiyent 5 və ya daha azdırsa, hər itkidə mərcinizi iki dəfə artıra bilərsiniz.\n" +
                            "- Koeffisiyent 5-dən 10-a qədərdirsə, bir mərcdə 2-3 məğlub mərc edə bilərsiniz və mərcinizi iki dəfə artıra bilərsiniz,\n" +
                            "və başqa 2-3 məğlub mərc edərək qələbə qazanana qədər belə edə bilərsiniz.\n" +
                            "- Koeffisiyent 10-dan çoxdursa, kiçik bir mərc ilə oynayın, çünki belə mərclərdə qazanc mərcdən 20-60x ola bilər. 😉\n\n" +
                            "Bu gün sınayın və kapitalınızın botumuzun köməyi ilə necə artdığını görün! 💹";
                } else {
                    answer = "*Bot bitsGap neyron şəbəkə klasterinə əsaslanır və təlim keçmişdir🧠*\n\n" +
                            "Bot 10.000-dən çox oyun ilə təlim keçmişdir 🎰\n" +
                            "Hazırda bot istifadəçiləri gündəlik kapitalının 15-25%-ni uğurla qazanırlar! 💰\n\n" +
                            "Hazırda bot hələ də yoxlamalar və düzəlişlər mərhələsindədir! Botun dəqiqliyi 87%-dir!\n\n" +
                            "Gəlirinizi maksimum artırmaq üçün bu təlimatlara əməl edin:\n\n" +
                            "🟢 1. Mesajın altında 'Qeydiyyatdan keç' düyməsinə klikləməklə botumuz vasitəsilə bir hesab qeydiyyatdan keçirin, əks halda siqnallara daxil ola bilməyəcəksiniz.\n\n" +
                            "🟢 2. Hesab balansınızı artırın.\n\n" +
                            "🟢 3. 1win oyunları bölməsinə keçin və ✈ 'Aviator' oyununu seçin.\n\n" +
                            "🟢 4. Minimum mərc təyin edin. Bu, risk idarəçiliyi üçün vacibdir!\n\n" +
                            "🟢 5. Botda siqnal tələb edin və botun siqnalları əsasında mərc edin.\n\n" +
                            "🟢 6. Siqnal uğursuz olarsa, növbəti siqnalda zərəri tam örtmək üçün mərcinizi iki dəfə (X²) artırmağı tövsiyə edirik.\n\n" +
                            "⚠Vacib qeyd: Mərcinizi iki dəfə artırmaq həmişə uyğun deyil. Yüksək koeffisiyentli proqnozlar var və hər itkidə mərcinizi iki dəfə artırırsınızsa, pulunuz tükənə bilər. Beləliklə, həqiqəti yadda saxlayın:\n" +
                            "- Siqnalda koeffisiyent 5 və ya daha azdırsa, hər itkidə mərcinizi iki dəfə artıra bilərsiniz.\n" +
                            "- Koeffisiyent 5-dən 10-a qədərdirsə, bir mərcdə 2-3 məğlub mərc edə bilərsiniz və mərcinizi iki dəfə artıra bilərsiniz,\n" +
                            "və başqa 2-3 məğlub mərc edərək qələbə qazanana qədər belə edə bilərsiniz.\n" +
                            "- Koeffisiyent 10-dan çoxdursa, kiçik bir mərc ilə oynayın, çünki belə mərclərdə qazanc mərcdən 20-60x ola bilər. 😉\n\n" +
                            "Bu gün sınayın və kapitalınızın botumuzun köməyi ilə necə artdığını görün! 💹";
                }
                break;
            case "turkish":
                if (user.getIsVerify()) {
                    answer = "*Bot, bitsGap sinir ağı kümesinde kurulmuş ve eğitilmiştir🧠*\n\n" +
                            "Bot, 10.000'den fazla oyunla eğitildi 🎰\n" +
                            "Şu anda, bot kullanıcıları günlük sermayelerinin %15-25'ini başarıyla kazanıyor! 💰\n\n" +
                            "Şu anda, bot hala kontroller ve ayarlamalar aşamasındadır! Botun doğruluğu %87!\n\n" +
                            "Karınızı en üst düzeye çıkarmak için bu talimatları izleyin:\n\n" +
                            "🟢 1. 1win oyunları bölümüne gidin ve ✈ 'Aviator' oyununu seçin.\n\n" +
                            "🟢 2. Minimum bahsi ayarlayın. Bu, risk yönetimi için önemlidir!\n\n" +
                            "🟢 3. Botta bir sinyal isteyin ve botun sinyallerine göre bahis yapın.\n\n" +
                            "🟢 4. Bir sinyal başarısız olursa, bir sonraki sinyalde kaybı tamamen karşılamak için bahsi iki katına çıkarmanızı (X²) öneririz.\n\n" +
                            "⚠Önemli not: Bahsi iki katına çıkarmak her zaman uygun değildir. Yüksek katsayılı tahminler vardır ve her kayıpta bahsi iki katına çıkarırsanız, paranız tükenme olasılığı vardır. Bu yüzden gerçeği hatırlayın:\n" +
                            "- Sinyaldeki katsayı 5 veya daha azsa, her kayıpta bahsi iki katına çıkarabilirsiniz.\n" +
                            "- Katsayı 5 ile 10 arasında ise, bir bahiste 2-3 kayıp bahis yapabilir ve bahsi iki katına çıkarabilirsiniz,\n" +
                            "ve 2-3 kayıp bahisten sonra kazanana kadar aynı şeyi yapabilirsiniz.\n" +
                            "- Katsayı 10'dan büyükse, küçük bir bahisle oynayın, çünkü bu tür bahislerde kazanç bahisten 20-60x olabilir. 😉\n\n" +
                            "Bugün deneyin ve sermayenizin botumuzla nasıl büyüdüğünü görün! 💹";
                } else {
                    answer = "*Bot, bitsGap sinir ağı kümesinde kurulmuş ve eğitilmiştir🧠*\n\n" +
                            "Bot, 10.000'den fazla oyunla eğitildi 🎰\n" +
                            "Şu anda, bot kullanıcıları günlük sermayelerinin %15-25'ini başarıyla kazanıyor! 💰\n\n" +
                            "Şu anda, bot hala kontroller ve ayarlamalar aşamasındadır! Botun doğruluğu %87!\n\n" +
                            "Karınızı en üst düzeye çıkarmak için bu talimatları izleyin:\n\n" +
                            "🟢 1. Mesajın altındaki 'Kaydol' düğmesine tıklayarak botumuz aracılığıyla bir hesap kaydedin, aksi takdirde sinyallere erişemezsiniz.\n\n" +
                            "🟢 2. Hesap bakiyenizi doldurun.\n\n" +
                            "🟢 3. 1win oyunları bölümüne gidin ve ✈ 'Aviator' oyununu seçin.\n\n" +
                            "🟢 4. Minimum bahsi ayarlayın. Bu, risk yönetimi için önemlidir!\n\n" +
                            "🟢 5. Botta bir sinyal isteyin ve botun sinyallerine göre bahis yapın.\n\n" +
                            "🟢 6. Bir sinyal başarısız olursa, bir sonraki sinyalde kaybı tamamen karşılamak için bahsi iki katına çıkarmanızı (X²) öneririz.\n\n" +
                            "⚠Önemli not: Bahsi iki katına çıkarmak her zaman uygun değildir. Yüksek katsayılı tahminler vardır ve her kayıpta bahsi iki katına çıkarırsanız, paranız tükenme olasılığı vardır. Bu yüzden gerçeği hatırlayın:\n" +
                            "- Sinyaldeki katsayı 5 veya daha azsa, her kayıpta bahsi iki katına çıkarabilirsiniz.\n" +
                            "- Katsayı 5 ile 10 arasında ise, bir bahiste 2-3 kayıp bahis yapabilir ve bahsi iki katına çıkarabilirsiniz,\n" +
                            "ve 2-3 kayıp bahisten sonra kazanana kadar aynı şeyi yapabilirsiniz.\n" +
                            "- Katsayı 10'dan büyükse, küçük bir bahisle oynayın, çünkü bu tür bahislerde kazanç bahisten 20-60x olabilir. 😉\n\n" +
                            "Bugün deneyin ve sermayenizin botumuzla nasıl büyüdüğünü görün! 💹";
                }
                break;
            case "portuguese":
                if (user.getIsVerify()) {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot ganham 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Vá para a seção de jogos 1win e selecione o jogo ✈ 'Aviator'.\n\n" +
                            "🟢 2. Defina a aposta mínima. Isso é importante para o gerenciamento de risco!\n\n" +
                            "🟢 3. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 4. Se um sinal falhar, recomendamos dobrar a aposta (X²) para cobrir completamente a perda no próximo sinal.\n\n" +
                            "⚠Nota importante: Dobrar a aposta nem sempre é adequado. Há previsões com altos coeficientes, e se você dobrar cada perda, há uma chance de ficar sem dinheiro. Então lembre-se da verdade:\n" +
                            "- Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "- Se o coeficiente estiver entre 5 e 10, você pode fazer 2-3 apostas perdedoras em uma aposta e dobrar a aposta,\n" +
                            "e fazer o mesmo por mais 2-3 apostas perdedoras até ganhar.\n" +
                            "- Se o coeficiente for maior que 10, jogue com uma pequena aposta, pois essas apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                            "Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                } else {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "O bot foi treinado com mais de 10.000 jogos 🎰\n" +
                            "Atualmente, os usuários do bot ganham 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Registre uma conta através do nosso bot clicando no botão 'Registrar' abaixo da mensagem, sem isso você não poderá acessar os sinais.\n\n" +
                            "🟢 2. Recarregue o saldo da sua conta.\n\n" +
                            "🟢 3. Vá para a seção de jogos 1win e selecione o jogo ✈ 'Aviator'.\n\n" +
                            "🟢 4. Defina a aposta mínima. Isso é importante para o gerenciamento de risco!\n\n" +
                            "🟢 5. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 6. Se um sinal falhar, recomendamos dobrar a aposta (X²) para cobrir completamente a perda no próximo sinal.\n\n" +
                            "⚠Nota importante: Dobrar a aposta nem sempre é adequado. Há previsões com altos coeficientes, e se você dobrar cada perda, há uma chance de ficar sem dinheiro. Então lembre-se da verdade:\n" +
                            "- Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "- Se o coeficiente estiver entre 5 e 10, você pode fazer 2-3 apostas perdedoras em uma aposta e dobrar a aposta,\n" +
                            "e fazer o mesmo por mais 2-3 apostas perdedoras até ganhar.\n" +
                            "- Se o coeficiente for maior que 10, jogue com uma pequena aposta, pois essas apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                            "Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                }
                break;
            case "arabic":
                if (user.getIsVerify()) {
                    answer = "*البوت يعتمد على شبكة عصبية تدريبية bitsGap🧠*\n\n" +
                            "تم تدريب البوت على أكثر من 10,000 لعبة 🎰\n" +
                            "حاليًا، يحقق مستخدمو البوت بنجاح 15-25% من رأس مالهم يوميًا! 💰\n\n" +
                            "في الوقت الحالي، لا يزال البوت يخضع لفحوصات وتعديلات! دقة البوت هي 87%!\n\n" +
                            "لزيادة أرباحك إلى أقصى حد، اتبع هذه التعليمات:\n\n" +
                            "🟢 1. انتقل إلى قسم ألعاب 1win واختر اللعبة ✈ 'Aviator'.\n\n" +
                            "🟢 2. ضع الحد الأدنى للرهان. هذا مهم لإدارة المخاطر!\n\n" +
                            "🟢 3. اطلب إشارة في البوت وضع الرهانات بناءً على إشارات البوت.\n\n" +
                            "🟢 4. إذا فشلت الإشارة، نوصي بمضاعفة (X²) الرهان لتغطية الخسارة بالكامل في الإشارة التالية.\n\n" +
                            "⚠ملاحظة هامة: مضاعفة الرهان ليست دائمًا مناسبة. هناك توقعات بمعاملات عالية، وإذا ضاعفت كل خسارة، فهناك فرصة نفاد الأموال. لذا تذكر الحقيقة:\n" +
                            "- إذا كان المعامل في الإشارة 5 أو أقل، يمكنك مضاعفة الرهان في كل خسارة.\n" +
                            "- إذا كان المعامل بين 5 و 10، يمكنك تحمل 2-3 رهانات خاسرة في رهان واحد ومضاعفة الرهان،\n" +
                            "والقيام بنفس الشيء ل 2-3 رهانات خاسرة أخرى حتى تفوز.\n" +
                            "- إذا كان المعامل أكبر من 10، العب برهان صغير، حيث يمكن أن تكون هذه الرهانات مكسبًا من 20-60x الرهان. 😉\n\n" +
                            "جربه اليوم وشاهد كيف ينمو رأس مالك بمساعدة البوت الخاص بنا! 💹";
                } else {
                    answer = "*البوت يعتمد على شبكة عصبية تدريبية bitsGap🧠*\n\n" +
                            "تم تدريب البوت على أكثر من 10,000 لعبة 🎰\n" +
                            "حاليًا، يحقق مستخدمو البوت بنجاح 15-25% من رأس مالهم يوميًا! 💰\n\n" +
                            "في الوقت الحالي، لا يزال البوت يخضع لفحوصات وتعديلات! دقة البوت هي 87%!\n\n" +
                            "لزيادة أرباحك إلى أقصى حد، اتبع هذه التعليمات:\n\n" +
                            "🟢 1. سجل حسابًا من خلال البوت الخاص بنا بالنقر على زر 'تسجيل' أسفل الرسالة، بدون ذلك لن تتمكن من الوصول إلى الإشارات.\n\n" +
                            "🟢 2. قم بتعبئة رصيد حسابك.\n\n" +
                            "🟢 3. انتقل إلى قسم ألعاب 1win واختر اللعبة ✈ 'Aviator'.\n\n" +
                            "🟢 4. ضع الحد الأدنى للرهان. هذا مهم لإدارة المخاطر!\n\n" +
                            "🟢 5. اطلب إشارة في البوت وضع الرهانات بناءً على إشارات البوت.\n\n" +
                            "🟢 6. إذا فشلت الإشارة، نوصي بمضاعفة (X²) الرهان لتغطية الخسارة بالكامل في الإشارة التالية.\n\n" +
                            "⚠ملاحظة هامة: مضاعفة الرهان ليست دائمًا مناسبة. هناك توقعات بمعاملات عالية، وإذا ضاعفت كل خسارة، فهناك فرصة نفاد الأموال. لذا تذكر الحقيقة:\n" +
                            "- إذا كان المعامل في الإشارة 5 أو أقل، يمكنك مضاعفة الرهان في كل خسارة.\n" +
                            "- إذا كان المعامل بين 5 و 10، يمكنك تحمل 2-3 رهانات خاسرة في رهان واحد ومضاعفة الرهان،\n" +
                            "والقيام بنفس الشيء ل 2-3 رهانات خاسرة أخرى حتى تفوز.\n" +
                            "- إذا كان المعامل أكبر من 10، العب برهان صغير، حيث يمكن أن تكون هذه الرهانات مكسبًا من 20-60x الرهان. 😉\n\n" +
                            "جربه اليوم وشاهد كيف ينمو رأس مالك بمساعدة البوت الخاص بنا! 💹";
                }
                break;
        }


        InlineKeyboardMarkup inlineKeyboardMarkup;
        if(user.getIsVerify()){
            inlineKeyboardMarkup = AviatorKeyboard.mainForInstructionIfUserIsRegistered(oneWinUrl,user.getLanguage());
        }
        else {
            inlineKeyboardMarkup = AviatorKeyboard.mainForInstructionIfUserIsNotRegistered(oneWinUrl,user.getLanguage());
        }
        botActions.sendMessageWithInlineKeyboardAndParseMARKDOWN(chatId, answer, inlineKeyboardMarkup);
        botActions.handleCallbackQuery(callbackQuery);
    }
}