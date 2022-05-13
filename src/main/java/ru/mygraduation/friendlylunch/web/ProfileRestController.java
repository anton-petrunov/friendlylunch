package ru.mygraduation.friendlylunch.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;

import java.util.List;

import static ru.mygraduation.friendlylunch.web.SecurityUtil.authUserId;

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

    @PutMapping(value = "/restaurants/{id}/voting", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int id) {
        super.vote(id, authUserId());
    }

    @GetMapping("/status")
    public String getUserVote() {
        return super.getUserVote(authUserId());
    }
}
