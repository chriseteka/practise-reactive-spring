package com.chrisworks.reactive.spring.controllers.handlers;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.BaseRoutes;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.OrderData;
import com.chrisworks.reactive.spring.services.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.chrisworks.reactive.spring.controllers.URIs.ORDER_BY_ID;
import static com.chrisworks.reactive.spring.controllers.URIs.URI_ROOT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class OrderHandler implements BaseRoutes {

    private final OrderServices orderServices;
    private final String ORDER_ID_PARAM = "orderId";

    @Override
    public RouterFunction<ServerResponse> routes() {

        return route()
                .GET(URI_ROOT, request -> ok().body(orderServices.fetchAll(), OrderData.class))
                .DELETE(URI_ROOT, request -> ok().body(orderServices.deleteAll(), OrderData.class))
                .PUT(ORDER_BY_ID, request -> {
                    var orderId = request.pathVariable(ORDER_ID_PARAM);
                    var updatedOrder = request.bodyToMono(OrderData.class)
                            .flatMap(orderData -> orderServices.updateById(orderId, orderData));
                    return ok().body(updatedOrder, OrderData.class);
                })
                .GET(ORDER_BY_ID, request -> {
                    var orderId = request.pathVariable(ORDER_ID_PARAM);
                    return ok().body(orderServices.fetchById(orderId), OrderData.class);
                })
                .DELETE(ORDER_BY_ID, request -> {
                    var orderId = request.pathVariable(ORDER_ID_PARAM);
                    return ok().body(orderServices.deleteById(orderId), OrderData.class);
                })
                .build();
    }
}
