package com.chrisworks.reactive.spring.controllers;

import com.chrisworks.reactive.spring.controllers.handlers.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.chrisworks.reactive.spring.controllers.URIs.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@EnableR2dbcRepositories
@Configuration
@RequiredArgsConstructor
public class AppController {

    private final UserHandler userHandler;

    private RouterFunction<ServerResponse> orderRoutes = route()
            .GET(URI_ROOT, res -> ok().body(Mono.just("Hello order"), String.class))
            .build();

    private RouterFunction<ServerResponse> paymentRoutes = route()
            .GET(URI_ROOT, res -> ok().body(Mono.just("Hello Payment"), String.class))
            .build();

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
                        .nest(path(ORDER_URI), () -> orderRoutes)

                        //All payment's URLs are handled here
                        .nest(path(PAYMENT_URI), () -> paymentRoutes)

                        .build())
                .build();
    }
}
