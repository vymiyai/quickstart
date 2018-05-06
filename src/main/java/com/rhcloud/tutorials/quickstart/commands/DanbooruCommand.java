package com.rhcloud.tutorials.quickstart.commands;

import com.rhcloud.tutorials.quickstart.DanbooruPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

@Component
public class DanbooruCommand implements IBotCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(DanbooruCommand.class);

    private String safebooruEndpoint = "https://safebooru.donmai.us/posts.json";
    private String danbooruEndpoint = "https://danbooru.donmai.us/posts.json";

    @Override
    public String execute(String[] tokenizedMessage, MessageReceivedEvent event) {

        // default result.
        String tags = tokenizedMessage.length == 1 ? "fav:Darkagma" : event.getMessage().getContent().split(" ", 2)[1];

        // determine endpoint.
        String targetEndpoint = event.getChannel().isNSFW() ? danbooruEndpoint : safebooruEndpoint;

        // log.
        LOGGER.info("Queried endpoint: {} | Tags: {}", targetEndpoint, tags);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(targetEndpoint)
                .queryParam("limit", 1)
                .queryParam("random", true)
                .queryParam("tags",tags);


        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<DanbooruPost[]> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                DanbooruPost[].class);

        if(response.getBody().length > 0)
            return response.getBody()[0].toString();
        else
            return "No results found.";
    }

    @Override
    public String getCommandName() {
        return "!danbooru";
    }

    @Override
    public String getCommandDescription() {
        return "Type !danbooru search_term for a random picture.";
    }
}
