package com.example.sampleforwebfluxwithsecurityjwt.security;

import com.example.sampleforwebfluxwithsecurityjwt.exception.JsonWebTokenNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil implements Serializable {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public static final String CLAIM_KEY_AUTHORITIES = "userAuthorities";
    private static final String CLAIM_KEY_NON_EXPIRED = "isUserNonExpired";
    private static final String CLAIM_KEY_IS_ENABLED = "isUserEnabled";

    public Mono<String> generateToken(UserDetails user) {
        final Date tokenIssuedDate = new Date();
        final Date tokenExpirationDate = new Date(tokenIssuedDate.getTime() + expirationTime);

        final Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_AUTHORITIES, user.getAuthorities());
        claims.put(CLAIM_KEY_NON_EXPIRED, user.isAccountNonExpired());
        claims.put(CLAIM_KEY_IS_ENABLED, user.isEnabled());

        return Mono.just(Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(tokenIssuedDate)
                .setExpiration(tokenExpirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact());
    }

    public Mono<Boolean> validateToken(String token) {
        return getClaimsFromToken(token)
                .switchIfEmpty(Mono.error(new JsonWebTokenNotFoundException(token)))
                .flatMap(claims -> {
                    final Date tokenExpirationDate = claims.getExpiration();
                    if (!tokenExpirationDate.after(new Date()))
                        return Mono.just(Boolean.FALSE);

                    final Boolean isUserNonExpired = claims.get(CLAIM_KEY_NON_EXPIRED, Boolean.class);
                    final Boolean isUserEnabled = claims.get(CLAIM_KEY_IS_ENABLED, Boolean.class);

                    if (!isUserEnabled || !isUserNonExpired)
                        return Mono.just(Boolean.FALSE);

                    return Mono.just(Boolean.TRUE);
                });
    }

    public Mono<String> getUsernameFromToken(String token) {
        return getClaimsFromToken(token)
                .switchIfEmpty(Mono.error(new JsonWebTokenNotFoundException(token)))
                .flatMap(claims -> Mono.just(claims.getSubject()));
    }

    public Mono<Claims> getClaimsFromToken(String token) {
        return Mono.just(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody());
    }
}