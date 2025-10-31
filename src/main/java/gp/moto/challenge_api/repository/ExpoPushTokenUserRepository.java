package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.ExpoPushTokenUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpoPushTokenUserRepository
    extends JpaRepository<ExpoPushTokenUser, Long> {}
