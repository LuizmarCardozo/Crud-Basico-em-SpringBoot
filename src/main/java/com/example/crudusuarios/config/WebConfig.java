package com.example.crudusuarios.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração personalizada do Spring MVC.
 * Responsável por redirecionar a URL "/crud" para o arquivo "crud.html".
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redireciona requisições para "/crud" diretamente para o arquivo "crud.html"
        // Útil quando você quer servir páginas estáticas fora do template engine (como Thymeleaf)
        registry.addViewController("/crud").setViewName("forward:/crud.html");
    }
}