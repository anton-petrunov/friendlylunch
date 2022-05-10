package ru.mygraduation.friendlylunch.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractProfileController {

    public ProfileRestController(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        super(userRepository, restaurantRepository);
    }

    @GetMapping
    public List<Restaurant> get() {
        return super.getRestaurantsWithMenuChecked();
    }
}
