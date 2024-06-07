package com.example.tgbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        "/registration/**", "/deposit/**","/redeposit/**", "/mines/**", "/luckyjet/**",
                                        "/images/**", "/styles/**", "/static/**", "/resources/**", "/aviator/**",
                                        "/coin/**"
                                ).permitAll()
                                .anyRequest().denyAll()
                )
                .httpBasic(httpBasic -> httpBasic.disable()); // Отключить базовую аутентификацию

        return http.build();
    }
}