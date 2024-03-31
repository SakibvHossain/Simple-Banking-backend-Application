package com.example.bankingapplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
        info = @Info(
                title = "Banking Application",
                description = "Spring Boot Banking Application Project",
                version = "v1-0",
                contact = @Contact(
                        name = "Sakib",
                        email = "sakibxhossain021@gmail.com",
                        url = "https://visualflow.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://visual.com/apache_licence"
                )
        ),externalDocs = @ExternalDocumentation(
        description = "More feature will be added!",
        url = "https://visual.com/future_docs"
)
)
public class SimpleBankingApplication {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleBankingApplication.class, args);
    }

}
