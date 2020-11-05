package com.chrisworks.reactive.spring.controllers.handlers;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface BaseHandler {

    RouterFunction<ServerResponse> defaultRoutes();
}
