package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.ExpoPushTokenUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpoPushTokenUserRepository
    extends JpaRepository<ExpoPushTokenUser, Long> {
    @Query(
        "select eptu from ExpoPushTokenUser eptu where eptu.token = :token and eptu.userId.idUsuario = :userId"
    )
    Optional<ExpoPushTokenUser> findByTokenAndUserId(String token, Long userId);
}
