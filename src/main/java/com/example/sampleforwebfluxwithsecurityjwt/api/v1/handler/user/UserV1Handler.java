package com.example.sampleforwebfluxwithsecurityjwt.api.v1.handler.user;

import com.example.sampleforwebfluxwithsecurityjwt.constant.ResponseStatus;
import com.example.sampleforwebfluxwithsecurityjwt.vo.common.response.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-08
 */
@Slf4j
@Component
public class UserV1Handler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().body(fromObject(new BasicResponse(ResponseStatus.OK)));
    }
}
