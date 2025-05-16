package gp.moto.challenge_api.dto.tipoMoto;

import gp.moto.challenge_api.model.TipoMoto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T11:31:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (BellSoft)"
)
@Component
public class TipoMotoMapperImpl implements TipoMotoMapper {

    @Override
    public TipoMoto toEntity(TipoMotoDto dto) {
        if ( dto == null ) {
            return null;
        }

        TipoMoto tipoMoto = new TipoMoto();

        tipoMoto.setNmTipo( dto.nome() );

        return tipoMoto;
    }

    @Override
    public TipoMotoDto toDto(TipoMoto entity) {
        if ( entity == null ) {
            return null;
        }

        String nome = null;

        nome = entity.getNmTipo();

        TipoMotoDto tipoMotoDto = new TipoMotoDto( nome );

        return tipoMotoDto;
    }

    @Override
    public void updateEntityFromDto(TipoMotoDto dto, TipoMoto tipoMoto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nome() != null ) {
            tipoMoto.setNmTipo( dto.nome() );
        }
    }
}
