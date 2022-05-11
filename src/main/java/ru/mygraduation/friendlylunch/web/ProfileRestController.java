package ru.mygraduation.friendlylunch.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends ProfileController {

    public ProfileRestController(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        super(userRepository, restaurantRepository);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAll() {
        return super.getRestaurantsWithMenuChecked();
    }

    @GetMapping("/restaurants/{id}")
    public String get(@PathVariable int id) {
        return super.getRestaurantMenuChecked(id);
    }

    @GetMapping("/status/{id}")
    public String getUserVote(@PathVariable int id) {
        return super.getUserVote(id);
    }
}
