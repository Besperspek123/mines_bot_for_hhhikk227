package com.example.tgbot.repository;

import com.example.tgbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    long count();
    long countByRegistrationDate(LocalDate registrationDate);

    long countByIsParticipantInLotteryIsTrue();

    @Query("SELECT COUNT(u) FROM User u WHERE u.registrationDate = :today")
    long countUsersRegisteredToday(@Param("today") LocalDate today);
    User getByUsername(String username);
    User getByChatId(long chatID);

    User getByOneWinId(Long oneWinId);

    List<User> getAllByIsDepositIsTrueAndIsVerifyTrue();
    List<User> getAllByIsDepositIsTrueAndIsVerifyTrueAndLanguage(String language);
    List<User> getAllByIsDepositIsTrueAndIsVerifyTrueAndLanguageNot(String language);

    List<User> getAllByIsVerifyIsFalse();
    List<User> getAllByIsVerifyIsFalseAndLanguage(String language);
    List<User> getAllByIsVerifyIsFalseAndLanguageNot(String language);

    List<User> getByLanguage(String language);

    List<User> getByLanguageNot(String language);

    List<User> getAllByIsDepositIsFalseAndIsVerifyTrue();
    List<User> getAllByIsDepositIsFalseAndIsVerifyTrueAndLanguage(String language);
    List<User> getAllByIsDepositIsFalseAndIsVerifyTrueAndLanguageNot(String language);
    List<User> getAllByIsDepositIsFalseAndIsVerifyTrueAndLanguageIn(List<String> languages);
    List<User> getAllByIsDepositIsFalseAndIsVerifyTrueAndLanguageNotIn(List<String> languages);
    List<User> getAllByIsDepositIsTrueAndIsVerifyTrueAndLanguageIn(List<String> languages);
    List<User> getAllByIsDepositIsTrueAndIsVerifyTrueAndLanguageNotIn(List<String> languages);
    List<User> getByLanguageIn(List<String> languages);
    List<User> getByLanguageNotIn(List<String> languages);
    List<User> getAllByIsVerifyIsFalseAndLanguageIn(List<String> languages);
    List<User> getAllByIsVerifyIsFalseAndLanguageNotIn(List<String> languages);
    @Query("SELECT COUNT(u) FROM User u WHERE u.registrationDate >= :start AND u.registrationDate <= :end")
    long countUsersRegisteredBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
