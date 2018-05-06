package com.rhcloud.tutorials.quickstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BotController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    //@Value("${discord.CLIENT_ID}")
    private String clientId;

    //@Value("${discord.CLIENT_SECRET}")
    private String clientSecret;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        log.warn("Address " + request.getRemoteAddr() + " accessed the resource.");
        return "404 NOT FOUND";
    }

}