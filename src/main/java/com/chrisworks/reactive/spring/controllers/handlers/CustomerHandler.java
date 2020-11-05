package com.chrisworks.reactive.spring.controllers.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class CustomerHandler implements BaseHandler {

    @Value("${app.base.root}") static String URI_ROOT;

    @Override
    public RouterFunction<ServerResponse> defaultRoutes() {

        return route()
                .GET(URI_ROOT, res -> ok().body("Hello customer", String.class))
                .build();
    }
}
