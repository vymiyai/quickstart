package com.rhcloud.tutorials.quickstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    // as of 23.06.2017, moving the autowired annotation from the setter to this
    // property fucks up everything.
    private static CommandHandler commandHandler;

    @Autowired
    private void setCommandHandler(CommandHandler ch) {
        commandHandler = ch;
    }

    private static String clientToken;

    @Value("${discord.CLIENT_TOKEN}")
    private void setClientToken(String ct) {
        clientToken = ct;
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        try {
            IDiscordClient cli = new ClientBuilder().withToken(clientToken).withRecommendedShardCount().build();

            cli.getDispatcher().registerListener(commandHandler);

            // Only login after all events are registered otherwise some may be
            // missed.
            cli.login();
        } catch (Exception e) {
            // do nothing.
            log.warn("WARNING - Discord4J :" + e.getMessage());
        }
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            log.info("Application is now online.");
        };
    }
}