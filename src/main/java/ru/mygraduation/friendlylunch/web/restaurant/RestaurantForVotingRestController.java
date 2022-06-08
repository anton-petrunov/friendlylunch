package ru.mygraduation.friendlylunch.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mygraduation.friendlylunch.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantForVotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantForVotingRestController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/profile/restaurants";

    @GetMapping
    public List<Restaurant> getRestaurantsWithCheckedDishes() {
        return super.getRestaurantsWithCheckedDishes();
    }

    @GetMapping("/{id}")
    public String getDishesOfCheckedRestaurant(@PathVariable int id) {
        return super.getDishesOfCheckedRestaurant(id);
    }
}
