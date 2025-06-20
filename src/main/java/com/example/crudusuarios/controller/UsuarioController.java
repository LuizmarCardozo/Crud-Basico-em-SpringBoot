package com.example.crudusuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.crudusuarios.model.Usuario;
import com.example.crudusuarios.repository.UsuarioRepository;

/**
 * Controlador REST para gerenciamento dos usuários.
 * Expõe endpoints para realizar operações CRUD via HTTP.
 */
@RestController
@RequestMapping("/usuarios") // URL base para todas as operações com "Usuario"
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Retorna todos os usuários cadastrados.
     */
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Cria um novo usuário com os dados enviados no corpo da requisição.
     */
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca um usuário pelo ID fornecido na URL.
     */
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Atualiza um usuário existente com base no ID e nos novos dados enviados.
     */
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setEmail(usuarioAtualizado.getEmail());
                return usuarioRepository.save(usuario);
            })
            .orElse(null); // Pode ser substituído por ResponseEntity.notFound() para lidar melhor com erros
    }

    /**
     * Deleta um usuário com base no ID fornecido.
     */
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}