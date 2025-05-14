package gp.moto.challenge_api.dto.endereco.pais;

import gp.moto.challenge_api.dto.endereco.EnderecoDto;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.model.Pais;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaisMapper {
    @Mapping(target = "idPais", ignore = true)
    Pais toEntity(PaisDto dto);
    PaisDto toDto(Pais entity);
}
