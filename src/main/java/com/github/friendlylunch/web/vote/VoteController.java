package com.github.friendlylunch.web.vote;

import com.github.friendlylunch.model.Vote;
import com.github.friendlylunch.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.web.SecurityUtil.authUserId;

public class VoteController {

    @Autowired
    VoteService voteService;

    public List<Vote> getAllByUser() {
        int userId = authUserId();
        return voteService.getAllByUser(userId);
    }

    public Vote vote(int menuId) {
        int userId = authUserId();
        return voteService.vote(userId, menuId);
    }
}
