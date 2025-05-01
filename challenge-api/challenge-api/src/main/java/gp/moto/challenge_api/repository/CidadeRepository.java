package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
