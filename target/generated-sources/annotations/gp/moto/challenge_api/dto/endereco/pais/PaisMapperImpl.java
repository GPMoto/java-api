package gp.moto.challenge_api.dto.endereco.pais;

import gp.moto.challenge_api.model.Pais;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T00:08:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class PaisMapperImpl implements PaisMapper {

    @Override
    public Pais toEntity(PaisDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pais pais = new Pais();

        pais.setNmPais( dto.nmPais() );

        return pais;
    }

    @Override
    public PaisDto toDto(Pais entity) {
        if ( entity == null ) {
            return null;
        }

        String nmPais = null;

        nmPais = entity.getNmPais();

        PaisDto paisDto = new PaisDto( nmPais );

        return paisDto;
    }

    @Override
    public void updateEntityFromDto(Pais pais, PaisDto paisDto) {
        if ( pais == null ) {
            return;
        }
    }
}
