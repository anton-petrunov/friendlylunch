package com.github.friendlylunch.util;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.model.Vote;
import com.github.friendlylunch.util.exception.IllegalRequestDataException;

import java.time.LocalTime;

public class VoteUtil {
    public static void checkTimeForRevote(LocalTime startTime, LocalTime endTime) {
        if (LocalTime.now().isAfter(startTime) && LocalTime.now().isBefore(endTime)) {
            throw new IllegalRequestDataException("From 11 am to 2 pm revoting is not allowed");
        }
    }

    public static void checkVoteRepeat(Menu menuForVote, Vote lastVote) {
        if (menuForVote.getId().equals(lastVote.getMenu().getId())) {
            throw new IllegalRequestDataException("You ara already votes for this menu " + menuForVote);
        }
    }
}
