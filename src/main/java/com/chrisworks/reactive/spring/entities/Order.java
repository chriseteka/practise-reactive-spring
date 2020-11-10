package com.chrisworks.reactive.spring.entities;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.OrderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "Orders")
public class Order {

    @Id
    private String orderId;
    private String products;
    private int quantity;
    private Payment payment;
    private User customer;

    public OrderData orderData(){
        return OrderData.builder()
                .id(this.orderId.toString())
                .amountPaid(this.payment.getAmount())
                .quantity(this.quantity)
                .products(this.products)
                .customerId(this.customer.getUserId().toString())
                .build();
    }
}
