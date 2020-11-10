package com.chrisworks.reactive.spring.services;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.PaymentData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface PaymentServices {
    Mono<PaymentData> fetchById(String paymentId);
    Flux<PaymentData> fetchAll();
    Mono<Void> deleteById(String paymentId);
    Mono<Void> deleteAll();
    Mono<PaymentData> updateById(String paymentId, BigDecimal amount);
}
