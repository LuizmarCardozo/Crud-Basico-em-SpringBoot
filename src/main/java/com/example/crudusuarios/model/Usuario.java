package com.example.crudusuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidade que representa um usuário no sistema.
 * Será mapeada para uma tabela no banco de dados automaticamente pelo JPA.
 */
@Entity
public class Usuario {

    // Identificador único gerado automaticamente (chave primária).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do usuário.
    private String nome;

    // Email do usuário.
    private String email;

    // Getter do ID (não possui setter, pois o ID é gerado pelo banco).
    public Long getId() {
        return id;
    }

    // Getter e Setter do nome.
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter do email.
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}