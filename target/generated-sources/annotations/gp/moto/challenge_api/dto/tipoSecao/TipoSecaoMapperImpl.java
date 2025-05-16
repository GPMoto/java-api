package gp.moto.challenge_api.dto.tipoSecao;

import gp.moto.challenge_api.model.TipoSecao;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T00:08:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class TipoSecaoMapperImpl implements TipoSecaoMapper {

    @Override
    public TipoSecao toEntity(TipoSecaoDto dto) {
        if ( dto == null ) {
            return null;
        }

        TipoSecao tipoSecao = new TipoSecao();

        tipoSecao.setNmSecao( dto.nomeSecao() );

        return tipoSecao;
    }

    @Override
    public TipoSecaoDto toDto(TipoSecao entity) {
        if ( entity == null ) {
            return null;
        }

        String nomeSecao = null;

        nomeSecao = entity.getNmSecao();

        TipoSecaoDto tipoSecaoDto = new TipoSecaoDto( nomeSecao );

        return tipoSecaoDto;
    }

    @Override
    public void updateEntityFromDto(TipoSecaoDto dto, TipoSecao entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nomeSecao() != null ) {
            entity.setNmSecao( dto.nomeSecao() );
        }
    }
}
