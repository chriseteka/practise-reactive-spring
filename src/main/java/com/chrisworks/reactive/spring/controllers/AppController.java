package com.chrisworks.reactive.spring.controllers;

import com.chrisworks.reactive.spring.controllers.handlers.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static com.chrisworks.reactive.spring.controllers.AppController.URIs.*;

@Configuration
public class AppController {

    @Autowired private CustomerHandler customerHandler;

    /*
     * This method is a functional way of specifying all the endpoint exposed by this application
     * It shall in itself take functions that are handlers that returns a server response
     * Many thanks to springg framework 5.
     */
    @Bean
    RouterFunction<ServerResponse> appEndpoints() {

        RouterFunction<ServerResponse> orderRoutes = route()
                .GET(URI_ROOT, res -> ok().body("Hello order", String.class))
                .build();

        RouterFunction<ServerResponse> paymentRoutes = route()
                .GET(URI_ROOT, res -> ok().body("Hello Payment", String.class))
                .build();

        return route()
                //"/api/rs"
                .nest(path(BASE_URI), baseRoute -> baseRoute

                        //All user's URLS are handled here "/api/rs/user"
                        .nest(path(USER_URI), () -> customerHandler.defaultRoutes())

                        //All order's URLS are handled here "/api/rs/order"
                        .nest(path(ORDER_URI), () -> orderRoutes)

                        //All payment's URLs are handled here
                        .nest(path(PAYMENT_URI), () -> paymentRoutes)

                        .build())
                .build();
    }


    //This class will hold all the URI used round the app
    static class URIs {

        static RequestPredicate JSON = accept(APPLICATION_JSON).or(accept(APPLICATION_JSON_UTF8));

        @Value("${app.base.url}") static String BASE_URI;
        @Value("${app.base.root}") static String URI_ROOT;

        static String USER_URI = "/user";
        static String ORDER_URI = "/order";
        static String PAYMENT_URI = "/payment";

    }
}
