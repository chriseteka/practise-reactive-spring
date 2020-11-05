package com.chrisworks.reactive.spring.Entities.DTOs;

import com.chrisworks.reactive.spring.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RequestResponseObject {

    public interface BaseRoutes {
        RouterFunction<ServerResponse> routes();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class LoginData {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderData {
        private String id;
        private String products;
        private int quantity;
        private BigDecimal amountPaid;
        private String customerId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentData {
        private String id;
        private String orderId;
        private BigDecimal amount;
        private LocalDateTime createdDate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RegisterData {
        private String id;
        private String name;
        private String state;
        private int age;
        private String username;
        private String password;
        private String userType;

        public User toUser() {
            return User.builder()
                    .name(this.name)
                    .state(this.state)
                    .age(this.getAge())
                    .username(this.username)
                    .password(this.password)
                    .userType(User.UserType.valueOf(this.userType))
                    .build();
        }
    }
}
