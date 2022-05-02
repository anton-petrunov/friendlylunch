package ru.mygraduation.friendlylunch;

import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.mygraduation.friendlylunch.FirstCheckData.restaurants;
import static ru.mygraduation.friendlylunch.FirstCheckData.users;
import static ru.mygraduation.friendlylunch.Util.*;

public class ProfileControllerPrototype {

    public ProfileControllerPrototype() {
    }

    public List<Restaurant> getAllRestaurantsForVoting() {
        return restaurants.stream()
                .filter(Util::checkMenu)
                .collect(Collectors.toList());
    }

    public String getRestaurantMenuForVoting(int restaurantId) {
        Map<Integer, Restaurant> restaurantsWithId = getRestaurantsWithId(restaurants);
        try {
            return checkMenu(restaurantsWithId.get(restaurantId)) ? restaurantsWithId.get(restaurantId).getDishes() : null;
        } catch (NullPointerException e) {
            throw new NullPointerException("This restaurant id is not exists");
        }
    }

    public void vote(int restaurantId, int userId) {
        Map<Integer, User> usersWithId = getUsersWithId(users);
        int userNumber = 0;
        for (Map.Entry<Integer, User> entry : usersWithId.entrySet()) {
            if (userId != entry.getKey()) {
                userNumber++;
            } else {
                break;
            }
        }

        if (usersWithId.get(userId).getVotedFor() == 0 || checkVotingAvailability(usersWithId.get(userId))) {
            users.get(userNumber).setVotedFor(restaurantId);
            users.get(userNumber).setVotingDateTime(LocalDateTime.now());

        } else if (usersWithId.get(userId).getVotedFor() > 0 && checkRevoteAvailability(usersWithId.get(userId))) {
            users.get(userNumber).setVotedFor(restaurantId);
            users.get(userNumber).setVotingDateTime(LocalDateTime.now());
        }
    }

    public String checkVotingStatus(int userId) {
        Map<Integer, User> usersWithId = getUsersWithId(users);
        if (checkVote(usersWithId.get(userId))) {
            return "User # " + userId + " votes for Restaurant # " + usersWithId.get(userId).getVotedFor() +
                    " Voting DateTime: " + usersWithId.get(userId).getVotingDateTime().toString() + "\n" +
                    "Menu is: " + getRestaurantMenuForVoting(3);
        } else {
            return "\nTHE VOTING of this User IS OUT OF DATE";
        }
    }

    public Map<Integer, Long> checkVotingResults() {
        return users.stream().filter(Util::checkVote)
                .collect(Collectors.groupingBy(User::getVotedFor, Collectors.counting()));
    }
}
