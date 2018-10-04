package com.example.sampleforwebfluxwithsecurityjwt.api;

import com.example.sampleforwebfluxwithsecurityjwt.domain.AuthRequest;
import com.example.sampleforwebfluxwithsecurityjwt.domain.AuthResponse;
import com.example.sampleforwebfluxwithsecurityjwt.security.JWTUtil;
import com.example.sampleforwebfluxwithsecurityjwt.security.PBKDF2Encoder;
import com.example.sampleforwebfluxwithsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationREST {
    private final JWTUtil jwtUtil;

    private final PBKDF2Encoder passwordEncoder;

    private final UserService userRepository;

    @Autowired
    public AuthenticationREST(JWTUtil jwtUtil, PBKDF2Encoder passwordEncoder, UserService userRepository) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public Mono<ResponseEntity<AuthResponse>> auth(@RequestBody AuthRequest authRequest) {
        return userRepository.findByUsername(authRequest.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(authRequest.getPassword()).equals(userDetails.getPassword()))
                return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        });
    }
}