package gp.moto.challenge_api.dto.secaoFilial;

import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.SecaoFilial;
import gp.moto.challenge_api.model.TipoSecao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SecaoFilialMapper {

    @Mapping(target = "idSecao", ignore = true)
    SecaoFilial toEntity(SecaoFilialDto dto);
    SecaoFilialDto toDto(SecaoFilial entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(SecaoFilialDto dto, @MappingTarget SecaoFilial entity);

    default TipoSecao mapIdToTipoSecao(Long id){
        if (id == null) return null;
        TipoSecao tipoSecao = new TipoSecao();
        tipoSecao.setIdTipoSecao(id);
        return tipoSecao;
    }

    default Long mapTipoSecaoToId(TipoSecao tipoSecao){
        return tipoSecao != null ? tipoSecao.getIdTipoSecao() : null;
    }

    default Filial mapIdToFilial(Long id){
        if (id == null) return null;
        Filial filial = new Filial();
        filial.setIdFilial(id);
        return filial;
    }

    default Long mapFilialToId(Filial filial){
        return filial != null ? filial.getIdFilial() : null;
    }

}
