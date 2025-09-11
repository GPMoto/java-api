package gp.moto.challenge_api.security;

import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
public class UsuarioConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Bean
    UserDetailsService gerarUsuario() {
        return username -> {
            Usuario usuario = usuarioRepository.findByNmUsuario(username)
                    .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

            return User.builder().username(usuario.getNmUsuario())
                    .password(usuario.getSenha())
                    .roles(usuario.getIdPerfil().getNmPerfil().toString())
                    .build();
        };
    }

}
