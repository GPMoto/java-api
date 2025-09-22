package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.security.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/autenticacao")
public class AutenticacaoController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String gerarTokenValido(@RequestParam String username, @RequestParam String password) {
        try {

            var auth = new UsernamePasswordAuthenticationToken(username, password);

            authenticationManager.authenticate(auth);

            return jwtUtil.construirToken(username);
        } catch (AuthenticationException e) {
            // Específico para erros de autenticação
            throw new ResourceNotFoundException("Usuário ou senha inválidos");
        } catch (Exception e) {
            // Log da exceção real para debug
            System.err.println("Erro inesperado durante login: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro interno durante autenticação");
        }
    }

}
