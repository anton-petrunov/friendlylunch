package com.github.friendlylunch.web.dish;

import com.github.friendlylunch.model.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController extends AbstractDishController {

    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/menus/{menuId}/dishes";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish,
                                                   @PathVariable int restaurantId, @PathVariable int menuId) {
        Dish created = super.create(dish, restaurantId, menuId);
        System.out.println(created.getMenu());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getMenu().getRestaurant().getId(),
                        created.getMenu().getId(), created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int restaurantId,
                       @PathVariable int menuId, @PathVariable int id) {
        super.update(dish, restaurantId, menuId, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId,
                       @PathVariable int menuId, @PathVariable int id) {
        super.delete(restaurantId, menuId, id);
    }

    @Override
    @GetMapping("/{id}")
    public Dish get(@PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int id) {
        return super.get(restaurantId, menuId, id);
    }

    @Override
    @GetMapping
    public List<Dish> getAll(@PathVariable int restaurantId, @PathVariable int menuId) {
        return super.getAll(restaurantId, menuId);
    }
}
