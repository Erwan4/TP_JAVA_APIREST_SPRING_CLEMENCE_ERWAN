package com.bootstrap.clipper.controllers;

import com.bootstrap.clipper.clients.QuarkusClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clipper/stores")
public class StoreProxyController {

    private final QuarkusClientService quarkusClientService;

    public StoreProxyController(QuarkusClientService quarkusClientService) {
        this.quarkusClientService = quarkusClientService;
    }

    @GetMapping
    public String fetchStoresFromQuarkus() {
        return quarkusClientService.getStores();
    }
}