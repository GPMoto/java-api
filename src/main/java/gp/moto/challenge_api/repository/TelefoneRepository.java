package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
