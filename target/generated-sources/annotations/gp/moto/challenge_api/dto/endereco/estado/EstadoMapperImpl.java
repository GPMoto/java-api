package gp.moto.challenge_api.dto.endereco.estado;

import gp.moto.challenge_api.model.Estado;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T00:08:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class EstadoMapperImpl implements EstadoMapper {

    @Override
    public Estado toEntity(EstadoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Estado estado = new Estado();

        estado.setNmEstado( dto.nome() );
        estado.setIdPais( mapIdToPais( dto.idPais() ) );

        return estado;
    }

    @Override
    public EstadoDto toDto(Estado estado) {
        if ( estado == null ) {
            return null;
        }

        String nome = null;
        Long idPais = null;

        nome = estado.getNmEstado();
        idPais = mapPaisToId( estado.getIdPais() );

        EstadoDto estadoDto = new EstadoDto( idPais, nome );

        return estadoDto;
    }

    @Override
    public void updateEntityFromDto(EstadoDto estadoDto, Estado estado) {
        if ( estadoDto == null ) {
            return;
        }

        if ( estadoDto.idPais() != null ) {
            estado.setIdPais( mapIdToPais( estadoDto.idPais() ) );
        }
    }
}
