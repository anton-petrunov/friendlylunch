package ru.mygraduation.friendlylunch.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends VoteController {

    static final String REST_URL = "/rest/profile/votes";

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@RequestBody int id) {
        super.vote(id);
    }

    @GetMapping
    public Map<String, String> getVote() {
        return super.getVote();
    }
}
