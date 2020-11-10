package com.chrisworks.reactive.spring.repositories;

import com.chrisworks.reactive.spring.entities.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface OrderRepository extends ReactiveCrudRepository<Order, String> {
}
