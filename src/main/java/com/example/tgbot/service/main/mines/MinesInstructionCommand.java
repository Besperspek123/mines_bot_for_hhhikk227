package com.example.tgbot.service.main.mines;

import com.example.tgbot.entity.User;
import com.example.tgbot.interfaces.BotActions;
import com.example.tgbot.interfaces.CommandWithCallback;
import com.example.tgbot.keyboard.games.MinesKeyboard;
import com.example.tgbot.service.PromoService;
import com.example.tgbot.service.UrlService;
import com.example.tgbot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class MinesInstructionCommand implements CommandWithCallback {
    private UrlService urlService;

    private PromoService promoService;
    private final BotActions botActions;
    private UserService userService;

    public MinesInstructionCommand(BotActions botActions, UrlService urlService, PromoService promoService, UserService userService) {
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
            botActions.sendPhoto(chatId, "/imagesrussian/mines/mines_logo.jpg");
        } else {
            botActions.sendPhoto(chatId, "/imagesenglish/mines/mines_logo.jpg");
        }
        String answer = "";
        if (userService.userIsRussian(user)) {
            if (user.getIsVerify()) {
                answer = "*Бот основан и обучен на кластере нейросети bitsGap🧠*\n\n" +
                        "Для тренировки бота было сыграно более 10 000 игр 🎰\n" +
                        "В данный момент пользователи бота успешно делают в день 15-25% от своего капитала! 💰\n\n" +
                        "На текущий момент бот по сей день проходит проверки и исправления! Точность бота составляет 87%!\n\n" +
                        "Для получения максимального профита следуйте следующей инструкции:\n\n" +
                        "🟢 1. Перейти в раздел 1win games и выбрать игру 💣 'MINES'.\n\n" +
                        "🟢 2. Выставить кол-во ловушек в размере трёх. Это важно!\n\n" +
                        "🟢 3. Запросить сигнал в боте и ставить по сигналам из бота.\n\n" +
                        "🟢 4. При неудачном сигнале советуем удвоить(Х²) ставку, чтобы полностью перекрыть потерю при следующем сигнале. \n\n" +
                        "   ⚠Важный нюанс: удваивать ставку подходит не всегда. Бывают прогнозы с большим количеством звездочек, и если удваивать каждый проигрыш, есть шанс остаться без денег. Так что запомните истину:\n" +
                        "   - Если звездочек в сигнале 5 или меньше, вы можете удваивать ставку каждый проигрыш.\n" +
                        "   - Если звездочек от 5 до 10, вы можете делать 2-3 проигрышных спина по одной ставке и удваивать ставку,\n" +
                        "     и делать соответственно так же 2-3 проигрышных спина и так пока не получится выиграть.\n" +
                        "   - Если звездочек больше 10, играйте по одной небольшой ставке, так как в таких ставках выигрыш может быть от 20-60х от ставки. 😉\n\n" +
                        "   Попробуйте сегодня и увидьте, как ваш капитал растет с помощью нашего бота! 💹";
            } else {
                answer = "*Бот основан и обучен на кластере нейросети bitsGap🧠*\n\n" +
                        "Для тренировки бота было сыграно более 10 000 игр 🎰\n" +
                        "В данный момент пользователи бота успешно делают в день 15-25% от своего капитала! 💰\n\n" +
                        "На текущий момент бот по сей день проходит проверки и исправления! Точность бота составляет 87%!\n\n" +
                        "Для получения максимального профита следуйте следующей инструкции:\n\n" +
                        "🟢 1. Зарегистировать аккаунт через нашего бота, по кнопке 'Зарегистрироваться' под сообщением, без " +
                        "этого вы не сможете получить доступ к сигналам.\n\n" +
                        "🟢 2. Пополнить баланс своего аккаунта.\n\n" +
                        "🟢 3. Перейти в раздел 1win games и выбрать игру 💣 'MINES'.\n\n" +
                        "🟢 4. Выставить кол-во ловушек в размере трёх. Это важно!\n\n" +
                        "🟢 5. Запросить сигнал в боте и ставить по сигналам из бота.\n\n" +
                        "🟢 6. При неудачном сигнале советуем удвоить(Х²) ставку, чтобы полностью перекрыть потерю при следующем сигнале. \n\n" +
                        "   ⚠Важный нюанс: удваивать ставку подходит не всегда. Бывают прогнозы с большим количеством звездочек, и если удваивать каждый проигрыш, есть шанс остаться без денег. Так что запомните истину:\n" +
                        "   - Если звездочек в сигнале 5 или меньше, вы можете удваивать ставку каждый проигрыш.\n" +
                        "   - Если звездочек от 5 до 10, вы можете делать 2-3 проигрышных спина по одной ставке и удваивать ставку,\n" +
                        "     и делать соответственно так же 2-3 проигрышных спина и так пока не получится выиграть.\n" +
                        "   - Если звездочек больше 10, играйте по одной небольшой ставке, так как в таких ставках выигрыш может быть от 20-60х от ставки. 😉\n\n" +
                        "   Попробуйте сегодня и увидьте, как ваш капитал растет с помощью нашего бота! 💹";

            }

        }
        if (userService.userIsEnglish(user)) {
            if (user.getIsVerify()) {
                answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                        "The bot has been trained with more than 10,000 games 🎰\n" +
                        "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                        "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                        "To maximize your profits, follow these instructions:\n\n" +
                        "🟢 1. Go to the 1win games section and select the game 💣 'MINES'.\n\n" +
                        "🟢 2. Set the number of traps to three. This is important!\n\n" +
                        "🟢 3. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                        "🟢 4. If a signal fails, we recommend doubling (X²) the bet to completely cover the loss in the next signal. \n\n" +
                        "   ⚠Important note: Doubling the bet is not always suitable. There are predictions with a large number of stars, and if you double each loss, there's a chance of running out of money. So remember the truth:\n" +
                        "   - If there are 5 stars or fewer in the signal, you can double the bet on each loss.\n" +
                        "   - If there are 5 to 10 stars, you can afford 2-3 losing spins at one bet and double the bet,\n" +
                        "     and do the same for another 2-3 losing spins until you win.\n" +
                        "   - If there are more than 10 stars, play with a small bet, as such bets can yield a win from 20-60x the bet. 😉\n\n" +
                        "   Try it today and see how your capital grows with our bot! 💹";
            } else {
                answer = "*The bot is based and trained on the neural network cluster bitsGap🧠*\n\n" +
                        "The bot has been trained with more than 10,000 games 🎰\n" +
                        "Currently, bot users successfully make 15-25% of their capital per day! 💰\n\n" +
                        "At the moment, the bot is still undergoing checks and adjustments! The accuracy of the bot is 87%!\n\n" +
                        "To maximize your profits, follow these instructions:\n\n" +
                        "🟢 1. Register an account through our bot by clicking the 'Register' button under the message, without this you will not be able to access the signals.\n\n" +
                        "🟢 2. Top up your account balance.\n\n" +
                        "🟢 3. Go to the 1win games section and select the game 💣 'MINES'.\n\n" +
                        "🟢 4. Set the number of traps to three. This is important!\n\n" +
                        "🟢 5. Request a signal in the bot and place bets based on the bot's signals.\n\n" +
                        "🟢 6. If a signal fails, we recommend doubling (X²) the bet to completely cover the loss in the next signal. \n\n" +
                        "   ⚠Important note: Doubling the bet is not always suitable. There are predictions with a large number of stars, and if you double each loss, there's a chance of running out of money. So remember the truth:\n" +
                        "   - If there are 5 stars or fewer in the signal, you can double the bet on each loss.\n" +
                        "   - If there are 5 to 10 stars, you can afford 2-3 losing spins at one bet and double the bet,\n" +
                        "     and do the same for another 2-3 losing spins until you win.\n" +
                        "   - If there are more than 10 stars, play with a small bet, as such bets can yield a win from 20-60x the bet. 😉\n\n" +
                        "   Try it today and see how your capital grows with our bot! 💹";
            }
        }
        if (userService.userIsHindi(user)) {
            if (user.getIsVerify()) {
                answer = "*बॉट BitsGap न्यूरल नेटवर्क क्लस्टर पर आधारित और प्रशिक्षित है🧠*\n\n" +
                        "बॉट को प्रशिक्षित करने के लिए 10,000 से अधिक खेल खेले गए 🎰\n" +
                        "वर्तमान में, बॉट उपयोगकर्ता अपने पूंजी का 15-25% प्रति दिन सफलतापूर्वक कमा रहे हैं! 💰\n\n" +
                        "इस समय, बॉट अभी भी जाँच और सुधार प्रक्रियाओं से गुजर रहा है! बॉट की सटीकता 87% है!\n\n" +
                        "अधिकतम लाभ प्राप्त करने के लिए, इन निर्देशों का पालन करें:\n\n" +
                        "🟢 1. 1win गेम्स सेक्शन में जाएं और खेल ✈ 'MINES' चुनें।\n\n" +
                        "🟢 2. जाल की संख्या तीन सेट करें। यह महत्वपूर्ण है!\n\n" +
                        "🟢 3. बॉट में संकेत का अनुरोध करें और बॉट के संकेतों के अनुसार दांव लगाएं।\n\n" +
                        "🟢 4. अगर संकेत असफल होता है, तो हम अगली बार पूरी हानि को कवर करने के लिए दांव को दुगुना (X²) करने की सलाह देते हैं।\n\n" +
                        "⚠ महत्वपूर्ण नोट: दांव को दुगुना करना हमेशा उपयुक्त नहीं होता। ऐसे पूर्वानुमान होते हैं जिनमें उच्च गुणक होता है, और यदि आप हर नुकसान पर दांव दुगुना करते हैं, तो धन समाप्त होने का खतरा होता है। तो सच्चाई को याद रखें:\n" +
                        "- यदि संकेत में गुणक 5 या उससे कम है, तो आप हर नुकसान पर दांव को दुगुना कर सकते हैं।\n" +
                        "- यदि गुणक 5 से 10 के बीच है, तो आप एक दांव पर 2-3 हारने वाले स्पिन कर सकते हैं और फिर दांव को दुगुना कर सकते हैं,\n" +
                        "और इसी प्रकार 2-3 हारने वाले स्पिन और करें जब तक जीत नहीं मिलती।\n" +
                        "- यदि गुणक 10 से अधिक है, तो छोटे दांव के साथ खेलें, क्योंकि ऐसे दांवों में जीत 20-60x दांव से हो सकती है। 😉\n\n" +
                        "आजमाएं और देखें कि आपका पूंजी हमारे बॉट के साथ कैसे बढ़ता है! 💹";
            } else {
                answer = "*बॉट BitsGap न्यूरल नेटवर्क क्लस्टर पर आधारित और प्रशिक्षित है🧠*\n\n" +
                        "बॉट को प्रशिक्षित करने के लिए 10,000 से अधिक खेल खेले गए 🎰\n" +
                        "वर्तमान में, बॉट उपयोगकर्ता अपने पूंजी का 15-25% प्रति दिन सफलतापूर्वक कमा रहे हैं! 💰\n\n" +
                        "इस समय, बॉट अभी भी जाँच और सुधार प्रक्रियाओं से गुजर रहा है! बॉट की सटीकता 87% है!\n\n" +
                        "अधिकतम लाभ प्राप्त करने के लिए, इन निर्देशों का पालन करें:\n\n" +
                        "🟢 1. हमारे बॉट के माध्यम से खाता पंजीकृत करें, संदेश के नीचे 'पंजीकरण करें' बटन पर क्लिक करें, इसके बिना आप संकेतों तक पहुंच प्राप्त नहीं कर सकते।\n\n" +
                        "🟢 2. अपने खाते का बैलेंस भरें।\n\n" +
                        "🟢 3. 1win गेम्स सेक्शन में जाएं और खेल ✈ 'MINES' चुनें।\n\n" +
                        "🟢 4. जाल की संख्या तीन सेट करें। यह महत्वपूर्ण है!\n\n" +
                        "🟢 5. बॉट में संकेत का अनुरोध करें और बॉट के संकेतों के अनुसार दांव लगाएं।\n\n" +
                        "🟢 6. अगर संकेत असफल होता है, तो हम अगली बार पूरी हानि को कवर करने के लिए दांव को दुगुना (X²) करने की सलाह देते हैं।\n\n" +
                        "⚠ महत्वपूर्ण नोट: दांव को दुगुना करना हमेशा उपयुक्त नहीं होता। ऐसे पूर्वानुमान होते हैं जिनमें उच्च गुणक होता है, और यदि आप हर नुकसान पर दांव दुगुना करते हैं, तो धन समाप्त होने का खतरा होता है। तो सच्चाई को याद रखें:\n" +
                        "- यदि संकेत में गुणक 5 या उससे कम है, तो आप हर नुकसान पर दांव को दुगुना कर सकते हैं।\n" +
                        "- यदि गुणक 5 से 10 के बीच है, तो आप एक दांव पर 2-3 हारने वाले स्पिन कर सकते हैं और फिर दांव को दुगुना कर सकते हैं,\n" +
                        "और इसी प्रकार 2-3 हारने वाले स्पिन और करें जब तक जीत नहीं मिलती।\n" +
                        "- यदि गुणक 10 से अधिक है, तो छोटे दांव के साथ खेलें, क्योंकि ऐसे दांवों में जीत 20-60x दांव से हो सकती है। 😉\n\n" +
                        "आजमाएं और देखें कि आपका पूंजी हमारे बॉट के साथ कैसे बढ़ता है! 💹";
            }
        }
        if (userService.userIsBrazilian(user)) {
            if (user.getIsVerify()) {
                answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                        "Para treinar o bot, mais de 10.000 jogos foram jogados 🎰\n" +
                        "Atualmente, os usuários do bot conseguem ganhar de 15-25% do seu capital por dia! 💰\n\n" +
                        "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                        "Para maximizar seus lucros, siga estas instruções:\n\n" +
                        "🟢 1. Vá para a seção de jogos 1win e selecione o jogo 💣 'MINES'.\n\n" +
                        "🟢 2. Defina o número de armadilhas como três. Isso é importante!\n\n" +
                        "🟢 3. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                        "🟢 4. Se um sinal falhar, recomendamos dobrar a aposta (X²) para cobrir completamente a perda no próximo sinal.\n\n" +
                        "⚠ Nota importante: dobrar a aposta nem sempre é adequado. Existem previsões com coeficientes altos e, se você dobrar cada perda, há a chance de ficar sem dinheiro. Portanto, lembre-se da verdade:\n" +
                        "- Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                        "- Se o coeficiente estiver entre 5 e 10, você pode fazer 2-3 giros perdidos com uma aposta e dobrar a aposta,\n" +
                        "e fazer o mesmo para outros 2-3 giros perdidos até ganhar.\n" +
                        "- Se o coeficiente for superior a 10, jogue com uma aposta pequena, pois tais apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                        "Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
            } else {
                answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                        "Para treinar o bot, mais de 10.000 jogos foram jogados 🎰\n" +
                        "Atualmente, os usuários do bot conseguem ganhar de 15-25% do seu capital por dia! 💰\n\n" +
                        "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                        "Para maximizar seus lucros, siga estas instruções:\n\n" +
                        "🟢 1. Registre uma conta através do nosso bot, clicando no botão 'Registrar' abaixo da mensagem, sem isso você não poderá acessar os sinais.\n\n" +
                        "🟢 2. Recarregue o saldo da sua conta.\n\n" +
                        "🟢 3. Vá para a seção de jogos 1win e selecione o jogo 💣 'MINES'.\n\n" +
                        "🟢 4. Defina o número de armadilhas como três. Isso é importante!\n\n" +
                        "🟢 5. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                        "🟢 6. Se um sinal falhar, recomendamos dobrar a aposta (X²) para cobrir completamente a perda no próximo sinal.\n\n" +
                        "⚠ Nota importante: dobrar a aposta nem sempre é adequado. Existem previsões com coeficientes altos e, se você dobrar cada perda, há a chance de ficar sem dinheiro. Portanto, lembre-se da verdade:\n" +
                        "- Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                        "- Se o coeficiente estiver entre 5 e 10, você pode fazer 2-3 giros perdidos com uma aposta e dobrar a aposta,\n" +
                        "e fazer o mesmo para outros 2-3 giros perdidos até ganhar.\n" +
                        "- Se o coeficiente for superior a 10, jogue com uma aposta pequena, pois tais apostas podem render um ganho de 20-60x a aposta. 😉\n\n" +
                        "Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
            }
        }
        if (userService.userIsSpanish(user)) {

            if (user.getIsVerify()) {
                answer = "*El bot está basado y entrenado en el clúster de redes neuronales de bitsGap🧠*\n\n" +
                        "Para entrenar al bot, se han jugado más de 10,000 juegos 🎰\n" +
                        "Actualmente, los usuarios del bot ganan con éxito entre el 15-25% de su capital por día! 💰\n\n" +
                        "En este momento, el bot todavía está siendo sometido a verificaciones y ajustes! La precisión del bot es del 87%!\n\n" +
                        "Para maximizar sus ganancias, siga estas instrucciones:\n\n" +
                        "🟢 1. Vaya a la sección de juegos de 1win y seleccione el juego 💣 'MINES'.\n\n" +
                        "🟢 2. Establezca el número de trampas en tres. ¡Esto es importante!\n\n" +
                        "🟢 3. Solicite una señal en el bot y haga apuestas basadas en las señales del bot.\n\n" +
                        "🟢 4. Si una señal falla, recomendamos doblar la apuesta (X²) para cubrir completamente la pérdida en la próxima señal.\n\n" +
                        "⚠ Nota importante: doblar la apuesta no siempre es adecuado. Hay predicciones con coeficientes altos y, si dobla cada pérdida, existe la posibilidad de quedarse sin dinero. Así que recuerde la verdad:\n" +
                        "- Si el coeficiente en la señal es 5 o menos, puede doblar la apuesta en cada pérdida.\n" +
                        "- Si el coeficiente está entre 5 y 10, puede permitirse 2-3 giros perdidos con una apuesta y doblar la apuesta,\n" +
                        "y hacer lo mismo para otros 2-3 giros perdidos hasta ganar.\n" +
                        "- Si el coeficiente es superior a 10, juegue con una apuesta pequeña, ya que tales apuestas pueden generar una ganancia de 20-60x la apuesta. 😉\n\n" +
                        "¡Inténtelo hoy y vea cómo crece su capital con nuestro bot! 💹";
            } else {
                answer = "*El bot está basado y entrenado en el clúster de redes neuronales de bitsGap🧠*\n\n" +
                        "Para entrenar al bot, se han jugado más de 10,000 juegos 🎰\n" +
                        "Actualmente, los usuarios del bot ganan con éxito entre el 15-25% de su capital por día! 💰\n\n" +
                        "En este momento, el bot todavía está siendo sometido a verificaciones y ajustes! La precisión del bot es del 87%!\n\n" +
                        "Para maximizar sus ganancias, siga estas instrucciones:\n\n" +
                        "🟢 1. Registre una cuenta a través de nuestro bot haciendo clic en el botón 'Registrarse' debajo del mensaje, sin esto no podrá acceder a las señales.\n\n" +
                        "🟢 2. Recargue el saldo de su cuenta.\n\n" +
                        "🟢 3. Vaya a la sección de juegos de 1win y seleccione el juego 💣 'MINES'.\n\n" +
                        "🟢 4. Establezca el número de trampas en tres. ¡Esto es importante!\n\n" +
                        "🟢 5. Solicite una señal en el bot y haga apuestas basadas en las señales del bot.\n\n" +
                        "🟢 6. Si una señal falla, recomendamos doblar la apuesta (X²) para cubrir completamente la pérdida en la próxima señal.\n\n" +
                        "⚠ Nota importante: doblar la apuesta no siempre es adecuado. Hay predicciones con coeficientes altos y, si dobla cada pérdida, existe la posibilidad de quedarse sin dinero. Así que recuerde la verdad:\n" +
                        "- Si el coeficiente en la señal es 5 o menos, puede doblar la apuesta en cada pérdida.\n" +
                        "- Si el coeficiente está entre 5 y 10, puede permitirse 2-3 giros perdidos con una apuesta y doblar la apuesta,\n" +
                        "y hacer lo mismo para otros 2-3 giros perdidos hasta ganar.\n" +
                        "- Si el coeficiente es superior a 10, juegue con una apuesta pequeña, ya que tales apuestas pueden generar una ganancia de 20-60x la apuesta. 😉\n\n" +
                        "¡Inténtelo hoy y vea cómo crece su capital con nuestro bot! 💹";
            }
        }
        if (userService.userIsUzbek(user)) {

            if (user.getIsVerify()) {
                answer = "*Bot bitsGap neyron tarmog'i klasteriga asoslangan va o'qitilgan🧠*\n\n" +
                        "Botni o'qitish uchun 10,000 dan ortiq o'yin o'ynaldi 🎰\n" +
                        "Hozirda, bot foydalanuvchilari kuniga o'z kapitalining 15-25% ini muvaffaqiyatli qilmoqdalar! 💰\n\n" +
                        "Ayni paytda, bot hali ham tekshiruvlardan va tuzatishlardan o'tmoqda! Botning aniqligi 87%!\n\n" +
                        "Maximal foyda olish uchun quyidagi ko'rsatmalarga amal qiling:\n\n" +
                        "🟢 1. 1win o'yinlari bo'limiga o'ting va 💣 'MINES' o'yinini tanlang.\n\n" +
                        "🟢 2. Tuzoq sonini uchga sozlang. Bu juda muhim!\n\n" +
                        "🟢 3. Botdan signal so'rang va bot signallari asosida tikish qiling.\n\n" +
                        "🟢 4. Agar signal muvaffaqiyatsiz bo'lsa, keyingi signalda yo'qotishni to'liq qoplash uchun stavkani ikki baravar oshirishni (X²) tavsiya qilamiz.\n\n" +
                        "⚠ Muhim eslatma: Stavkani ikki baravar oshirish har doim ham mos kelmaydi. Ba'zi bashoratlar yuqori koeffitsiyentlarga ega bo'lib, agar har bir yo'qotishda ikki baravar oshirsangiz, puldan qolishingiz mumkin. Shuning uchun, haqiqatni eslab qoling:\n" +
                        "- Agar signalda koeffitsiyent 5 yoki undan kam bo'lsa, har bir yo'qotishda stavkani ikki baravar oshirishingiz mumkin.\n" +
                        "- Agar koeffitsiyent 5 dan 10 gacha bo'lsa, bir stavka bilan 2-3 yo'qotishli aylanishni qilishingiz va stavkani ikki baravar oshirishingiz mumkin,\n" +
                        "va g'alaba qozonmaguncha yana 2-3 yo'qotishli aylanishni qilishingiz mumkin.\n" +
                        "- Agar koeffitsiyent 10 dan yuqori bo'lsa, kichik stavka bilan o'ynang, chunki bunday stavkalar 20-60x gacha yutuq keltirishi mumkin. 😉\n\n" +
                        "Bugun sinab ko'ring va botimiz yordamida kapitalingiz qanday o'sayotganini ko'ring! 💹";
            } else {
                answer = "*Bot bitsGap neyron tarmog'i klasteriga asoslangan va o'qitilgan🧠*\n\n" +
                        "Botni o'qitish uchun 10,000 dan ortiq o'yin o'ynaldi 🎰\n" +
                        "Hozirda, bot foydalanuvchilari kuniga o'z kapitalining 15-25% ini muvaffaqiyatli qilmoqdalar! 💰\n\n" +
                        "Ayni paytda, bot hali ham tekshiruvlardan va tuzatishlardan o'tmoqda! Botning aniqligi 87%!\n\n" +
                        "Maximal foyda olish uchun quyidagi ko'rsatmalarga amal qiling:\n\n" +
                        "🟢 1. Bot orqali hisob qaydnomangizni ro'yxatdan o'tkazing, xabarning ostidagi 'Ro'yxatdan o'tish' tugmasini bosing, aks holda signallarga kirish imkoniga ega bo'lmaysiz.\n\n" +
                        "🟢 2. Hisob qaydnomangiz balansini to'ldiring.\n\n" +
                        "🟢 3. 1win o'yinlari bo'limiga o'ting va 💣 'MINES' o'yinini tanlang.\n\n" +
                        "🟢 4. Tuzoq sonini uchga sozlang. Bu juda muhim!\n\n" +
                        "🟢 5. Botdan signal so'rang va bot signallari asosida tikish qiling.\n\n" +
                        "🟢 6. Agar signal muvaffaqiyatsiz bo'lsa, keyingi signalda yo'qotishni to'liq qoplash uchun stavkani ikki baravar oshirishni (X²) tavsiya qilamiz.\n\n" +
                        "⚠ Muhim eslatma: Stavkani ikki baravar oshirish har doim ham mos kelmaydi. Ba'zi bashoratlar yuqori koeffitsiyentlarga ega bo'lib, agar har bir yo'qotishda ikki baravar oshirsangiz, puldan qolishingiz mumkin. Shuning uchun, haqiqatni eslab qoling:\n" +
                        "- Agar signalda koeffitsiyent 5 yoki undan kam bo'lsa, har bir yo'qotishda stavkani ikki baravar oshirishingiz mumkin.\n" +
                        "- Agar koeffitsiyent 5 dan 10 gacha bo'lsa, bir stavka bilan 2-3 yo'qotishli aylanishni qilishingiz va stavkani ikki baravar oshirishingiz mumkin,\n" +
                        "va g'alaba qozonmaguncha yana 2-3 yo'qotishli aylanishni qilishingiz mumkin.\n" +
                        "- Agar koeffitsiyent 10 dan yuqori bo'lsa, kichik stavka bilan o'ynang, chunki bunday stavkalar 20-60x gacha yutuq keltirishi mumkin. 😉\n\n" +
                        "Bugun sinab ko'ring va botimiz yordamida kapitalingiz qanday o'sayotganini ko'ring! 💹";
            }
        }
        if (userService.userIsAzerbaijani(user)) {
            if (user.getIsVerify()) {
                answer = "*Bot bitsGap neyron şəbəkə klasterinə əsaslanır və öyrədilib🧠*\n\n" +
                        "Botu öyrətmək üçün 10,000-dən çox oyun oynanılıb 🎰\n" +
                        "Hazırda, bot istifadəçiləri gün ərzində kapitalının 15-25%-ni uğurla əldə edirlər! 💰\n\n" +
                        "Hazırda bot hələ də yoxlamalardan və düzəlişlərdən keçir! Botun dəqiqliyi 87%-dir!\n\n" +
                        "Maksimal qazancı əldə etmək üçün bu təlimatlara əməl edin:\n\n" +
                        "🟢 1. 1win oyunları bölməsinə keçin və 💣 'MINES' oyununu seçin.\n\n" +
                        "🟢 2. Tələ sayını üçə təyin edin. Bu çox vacibdir!\n\n" +
                        "🟢 3. Botda siqnal tələb edin və bot siqnalları əsasında mərc edin.\n\n" +
                        "🟢 4. Siqnal uğursuz olarsa, növbəti siqnalda itkiyi tamamilə əvəz etmək üçün mərcinizi ikiqat artırmağı (X²) tövsiyə edirik.\n\n" +
                        "⚠ Vacib qeyd: Mərcinizi ikiqat artırmaq həmişə uyğun deyil. Yüksək əmsallara malik bəzi proqnozlar var və hər itkini ikiqat artırsanız, puldan məhrum ola bilərsiniz. Beləliklə, həqiqəti xatırlayın:\n" +
                        "- Siqnalda əmsal 5 və ya daha azdırsa, hər itkini ikiqat artırmaq olar.\n" +
                        "- Əmsal 5-10 arasındadırsa, bir mərc ilə 2-3 itkili fırlatma edə bilərsiniz və mərcinizi ikiqat artıra bilərsiniz,\n" +
                        "və qələbə qazanana qədər yenə də 2-3 itkili fırlatma edə bilərsiniz.\n" +
                        "- Əmsal 10-dan yuxarıdırsa, kiçik mərc ilə oynayın, çünki bu mərc növləri ilə 20-60x qazanc əldə edə bilərsiniz. 😉\n\n" +
                        "Bugün sınayın və botumuzla kapitalınızın necə artdığını görün! 💹";
            } else {
                answer = "*Bot bitsGap neyron şəbəkə klasterinə əsaslanır və öyrədilib🧠*\n\n" +
                        "Botu öyrətmək üçün 10,000-dən çox oyun oynanılıb 🎰\n" +
                        "Hazırda, bot istifadəçiləri gün ərzində kapitalının 15-25%-ni uğurla əldə edirlər! 💰\n\n" +
                        "Hazırda bot hələ də yoxlamalardan və düzəlişlərdən keçir! Botun dəqiqliyi 87%-dir!\n\n" +
                        "Maksimal qazancı əldə etmək üçün bu təlimatlara əməl edin:\n\n" +
                        "🟢 1. Hesabınızı bizim bot vasitəsilə qeydiyyatdan keçirin, xabərdarlıq mesajının altında 'Qeydiyyatdan keç' düyməsini basın, əks halda siqnallara giriş əldə edə bilməyəcəksiniz.\n\n" +
                        "🟢 2. Hesabınızın balansını artırın.\n\n" +
                        "🟢 3. 1win oyunları bölməsinə keçin və 💣 'MINES' oyununu seçin.\n\n" +
                        "🟢 4. Tələ sayını üçə təyin edin. Bu çox vacibdir!\n\n" +
                        "🟢 5. Botda siqnal tələb edin və bot siqnalları əsasında mərc edin.\n\n" +
                        "🟢 6. Siqnal uğursuz olarsa, növbəti siqnalda itkiyi tamamilə əvəz etmək üçün mərcinizi ikiqat artırmağı (X²) tövsiyə edirik.\n\n" +
                        "⚠ Vacib qeyd: Mərcinizi ikiqat artırmaq həmişə uyğun deyil. Yüksək əmsallara malik bəzi proqnozlar var və hər itkini ikiqat artırsanız, puldan məhrum ola bilərsiniz. Beləliklə, həqiqəti xatırlayın:\n" +
                        "- Siqnalda əmsal 5 və ya daha azdırsa, hər itkini ikiqat artırmaq olar.\n" +
                        "- Əmsal 5-10 arasındadırsa, bir mərc ilə 2-3 itkili fırlatma edə bilərsiniz və mərcinizi ikiqat artıra bilərsiniz,\n" +
                        "və qələbə qazanana qədər yenə də 2-3 itkili fırlatma edə bilərsiniz.\n" +
                        "- Əmsal 10-dan yuxarıdırsa, kiçik mərc ilə oynayın, çünki bu mərc növləri ilə 20-60x qazanc əldə edə bilərsiniz. 😉\n\n" +
                        "Bugün sınayın və botumuzla kapitalınızın necə artdığını görün! 💹";
            }
        }
        if (userService.userIsTurkish(user)) {
                if (user.getIsVerify()) {
                    answer = "*Bot, bitsGap sinir ağı kümesine dayanır ve eğitilmiştir🧠*\n\n" +
                            "Botu eğitmek için 10.000'den fazla oyun oynandı 🎰\n" +
                            "Şu anda, bot kullanıcıları günlük sermayelerinin %15-25'ini başarıyla kazanmaktadır! 💰\n\n" +
                            "Şu anda bot hala kontrollerden ve düzeltmelerden geçmektedir! Botun doğruluğu %87'dir!\n\n" +
                            "Maksimum kar elde etmek için aşağıdaki talimatları izleyin:\n\n" +
                            "🟢 1. 1win oyunları bölümüne gidin ve 💣 'MINES' oyununu seçin.\n\n" +
                            "🟢 2. Tuzak sayısını üç olarak ayarlayın. Bu çok önemlidir!\n\n" +
                            "🟢 3. Botta sinyal isteyin ve bot sinyalleri temelinde bahis yapın.\n\n" +
                            "🟢 4. Sinyal başarısız olursa, bir sonraki sinyalde kaybı tamamen telafi etmek için bahsi iki katına çıkarmayı (X²) öneririz.\n\n" +
                            "⚠ Önemli not: Bahsi iki katına çıkarmak her zaman uygun değildir. Yüksek katsayılı bazı tahminler vardır ve her kaybı iki katına çıkarırsanız, para kaybedebilirsiniz. Bu yüzden gerçeği unutmayın:\n" +
                            "- Sinyalde katsayı 5 veya daha az ise, her kaybı iki katına çıkarabilirsiniz.\n" +
                            "- Katsayı 5 ile 10 arasında ise, bir bahisle 2-3 kayıplı dönüş yapabilir ve bahsi iki katına çıkarabilirsiniz,\n" +
                            "ve kazanana kadar aynı şekilde 2-3 kayıplı dönüş daha yapabilirsiniz.\n" +
                            "- Katsayı 10'dan fazla ise, küçük bir bahisle oynayın, çünkü bu tür bahislerde kazanç bahsin 20-60 katı kadar olabilir. 😉\n\n" +
                            "Bugün deneyin ve botumuzla sermayenizin nasıl büyüdüğünü görün! 💹";
                } else {
                    answer = "*Bot, bitsGap sinir ağı kümesine dayanır ve eğitilmiştir🧠*\n\n" +
                            "Botu eğitmek için 10.000'den fazla oyun oynandı 🎰\n" +
                            "Şu anda, bot kullanıcıları günlük sermayelerinin %15-25'ini başarıyla kazanmaktadır! 💰\n\n" +
                            "Şu anda bot hala kontrollerden ve düzeltmelerden geçmektedir! Botun doğruluğu %87'dir!\n\n" +
                            "Maksimum kar elde etmek için aşağıdaki talimatları izleyin:\n\n" +
                            "🟢 1. Botumuz aracılığıyla bir hesap kaydedin, mesajın altındaki 'Kaydol' düğmesine tıklayın, aksi takdirde sinyallere erişemezsiniz.\n\n" +
                            "🟢 2. Hesap bakiyenizi doldurun.\n\n" +
                            "🟢 3. 1win oyunları bölümüne gidin ve 💣 'MINES' oyununu seçin.\n\n" +
                            "🟢 4. Tuzak sayısını üç olarak ayarlayın. Bu çok önemlidir!\n\n" +
                            "🟢 5. Botta sinyal isteyin ve bot sinyalleri temelinde bahis yapın.\n\n" +
                            "🟢 6. Sinyal başarısız olursa, bir sonraki sinyalde kaybı tamamen telafi etmek için bahsi iki katına çıkarmayı (X²) öneririz.\n\n" +
                            "⚠ Önemli not: Bahsi iki katına çıkarmak her zaman uygun değildir. Yüksek katsayılı bazı tahminler vardır ve her kaybı iki katına çıkarırsanız, para kaybedebilirsiniz. Bu yüzden gerçeği unutmayın:\n" +
                            "- Sinyalde katsayı 5 veya daha az ise, her kaybı iki katına çıkarabilirsiniz.\n" +
                            "- Katsayı 5 ile 10 arasında ise, bir bahisle 2-3 kayıplı dönüş yapabilir ve bahsi iki katına çıkarabilirsiniz,\n" +
                            "ve kazanana kadar aynı şekilde 2-3 kayıplı dönüş daha yapabilirsiniz.\n" +
                            "- Katsayı 10'dan fazla ise, küçük bir bahisle oynayın, çünkü bu tür bahislerde kazanç bahsin 20-60 katı kadar olabilir. 😉\n\n" +
                            "Bugün deneyin ve botumuzla sermayenizin nasıl büyüdüğünü görün! 💹";
                }
            }
        if (userService.userIsPortuguese(user)) {
                if (user.getIsVerify()) {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "Mais de 10.000 jogos foram jogados para treinar o bot 🎰\n" +
                            "Atualmente, os usuários do bot estão ganhando 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Vá para a seção de jogos 1win e selecione o jogo 💣 'MINES'.\n\n" +
                            "🟢 2. Defina o número de armadilhas para três. Isso é importante!\n\n" +
                            "🟢 3. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 4. Se um sinal falhar, recomendamos dobrar a aposta (X²) para cobrir totalmente a perda no próximo sinal.\n\n" +
                            "⚠ Nota importante: Dobrar a aposta nem sempre é adequado. Há previsões com altos coeficientes, e se você dobrar cada perda, pode acabar sem dinheiro. Portanto, lembre-se da verdade:\n" +
                            "- Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "- Se o coeficiente estiver entre 5 e 10, você pode fazer 2-3 giros perdidos em uma aposta e dobrar a aposta,\n" +
                            "e fazer o mesmo para mais 2-3 giros perdidos até ganhar.\n" +
                            "- Se o coeficiente for superior a 10, jogue com uma aposta pequena, pois essas apostas podem render um ganho de 20-60x da aposta. 😉\n\n" +
                            "Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                } else {
                    answer = "*O bot é baseado e treinado no cluster de rede neural bitsGap🧠*\n\n" +
                            "Mais de 10.000 jogos foram jogados para treinar o bot 🎰\n" +
                            "Atualmente, os usuários do bot estão ganhando 15-25% de seu capital por dia! 💰\n\n" +
                            "No momento, o bot ainda está passando por verificações e ajustes! A precisão do bot é de 87%!\n\n" +
                            "Para maximizar seus lucros, siga estas instruções:\n\n" +
                            "🟢 1. Registre uma conta através do nosso bot, clicando no botão 'Registrar-se' abaixo da mensagem, sem isso você não poderá acessar os sinais.\n\n" +
                            "🟢 2. Recarregue o saldo da sua conta.\n\n" +
                            "🟢 3. Vá para a seção de jogos 1win e selecione o jogo 💣 'MINES'.\n\n" +
                            "🟢 4. Defina o número de armadilhas para três. Isso é importante!\n\n" +
                            "🟢 5. Solicite um sinal no bot e faça apostas com base nos sinais do bot.\n\n" +
                            "🟢 6. Se um sinal falhar, recomendamos dobrar a aposta (X²) para cobrir totalmente a perda no próximo sinal.\n\n" +
                            "⚠ Nota importante: Dobrar a aposta nem sempre é adequado. Há previsões com altos coeficientes, e se você dobrar cada perda, pode acabar sem dinheiro. Portanto, lembre-se da verdade:\n" +
                            "- Se o coeficiente no sinal for 5 ou menos, você pode dobrar a aposta em cada perda.\n" +
                            "- Se o coeficiente estiver entre 5 e 10, você pode fazer 2-3 giros perdidos em uma aposta e dobrar a aposta,\n" +
                            "e fazer o mesmo para mais 2-3 giros perdidos até ganhar.\n" +
                            "- Se o coeficiente for superior a 10, jogue com uma aposta pequena, pois essas apostas podem render um ganho de 20-60x da aposta. 😉\n\n" +
                            "Experimente hoje e veja como seu capital cresce com nosso bot! 💹";
                }
            }
        if (userService.userIsArabic(user)) {
                if (user.getIsVerify()) {
                    answer = "*البوت مبني ومدرب على مجموعة الشبكة العصبية bitsGap🧠*\n\n" +
                            "تم لعب أكثر من 10,000 لعبة لتدريب البوت 🎰\n" +
                            "حاليًا، يحقق مستخدمو البوت بنجاح 15-25% من رأس مالهم يوميًا! 💰\n\n" +
                            "في الوقت الحالي، لا يزال البوت يخضع للفحص والتعديلات! دقة البوت هي 87%!\n\n" +
                            "لزيادة أرباحك إلى أقصى حد، اتبع هذه التعليمات:\n\n" +
                            "🟢 1. انتقل إلى قسم ألعاب 1win واختر لعبة 💣 'MINES'.\n\n" +
                            "🟢 2. اضبط عدد الفخاخ على ثلاثة. هذا مهم!\n\n" +
                            "🟢 3. اطلب إشارة في البوت وضع رهانات بناءً على إشارات البوت.\n\n" +
                            "🟢 4. إذا فشلت الإشارة، نوصي بمضاعفة الرهان (X²) لتغطية الخسارة بالكامل في الإشارة التالية.\n\n" +
                            "⚠ ملاحظة هامة: مضاعفة الرهان ليست مناسبة دائمًا. هناك توقعات بمعاملات عالية، وإذا قمت بمضاعفة كل خسارة، فهناك فرصة لفقدان المال. لذا تذكر الحقيقة:\n" +
                            "- إذا كان المعامل في الإشارة 5 أو أقل، يمكنك مضاعفة الرهان في كل خسارة.\n" +
                            "- إذا كان المعامل بين 5 و 10، يمكنك تحمل 2-3 رهانات خاسرة في رهان واحد ومضاعفة الرهان،\n" +
                            "ويمكنك فعل الشيء نفسه مع 2-3 رهانات خاسرة حتى تفوز.\n" +
                            "- إذا كان المعامل أكثر من 10، العب برهان صغير، حيث يمكن أن يكون الربح في هذه الرهانات من 20-60 ضعف الرهان. 😉\n\n" +
                            "جربه اليوم وشاهد كيف ينمو رأس مالك مع بوتنا! 💹";
                } else {
                    answer = "*البوت مبني ومدرب على مجموعة الشبكة العصبية bitsGap🧠*\n\n" +
                            "تم لعب أكثر من 10,000 لعبة لتدريب البوت 🎰\n" +
                            "حاليًا، يحقق مستخدمو البوت بنجاح 15-25% من رأس مالهم يوميًا! 💰\n\n" +
                            "في الوقت الحالي، لا يزال البوت يخضع للفحص والتعديلات! دقة البوت هي 87%!\n\n" +
                            "لزيادة أرباحك إلى أقصى حد، اتبع هذه التعليمات:\n\n" +
                            "🟢 1. سجل حسابًا عبر بوتنا، بالنقر على زر 'تسجيل' أسفل الرسالة، بدون ذلك لن تتمكن من الوصول إلى الإشارات.\n\n" +
                            "🟢 2. أعد شحن رصيد حسابك.\n\n" +
                            "🟢 3. انتقل إلى قسم ألعاب 1win واختر لعبة 💣 'MINES'.\n\n" +
                            "🟢 4. اضبط عدد الفخاخ على ثلاثة. هذا مهم!\n\n" +
                            "🟢 5. اطلب إشارة في البوت وضع رهانات بناءً على إشارات البوت.\n\n" +
                            "🟢 6. إذا فشلت الإشارة، نوصي بمضاعفة الرهان (X²) لتغطية الخسارة بالكامل في الإشارة التالية.\n\n" +
                            "⚠ ملاحظة هامة: مضاعفة الرهان ليست مناسبة دائمًا. هناك توقعات بمعاملات عالية، وإذا قمت بمضاعفة كل خسارة، فهناك فرصة لفقدان المال. لذا تذكر الحقيقة:\n" +
                            "- إذا كان المعامل في الإشارة 5 أو أقل، يمكنك مضاعفة الرهان في كل خسارة.\n" +
                            "- إذا كان المعامل بين 5 و 10، يمكنك تحمل 2-3 رهانات خاسرة في رهان واحد ومضاعفة الرهان،\n" +
                            "ويمكنك فعل الشيء نفسه مع 2-3 رهانات خاسرة حتى تفوز.\n" +
                            "- إذا كان المعامل أكثر من 10، العب برهان صغير، حيث يمكن أن يكون الربح في هذه الرهانات من 20-60 ضعف الرهان. 😉\n\n" +
                            "جربه اليوم وشاهد كيف ينمو رأس مالك مع بوتنا! 💹";
                }
            }

            InlineKeyboardMarkup inlineKeyboardMarkup;
            if (user.getIsVerify()) {
                inlineKeyboardMarkup = MinesKeyboard.mainForInstructionIfUserIsRegistered(oneWinUrl, user.getLanguage());
            } else {
                inlineKeyboardMarkup = MinesKeyboard.mainForInstructionIfUserIsNotRegistered(oneWinUrl, user.getLanguage());
            }

            botActions.sendMessageWithInlineKeyboardAndParseMARKDOWN(chatId, answer, inlineKeyboardMarkup);
            botActions.handleCallbackQuery(callbackQuery);

        }
}
