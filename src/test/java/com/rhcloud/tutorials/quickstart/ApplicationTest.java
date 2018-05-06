package com.rhcloud.tutorials.quickstart;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ApplicationTest {

    @Test
    public void testApplication(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://safebooru.donmai.us/posts.json")
                .queryParam("limit", 1)
                .queryParam("random", true)
                .queryParam("tags","kantai_collection fav:Darkagma");


        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<DanbooruPost[]> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                DanbooruPost[].class);

        Assert.assertTrue(response.getBody().length == 1);
    }
}
