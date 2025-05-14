package gp.moto.challenge_api.dto.contato;

import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.model.Telefone;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContatoMapper {
    ContatoDTO toDto(Contato entity);

    @Mapping(target = "idContato", ignore = true)
    Contato toEntity(ContatoDTO contatoDTO);

    ContatoProjection toProjection(Contato contato);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ContatoDTO dto, @MappingTarget Contato entity);

    default Telefone mapIdToTelefone(Long id){
        if (id == null) return null;
        Telefone telefone = new Telefone();
        telefone.setId_telefone(id);
        return telefone;
    }

    default Long mapTelefoneToId(Telefone telefone){
        return telefone != null ? telefone.getId_telefone() : null;
    }
}
