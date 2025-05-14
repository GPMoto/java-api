package gp.moto.challenge_api.dto.usuario;

import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.Perfil;
import gp.moto.challenge_api.model.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "nmUsuario", source = "nome")
    @Mapping(target = "nmEmail", source = "email")
    Usuario toEntity(UsuarioDto dto);

    @Mapping(target = "nome", source = "nmUsuario")
    @Mapping(target = "email", source = "nmEmail")
    UsuarioDto toDto(Usuario entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "nmUsuario", source = "nome")
    @Mapping(target = "nmEmail", source = "email")
    void updateEntityFromDto(UsuarioDto dto, @MappingTarget Usuario usuario);


    default Perfil mapIdToPerfil(Long id){
        if (id == null) return null;
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(id);
        return perfil;
    }

    default Long mapPerfilToId(Perfil perfil){
        return perfil != null ? perfil.getIdPerfil() : null;
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
