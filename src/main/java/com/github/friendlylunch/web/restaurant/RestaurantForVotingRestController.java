package com.github.friendlylunch.web.restaurant;

import com.github.friendlylunch.model.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantForVotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantForVotingRestController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/profile/restaurants";

    @Override
    @GetMapping("/")
    public List<Restaurant> getAllChecked() {
        return super.getAllChecked();
    }

    @Override
    @GetMapping("/with-menus")
    public List<Restaurant> getAllCheckedWithMenus() {
        return super.getAllCheckedWithMenus();
    }

    @Override
    @GetMapping("/{id}")
    public Restaurant getChecked(@PathVariable int id) {
        return super.getChecked(id);
    }
}
