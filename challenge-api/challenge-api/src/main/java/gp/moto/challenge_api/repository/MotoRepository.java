package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
}
