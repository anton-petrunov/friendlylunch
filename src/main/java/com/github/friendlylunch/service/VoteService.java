package com.github.friendlylunch.service;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.model.Vote;
import com.github.friendlylunch.repository.MenuRepository;
import com.github.friendlylunch.repository.VoteRepository;
import com.github.friendlylunch.web.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.github.friendlylunch.util.Util.*;
import static com.github.friendlylunch.util.ValidationUtil.checkNotFoundWithId;
import static com.github.friendlylunch.util.VoteUtil.checkTimeForRevote;
import static com.github.friendlylunch.util.VoteUtil.checkVoteRepeat;

@Service
public class VoteService {

    private static final LocalTime ELEVEN_AM = LocalTime.of(11, 0);

    private static final LocalTime TWO_PM = LocalTime.of(14, 0);

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    MenuRepository menuRepository;

    public List<Vote> getAll(int userId) {
        return voteRepository.getAll(userId);
    }

    public Vote vote(int userId, int menuId) {
        Menu menu = checkNotFoundWithId(menuRepository.findById(menuId).orElse(null), menuId);
        int restaurantId = menu.getRestaurant().getId();
        Menu menuForVote = menuRepository.getByMenuDateAndDishesSize(restaurantId, menuId, nextLunchDate);
        checkMenuIsNull(menuForVote, restaurantId, menuId);

        List<Vote> votingStory = getAll(userId);
        if (votingStory.size() != 0) {
            Vote lastVote = getAll(userId).get(0);

            if (lastVote.getVotingDateTime().isAfter(previousLunchDateTime())) {
                checkTimeForRevote(ELEVEN_AM, TWO_PM);
                checkVoteRepeat(menuForVote, lastVote);
            }
        }
        return voteRepository.save(new Vote(menuForVote, SecurityUtil.get().getUser(), LocalDateTime.now()));
    }
}
