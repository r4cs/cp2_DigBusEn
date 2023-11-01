package br.com.digital.cp2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI geraDocumentacao() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.description("Url de desenvolvimento local");

        Contact contact = new Contact();
        contact.setEmail("rm97373@fiap.com.br");
        contact.setName("Raquel");

        Info info = new Info().title("Checkpoint 2")
                .version("0.1")
                .contact(contact)
                .description("Department x Employee jpa");

        return new OpenAPI()
                    .info(info)
                    .servers(List.of(devServer));
    }
}