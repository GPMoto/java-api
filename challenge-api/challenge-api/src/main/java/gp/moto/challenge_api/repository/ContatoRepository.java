package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
