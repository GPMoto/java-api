package gp.moto.challenge_api.service;

import com.google.firebase.messaging.*;
import gp.moto.challenge_api.model.PushToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FirebaseMessagingService {

    public void sendNotification(List<PushToken> tokens, String title, String body) {
        if (tokens == null || tokens.isEmpty()) {
            log.warn("No tokens to send notification");
            return;
        }

        List<String> tokenList = tokens.stream()
                .map(PushToken::getToken)
                .filter(t -> t != null && !t.trim().isEmpty())
                .collect(Collectors.toList());

        log.info("Sending notification to {} tokens", tokenList.size());

        int successCount = 0;
        List<String> failedTokens = new ArrayList<>();

        // Enviar individualmente para cada token usando a API HTTP v1
        for (String token : tokenList) {
            try {
                Message message = Message.builder()
                        .setToken(token)
                        .setNotification(Notification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build())
                        .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                        .build();

                String response = FirebaseMessaging.getInstance().send(message);
                log.debug("Successfully sent message to token: {} - Response: {}", token, response);
                successCount++;
            } catch (FirebaseMessagingException e) {
                log.error("Error sending notification to token: {} - Error: {}", token, e.getMessage());
                failedTokens.add(token);
            }
        }

        log.info("Sent {} messages successfully out of {}", successCount, tokenList.size());
        if (!failedTokens.isEmpty()) {
            log.warn("Failed to send to {} tokens: {}", failedTokens.size(), failedTokens);
        }
    }
}
