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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SegurancaConfig {

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filtrarRota(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable)
            .headers(banco -> banco.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .authorizeHttpRequests(request ->
                request
                    .requestMatchers(HttpMethod.POST, "/api/usuario", "/api/autenticacao/login", "/login","/view/moto","/view/moto/teste", "/view/moto/**","/view/filial/**","/view/uwb/editar/**").permitAll()
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/api/autenticacao/view", "/view/moto/nova",
                        "/login/index", "/view/moto/editar/**" , "/view/moto/remover/**","/view/filial/**","/view/filial/editar/**"
                        , "/view/uwb/**").permitAll()
                    .requestMatchers("/login", "/logout", "/login/**").permitAll()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(servidor ->
                servidor.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
            .formLogin(login -> 
                login
                    .loginPage("/login")
                    .defaultSuccessUrl("/login/index", true)
                    .failureUrl("/login?falha=true")
                    .permitAll()
            )
            .logout(logout -> 
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
            )
            .exceptionHandling(exception -> 
                exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/acesso_negado");
                })
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}