package com.example.happytownclone.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${happytown.openapi.url}")
    private String serverUrl;

    @Value("${happytown.openapi.contact.email}")
    private String contactEmail;

    @Value("${happytown.openapi.contact.name}")
    private String contactName;

    @Value("${happytown.openapi.contact.url}")
    private String contactUrl;

    @Value("${happytown.openapi.info.title}")
    private String infoTitle;

    @Value("${happytown.openapi.info.version}")
    private String infoVersion;

    @Value("${happytown.openapi.info.version}")
    private String infoDescription;

    @Bean
    public OpenAPI getOpenAPI() {
        Server server = new Server();
        server.setUrl(serverUrl);
        server.setDescription("Happy Town Clone API");

        Contact contact = new Contact();
        contact.setEmail(contactEmail);
        contact.setName(contactName);
        contact.setUrl(contactUrl);

        Info info = new Info()
                .title(infoTitle)
                .version(infoVersion)
                .contact(contact)
                .description(infoDescription);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
