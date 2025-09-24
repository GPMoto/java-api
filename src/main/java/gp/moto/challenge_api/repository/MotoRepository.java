package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.SecaoFilial;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;



public interface MotoRepository extends JpaRepository<Moto, Long> {
    @Query(value = "select m from Moto m where m.idSecaoFilial.idFilial.idFilial = :idFilial")
    Page<Moto> findAllByFilial(Pageable pageable, Long idFilial);

    @Query(value = "select m from Moto m where m.idSecaoFilial.idFilial.idFilial = :idFilial " +
           "and (LOWER(m.identificador) like LOWER(concat('%', :search, '%')) " +
           "or LOWER(m.status) like LOWER(concat('%', :search, '%')))")
    Page<Moto> findAllByFilialWithSearch(Pageable pageable, Long idFilial, String search);


    @Query(value = "select m from Moto m where m.idSecaoFilial.idSecao = :idSecaoFilial " +
           "and (LOWER(m.identificador) like LOWER(concat('%', :search, '%')) " +
           "or LOWER(m.status) like LOWER(concat('%', :search, '%')))")
    Page<Moto> findByIdSecaoFilialWithSearch(Pageable pageable, Long idSecaoFilial, String search);

    @Query(value = "select m from Moto m where m.idSecaoFilial.idSecao = :idSecaoFilial")
    Page<Moto> findByIdSecaoFilial(Pageable pageable, Long idSecaoFilial);

}
