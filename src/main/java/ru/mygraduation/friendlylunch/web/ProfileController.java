package ru.mygraduation.friendlylunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;
import ru.mygraduation.friendlylunch.util.exception.NotFoundException;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.mygraduation.friendlylunch.util.Util.*;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.checkNew;

@ApiIgnore
@Controller
public class ProfileController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;
    private PasswordEncoder passwordEncoder;

    public ProfileController(UserRepository userRepository, RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Restaurant> getRestaurantsWithMenuChecked() {
        log.info("getAll restaurants with menu checked");
        return restaurantRepository.getBetween(previousLunchDateTime(), nextLunchDateTime()).stream()
                .filter(r -> r.getDishes() != null)
                .collect(Collectors.toList());
    }

    public String getRestaurantMenuChecked(int id) {
        log.info("get dishes of checked restaurant {}", id);
        Restaurant restaurant = restaurantRepository.get(id);
        if (checkMenu(restaurant)) {
            return restaurant.getDishes();
        } else {
            throw new NotFoundException("Restaurant " + id + " is not available for voting");
        }
    }

    public void vote(int restaurantId, int userId) {
        log.info("vote of user {} for restaurant {}", userId, restaurantId);
        if (checkMenu(restaurantRepository.get(restaurantId))) {
            User user = userRepository.get(userId);
            if (checkVotingAvailability(user) || checkRevoteAvailability(user)) {
                user.setVotedFor(restaurantId);
                user.setVotingDateTime(LocalDateTime.now());
                userRepository.save(user);
            }
        }
    }

    public String getUserVote(int id) {
        User user = userRepository.get(id);
        log.info("get vote of user {}", id);
        return user.getVotedFor() + "\n" + user.getVotingDateTime().toString();
    }

    // todo REFACTOR User Create

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }
}
