package gp.moto.challenge_api.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    OpenAPI configurarSwagger() {
        return new OpenAPI().info(new Info()
                .title("GpsMottu")
                .description("Localizador e analisador de motos em filiais")
                .summary("API para localização, rastreamento e análise de motos distribuídas em múltiplas filiais")
                .version("v1.0.0")
                .license(new License().url("https://github.com/GPMoto/")
                        .name("Licença - GpsMottu - v1.0.0"))
                .termsOfService("Termos de Serviço"));
    }
}
