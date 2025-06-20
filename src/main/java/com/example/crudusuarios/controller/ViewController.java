package com.example.crudusuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsável por mapear rotas de visualização (views).
 * Retorna os nomes dos templates HTML localizados em: src/main/resources/templates
 */
@Controller
public class ViewController {

    /**
     * Redireciona a rota raiz ("/") para o arquivo index.html.
     */
    @GetMapping("/")
    public String index() {
        return "index";  // Renderiza o template index.html
    }

    /**
     * Redireciona a rota "/crud" para o arquivo crud.html.
     */
    @GetMapping("/crud")
    public String crud() {
        return "crud";  // Renderiza o template crud.html
    }
}