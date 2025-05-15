package gp.moto.challenge_api.dto.filial;

import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.model.Filial;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FilialMapper {

    FilialDTO toFilialDTO(Filial filial);

    @Mapping(target = "idFilial", ignore = true)
    Filial toFilial(FilialDTO filialDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(FilialDTO dto, @MappingTarget Filial entity);

    default Long mapEnderecoToId(Endereco endereco) {
        return endereco != null ? endereco.getIdEndereco() : null;
    }

    default Endereco mapIdToEndereco(Long id) {
        if (id == null) return null;
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(id);
        return endereco;
    }

    default Long mapContatoToId(Contato contato) {
        return contato != null ? contato.getIdContato() : null;
    }

    default Contato mapIdToContato(Long id) {
        if (id == null) return null;
        Contato contato = new Contato();
        contato.setIdContato(id);
        return contato;
    }
}
