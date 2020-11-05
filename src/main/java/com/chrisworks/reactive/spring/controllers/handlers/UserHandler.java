package com.chrisworks.reactive.spring.controllers.handlers;

import com.chrisworks.reactive.spring.Entities.DTOs.RequestResponseObject.BaseRoutes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandler implements BaseRoutes {


    @Override
    public RouterFunction<ServerResponse> routes() {

        String URI_ROOT = "";
        return route()
                .GET(URI_ROOT, res -> ok().body("Hello customer", String.class))
                .build();
    }
}
