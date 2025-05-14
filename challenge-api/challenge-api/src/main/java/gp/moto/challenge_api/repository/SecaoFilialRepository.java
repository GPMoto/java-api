package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.SecaoFilial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecaoFilialRepository extends JpaRepository<SecaoFilial, Long> {

    @Query(value = "select sf from SecaoFilial sf where sf.idFilial.idFilial = :idFilial")
    List<SecaoFilial> findAllByFilial(Long idFilial);
}
