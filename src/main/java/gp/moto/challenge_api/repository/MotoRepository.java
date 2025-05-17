package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Moto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MotoRepository extends JpaRepository<Moto, Long> {
    @Query(value = "select m from Moto m where m.idSecaoFilial.idFilial.idFilial = :idFilial")
    Page<Moto> findAllByFilial(Pageable pageable, Long idFilial);
}
