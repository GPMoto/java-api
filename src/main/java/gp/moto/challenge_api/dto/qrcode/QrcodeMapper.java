package gp.moto.challenge_api.dto.qrcode;

import gp.moto.challenge_api.dto.moto.MotoMapper;
import gp.moto.challenge_api.dto.moto.MotoProjection;
import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Qrcode;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface QrcodeMapper {

    
    @Mapping(target = "filial", source = "idFilial")
    @Mapping(target = "idMoto", source = "idMoto")
    @Mapping(target = "idQrcode", ignore = true)
    Qrcode toEntity(QrcodeDTO dto);
    
    
    @Mapping(target = "idFilial", source = "filial")
    @Mapping(target = "idMoto", source = "idMoto")
    QrcodeDTO toDto(Qrcode entity);


    @Mapping(target = "moto", source = "idMoto")
    @Mapping(target = "filial", source = "filial")
    QrcodeProjection toProjection(Qrcode entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "filial", source = "idFilial")
    @Mapping(target = "idMoto", source = "idMoto")
    @Mapping(target = "idQrcode", ignore = true)
    void updateEntityFromDto(QrcodeDTO dto, @MappingTarget Qrcode entity);

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

    default Filial mapIdToFilial(Long id){
        if (id == null) return null;
        Filial filial = new Filial();
        filial.setIdFilial(id);
        return filial;
    }
    
    default Long  mapIdFilialToId(Filial filial){
        return filial != null ? filial.getIdFilial() : null;
    }

    default Long mapEnderecoToId(Endereco endereco) {
        return endereco != null ? endereco.getIdEndereco() : null;
    }

    default Long mapContatoToId(Contato contato) {
        return contato != null ? contato.getIdContato() : null;
    }

}
