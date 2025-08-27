package com.joaquin.Shop.adapter.in.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthRestAdapter {

    @GetMapping("/profile") // Ejemplo de endpoint protegido
    public String profile(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "Profile for: " + authentication.getName();
        }
        return "Unauthorized";
    }
}