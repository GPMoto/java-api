package gp.moto.challenge_api.dto.endereco.pais;

import gp.moto.challenge_api.dto.endereco.EnderecoDto;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.model.Pais;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaisMapper {
    @Mapping(target = "idPais", ignore = true)
    @Mapping(target = "nmPais", source = "nmPais")
    Pais toEntity(PaisDto dto);
    @Mapping(target = "nmPais", source = "nmPais")
    PaisDto toDto(Pais entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(Pais pais, @MappingTarget PaisDto paisDto);
}
