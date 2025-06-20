package com.example.crudusuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsável por exibir as páginas da aplicação (views).
 * Retorna os nomes dos arquivos HTML dentro de src/main/resources/templates.
 */
@Controller
public class ViewController {

    /**
     * Mapeia a raiz do site ("/") e renderiza a tela de listagem de usuários.
     * Exemplo: GET http://localhost:8080/
     */
    @GetMapping("/")
    public String index() {
        return "index"; // Renderiza o arquivo index.html (sem extensão)
    }

    /**
     * Mapeia a rota "/crud" e renderiza a página de gerenciamento de usuários.
     * Exemplo: GET http://localhost:8080/crud
     */
    @GetMapping("/crud")
    public String crud() {
        return "crud"; // Renderiza o arquivo crud.html
    }
}