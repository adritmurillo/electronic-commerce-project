package com.joaquin.Shop.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestAdapter {

    @GetMapping("/test")
    public String testEndpoint() {
        return "Endpoint de prueba funcionando!";
    }
}