package com.rhcloud.tutorials.quickstart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Value("${aoe2.wololo}")
	private String wololo;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot! " + System.getenv("POSTGRESQL_PASSWORD");
    }

}