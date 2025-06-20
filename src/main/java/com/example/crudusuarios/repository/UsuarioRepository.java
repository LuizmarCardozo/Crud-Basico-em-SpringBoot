package com.example.crudusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.crudusuarios.model.Usuario;

/**
 * Interface responsável por operações de acesso a dados (DAO) da entidade Usuario.
 * Estende JpaRepository para fornecer métodos prontos como salvar, deletar, buscar por ID, etc.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Não é necessário declarar métodos aqui, pois JpaRepository já fornece os métodos básicos de CRUD.
}