package com.example.sampleforwebfluxwithsecurityjwt.api.v1.router.user;

import com.example.sampleforwebfluxwithsecurityjwt.api.v1.handler.user.UserV1Handler;
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
public class UserV1Router {
    @Bean
    public RouterFunction<ServerResponse> userV1Route(UserV1Handler userV1Handler) {
        return RouterFunctions
                .route(RequestPredicates
                        .GET("/api/v1/user/hello")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userV1Handler::hello)
                ;
    }
}
