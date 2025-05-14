package gp.moto.challenge_api.dto.perfil;

import gp.moto.challenge_api.model.Perfil;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T11:50:48-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (BellSoft)"
)
@Component
public class PerfilMapperImpl implements PerfilMapper {

    @Override
    public Perfil toEntity(PerfilDto dto) {
        if ( dto == null ) {
            return null;
        }

        Perfil perfil = new Perfil();

        perfil.setNmPerfil( dto.nome() );

        return perfil;
    }

    @Override
    public PerfilDto toDto(Perfil entity) {
        if ( entity == null ) {
            return null;
        }

        String nome = null;

        nome = entity.getNmPerfil();

        PerfilDto perfilDto = new PerfilDto( nome );

        return perfilDto;
    }

    @Override
    public void updateEntityFromDto(PerfilDto dto, Perfil entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nome() != null ) {
            entity.setNmPerfil( dto.nome() );
        }
    }
}
