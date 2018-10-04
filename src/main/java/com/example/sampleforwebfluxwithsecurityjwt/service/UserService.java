package com.example.sampleforwebfluxwithsecurityjwt.service;

import com.example.sampleforwebfluxwithsecurityjwt.domain.Role;
import com.example.sampleforwebfluxwithsecurityjwt.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class UserService {
    private final String userUsername = "user";// password: user
    private final User user = new User(userUsername,
            "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=",
            true,
            Collections.singletonList(Role.ROLE_USER));

    private final String adminUsername = "admin";// password: admin
    private final User admin = new User(adminUsername,
            "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=",
            true,
            Collections.singletonList(Role.ROLE_ADMIN));

    public Mono<User> findByUsername(String username) {
        switch (username) {
            case userUsername:
                return Mono.just(user);
            case adminUsername:
                return Mono.just(admin);
            default:
                return Mono.empty();
        }
    }
}
