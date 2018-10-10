package com.example.sampleforwebfluxwithsecurityjwt.api.v1.router.admin;

import com.example.sampleforwebfluxwithsecurityjwt.api.v1.handler.admin.AdminV1Handler;
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
public class AdminV1Router {
    @Bean
    public RouterFunction<ServerResponse> adminV1Route(AdminV1Handler adminV1Handler) {
        return RouterFunctions
                .route(RequestPredicates
                        .GET("/api/v1/admin/hello")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), adminV1Handler::hello)
                ;
    }
}
