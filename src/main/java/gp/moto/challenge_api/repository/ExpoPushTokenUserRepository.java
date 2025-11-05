package gp.moto.challenge_api.repository;

import gp.moto.challenge_api.model.PushToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpoPushTokenUserRepository
    extends JpaRepository<PushToken, Long> {
    @Query(
        "select pt from PushToken pt where pt.token = :token and pt.userId.idUsuario = :userId"
    )
    Optional<PushToken> findByTokenAndUserId(String token, Long userId);
}
