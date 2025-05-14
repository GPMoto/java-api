package gp.moto.challenge_api.dto.telefone;

import gp.moto.challenge_api.model.Telefone;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {
    Telefone toEntity(TelefoneDTO dto);
    TelefoneDTO toDto(Telefone telefone);
    TelefoneProjection toProjection(Telefone telefone);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTelefoneFromDto(TelefoneDTO dto, @MappingTarget Telefone entity);

}
