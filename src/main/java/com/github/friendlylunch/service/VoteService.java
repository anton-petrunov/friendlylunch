package com.github.friendlylunch.service;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.model.Vote;
import com.github.friendlylunch.repository.MenuRepository;
import com.github.friendlylunch.repository.VoteRepository;
import com.github.friendlylunch.util.exception.IllegalRequestDataException;
import com.github.friendlylunch.web.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.github.friendlylunch.util.Util.nextLunchDate;
import static com.github.friendlylunch.util.Util.previousLunchDateTime;

@Service
public class VoteService {

    private static final LocalTime ELEVEN_AM = LocalTime.of(11, 0);

    private static final LocalTime TWO_PM = LocalTime.of(14, 0);

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    MenuRepository menuRepository;

    public List<Vote> getAllByUser(int userId) {
        return voteRepository.getAllByUser(userId);
    }

    public Vote vote(int userId, int menuId) {
        Menu menuForVote = menuRepository.getByIdWithDishes(menuId);

        boolean menuIsChecked = menuForVote.getDate().isEqual(nextLunchDate) &&
                menuForVote.getDishes().size() > 0;

        if (!menuIsChecked) {
            throw new IllegalRequestDataException("Menu " + menuForVote + " is not available for voting");
        }

        List<Vote> votingStory = getAllByUser(userId);
        Vote created = new Vote(null, menuForVote, SecurityUtil.get().getUser(), LocalDateTime.now());

        if (votingStory.size() != 0) {
            Vote lastVote = getAllByUser(userId).get(0);

            if (lastVote.getVotingDateTime().isAfter(previousLunchDateTime())) {
                if (LocalTime.now().isAfter(ELEVEN_AM) && LocalTime.now().isBefore(TWO_PM)) {
                    throw new IllegalRequestDataException("Sorry, from 11 am to 2 pm revoting is not allowed");
                }

                if (menuForVote.getId().equals(lastVote.getMenu().getId())) {
                    throw new IllegalRequestDataException("You ara already votes for this menu " + menuForVote);
                }
            }
        }
        return voteRepository.save(created);
    }
}
