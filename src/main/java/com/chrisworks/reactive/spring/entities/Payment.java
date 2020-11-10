package com.chrisworks.reactive.spring.entities;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.PaymentData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "Payments")
public class Payment {

    @Id
    private String paymentId;
    private Order order;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public PaymentData paymentData(){
        return PaymentData.builder()
                .id(paymentId.toString())
                .amount(this.amount)
                .createdDate(this.createdAt)
                .orderId(this.order.getOrderId().toString())
                .build();
    }
}
