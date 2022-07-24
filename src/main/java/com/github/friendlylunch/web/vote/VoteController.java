package com.github.friendlylunch.web.vote;

import com.github.friendlylunch.model.Vote;
import com.github.friendlylunch.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.web.SecurityUtil.authUserId;

public class VoteController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    VoteService voteService;

    public List<Vote> getAll() {
        int userId = authUserId();
        log.info("getAllByUser {}", authUserId());
        return voteService.getAll(userId);
    }

    public Vote vote(int menuId) {
        int userId = authUserId();
        log.info("vote of user {} for menu {}", userId, menuId);
        return voteService.vote(userId, menuId);
    }
}
