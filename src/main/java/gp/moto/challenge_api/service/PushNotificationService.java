package gp.moto.challenge_api.service;

import com.github.flanchanowo.ExpoPushNotificationClient;
import com.github.flanchanowo.request.PushNotification;
import com.github.flanchanowo.response.TicketResponse;
import com.github.flanchanowo.response.enums.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

class PushNotificationService {

    public Optional<Map<String, Status>> sendNotification(
        List<String> toList,
        String message,
        String title
    ) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ExpoPushNotificationClient client =
                ExpoPushNotificationClient.builder()
                    .setHttpClient(httpClient)
                    .build();

            PushNotification pushNotification = new PushNotification();
            pushNotification.setTo(toList);
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
}
