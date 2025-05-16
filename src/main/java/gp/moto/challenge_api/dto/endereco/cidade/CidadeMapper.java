package gp.moto.challenge_api.dto.endereco.cidade;

import gp.moto.challenge_api.model.Cidade;
import gp.moto.challenge_api.model.Estado;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CidadeMapper {
    @Mapping(target = "idCidade", ignore = true)
    @Mapping(target = "nmCidade", source = "nome")
    Cidade toEntity(CidadeDto dto);

    @Mapping(target = "nome", source = "nmCidade")
    CidadeDto toDto(Cidade entity);

    default Long mapEstadoToId(Estado estado){
        return estado != null ? estado.getIdEstado() : null;
    }

    default Estado mapIdToEstado(Long id){
        if (id == null) return null;
        Estado estado = new Estado();
        estado.setIdEstado(id);
        return estado;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CidadeDto dto, @MappingTarget Cidade cidade);
}
