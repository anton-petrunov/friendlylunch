package ru.mygraduation.friendlylunch.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.service.RestaurantService;
import ru.mygraduation.friendlylunch.service.UserService;
import ru.mygraduation.friendlylunch.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.Map;

import static ru.mygraduation.friendlylunch.util.Util.*;

public class VoteController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    public void vote(int id) {
        User user = SecurityUtil.get().getUser();
        log.info("vote of user {} for restaurant {}", user.getId(), id);
        if (checkDishes(restaurantService.get(id))) {
            if (checkVotingAvailability(user) || checkRevoteAvailability(user)) {
                user.setVotedFor(id);
                user.setVotingDateTime(LocalDateTime.now());
                userService.updateWithoutPasswordEncoding(user);
            }
        }
    }

    public Map<String, String> getVote() {
        int userId = SecurityUtil.authUserId();
        log.info("get vote of user {}", userId);
        User user = userService.get(userId);
        return Map.of("VotedFor", user.getVotedFor().toString(),
                "VotingDateTime", user.getVotingDateTime().toString());
    }
}