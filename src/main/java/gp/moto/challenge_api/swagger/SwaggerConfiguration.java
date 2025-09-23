package gp.moto.challenge_api.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityScheme;

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
                .termsOfService("Termos de Serviço"))
        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
