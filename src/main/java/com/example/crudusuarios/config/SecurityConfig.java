package com.example.crudusuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança com Spring Security.
 * Define regras de acesso, login/logout e usuários em memória.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomSuccessHandler successHandler; // Redirecionamento dinâmico após login

    /**
     * Define as regras de segurança da aplicação.
     * Controla quem pode acessar quais endpoints e como funciona o login/logout.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa CSRF (útil para APIs ou protótipos)
            .authorizeHttpRequests(auth -> auth
                // Permite que ADMIN e USER vejam a lista de usuários
                .requestMatchers(HttpMethod.GET, "/usuarios").hasAnyRole("ADMIN", "USER")

                // Qualquer outro acesso à API de usuários requer ADMIN
                .requestMatchers("/usuarios/**").hasRole("ADMIN")

                // Página de CRUD acessível para ambos, mas UI vai controlar permissões
                .requestMatchers("/crud").hasAnyRole("ADMIN", "USER")

                // Libera recursos estáticos (CSS e JS)
                .requestMatchers("/css/**", "/js/**").permitAll()

                // Qualquer outra requisição precisa estar autenticada
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                // Usa o handler customizado para redirecionar por perfil
                .successHandler(successHandler)
                .permitAll()
            )
            // Logout leva o usuário para a tela de login com mensagem
            .logout(logout -> logout.logoutSuccessUrl("/login?logout"));

        return http.build();
    }

    /**
     * Define usuários em memória para autenticação.
     * Obs: {noop} diz ao Spring que a senha não está criptografada (somente para testes).
     */
    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("admin")
            .password("{noop}123qwe!@#")
            .roles("ADMIN")
            .build());

        manager.createUser(User.withUsername("usuario")
            .password("{noop}123qwe123")
            .roles("USER")
            .build());

        return manager;
    }
}