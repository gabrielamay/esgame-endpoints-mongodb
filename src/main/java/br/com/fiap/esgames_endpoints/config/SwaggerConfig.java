package br.com.fiap.esgames_endpoints.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ESGames API")
                        .description("API RESTful do projeto ESGames - Sistema de gamificação para práticas ESG")
                        .version("0.0.2")
                        .contact(new Contact()
                                .name("FIAP")
                                .email("contato@fiap.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url("http://localhost:8080/api").description("Servidor Local"),
                        new Server().url("https://esgame-prod-fzhbdrc2gkewd9g5.brazilsouth-01.azurewebsites.net/api").description("Servidor de Produção"),
                        new Server().url("https://esgame-staging-esbjf5fdaxf0enf8.brazilsouth-01.azurewebsites.net/api").description("Servidor de Staging")
                ));
    }
}
