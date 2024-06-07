package com.example.tgbot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Import(SecurityConfig.class)
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/news").setViewName("news");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mines/**")  // Добавьте двойной символ звездочки для включения всех подпутей
                .addResourceLocations("classpath:/mines/");
        registry.addResourceHandler("/static/**")  // Добавьте двойной символ звездочки для включения всех подпутей
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/webjars/"); // Удалите "**" из пути
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/images/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/js/");
    }

}
