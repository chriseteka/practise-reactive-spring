package com.chrisworks.reactive.spring.controllers.handlers;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.BaseRoutes;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.PaymentData;
import com.chrisworks.reactive.spring.services.PaymentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.chrisworks.reactive.spring.controllers.URIs.PAYMENT_BY_ID;
import static com.chrisworks.reactive.spring.controllers.URIs.URI_ROOT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class PaymentHandler implements BaseRoutes {

    private final PaymentServices paymentServices;
    private final String PAYMENT_ID_PARAM = "paymentId";

    @Override
    public RouterFunction<ServerResponse> routes() {

        return route()
            .GET(URI_ROOT, request -> ok().body(paymentServices.fetchAll(), PaymentData.class))
            .DELETE(URI_ROOT, request -> ok().body(paymentServices.deleteAll(), PaymentData.class))
            .PUT(PAYMENT_BY_ID, request -> {
                var paymentId = request.pathVariable(PAYMENT_ID_PARAM);
                var updatedPayment = request.bodyToMono(PaymentData.class)
                        .flatMap(paymentData -> paymentServices.updateById(paymentId, paymentData.getAmount()));
                return ok().body(updatedPayment, PaymentData.class);
            })
            .GET(PAYMENT_BY_ID, request -> {
                var paymentId = request.pathVariable(PAYMENT_ID_PARAM);
                return ok().body(paymentServices.fetchById(paymentId), PaymentData.class);
            })
            .DELETE(PAYMENT_BY_ID, request -> {
                var paymentId = request.pathVariable(PAYMENT_ID_PARAM);
                return ok().body(paymentServices.deleteById(paymentId), PaymentData.class);
            })
            .build();
    }
}
