package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Uwb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UwbRepository extends JpaRepository<Uwb, Long> {

    @Query(value = "select u from Uwb u where u.idMoto.idSecaoFilial = :idFilial")
    Page<Uwb> findAllByFilialPage(Pageable pageable, Long idFilial);
}
