package com.chrisworks.reactive.spring.repositories;

import com.chrisworks.reactive.spring.entities.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {
}
