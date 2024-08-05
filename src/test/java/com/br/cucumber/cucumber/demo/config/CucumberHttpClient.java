package com.br.cucumber.cucumber.demo.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
//@Scope(SCOPE_CUCUMBER_GLUE)
public class CucumberHttpClient {

    private final RestTemplate restTemplate;

    private final String url = "http://localhost:8080";

    public CucumberHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public HttpEntity<?> cucumberGet(final String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.getForEntity(url + path, String.class);
    }

}
