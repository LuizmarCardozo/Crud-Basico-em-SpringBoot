package com.example.crudusuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um usuário no sistema.
 * Contém atributos pessoais, endereço, telefones e e-mails.
 */
@Entity
public class Usuario {

    // Identificador único gerado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome obrigatório, entre 3 e 100 caracteres
    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    // CPF obrigatório, validado com anotação do Hibernate
    @NotBlank
    @CPF
    private String cpf;

    // Endereço completo — todos os campos obrigatórios
    @NotBlank private String cep;            // Código postal
    @NotBlank private String logradouro;     // Rua ou avenida
    @NotBlank private String bairro;         // Bairro
    @NotBlank private String cidade;         // Cidade
    @NotBlank private String uf;             // Estado (sigla)
    @NotBlank private String complemento;    // Complemento (ex: apto, bloco)

    /**
     * Lista de telefones associados ao usuário.
     * Cada telefone é embutido como valor (não é uma entidade).
     * Armazenados na tabela 'usuario_telefones'.
     */
    @ElementCollection
    @CollectionTable(name = "usuario_telefones", joinColumns = @JoinColumn(name = "usuario_id"))
    private List<Telefone> telefones = new ArrayList<>();

    /**
     * Lista de e-mails do usuário.
     * Validação individual para garantir que cada item seja um e-mail válido e não em branco.
     * Armazenados na tabela 'usuario_emails'.
     */
    @ElementCollection
    @CollectionTable(name = "usuario_emails", joinColumns = @JoinColumn(name = "usuario_id"))
    private List<@NotBlank @Email String> emails = new ArrayList<>();

    // ===================== Getters e Setters =====================

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<String> getEmails() {
        return emails;
    }
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}