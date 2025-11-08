package gp.moto.challenge_api.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.credentials.url}")
    private String firebaseCredentialsUrl;

    @PostConstruct
    public void initializeFirebase() throws IOException {
        InputStream serviceAccount;

        if (firebaseCredentialsUrl != null && !firebaseCredentialsUrl.isEmpty()) {
            // Busca do Azure Blob Storage com URL SAS completa
            serviceAccount = downloadFromAzure(firebaseCredentialsUrl);
        } else {
            // Fallback para arquivo local (desenvolvimento)
            serviceAccount = getClass().getResourceAsStream(
                "/gpsmottu-firebase-adminsdk-fbsvc-18ccf8cf44.json"
            );
        }

        try (serviceAccount) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("gpsmottu")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        }
    }

    private InputStream downloadFromAzure(String url) throws IOException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<byte[]> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofByteArray()
            );

            if (response.statusCode() != 200) {
                throw new IOException(
                    "Erro ao baixar credenciais do Azure: " + response.statusCode()
                );
            }

            return new ByteArrayInputStream(response.body());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Download interrompido", e);
        }
    }
}
