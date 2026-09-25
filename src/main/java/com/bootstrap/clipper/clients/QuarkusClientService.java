package com.bootstrap.clipper.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class QuarkusClientService {

    private final RestClient restClient;

    public QuarkusClientService(@Value("${clipper.quarkus-server-url}") String quarkusUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(quarkusUrl)
                .build();
    }

    public String getStores() {
        return restClient.get()
                .uri("/api/stores")
                .retrieve()
                .body(String.class);
    }
}