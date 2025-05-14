package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "select user from Usuario user where user.idFilial.idFilial = :idFilial")
    List<Usuario> findAllByFilial(Long idFilial);
}
