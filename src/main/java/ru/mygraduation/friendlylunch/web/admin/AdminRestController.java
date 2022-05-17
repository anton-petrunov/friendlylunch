package ru.mygraduation.friendlylunch.web.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController extends AbstractAdminController {

    static final String REST_URL = "/rest/admin";

    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Restaurant> createRestaurantWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = super.createRestaurant(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/restaurant/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        super.updateRestaurant(restaurant, id);
    }

    @Override
    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        return super.getRestaurant(id);
    }

    @Override
    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return super.getAllRestaurants();
    }

    @Override
    @DeleteMapping("/restaurants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable int id) {
        super.deleteRestaurant(id);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUserWithLocation(@RequestBody User user) {
        User created = super.createUser(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("user/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody User user, @PathVariable int id) {
        super.updateUser(user, id);
    }

    @Override
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        return super.getUser(userId);
    }

    @Override
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return super.getAllUsers();
    }

    @Override
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        super.deleteUser(userId);
    }

    @Override
    @GetMapping("/restaurants/{restaurantId}/dishes")
    public String getDishes(@PathVariable int restaurantId) {
        return super.getDishes(restaurantId);
    }

    @Override
    @DeleteMapping(value = "/restaurants/{restaurantId}/dishes")
    public void deleteDishes(@PathVariable int restaurantId) {
        super.deleteDishes(restaurantId);
    }

    @Override
    @PutMapping(value = "/restaurants/{restaurantId}/dishes")
    public void updateDishes(@RequestBody String dishes, @PathVariable int restaurantId) {
        super.updateDishes(dishes, restaurantId);
    }
}
