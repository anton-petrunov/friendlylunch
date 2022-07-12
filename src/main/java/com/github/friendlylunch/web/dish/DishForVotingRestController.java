package com.github.friendlylunch.web.dish;

import com.github.friendlylunch.model.Dish;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = DishForVotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishForVotingRestController extends AbstractDishController {

    static final String REST_URL = "/rest/profile/restaurants/{restaurantId}/menuId/{menuId}/dishes";

    @Override
    @GetMapping()
    public List<Dish> getAllChecked(@PathVariable int restaurantId, @PathVariable int menuId) {
        return super.getAllChecked(restaurantId, menuId);
    }

    @Override
    @GetMapping("/{id}")
    public Dish getChecked(@PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int id) {
        return super.getChecked(restaurantId, menuId, id);
    }
}
