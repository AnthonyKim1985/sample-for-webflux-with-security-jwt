package com.example.sampleforwebfluxwithsecurityjwt.api.v1.router.auth;

import com.example.sampleforwebfluxwithsecurityjwt.api.v1.handler.auth.AuthV1Handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-08
 */
@Slf4j
@Configuration
public class AuthV1Router {
    @Bean
    public RouterFunction<ServerResponse> authV1Route(AuthV1Handler authV1Handler) {
        return RouterFunctions
                .route(RequestPredicates
                        .POST("/api/v1/auth/signin")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), authV1Handler::signIn)
                ;
    }
}
