package ru.mygraduation.friendlylunch.web.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static ru.mygraduation.friendlylunch.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractProfileController {

    static final String REST_URL = "/rest/profile";

    @GetMapping("/restaurants")
    public List<Restaurant> getAll() {
        return super.getRestaurantsWithCheckedDishes();
    }

    @GetMapping("/restaurants/{id}")
    public String get(@PathVariable int id) {
        return super.getDishesOfCheckedRestaurant(id);
    }

    @PutMapping(value = "/restaurants/{id}/voting")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int id) {
        super.vote(id, authUserId());
    }

    @GetMapping("/status")
    public Map<String, String> getProfileVote() {
        return super.getProfileVote(authUserId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        User created = super.createUser(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/").build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}