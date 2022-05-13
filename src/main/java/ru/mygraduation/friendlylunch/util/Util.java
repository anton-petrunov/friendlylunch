package ru.mygraduation.friendlylunch.util;

import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Util {

    public static final LocalTime LUNCH_TIME = LocalTime.of(14, 0);

    public static final LocalTime STOP_VOTING_TIME = LocalTime.of(11, 0);

    public static LocalDateTime dateTimeNow = LocalDateTime.now();
    public static LocalDate dateNow = dateTimeNow.toLocalDate();
    public static LocalTime timeNow = dateTimeNow.toLocalTime();


    public static LocalDateTime nextLunchDateTime() {
        if (timeNow.isBefore(LUNCH_TIME)) {
            return LocalDateTime.of(dateNow, LUNCH_TIME);
        } else {
            return LocalDateTime.of(dateNow.plus(1, ChronoUnit.DAYS), LUNCH_TIME);
        }
    }

    public static LocalDateTime previousLunchDateTime() {
        return nextLunchDateTime().minus(1, ChronoUnit.DAYS);
    }

    public static LocalDateTime stopVotingDateTime() {
        if (timeNow.isBefore(LUNCH_TIME)) {
            return LocalDateTime.of(dateNow, STOP_VOTING_TIME);
        } else {
            return LocalDateTime.of(dateNow.plus(1, ChronoUnit.DAYS), STOP_VOTING_TIME);
        }
    }

    public static boolean checkMenu(Restaurant restaurant) {
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
        return checkVote(user) && dateTimeNow.isAfter(previousLunchDateTime()) &&
                dateTimeNow.isBefore(stopVotingDateTime());
    }
}
