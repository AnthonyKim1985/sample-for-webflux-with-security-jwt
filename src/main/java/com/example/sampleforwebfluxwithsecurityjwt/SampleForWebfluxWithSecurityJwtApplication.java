package com.example.sampleforwebfluxwithsecurityjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-11
 */
@EnableMongoAuditing
@EnableSwagger2WebFlux
@SpringBootApplication
@EnableReactiveMongoRepositories
public class SampleForWebfluxWithSecurityJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleForWebfluxWithSecurityJwtApplication.class, args);
    }
}
