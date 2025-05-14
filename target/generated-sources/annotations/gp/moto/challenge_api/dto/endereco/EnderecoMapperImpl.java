package gp.moto.challenge_api.dto.endereco;

import gp.moto.challenge_api.model.Endereco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T11:31:35-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (BellSoft)"
)
@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public Endereco toEntity(EnderecoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setIdCidade( mapIdToCidade( dto.cidade() ) );
        if ( dto.numLogradouro() != null ) {
            endereco.setNrLogradouro( Long.parseLong( dto.numLogradouro() ) );
        }
        endereco.setNmLogradouro( dto.logradouro() );
        endereco.setCep( dto.cep() );

        return endereco;
    }

    @Override
    public EnderecoDto toDto(Endereco entity) {
        if ( entity == null ) {
            return null;
        }

        String numLogradouro = null;
        String logradouro = null;
        Long cidade = null;
        String cep = null;

        if ( entity.getNrLogradouro() != null ) {
            numLogradouro = String.valueOf( entity.getNrLogradouro() );
        }
        logradouro = entity.getNmLogradouro();
        cidade = mapCidadeToId( entity.getIdCidade() );
        cep = entity.getCep();

        EnderecoDto enderecoDto = new EnderecoDto( logradouro, numLogradouro, cidade, cep );

        return enderecoDto;
    }

    @Override
    public void updateEntityFromDto(EnderecoDto dto, Endereco entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.cep() != null ) {
            entity.setCep( dto.cep() );
        }
    }
}
