package com.example.happytownclone.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${happytown.openapi.url}")
    private String url;

    @Bean
    public OpenAPI getOpenAPI() {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Happy Town Clone API");

        Contact contact = new Contact();
        contact.setEmail("theophile.banogo@gmail.com");
        contact.setName("Serge BANOGO");
        contact.setUrl("https://happytownclone.com/");

        License mitLicense = new License();
        mitLicense.setName("MIT");

        Info info = new Info()
                .title("Happy Town Clone")
                .version("1.0")
                .contact(contact)
                .license(mitLicense)
                .description("This API exposes endpoints to manage tutorials").termsOfService("https:://happytownclone.com/terms");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
