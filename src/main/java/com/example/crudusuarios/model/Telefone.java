package com.example.crudusuarios.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

/**
 * Representa um telefone vinculado ao usuário.
 */
@Embeddable
public class Telefone {

    @NotBlank
    private String tipo;   // Ex: residencial, comercial, celular

    @NotBlank
    private String numero; // Armazenado sem máscara

    // Getters e setters

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}