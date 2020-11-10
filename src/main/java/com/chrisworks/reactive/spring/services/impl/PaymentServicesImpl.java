package com.chrisworks.reactive.spring.services.impl;

import com.chrisworks.reactive.spring.entities.Payment;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.PaymentData;
import com.chrisworks.reactive.spring.repositories.PaymentRepository;
import com.chrisworks.reactive.spring.services.PaymentServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.convertStringToUUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServicesImpl implements PaymentServices {

    private final PaymentRepository paymentRepository;

    @Override
    public Mono<PaymentData> fetchById(String paymentId) {

        return paymentRepository
                .findById(convertStringToUUID(paymentId))
                .map(Payment::paymentData);
    }

    @Override
    public Flux<PaymentData> fetchAll() {

        return paymentRepository.findAll().map(Payment::paymentData);
    }

    @Override
    public Mono<Void> deleteById(String userId) {

        return paymentRepository.deleteById(convertStringToUUID(userId));
    }

    @Override
    public Mono<Void> deleteAll() {

        return paymentRepository.deleteAll();
    }

    @Override
    public Mono<PaymentData> updateById(String paymentId, BigDecimal amount) {

        return paymentRepository
                .findById(convertStringToUUID(paymentId))
                .flatMap(payment -> {
                    payment.setAmount(amount);
                    return paymentRepository.save(payment)
                            .map(Payment::paymentData);
                });
    }
}
