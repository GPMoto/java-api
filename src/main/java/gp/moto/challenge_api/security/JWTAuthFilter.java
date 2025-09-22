package gp.moto.challenge_api.security;

import gp.moto.challenge_api.exception.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final OpenAPI configurarSwagger;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsService usuario;

    JWTAuthFilter(OpenAPI configurarSwagger) {
        this.configurarSwagger = configurarSwagger;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            try {
                String token = header.substring(7);
                String username = jwtUtil.extrairUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = usuario.loadUserByUsername(username);

                    if (jwtUtil.validarToken(token)) {

                        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(auth);

                    }

                }
            } catch (ExpiredJwtException | MalformedJwtException | SignatureException | InvalidTokenException e) {
                if (request.getRequestURI().startsWith("/api")) {
                    // Check if response is already committed
                    if (!response.isCommitted()) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json");
                        response.getWriter().write("{\"message\": \"Não autenticado\"}");
                        response.getWriter().flush();
                    }
                    return; // Don't continue with the filter chain
                } else {
                    throw e;
                }
            }
        }

        filterChain.doFilter(request, response);

    }

}
