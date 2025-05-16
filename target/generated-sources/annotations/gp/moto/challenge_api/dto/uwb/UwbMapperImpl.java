package gp.moto.challenge_api.dto.uwb;

import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Uwb;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T00:08:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class UwbMapperImpl implements UwbMapper {

    @Override
    public Uwb toEntity(UwbDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Moto idMoto = null;
        String vlUwb = null;

        idMoto = mapIdToMoto( dto.idMoto() );
        vlUwb = dto.vlUwb();

        Uwb uwb = new Uwb( idMoto, vlUwb );

        return uwb;
    }

    @Override
    public UwbDTO toDto(Uwb entity) {
        if ( entity == null ) {
            return null;
        }

        String vlUwb = null;
        Long idMoto = null;

        vlUwb = entity.getVlUwb();
        idMoto = mapMotoToId( entity.getIdMoto() );

        UwbDTO uwbDTO = new UwbDTO( vlUwb, idMoto );

        return uwbDTO;
    }

    @Override
    public void updateEntityFromDto(UwbDTO dto, Uwb entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.idMoto() != null ) {
            entity.setIdMoto( mapIdToMoto( dto.idMoto() ) );
        }
        if ( dto.vlUwb() != null ) {
            entity.setVlUwb( dto.vlUwb() );
        }
    }
}
