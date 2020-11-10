package com.chrisworks.reactive.spring.services.impl;

import com.chrisworks.reactive.spring.entities.Order;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.OrderData;
import com.chrisworks.reactive.spring.repositories.OrderRepository;
import com.chrisworks.reactive.spring.services.OrderServices;
import com.chrisworks.reactive.spring.services.PaymentServices;
import com.chrisworks.reactive.spring.services.UserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.convertStringToUUID;
import static ir.cafebabe.math.utils.BigDecimalUtils.is;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServicesImpl implements OrderServices {

    private final OrderRepository orderRepository;
    private final PaymentServices paymentServices;
    private final UserServices userServices;

    @Override
    public Mono<OrderData> fetchById(String orderId) {

        return orderRepository
                .findById(convertStringToUUID(orderId))
                .map(Order::orderData);
    }

    @Override
    public Flux<OrderData> fetchAll() {

        return orderRepository.findAll().map(Order::orderData);
    }

    @Override
    public Mono<Void> deleteById(String orderId) {

        return orderRepository
                .deleteById(convertStringToUUID(orderId));
    }

    @Override
    public Mono<Void> deleteAll() {

        return orderRepository.deleteAll();
    }

    @Override
    public Mono<OrderData> updateById(String orderId, OrderData orderData) {

        return orderRepository
                .findById(convertStringToUUID(orderId))
                .flatMap(order -> {

                    //Update payment suppose there was a change in the payments
                    if (is(order.getPayment().getAmount()).notEq(orderData.getAmountPaid()))
                        paymentServices
                                .updateById(order.getPayment().getPaymentId().toString(), orderData.getAmountPaid());

                    //Update the user(customer) details if there were a change
                    userServices.fetchById(orderData.getCustomerId())
                            .doOnSuccess(userData -> {
                                var customer = order.getCustomer();
                                if (!userData.equals(customer.userData()))
                                    userServices.updateById(customer.getUserId().toString(), userData);
                            });

                    order.setProducts(orderData.getProducts());
                    order.setQuantity(orderData.getQuantity());

                    return orderRepository.save(order).map(Order::orderData);
                });
    }
}
