package gp.moto.challenge_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gp.moto.challenge_api.exception.ForbiddenException;
import gp.moto.challenge_api.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SegurancaConfig {

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filtrarRota(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .headers(banco -> banco.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(request -> request

                        .requestMatchers("/login", "/logout", "/login/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/autenticacao/login").permitAll()
                        .requestMatchers("/api/autenticacao/view").permitAll()

                        .requestMatchers("/view/filial/editar/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/view/usuario/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMINISTRADOR")

                        .requestMatchers("/view/**").authenticated()
                        .requestMatchers("/api/**").authenticated()

                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(servidor -> servidor.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/login/index", true)
                        .failureUrl("/login?falha=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            String requestPath = request.getRequestURI();
                            if (requestPath.startsWith("/api")) {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setCharacterEncoding("UTF-8");
                                response.setContentType("application/json");
                                response.getWriter().write("{\"message\": \"Não autenticado\"}");
                            } else {
                                response.sendRedirect("/login");
                            }
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            String requestPath = request.getRequestURI();
                            if (requestPath.startsWith("/api")) {
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                response.setCharacterEncoding("UTF-8");
                                response.setContentType("application/json");
                                response.getWriter().write("{\"message\": \"Acesso negado\"}");
                            } else {
                                response.sendRedirect("/login/acesso_negado");
                            }
                        }));

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}