package com.chrisworks.reactive.spring.Entities;

import com.chrisworks.reactive.spring.Entities.DTOs.RequestResponseObject.PaymentData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "Payments")
public class Payment {

    private String paymentId;
    private Order order;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public PaymentData paymentData(){
        return PaymentData.builder()
                .id(paymentId)
                .amount(this.amount)
                .createdDate(this.createdAt)
                .orderId(this.order.getOrderId())
                .build();
    }
}
