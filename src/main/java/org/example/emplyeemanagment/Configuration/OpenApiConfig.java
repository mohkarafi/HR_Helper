package org.example.emplyeemanagment.Configuration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "HR-HELPER",
                version = "v1.0",
                description = "Backend application for HR management",
                contact = @Contact(
                        name = "Mohamed Karafi",
                        email = "karafimhd@gmail.com",
                        url = "https://github.com/mohkarafi"
                ),

                license = @License(
                        name = "HR-HELPER License",
                        url = "https://github.com/mohkarafi"
                )
        ),
        servers = @Server(
          description = "local ENV",
                url = "https://github.com/mohkarafi/HR_Helper"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Full documentation",
                url = "https://github.com/mohkarafi/hr-helper-docs"
        )
)
public class OpenApiConfig {
}
