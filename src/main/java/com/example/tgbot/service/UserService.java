package com.example.tgbot.service;


import com.example.tgbot.entity.User;
import com.example.tgbot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long getAmountOfUsersInBot(){
        return userRepository.count();
    }

    public long getAmountOfUsersInBotThatTakePartInLottery(){
        return userRepository.countByIsParticipantInLotteryIsTrue();
    }

    public User getInfoAboutUserByChatID(long chatID){
        return userRepository.getByChatId(chatID);
    }

    public User getInfoAboutUserByUsername(String username){
        return userRepository.getByUsername(username);
    }
    public void setDefaultAwaitingMode(long chatId){
        User user = userRepository.getByChatId(chatId);
        user.setAwaitingMode(false);
        userRepository.save(user);
    }

    public void setDefaultIsDeposit (long chatId){
        User user = userRepository.getByChatId(chatId);
        user.setIsDeposit(false);
        userRepository.save(user);
    }

    public void addAdministrationRoleToUser(String username){
        User user = userRepository.getByUsername(username);
        user.setIsAdministrator(true);
        userRepository.save(user);
    }
    public void setIsAdministratorToDefault(long chatId){
        User user = userRepository.getByChatId(chatId);
        user.setIsAdministrator(false);
        userRepository.save(user);
    }
    public void setIsAdministratorToDefault(String username){
        User user = userRepository.getByUsername(username);
        user.setIsAdministrator(false);
        userRepository.save(user);
    }

