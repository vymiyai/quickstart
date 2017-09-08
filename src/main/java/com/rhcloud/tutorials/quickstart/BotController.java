package com.rhcloud.tutorials.quickstart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {

    //@Value("${discord.CLIENT_ID}")
    private String clientId;

    //@Value("${discord.CLIENT_SECRET}")
    private String clientSecret;

    @RequestMapping("/")
    public String index() {
        String response = "Bot is probably alive.";
        return response;
    }

}