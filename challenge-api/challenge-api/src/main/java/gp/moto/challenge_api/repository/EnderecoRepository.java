package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
