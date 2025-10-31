package gp.moto.challenge_api.service;

import com.github.flanchanowo.ExpoPushNotificationClient;
import com.github.flanchanowo.request.PushNotification;
import com.github.flanchanowo.response.TicketResponse;
import com.github.flanchanowo.response.enums.Status;
import gp.moto.challenge_api.dto.notification.ExpoPushTokenUserDto;
import gp.moto.challenge_api.model.ExpoPushTokenUser;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.ExpoPushTokenUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushNotificationService {

    private final ExpoPushTokenUserRepository expoPushTokenUserRepository;

    public Optional<Map<String, Status>> sendNotification(
        List<ExpoPushTokenUser> toList,
        String message,
        String title
    ) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ExpoPushNotificationClient client =
                ExpoPushNotificationClient.builder()
                    .setHttpClient(httpClient)
                    .build();

            PushNotification pushNotification = new PushNotification();
            pushNotification.setTo(
                toList
                    .stream()
                    .map(ExpoPushTokenUser::getToken)
                    .collect(Collectors.toList())
            );
            pushNotification.setTitle(title);
            pushNotification.setBody(message);

            List<PushNotification> notifications = new ArrayList<>();
            notifications.add(pushNotification);

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
            return Optional.empty();
        }
    }

    public ExpoPushTokenUser saveTokenForUser(Usuario usuario, String token) {
        ExpoPushTokenUser tokenUser = new ExpoPushTokenUser();
        tokenUser.setUserId(usuario);
        tokenUser.setToken(token);
        return expoPushTokenUserRepository.save(tokenUser);
    }
}
