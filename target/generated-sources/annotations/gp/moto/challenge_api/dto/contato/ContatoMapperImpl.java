package gp.moto.challenge_api.dto.contato;

import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.model.Telefone;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T11:31:35-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (BellSoft)"
)
@Component
public class ContatoMapperImpl implements ContatoMapper {

    @Override
    public ContatoDTO toDto(Contato entity) {
        if ( entity == null ) {
            return null;
        }

        String nmDono = null;
        Integer status = null;
        Long idTelefone = null;

        nmDono = entity.getNmDono();
        status = entity.getStatus();
        idTelefone = mapTelefoneToId( entity.getIdTelefone() );

        ContatoDTO contatoDTO = new ContatoDTO( nmDono, status, idTelefone );

        return contatoDTO;
    }

    @Override
    public Contato toEntity(ContatoDTO contatoDTO) {
        if ( contatoDTO == null ) {
            return null;
        }

        Contato contato = new Contato();

        contato.setNmDono( contatoDTO.nmDono() );
        if ( contatoDTO.status() != null ) {
            contato.setStatus( contatoDTO.status() );
        }
        contato.setIdTelefone( mapIdToTelefone( contatoDTO.idTelefone() ) );

        return contato;
    }

    @Override
    public ContatoProjection toProjection(Contato contato) {
        if ( contato == null ) {
            return null;
        }

        String nmDono = null;
        Integer status = null;
        Telefone idTelefone = null;

        nmDono = contato.getNmDono();
        status = contato.getStatus();
        idTelefone = contato.getIdTelefone();

        ContatoProjection contatoProjection = new ContatoProjection( nmDono, status, idTelefone );

        return contatoProjection;
    }

    @Override
    public void updateEntityFromDto(ContatoDTO dto, Contato entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nmDono() != null ) {
            entity.setNmDono( dto.nmDono() );
        }
        if ( dto.status() != null ) {
            entity.setStatus( dto.status() );
        }
        if ( dto.idTelefone() != null ) {
            entity.setIdTelefone( mapIdToTelefone( dto.idTelefone() ) );
        }
    }
}
