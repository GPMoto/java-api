package gp.moto.challenge_api.dto.filial;

import gp.moto.challenge_api.model.Filial;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T11:50:49-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (BellSoft)"
)
@Component
public class FilialMapperImpl implements FilialMapper {

    @Override
    public FilialDTO toFilialDTO(Filial filial) {
        if ( filial == null ) {
            return null;
        }

        String cnpjFilial = null;
        String senhaFilial = null;
        Long idEndereco = null;
        Long idContato = null;

        cnpjFilial = filial.getCnpjFilial();
        senhaFilial = filial.getSenhaFilial();
        idEndereco = mapEnderecoToId( filial.getIdEndereco() );
        idContato = mapContatoToId( filial.getIdContato() );

        Long idSecaoFilial = null;

        FilialDTO filialDTO = new FilialDTO( cnpjFilial, senhaFilial, idEndereco, idContato, idSecaoFilial );

        return filialDTO;
    }

    @Override
    public Filial toFilial(FilialDTO filialDTO) {
        if ( filialDTO == null ) {
            return null;
        }

        Filial filial = new Filial();

        filial.setCnpjFilial( filialDTO.cnpjFilial() );
        filial.setSenhaFilial( filialDTO.senhaFilial() );
        filial.setIdEndereco( mapIdToEndereco( filialDTO.idEndereco() ) );
        filial.setIdContato( mapIdToContato( filialDTO.idContato() ) );

        return filial;
    }

    @Override
    public void updateEntityFromDto(FilialDTO dto, Filial entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.cnpjFilial() != null ) {
            entity.setCnpjFilial( dto.cnpjFilial() );
        }
        if ( dto.senhaFilial() != null ) {
            entity.setSenhaFilial( dto.senhaFilial() );
        }
        if ( dto.idEndereco() != null ) {
            entity.setIdEndereco( mapIdToEndereco( dto.idEndereco() ) );
        }
        if ( dto.idContato() != null ) {
            entity.setIdContato( mapIdToContato( dto.idContato() ) );
        }
    }
}
