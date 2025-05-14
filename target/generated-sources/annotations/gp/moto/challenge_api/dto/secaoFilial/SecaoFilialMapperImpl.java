package gp.moto.challenge_api.dto.secaoFilial;

import gp.moto.challenge_api.model.SecaoFilial;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T11:31:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (BellSoft)"
)
@Component
public class SecaoFilialMapperImpl implements SecaoFilialMapper {

    @Override
    public SecaoFilial toEntity(SecaoFilialDto dto) {
        if ( dto == null ) {
            return null;
        }

        SecaoFilial secaoFilial = new SecaoFilial();

        if ( dto.lado1() != null ) {
            secaoFilial.setLado1( dto.lado1() );
        }
        if ( dto.lado2() != null ) {
            secaoFilial.setLado2( dto.lado2() );
        }
        if ( dto.lado3() != null ) {
            secaoFilial.setLado3( dto.lado3() );
        }
        if ( dto.lado4() != null ) {
            secaoFilial.setLado4( dto.lado4() );
        }
        secaoFilial.setIdTipoSecao( mapIdToTipoSecao( dto.idTipoSecao() ) );
        secaoFilial.setIdFilial( mapIdToFilial( dto.idFilial() ) );

        return secaoFilial;
    }

    @Override
    public SecaoFilialDto toDto(SecaoFilial entity) {
        if ( entity == null ) {
            return null;
        }

        Double lado1 = null;
        Double lado2 = null;
        Double lado3 = null;
        Double lado4 = null;
        Long idTipoSecao = null;
        Long idFilial = null;

        lado1 = entity.getLado1();
        lado2 = entity.getLado2();
        lado3 = entity.getLado3();
        lado4 = entity.getLado4();
        idTipoSecao = mapTipoSecaoToId( entity.getIdTipoSecao() );
        idFilial = mapFilialToId( entity.getIdFilial() );

        SecaoFilialDto secaoFilialDto = new SecaoFilialDto( lado1, lado2, lado3, lado4, idTipoSecao, idFilial );

        return secaoFilialDto;
    }

    @Override
    public void updateEntityFromDto(SecaoFilialDto dto, SecaoFilial entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.lado1() != null ) {
            entity.setLado1( dto.lado1() );
        }
        if ( dto.lado2() != null ) {
            entity.setLado2( dto.lado2() );
        }
        if ( dto.lado3() != null ) {
            entity.setLado3( dto.lado3() );
        }
        if ( dto.lado4() != null ) {
            entity.setLado4( dto.lado4() );
        }
        if ( dto.idTipoSecao() != null ) {
            entity.setIdTipoSecao( mapIdToTipoSecao( dto.idTipoSecao() ) );
        }
        if ( dto.idFilial() != null ) {
            entity.setIdFilial( mapIdToFilial( dto.idFilial() ) );
        }
    }
}
