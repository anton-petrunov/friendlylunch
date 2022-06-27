package com.github.friendlylunch.util;

import com.github.friendlylunch.model.Restaurant;
import com.github.friendlylunch.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Util {

    public static final LocalTime LUNCH_TIME = LocalTime.of(14, 0);

    public static final LocalTime STOP_VOTING_TIME = LocalTime.of(11, 0);

    public static LocalDateTime nextLunchDateTime() {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        if (dateTimeNow.toLocalTime().isBefore(LUNCH_TIME)) {
            return LocalDateTime.of(dateTimeNow.toLocalDate(), LUNCH_TIME);
        } else {
            return LocalDateTime.of(dateTimeNow.toLocalDate().plus(1, ChronoUnit.DAYS), LUNCH_TIME);
        }
    }

    public static LocalDateTime previousLunchDateTime() {
        return nextLunchDateTime().minus(1, ChronoUnit.DAYS);
    }

    public static LocalDateTime stopVotingDateTime() {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        if (dateTimeNow.toLocalTime().isBefore(LUNCH_TIME)) {
            return LocalDateTime.of(dateTimeNow.toLocalDate(), STOP_VOTING_TIME);
        } else {
            return LocalDateTime.of(dateTimeNow.toLocalDate().plus(1, ChronoUnit.DAYS), STOP_VOTING_TIME);
        }
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
