package gp.moto.challenge_api.dto.moto;

import gp.moto.challenge_api.model.Moto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T11:31:35-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (BellSoft)"
)
@Component
public class MotoMapperImpl implements MotoMapper {

    @Override
    public MotoDTO toMotoDTO(Moto moto) {
        if ( moto == null ) {
            return null;
        }

        Long idTipoMoto = null;
        String status = null;
        String condicoesManutencao = null;
        String identificador = null;
        Long idSecaoFilial = null;

        idTipoMoto = mapTipoMotoToLong( moto.getIdTipoMoto() );
        status = moto.getStatus();
        condicoesManutencao = moto.getCondicoesManutencao();
        identificador = moto.getIdentificador();
        idSecaoFilial = mapSecaoFilialToLong( moto.getIdSecaoFilial() );

        MotoDTO motoDTO = new MotoDTO( status, condicoesManutencao, identificador, idTipoMoto, idSecaoFilial );

        return motoDTO;
    }

    @Override
    public Moto toMoto(MotoDTO motoDTO) {
        if ( motoDTO == null ) {
            return null;
        }

        Moto moto = new Moto();

        moto.setStatus( motoDTO.status() );
        moto.setCondicoesManutencao( motoDTO.condicoesManutencao() );
        moto.setIdentificador( motoDTO.identificador() );
        moto.setIdTipoMoto( mapLongToTipoMoto( motoDTO.idTipoMoto() ) );
        moto.setIdSecaoFilial( mapLongToSecaoFilial( motoDTO.idSecaoFilial() ) );

        return moto;
    }

    @Override
    public void updateEntityFromDto(MotoDTO dto, Moto entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.status() != null ) {
            entity.setStatus( dto.status() );
        }
        if ( dto.condicoesManutencao() != null ) {
            entity.setCondicoesManutencao( dto.condicoesManutencao() );
        }
        if ( dto.identificador() != null ) {
            entity.setIdentificador( dto.identificador() );
        }
        if ( dto.idTipoMoto() != null ) {
            entity.setIdTipoMoto( mapLongToTipoMoto( dto.idTipoMoto() ) );
        }
        if ( dto.idSecaoFilial() != null ) {
            entity.setIdSecaoFilial( mapLongToSecaoFilial( dto.idSecaoFilial() ) );
        }
    }

    @Override
    public MotoProjection toProjection(Moto moto) {
        if ( moto == null ) {
            return null;
        }

        Long idMoto = null;
        String status = null;
        String condicoesManutencao = null;
        String identificador = null;
        Long idTipoMoto = null;
        Long idSecaoFilial = null;

        idMoto = moto.getIdMoto();
        status = moto.getStatus();
        condicoesManutencao = moto.getCondicoesManutencao();
        identificador = moto.getIdentificador();
        idTipoMoto = mapTipoMotoToLong( moto.getIdTipoMoto() );
        idSecaoFilial = mapSecaoFilialToLong( moto.getIdSecaoFilial() );

        MotoProjection motoProjection = new MotoProjection( idMoto, status, condicoesManutencao, identificador, idTipoMoto, idSecaoFilial );

        return motoProjection;
    }
}
