package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.Uwb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UwbRepository extends JpaRepository<Uwb, Long> {


}
