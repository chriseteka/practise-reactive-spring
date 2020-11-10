package com.chrisworks.reactive.spring.services;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.OrderData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderServices {
    Mono<OrderData> fetchById(String orderId);
    Flux<OrderData> fetchAll();
    Mono<Void> deleteById(String orderId);
    Mono<Void> deleteAll();
    Mono<OrderData> updateById(String orderId, OrderData orderData);
}
