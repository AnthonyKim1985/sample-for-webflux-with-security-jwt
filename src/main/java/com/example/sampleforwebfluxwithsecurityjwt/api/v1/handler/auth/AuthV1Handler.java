package com.example.sampleforwebfluxwithsecurityjwt.api.v1.handler.auth;

import com.example.sampleforwebfluxwithsecurityjwt.constant.ResponseStatus;
import com.example.sampleforwebfluxwithsecurityjwt.exception.FailureToIssueTokenException;
import com.example.sampleforwebfluxwithsecurityjwt.exception.InvalidAuthRequestException;
import com.example.sampleforwebfluxwithsecurityjwt.exception.UnauthorizedUserException;
import com.example.sampleforwebfluxwithsecurityjwt.security.JWTUtil;
import com.example.sampleforwebfluxwithsecurityjwt.vo.common.response.BasicResponse;
import com.example.sampleforwebfluxwithsecurityjwt.vo.v1.request.AuthRequest;
import com.example.sampleforwebfluxwithsecurityjwt.vo.v1.response.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-08
 */
@Slf4j
@Component
public class AuthV1Handler {
    private final JWTUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final ReactiveUserDetailsService userDetailsService;

    @Autowired
    public AuthV1Handler(@NotNull JWTUtil jwtUtil,
                         @NotNull PasswordEncoder passwordEncoder,
                         @NotNull ReactiveUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public Mono<ServerResponse> signIn(ServerRequest request) {
        return request.body(toMono(AuthRequest.class))
                .switchIfEmpty(Mono.error(new InvalidAuthRequestException()))
                .flatMap(authRequest -> userDetailsService.findByUsername(authRequest.getUsername())
                        .switchIfEmpty(Mono.error(new UnauthorizedUserException(authRequest.getUsername())))
                        .flatMap(userDetails -> {
                            if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword()))
                                return ServerResponse.badRequest().body(fromObject(new BasicResponse(ResponseStatus.BAD_REQUEST_UNAUTHORIZED)));

                            return jwtUtil.generateToken(userDetails)
                                    .switchIfEmpty(Mono.error(new FailureToIssueTokenException()))
                                    .flatMap(token -> ServerResponse.ok().body(fromObject(new AuthResponse(ResponseStatus.OK_TOKEN_ISSUED, token))));
                        })
                );
    }
}
