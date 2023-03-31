package com.upspoon.gateway.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author burak.yesildal
 */
@Configuration
public class Oauth2OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi() {
        OAuthFlow oAuthFlow = new OAuthFlow();
        oAuthFlow.setAuthorizationUrl("http://127.0.0.1:9000");
        oAuthFlow.tokenUrl("http://127.0.0.1:9000/oauth2/token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("OAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("jwt")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")
                        .flows(new OAuthFlows().authorizationCode(oAuthFlow))))
                .addSecurityItem(new SecurityRequirement().addList("OAuth"))
                .info(new Info()
                        .title("Upspoon Gateway API"));
    }
}
