package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
}
