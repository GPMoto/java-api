package gp.moto.challenge_api.dto.endereco.cidade;

import gp.moto.challenge_api.model.Cidade;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T00:08:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class CidadeMapperImpl implements CidadeMapper {

    @Override
    public Cidade toEntity(CidadeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cidade cidade = new Cidade();

        cidade.setNmCidade( dto.nome() );
        cidade.setIdEstado( mapIdToEstado( dto.idEstado() ) );

        return cidade;
    }

    @Override
    public CidadeDto toDto(Cidade entity) {
        if ( entity == null ) {
            return null;
        }

        String nome = null;
        Long idEstado = null;

        nome = entity.getNmCidade();
        idEstado = mapEstadoToId( entity.getIdEstado() );

        CidadeDto cidadeDto = new CidadeDto( nome, idEstado );

        return cidadeDto;
    }

    @Override
    public void updateEntityFromDto(CidadeDto dto, Cidade cidade) {
        if ( dto == null ) {
            return;
        }

        if ( dto.idEstado() != null ) {
            cidade.setIdEstado( mapIdToEstado( dto.idEstado() ) );
        }
    }
}