//    public void saveUser(Update update,String language){
//        User user = new User();
//        user.setChatId(update.getMessage().getChatId());
//        user.setIsParticipantInLottery(false);
//        //НА ВРЕМЯ ТЕСТА
////        user.setIsVerify(false);
//        user.setIsVerify(true);
//
//        user.setAwaitingMode(false);
//        user.setOneWinId(null);
//
//        //НА ВРЕМЯ ТЕСТА
////        user.setIsDeposit(false);
//        user.setIsDeposit(true);
//
//        user.setIsEnteredToTheChannel(false);
//        user.setLanguage(language);
//        if (userRepository.count() == 0){
//            user.setIsAdministrator(true);
//        }
//        else user.setIsAdministrator(false);
//        System.out.println(update.getMessage().getChat().getFirstName());
//        if(update.getMessage().getChat().getFirstName() == null){
//            user.setFirstName("user");
//        }else user.setFirstName(update.getMessage().getChat().getFirstName());
//        if(update.getMessage().getFrom().getUserName() == null || update.getMessage().getFrom().getUserName().equals("")){
//            user.setUsername(null);
//        }
//        else user.setUsername(update.getMessage().getFrom().getUserName());
//        user.setRegistrationDate(LocalDateTime.now().toLocalDate());
//        userRepository.save(user);
//    }

    public void saveUser(CallbackQuery callbackQuery, String language){
        User user = new User();
        user.setChatId(callbackQuery.getMessage().getChatId());
        user.setIsParticipantInLottery(false);
        //НА ВРЕМЯ ТЕСТА
//        user.setIsVerify(true);
        user.setIsVerify(false);

        user.setAwaitingMode(false);
        user.setOneWinId(null);
        //НА ВРЕМЯ ТЕСТА
//        user.setIsDeposit(true);
        user.setIsDeposit(false);
        user.setIsEnteredToTheChannel(false);
        user.setLanguage(language);
        if (userRepository.count() == 0){
            user.setIsAdministrator(true);
        }
        else user.setIsAdministrator(false);
        if(callbackQuery.getMessage().getChat().getFirstName() == null || callbackQuery.getMessage().getChat().getFirstName().contains("_")){
            if (language.equals("russian")){
                user.setFirstName("Пользователь");
            }
            else user.setFirstName("User");
        }
        else {
            user.setFirstName(callbackQuery.getFrom().getFirstName());
        }

        if(callbackQuery.getFrom().getUserName() == null || callbackQuery.getFrom().getUserName().equals("")){
            user.setUsername(null);
        }
        else user.setUsername(callbackQuery.getFrom().getUserName());
        user.setRegistrationDate(LocalDateTime.now().toLocalDate());
        userRepository.save(user);
    }

    public void setVerifyIsTrue(long chatID){
        User user = userRepository.getByChatId(chatID);
        user.setIsVerify(true);
        userRepository.save(user);
    }

    public void setIsEnteredToTheChannelIsTrue(long chatID){
        User user = userRepository.getByChatId(chatID);
        user.setIsEnteredToTheChannel(true);
        userRepository.save(user);
    }

    public void setIsEnteredToTheChannelIsFalse(long chatID){
        User user = userRepository.getByChatId(chatID);
        user.setIsEnteredToTheChannel(false);
        userRepository.save(user);
    }

    public void setVerifyIsDefault(long chatID){
        User user = userRepository.getByChatId(chatID);
        user.setIsVerify(false);
        userRepository.save(user);
    }

    public List<User> geAllUsersWhoIsDeposit(){
        return userRepository.getAllByIsDepositIsTrueAndIsVerifyTrue();
    }

    public List<User> geAllRuUsersWhoIsDeposit() {
        List<String> languages = Arrays.asList("russian", "uzbek","azerbaijani");
        return userRepository.getAllByIsDepositIsTrueAndIsVerifyTrueAndLanguageIn(languages);
    }
    public List<User> geAllEngUsersWhoIsDeposit(){
        return userRepository.getAllByIsDepositIsTrueAndIsVerifyTrueAndLanguage("english");
    }
    public List<User> geAllNotRuUsersWhoIsDeposit() {
        List<String> languagesToExclude = Arrays.asList("russian", "uzbek","azerbaijani");
        return userRepository.getAllByIsDepositIsTrueAndIsVerifyTrueAndLanguageNotIn(languagesToExclude);
    }

    public void setIsParticipantIsDefault(long chatID){
        User user = userRepository.getByChatId(chatID);
        user.setIsParticipantInLottery(false);
        userRepository.save(user);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }

    public long getAmountOfNewUsersInBotToday() {
        return userRepository.countUsersRegisteredToday(LocalDateTime.now().toLocalDate());
    }

    public User getByOneWinId(long oneWinId){
        return userRepository.getByOneWinId(oneWinId);
    }

    public long getAmountOfNewUsersInBotYesterday() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDate yesterdayDate = yesterday.toLocalDate();
        return userRepository.countUsersRegisteredToday(yesterdayDate);
    }

    public long getNumberOfUsersForCurrentMonth() {
        LocalDate now = LocalDate.now();
        LocalDate startOfCurrentMonth = now.withDayOfMonth(1);
        LocalDate endOfCurrentMonth = now.plusMonths(1).withDayOfMonth(1).minusDays(1);
        return userRepository.countUsersRegisteredBetween(startOfCurrentMonth, endOfCurrentMonth);
    }

    public long getNumberOfUsersForPreviousMonth() {
        LocalDate now = LocalDate.now();
        LocalDate startOfPreviousMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfPreviousMonth = now.withDayOfMonth(1).minusDays(1);
        return userRepository.countUsersRegisteredBetween(startOfPreviousMonth, endOfPreviousMonth);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public List<User> getRuAllUsers() {
        List<String> languages = Arrays.asList("russian", "uzbek","azerbaijani");
        return userRepository.getByLanguageIn(languages);
    }
    public List<User> getEngAllUsers(){
        return userRepository.getByLanguage("english");
    }
    public List<User> getAllNotRuUsers() {
        List<String> languagesToExclude = Arrays.asList("russian", "uzbek","azerbaijani");
        return userRepository.getByLanguageNotIn(languagesToExclude);
    }
    public List<User> getAllUsersWhoIsNotRegistered(){
        return userRepository.getAllByIsVerifyIsFalse();
    }
    public List<User> getRuAllUsersWhoIsNotRegistered() {
        List<String> languages = Arrays.asList("russian", "uzbek","azerbaijani");
        return userRepository.getAllByIsVerifyIsFalseAndLanguageIn(languages);
    }
    public List<User> getEngAllUsersWhoIsNotRegistered(){
        return userRepository.getAllByIsVerifyIsFalseAndLanguage("english");
    }
    public List<User> getNotRuAllUsersWhoIsNotRegistered() {
        List<String> languagesToExclude = Arrays.asList("russian", "uzbek","azerbaijani");
        return userRepository.getAllByIsVerifyIsFalseAndLanguageNotIn(languagesToExclude);
    }
    public void switchToRussianLanguage(User user) {
        user.setLanguage("russian");
        userRepository.save(user);
    }

    public void switchToEnglishLanguage(User user) {
        user.setLanguage("english");
        userRepository.save(user);
    }

    public void switchToHindiLanguage(User user) {
        user.setLanguage("hindi");
        userRepository.save(user);
    }

    public void switchToBrazilianPortugueseLanguage(User user) {
        user.setLanguage("brazilian");
        userRepository.save(user);
    }

    public void switchToSpanishLanguage(User user) {
        user.setLanguage("spanish");
        userRepository.save(user);
    }

    public void switchToUzbekLanguage(User user) {
        user.setLanguage("uzbek");
        userRepository.save(user);
    }

    public void switchToAzerbaijaniLanguage(User user) {
        user.setLanguage("azerbaijani");
        userRepository.save(user);
    }

    public void switchToTurkishLanguage(User user) {
        user.setLanguage("turkish");
        userRepository.save(user);
    }

    public void switchToPortugueseLanguage(User user) {
        user.setLanguage("portuguese");
        userRepository.save(user);
    }

    public void switchToArabicLanguage(User user) {
        user.setLanguage("arabic");
        userRepository.save(user);
    }

    public List<User> getAllUsersWhoIsRegisteredButNotDeposit(){
        return userRepository.getAllByIsDepositIsFalseAndIsVerifyTrue();
    }
    public List<User> getAllRuUsersWhoIsRegisteredButNotDeposit() {
        List<String> languages = Arrays.asList("russian", "uzbek","azerbaijani");
        return userRepository.getAllByIsDepositIsFalseAndIsVerifyTrueAndLanguageIn(languages);
    }
    public List<User> getAllEngUsersWhoIsRegisteredButNotDeposit(){
        return userRepository.getAllByIsDepositIsFalseAndIsVerifyTrueAndLanguage("english");
    }

    public List<User> getAllNotRuUsersWhoIsRegisteredButNotDeposit() {
        List<String> languagesToExclude = Arrays.asList("russian","uzbek","azerbaijani");
        return userRepository.getAllByIsDepositIsFalseAndIsVerifyTrueAndLanguageNotIn(languagesToExclude);
    }

    public void deleteDataForUser(User user) {
        userRepository.delete(user);
    }

    public void enableAwaitingMode(long chatId) {
        User user = userRepository.getByChatId(chatId);
        user.setAwaitingMode(true);
        userRepository.save(user);
    }
    public boolean isUserExistByOneWinId(long oneWinId){
        User user = userRepository.getByOneWinId(oneWinId);
        if (user != null){
            return true;
        }
        return false;
    }

    public void setIsDepositedIsTrue(String playerId) {
        User user = userRepository.getByOneWinId(Long.parseLong(playerId));
        if (user != null){
            user.setIsDeposit(true);
            userRepository.save(user);
        }
    }
    public void setRussianLanguageToUser(long chatId){
        User user = userRepository.getByChatId(chatId);
        user.setLanguage("russian");
        userRepository.save(user);
    }
    public void setEnglishLanguageToUser(long chatId){
        User user = userRepository.getByChatId(chatId);
        user.setLanguage("english");
        userRepository.save(user);
    }

    public boolean userIsRussian(User user) {
        return "russian".equals(user.getLanguage());
    }

    public boolean userIsEnglish(User user) {
        return "english".equals(user.getLanguage());
    }

    public boolean userIsHindi(User user) {
        return "hindi".equals(user.getLanguage());
    }

    public boolean userIsBrazilian(User user) {
        return "brazilian".equals(user.getLanguage());
    }

    public boolean userIsSpanish(User user) {
        return "spanish".equals(user.getLanguage());
    }

    public boolean userIsUzbek(User user) {
        return "uzbek".equals(user.getLanguage());
    }

    public boolean userIsAzerbaijani(User user) {
        return "azerbaijani".equals(user.getLanguage());
    }

    public boolean userIsTurkish(User user) {
        return "turkish".equals(user.getLanguage());
    }

    public boolean userIsPortuguese(User user) {
        return "portuguese".equals(user.getLanguage());
    }

    public boolean userIsArabic(User user) {
        return "arabic".equals(user.getLanguage());
    }

    public User getInfoAboutUserByOneWinId(String oneWinId) {
        return userRepository.getByOneWinId(Long.valueOf(oneWinId));
    }
}