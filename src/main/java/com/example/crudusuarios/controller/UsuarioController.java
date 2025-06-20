package com.example.crudusuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.crudusuarios.model.Usuario;
import com.example.crudusuarios.repository.UsuarioRepository;

import jakarta.validation.Valid;

/**
 * Controlador REST responsável por operações de CRUD para a entidade Usuario.
 * Os endpoints expõem rotas HTTP acessíveis a partir de /usuarios.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Retorna uma lista com todos os usuários cadastrados no banco.
     * Método acessado via GET em /usuarios.
     */
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Cria um novo usuário com os dados recebidos no corpo da requisição.
     * Valida os campos de acordo com as anotações da classe Usuario.
     * Acesso: POST em /usuarios
     */
    @PostMapping
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca um único usuário pelo ID informado na URL.
     * Se não encontrar, retorna null.
     * Acesso: GET em /usuarios/{id}
     */
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Atualiza os dados de um usuário com base no ID.
     * Se o usuário existir, aplica os novos dados recebidos e salva.
     * Acesso: PUT em /usuarios/{id}
     */
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario atualizado) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                // Atualiza os campos manualmente
                usuario.setNome(atualizado.getNome());
                usuario.setCpf(atualizado.getCpf());
                usuario.setCep(atualizado.getCep());
                usuario.setLogradouro(atualizado.getLogradouro());
                usuario.setBairro(atualizado.getBairro());
                usuario.setCidade(atualizado.getCidade());
                usuario.setUf(atualizado.getUf());
                usuario.setComplemento(atualizado.getComplemento());
                usuario.setEmails(atualizado.getEmails());
                usuario.setTelefones(atualizado.getTelefones());
                return usuarioRepository.save(usuario);
            })
            .orElse(null);
    }

    /**
     * Deleta um usuário com base no ID fornecido na URL.
     * Acesso: DELETE em /usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}