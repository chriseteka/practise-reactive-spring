package com.chrisworks.reactive.spring.repositories;

import com.chrisworks.reactive.spring.entities.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

    @Query("SELECT * FROM reactive_spring.users WHERE username = $1")
    Flux<User> fetchByUsername(String username);

}
