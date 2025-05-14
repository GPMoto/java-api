package gp.moto.challenge_api.dto.endereco;

import gp.moto.challenge_api.model.Cidade;
import gp.moto.challenge_api.model.Endereco;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    @Mapping(target = "idCidade", source = "cidade")
    @Mapping(target = "nrLogradouro", source = "numLogradouro")
    @Mapping(target = "nmLogradouro", source = "logradouro")
    @Mapping(target = "idEndereco", ignore = true)
    Endereco toEntity(EnderecoDto dto);

    @Mapping(target = "numLogradouro", source = "nrLogradouro")
    @Mapping(target = "logradouro", source = "nmLogradouro")
    @Mapping(target = "cidade", source = "idCidade")
    EnderecoDto toDto(Endereco entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EnderecoDto dto, @MappingTarget Endereco entity);

    default Cidade mapIdToCidade(Long id){
        if (id == null) return null;
        Cidade cidade = new Cidade();
        cidade.setIdCidade(id);
        return cidade;
    }

    default Long mapCidadeToId(Cidade cidade){
        return cidade != null ? cidade.getIdCidade() : null;
    }

}
