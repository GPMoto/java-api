package gp.moto.challenge_api.dto.tipoSecao;

import gp.moto.challenge_api.model.TipoSecao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoSecaoMapper {

    @Mapping(target = "nmSecao", source = "nomeSecao")
    @Mapping(target = "idTipoSecao", ignore = true)
    TipoSecao toEntity(TipoSecaoDto dto);
    @Mapping(target = "nomeSecao", source = "nmSecao")
    TipoSecaoDto toDto(TipoSecao entity);

    @Mapping(target = "nmSecao", source = "nomeSecao")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TipoSecaoDto dto,  @MappingTarget TipoSecao entity);
}
