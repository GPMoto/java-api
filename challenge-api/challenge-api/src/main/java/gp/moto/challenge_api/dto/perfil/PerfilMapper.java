package gp.moto.challenge_api.dto.perfil;

import gp.moto.challenge_api.model.Perfil;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PerfilMapper {

    @Mapping(target = "nmPerfil", source = "nome")
    @Mapping(target = "idPerfil", ignore = true)
    Perfil toEntity(PerfilDto dto);

    @Mapping(target = "nome", source = "nmPerfil")
    PerfilDto toDto(Perfil entity);

    @Mapping(target = "nmPerfil", source = "nome")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PerfilDto dto, @MappingTarget Perfil entity);
}
