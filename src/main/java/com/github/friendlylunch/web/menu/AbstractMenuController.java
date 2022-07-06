package com.github.friendlylunch.web.menu;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static com.github.friendlylunch.util.ValidationUtil.checkNew;

public abstract class AbstractMenuController {

    @Autowired
    MenuService menuService;

    public Menu create(Menu menu) {
        checkNew(menu);
        return menuService.create(menu);
    }

    public void update(Menu menu, int id) {
        assureIdConsistent(menu, id);
        menuService.update(menu);
    }

    public void delete(int restaurantId, int id) {
        menuService.delete(restaurantId, id);
    }

    public Menu get(int restaurantId, int id) {
        return menuService.get(restaurantId, id);
    }

    public List<Menu> getAll(int restaurantId) {
        return menuService.getAll(restaurantId);
    }

    public List<Menu> getAllMenusForNextLunch() {
        return menuService.getAllMenusForNextLunch();
    }
}
