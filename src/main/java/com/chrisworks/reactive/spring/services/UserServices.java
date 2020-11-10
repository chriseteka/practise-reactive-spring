package com.chrisworks.reactive.spring.services;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.RegisterData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserServices extends AuthServices {

    Mono<RegisterData> fetchById(String userId);
    Mono<RegisterData> fetchByUsername(String username);
    Flux<RegisterData> fetchAll();
    Mono<Void> deleteById(String userId);
    Mono<Void> deleteByUsername(String username);
    Mono<Void> deleteAll();
    Mono<RegisterData> updateById(String userId, RegisterData userData);
    Mono<RegisterData> updateByUsername(String username, RegisterData userData);
}
