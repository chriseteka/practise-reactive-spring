package com.chrisworks.reactive.spring.controllers;

import com.chrisworks.reactive.spring.controllers.handlers.OrderHandler;
import com.chrisworks.reactive.spring.controllers.handlers.PaymentHandler;
import com.chrisworks.reactive.spring.controllers.handlers.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.chrisworks.reactive.spring.controllers.URIs.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor
public class AppController {

    private final UserHandler userHandler;
    private final OrderHandler orderHandler;
    private final PaymentHandler paymentHandler;

    /*
     * This method is a functional way of specifying all the endpoint exposed by this application
     * It shall in itself take functions that are handlers that returns a server response
     * Many thanks to spring framework 5.
     */
    @Bean
    public RouterFunction<ServerResponse> appEndpoints() {
        return route()
                //"/api/rs"
                .nest(path(BASE_URI), baseRoute -> baseRoute

                        //All user's URLS are handled here "/api/rs/user"
                        .nest(path(USER_URI), userHandler::routes)

                        //All order's URLS are handled here "/api/rs/order"
                        .nest(path(ORDER_URI), orderHandler::routes)

                        //All payment's URLs are handled here "/api/rs/payment"
                        .nest(path(PAYMENT_URI), paymentHandler::routes)

                        .build())
                .build();
    }
}
