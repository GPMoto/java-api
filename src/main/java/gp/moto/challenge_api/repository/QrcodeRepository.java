package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Qrcode;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QrcodeRepository extends JpaRepository<Qrcode, Long> {

    @Query("SELECT u FROM Qrcode u WHERE u.filial.idFilial = :idFilial")
    Page<Qrcode> findAllByFilialPage(Pageable pageable, @Param("idFilial") Long idFilial);

    Optional<Qrcode> findByIdMoto(Moto moto);

}
