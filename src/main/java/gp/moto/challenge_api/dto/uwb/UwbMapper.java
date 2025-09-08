package gp.moto.challenge_api.dto.uwb;

import gp.moto.challenge_api.dto.moto.MotoMapper;
import gp.moto.challenge_api.dto.moto.MotoProjection;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Uwb;
import gp.moto.challenge_api.repository.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface UwbMapper {

    

    @Mapping(target = "idUwb", ignore = true)
    Uwb toEntity(UwbDTO dto);
    UwbDTO toDto(Uwb entity);


    @Mapping(target = "moto", source = "idMoto")
    UwbProjection toProjection(Uwb entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UwbDTO dto, @MappingTarget Uwb entity);

    default MotoProjection mapMotoToMotoProjection(Moto moto){
        return MotoMapper.INSTANCE.toProjection(moto);
    }

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
