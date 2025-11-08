package gp.moto.challenge_api.config;

import com.google.firebase.FirebaseApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de configuração do Firebase")
class FirebaseConfigTest {

    // @Autowired
    // private FirebaseConfig firebaseConfig;

    // @AfterEach
    // void cleanup() {
    //     // Limpa as instâncias do Firebase após cada teste
    //     FirebaseApp.getApps().forEach(app -> {
    //         try {
    //             app.delete();
    //         } catch (Exception e) {
    //             // Ignora erros na limpeza
    //         }
    //     });
    // }

    // @Test
    // @DisplayName("Deve ter o FirebaseConfig bean disponível no contexto")
    // void deveInjetarFirebaseConfigBean() {
    //     // Then
    //     assertNotNull(firebaseConfig,
    //         "O bean FirebaseConfig deve estar disponível no contexto Spring");
    // }

    // @Test
    // @DisplayName("Deve carregar credenciais do Azure e inicializar o FirebaseApp")
    // void deveCarregarCredenciaisDoAzureEInicializarFirebase() {
    //     // Given & When
    //     // O FirebaseConfig já foi inicializado pelo Spring durante o @PostConstruct
    //     // com a URL do Azure configurada na variável de ambiente FIREBASE_CREDENTIALS_URL
    //     boolean isInitialized = !FirebaseApp.getApps().isEmpty();

    //     // Then
    //     assertTrue(isInitialized,
    //         "O FirebaseApp deve ser inicializado com as credenciais carregadas do Azure");

    //     FirebaseApp app = FirebaseApp.getInstance();
    //     assertNotNull(app, "A instância do FirebaseApp não deve ser nula");
    //     assertNotNull(app.getOptions(), "As opções do FirebaseApp devem estar configuradas");
    //     assertEquals("gpsmottu", app.getOptions().getProjectId(),
    //         "O project ID deve ser 'gpsmottu', confirmando que as credenciais foram carregadas corretamente");
    // }

    // @Test
    // @DisplayName("Deve ter apenas uma instância do FirebaseApp após inicialização")
    // void deveTermApenasUmaInstanciaDoFirebase() {
    //     // Given & When
    //     int appCount = FirebaseApp.getApps().size();

    //     // Then
    //     assertEquals(1, appCount,
    //         "Deve haver exatamente uma instância do FirebaseApp após a inicialização");
    // }

    // @Test
    // @DisplayName("Não deve criar instância duplicada ao chamar initializeFirebase novamente")
    // void naoDeveInicializarFirebaseDuasVezes() {
    //     // Given
    //     assertTrue(FirebaseApp.getApps().size() > 0,
    //         "Deve haver pelo menos uma instância do Firebase inicializada");

    //     int initialAppCount = FirebaseApp.getApps().size();

    //     // When
    //     // Tenta inicializar novamente
    //     try {
    //         firebaseConfig.initializeFirebase();
    //     } catch (Exception e) {
    //         // Pode lançar exceção, mas não deve duplicar instâncias
    //     }

    //     int finalAppCount = FirebaseApp.getApps().size();

    //     // Then
    //     assertEquals(initialAppCount, finalAppCount,
    //         "Não deve criar instâncias duplicadas do FirebaseApp");
    //     assertEquals(1, finalAppCount,
    //         "Deve continuar com exatamente uma instância do FirebaseApp");
    // }

    // @Test
    // @DisplayName("Deve ter credenciais válidas configuradas no FirebaseApp")
    // void deveTermCredenciaisValidasConfiguradas() {
    //     // Given & When
    //     assertTrue(!FirebaseApp.getApps().isEmpty(),
    //         "O FirebaseApp deve estar inicializado");

    //     FirebaseApp app = FirebaseApp.getInstance();

    //     // Then
    //     assertNotNull(app.getOptions(), "As opções do Firebase devem existir");
    //     assertNotNull(app.getOptions().getProjectId(), "O project ID não deve ser nulo");
    //     assertFalse(app.getOptions().getProjectId().isEmpty(),
    //         "O project ID não deve estar vazio");
    //     assertEquals("gpsmottu", app.getOptions().getProjectId(),
    //         "O project ID deve corresponder ao esperado");
    // }

    @Test
    @DisplayName("Deve conseguir baixar o JSON de credenciais do Azure Blob Storage")
    void deveBaixarJsonDoAzureBlobStorage() throws Exception {
        // Given
        String azureUrl = System.getenv("FIREBASE_CREDENTIALS_URL");
        assertNotNull(azureUrl,
            "A variável de ambiente FIREBASE_CREDENTIALS_URL deve estar definida");
        assertFalse(azureUrl.isEmpty(),
            "A variável de ambiente FIREBASE_CREDENTIALS_URL não deve estar vazia");

        // When
        java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(azureUrl))
                .GET()
                .build();

        java.net.http.HttpResponse<byte[]> response = client.send(
            request,
            java.net.http.HttpResponse.BodyHandlers.ofByteArray()
        );

        // Then
        assertEquals(200, response.statusCode(),
            "A requisição ao Azure Blob Storage deve retornar status 200");

        assertNotNull(response.body(),
            "O corpo da resposta não deve ser nulo");

        assertTrue(response.body().length > 0,
            "O JSON baixado deve ter conteúdo");

        // Valida que é um JSON válido
        String jsonContent = new String(response.body());
        assertTrue(jsonContent.contains("project_id"),
            "O JSON deve conter o campo 'project_id'");
        assertTrue(jsonContent.contains("gpsmottu"),
            "O JSON deve conter o project_id 'gpsmottu'");
        assertTrue(jsonContent.contains("private_key"),
            "O JSON deve conter o campo 'private_key'");
    }
}
