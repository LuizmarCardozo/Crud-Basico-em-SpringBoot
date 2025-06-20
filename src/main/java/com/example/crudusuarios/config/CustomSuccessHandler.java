package com.example.crudusuarios.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * Handler customizado que define o redirecionamento após login bem-sucedido.
 * Redireciona dinamicamente com base na role do usuário autenticado.
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Após autenticação bem-sucedida, verifica a role do usuário
     * e redireciona para a página apropriada.
     *
     * - ADMIN → /crud
     * - USER  → /
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
                                        throws IOException, ServletException {
        var roles = authentication.getAuthorities().toString();

        if (roles.contains("ADMIN")) {
            response.sendRedirect("/crud");
        } else {
            response.sendRedirect("/");
        }
    }
}