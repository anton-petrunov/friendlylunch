package ru.mygraduation.friendlylunch.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static ru.mygraduation.friendlylunch.util.JsonUtil.checkJsonMap;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController extends DishController {

    static final String REST_URL = "/rest/admin/restaurants/{id}/dishes";

    @Override
    @GetMapping
    public String get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody String dishes, @PathVariable int id) {
        checkJsonMap(dishes);
        super.update(dishes, id);
    }
}
