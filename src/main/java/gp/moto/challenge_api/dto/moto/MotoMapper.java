package gp.moto.challenge_api.dto.moto;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.SecaoFilial;
import gp.moto.challenge_api.model.TipoMoto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MotoMapper {
    @Mapping(target = "idTipoMoto", source = "idTipoMoto")
    @Mapping(target = "idSecaoFilial", source = "idSecaoFilial")
    MotoDTO toMotoDTO(Moto moto);

    @Mapping(target = "idMoto", ignore = true)
    @Mapping(target = "idTipoMoto", source = "idTipoMoto")
    @Mapping(target = "idSecaoFilial", source = "idSecaoFilial")
    Moto toMoto(MotoDTO motoDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MotoDTO dto, @MappingTarget Moto entity);

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
}