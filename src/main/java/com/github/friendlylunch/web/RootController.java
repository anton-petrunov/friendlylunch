package com.github.friendlylunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "/index.html";
    }

    @GetMapping("/index")
    public String index() {
        log.info("index");
        return "/index.html";
    }
}
