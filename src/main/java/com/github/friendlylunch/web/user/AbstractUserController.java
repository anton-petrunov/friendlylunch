package com.github.friendlylunch.web.user;

import com.github.friendlylunch.model.User;
import com.github.friendlylunch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static com.github.friendlylunch.util.ValidationUtil.checkNew;

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
        Assert.notNull(user, "user must not be null");
        assureIdConsistent(user, id);
        userService.update(user, id);
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
