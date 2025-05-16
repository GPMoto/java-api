package gp.moto.challenge_api.dto.telefone;

import gp.moto.challenge_api.model.Telefone;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T11:31:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (BellSoft)"
)
@Component
public class TelefoneMapperImpl implements TelefoneMapper {

    @Override
    public Telefone toEntity(TelefoneDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Telefone telefone = new Telefone();

        telefone.setDdi( dto.ddi() );
        telefone.setDdd( dto.ddd() );
        telefone.setNumero( dto.numero() );

        return telefone;
    }

    @Override
    public TelefoneDTO toDto(Telefone telefone) {
        if ( telefone == null ) {
            return null;
        }

        String ddi = null;
        String ddd = null;
        String numero = null;

        ddi = telefone.getDdi();
        ddd = telefone.getDdd();
        numero = telefone.getNumero();

        TelefoneDTO telefoneDTO = new TelefoneDTO( ddi, ddd, numero );

        return telefoneDTO;
    }

    @Override
    public TelefoneProjection toProjection(Telefone telefone) {
        if ( telefone == null ) {
            return null;
        }

        Long id_telefone = null;
        String ddi = null;
        String ddd = null;
        String numero = null;

        id_telefone = telefone.getId_telefone();
        ddi = telefone.getDdi();
        ddd = telefone.getDdd();
        numero = telefone.getNumero();

        TelefoneProjection telefoneProjection = new TelefoneProjection( id_telefone, ddi, ddd, numero );

        return telefoneProjection;
    }

    @Override
    public void updateTelefoneFromDto(TelefoneDTO dto, Telefone entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.ddi() != null ) {
            entity.setDdi( dto.ddi() );
        }
        if ( dto.ddd() != null ) {
            entity.setDdd( dto.ddd() );
        }
        if ( dto.numero() != null ) {
            entity.setNumero( dto.numero() );
        }
    }
}
