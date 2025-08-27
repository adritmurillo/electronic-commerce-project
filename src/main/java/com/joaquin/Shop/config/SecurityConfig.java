// ANTES DEL ULTIMO CAMBIO

package com.joaquin.Shop.config;

import com.joaquin.Shop.domain.port.UserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;

@Configuration
public class SecurityConfig {

    private final UserPort userPort;

    public SecurityConfig(UserPort userPort) {
        this.userPort = userPort;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/products/**", "/api/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginProcessingUrl("/api/login")
                        .usernameParameter(SPRING_SECURITY_FORM_USERNAME_KEY) // Asegura el parámetro correcto
                        .passwordParameter(SPRING_SECURITY_FORM_PASSWORD_KEY) // Asegura el parámetro correcto
                        .successHandler((request, response, authentication) -> {
                            response.setStatus(200);
                            response.getWriter().write("{\"message\": \"Login successful for: " + authentication.getName() + "\"}");
                        })
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .csrf(AbstractHttpConfigurer::disable); // Deshabilitar CSRF temporalmente

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return username -> userPort.findByUsername(username)
                .map(user -> {
                    System.out.println("Usuario encontrado: " + user.getUsername() + ", password: " + user.getPassword());
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            java.util.Collections.emptyList());
                })
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found: " + username));
    }
}