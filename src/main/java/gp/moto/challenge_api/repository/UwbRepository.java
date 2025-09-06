package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Uwb;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UwbRepository extends JpaRepository<Uwb, Long> {

    @Query(value = "select u from Uwb u join fetch u.idMoto m join fetch m.idSecaoFilial sf join fetch sf.idFilial f where f.idFilial = :idFilial")
    Page<Uwb> findAllByFilialPage(Pageable pageable, Long idFilial);

    Optional<Uwb> findByIdMoto(Moto moto);

}
