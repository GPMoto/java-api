package gp.moto.challenge_api.service;

import com.github.flanchanowo.ExpoPushNotificationClient;
import com.github.flanchanowo.request.PushNotification;
import com.github.flanchanowo.request.PushNotification.Priority;
import com.github.flanchanowo.response.TicketResponse;
import com.github.flanchanowo.response.enums.Status;
import gp.moto.challenge_api.model.PushToken;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.ExpoPushTokenUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PushNotificationService {

    private final ExpoPushTokenUserRepository expoPushTokenUserRepository;

    public Optional<Map<String, Status>> sendNotification(
        List<PushToken> toList,
        String title,
        String message
    ) {
        if (toList == null || toList.isEmpty()) {
            log.warn("No tokens to send notification");
            return Optional.empty();
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ExpoPushNotificationClient client =
                ExpoPushNotificationClient.builder()
                    .setHttpClient(httpClient)
                    .build();

            PushNotification pushNotification = new PushNotification();

            List<String> tokens = toList
                .stream()
                .map(PushToken::getToken)
                .filter(token -> token != null && !token.trim().isEmpty())
                .collect(Collectors.toList());

            if (tokens.isEmpty()) {
                log.warn("No valid tokens found after filtering");
                return Optional.empty();
            }

            log.info("sending to tokens: {}", tokens);

            pushNotification.setTo(tokens);
            pushNotification.setTitle(title);
            pushNotification.setBody(message);
            pushNotification.setChannelId("default");

            List<PushNotification> notifications = new ArrayList<>();
            notifications.add(pushNotification);

            log.info("notifications: {}", notifications);

            List<TicketResponse.Ticket> response = client.sendPushNotifications(
                notifications
            );

            Map<String, Status> result = response
                .stream()
                .collect(
                    Collectors.toMap(
                        TicketResponse.Ticket::getId,
                        TicketResponse.Ticket::getStatus
                    )
                );

            return Optional.of(result);
        } catch (Exception e) {
            log.error("Error sending push notification", e);
            return Optional.empty();
        }
    }

    public PushToken saveTokenForUser(Usuario usuario, String token) {
        Optional<PushToken> existingToken =
            expoPushTokenUserRepository.findByTokenAndUserId(
                token,
                usuario.getIdUsuario()
            );

        if (existingToken.isPresent()) {
            return existingToken.get();
        }

        PushToken tokenUser = new PushToken();
        tokenUser.setUserId(usuario);
        tokenUser.setToken(token);
        return expoPushTokenUserRepository.save(tokenUser);
    }
}
