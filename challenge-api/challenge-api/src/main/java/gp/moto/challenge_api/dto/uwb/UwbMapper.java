package gp.moto.challenge_api.dto.uwb;

import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Uwb;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UwbMapper {

    @Mapping(target = "idUwb", ignore = true)
    Uwb toEntity(UwbDTO dto);
    UwbDTO toDto(Uwb entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UwbDTO dto, @MappingTarget Uwb entity);

    default Moto mapIdToMoto(Long id){
        if (id == null) return null;
        Moto moto = new Moto();
        moto.setIdMoto(id);
        return moto;
    }

    default Long mapMotoToId(Moto moto){
        return moto != null ? moto.getIdMoto() : null;
    }

}
