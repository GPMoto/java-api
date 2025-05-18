package gp.moto.challenge_api.dto.moto;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.SecaoFilial;
import gp.moto.challenge_api.model.TipoMoto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface MotoMapper {

    MotoMapper INSTANCE = Mappers.getMapper(MotoMapper.class);

    @Mapping(target = "idTipoMoto", source = "idTipoMoto")
    MotoDTO toMotoDTO(Moto moto);

    @Mapping(target = "idMoto", ignore = true)
    Moto toMoto(MotoDTO motoDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MotoDTO dto, @MappingTarget Moto entity);

    MotoProjection toProjection(Moto moto);

    default Long mapTipoMotoToLong(TipoMoto tipoMoto) {
        return tipoMoto != null ? tipoMoto.getId_tipo_moto() : null;
    }

    default TipoMoto mapLongToTipoMoto(Long id) {
        if (id == null) {
            return null;
        }
        TipoMoto tipoMoto = new TipoMoto();
        tipoMoto.setId_tipo_moto(id);
        return tipoMoto;
    }

    default Long mapSecaoFilialToLong(SecaoFilial secaoFilial) {
        return secaoFilial != null ? secaoFilial.getIdSecao() : null;
    }

    default SecaoFilial mapLongToSecaoFilial(Long id) {
        if (id == null) {
            return null;
        }
        SecaoFilial secaoFilial = new SecaoFilial();
        secaoFilial.setIdSecao(id);
        return secaoFilial;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default Page<MotoProjection> toProjection(Page<Moto> allByFilial){
        if (allByFilial == null) return Page.empty();

        return allByFilial.map(this::toProjection);
    };


}