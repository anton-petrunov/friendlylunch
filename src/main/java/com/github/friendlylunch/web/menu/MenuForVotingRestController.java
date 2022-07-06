package com.github.friendlylunch.web.menu;

import com.github.friendlylunch.model.Menu;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = MenuForVotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuForVotingRestController extends AbstractMenuController {

    static final String REST_URL = "/rest/profile/menus-for-voting";

    @Override
    @GetMapping
    public List<Menu> getAllMenusForNextLunch() {
        return super.getAllMenusForNextLunch();
    }
}
