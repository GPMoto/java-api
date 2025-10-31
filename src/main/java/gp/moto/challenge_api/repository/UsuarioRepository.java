package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(
        value = "select user from Usuario user where user.idFilial.idFilial = :idFilial"
    )
    List<Usuario> findAllByFilial(Long idFilial);

    @Query(
        value = "select user from Usuario user where user.idPerfil.nmPerfil = :perfilEnum and user.idFilial.idFilial = :filialId"
    )
    List<Usuario> findByPerfilAndFilial(String perfilEnum, Long filialId);

    Optional<Usuario> findByNmUsuario(String nmUsuario);
}
