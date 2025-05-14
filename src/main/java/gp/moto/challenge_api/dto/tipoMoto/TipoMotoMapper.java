package gp.moto.challenge_api.dto.tipoMoto;

import gp.moto.challenge_api.model.TipoMoto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoMotoMapper {

    @Mapping(target = "nmTipo", source = "nome")
    @Mapping(target = "id_tipo_moto", ignore = true)
    TipoMoto toEntity(TipoMotoDto dto);

    @Mapping(target = "nome", source = "nmTipo")
    TipoMotoDto toDto(TipoMoto entity);

    @Mapping(target = "nmTipo", source = "nome")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TipoMotoDto dto, @MappingTarget TipoMoto tipoMoto);
}
