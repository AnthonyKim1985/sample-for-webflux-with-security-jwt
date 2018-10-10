package com.example.sampleforwebfluxwithsecurityjwt.persistence;

import com.example.sampleforwebfluxwithsecurityjwt.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-08
 */
@Repository
public interface ReactiveUserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);
}
