package gp.moto.challenge_api.dto.usuario;

import gp.moto.challenge_api.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T00:08:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioDto dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNmUsuario( dto.nome() );
        usuario.setNmEmail( dto.email() );
        usuario.setSenha( dto.senha() );
        usuario.setIdFilial( mapIdToFilial( dto.idFilial() ) );
        usuario.setIdPerfil( mapIdToPerfil( dto.idPerfil() ) );

        return usuario;
    }

    @Override
    public UsuarioDto toDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        String nome = null;
        String email = null;
        String senha = null;
        Long idFilial = null;
        Long idPerfil = null;

        nome = entity.getNmUsuario();
        email = entity.getNmEmail();
        senha = entity.getSenha();
        idFilial = mapFilialToId( entity.getIdFilial() );
        idPerfil = mapPerfilToId( entity.getIdPerfil() );

        UsuarioDto usuarioDto = new UsuarioDto( nome, email, senha, idFilial, idPerfil );

        return usuarioDto;
    }

    @Override
    public void updateEntityFromDto(UsuarioDto dto, Usuario usuario) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nome() != null ) {
            usuario.setNmUsuario( dto.nome() );
        }
        if ( dto.email() != null ) {
            usuario.setNmEmail( dto.email() );
        }
        if ( dto.senha() != null ) {
            usuario.setSenha( dto.senha() );
        }
        if ( dto.idFilial() != null ) {
            usuario.setIdFilial( mapIdToFilial( dto.idFilial() ) );
        }
        if ( dto.idPerfil() != null ) {
            usuario.setIdPerfil( mapIdToPerfil( dto.idPerfil() ) );
        }
    }
}
