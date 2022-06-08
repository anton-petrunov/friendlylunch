package ru.mygraduation.friendlylunch.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.service.RestaurantService;
import ru.mygraduation.friendlylunch.service.UserService;

import java.time.LocalDateTime;
import java.util.Map;

import static ru.mygraduation.friendlylunch.util.Util.*;

public class VoteController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    public void vote(int restaurantId, int userId) {
        log.info("vote of user {} for restaurant {}", userId, restaurantId);
        if (checkDishes(restaurantService.get(restaurantId))) {
            User user = userService.get(userId);
            if (checkVotingAvailability(user) || checkRevoteAvailability(user)) {
                user.setVotedFor(restaurantId);
                user.setVotingDateTime(LocalDateTime.now());
                userService.updateWithoutPasswordEncoding(user, userId);
            }
        }
    }

    public Map<String, String> getProfileVote(int id) {
        log.info("get vote of user {}", id);
        User user = userService.get(id);
        return Map.of("VotedFor", user.getVotedFor().toString(),
                "VotingDateTime", user.getVotingDateTime().toString());
    }
}