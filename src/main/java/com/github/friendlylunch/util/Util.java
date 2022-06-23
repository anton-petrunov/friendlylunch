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

    public static boolean checkDishes(Restaurant restaurant) {
        LocalDateTime dishesUpdateDateTime = restaurant.getDishesUpdateDateTime();
        return restaurant.getDishes() != null && dishesUpdateDateTime.isAfter(previousLunchDateTime()) &&
                dishesUpdateDateTime.isBefore(nextLunchDateTime());
    }

    public static boolean checkVote(User user) {
        LocalDateTime votingDateTime = user.getVotingDateTime();
        return user.getVotedFor() > 0 && votingDateTime.isAfter(previousLunchDateTime()) &&
                votingDateTime.isBefore(nextLunchDateTime());
    }

    public static boolean checkVotingAvailability(User user) {
        LocalDateTime votingDateTime = user.getVotingDateTime();
        return votingDateTime.isBefore(previousLunchDateTime());
    }

    public static boolean checkRevoteAvailability(User user) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        return checkVote(user) && dateTimeNow.isAfter(previousLunchDateTime()) &&
                dateTimeNow.isBefore(stopVotingDateTime());
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
