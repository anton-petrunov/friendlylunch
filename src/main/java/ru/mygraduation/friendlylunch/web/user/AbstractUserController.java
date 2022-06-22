package ru.mygraduation.friendlylunch.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.service.UserService;

import java.util.List;

import static ru.mygraduation.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return userService.create(user);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        userService.updateWithPasswordEncoding(user);
    }

    public User get(int id) {
        log.info("get user {}", id);
        return userService.get(id);
    }

    public List<User> getAll() {
        log.info("getAll users");
        return userService.getAll();
    }

    public void delete(int id) {
        log.info("delete user {}", id);
        userService.delete(id);
    }
}
