package gp.moto.challenge_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.CacheControl;

@Configuration
@Profile("dev")
public class DevConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Desabilitar cache para todos os recursos estáticos em desenvolvimento
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/templates/")
                .setCacheControl(CacheControl.noCache().mustRevalidate());

        // CSS e JS sem cache
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCacheControl(CacheControl.noCache());

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCacheControl(CacheControl.noCache());

        // Imagens e assets
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/")
                .setCacheControl(CacheControl.noCache());

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/assets/images/")
                .setCacheControl(CacheControl.noCache());
    }
}