package com.chrisworks.reactive.spring.controllers.handlers;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.LoginResponseData;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.RegisterData;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.LoginData;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.BaseRoutes;
import com.chrisworks.reactive.spring.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import static com.chrisworks.reactive.spring.controllers.URIs.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class UserHandler implements BaseRoutes {

    private final UserServices userServices;
    private final String NEW_USER_URL_BUILDER = "%s%s/%s";
    private final String USER_ID_PARAM = "userId";
    private final String USER_NAME_PARAM = "userName";

    @Override
    public RouterFunction<ServerResponse> routes() {
        return route()
            .POST(USER_REGISTER, serverRequest -> {
                var registeredUser = serverRequest.bodyToMono(RegisterData.class)
                        .flatMap(userServices::signUp);
                return created(URI.create(String.format(NEW_USER_URL_BUILDER, BASE_URI,
                        USER_URI, registeredUser.map(RegisterData::getId))))
                        .body(registeredUser, RegisterData.class);
            })
            .POST(USER_LOGIN, serverRequest -> {
                var loggedInUser = serverRequest.bodyToMono(LoginData.class)
                        .flatMap(userServices::signIn);
                return ok().body(loggedInUser, LoginResponseData.class);
            })
            .PUT(USER_BY_ID, serverRequest -> {
                var userId = serverRequest.pathVariable(USER_ID_PARAM);
                var updatedUser = serverRequest.bodyToMono(RegisterData.class)
                        .flatMap(updateData -> userServices.updateById(userId, updateData));
                return ok().body(updatedUser, RegisterData.class);
            })
            .PUT(USER_BY_USERNAME, serverRequest -> {
                var userName = serverRequest.pathVariable(USER_NAME_PARAM);
                var updatedUser = serverRequest.bodyToMono(RegisterData.class)
                        .flatMap(updateData -> userServices.updateByUsername(userName, updateData));
                return ok().body(updatedUser, RegisterData.class);
            })
            .DELETE(USER_BY_ID, serverRequest -> {
                var userId = serverRequest.pathVariable(USER_ID_PARAM);
                return ok().body(userServices.deleteById(userId), RegisterData.class);
            })
            .DELETE(USER_BY_USERNAME, serverRequest -> {
                var userName = serverRequest.pathVariable(USER_NAME_PARAM);
                return ok().body(userServices.deleteByUsername(userName), RegisterData.class);
            })
            .DELETE(URI_ROOT, res -> ok().body(userServices.deleteAll(), RegisterData.class))
            .GET(USER_BY_ID, serverRequest -> {
                var userId = serverRequest.pathVariable(USER_ID_PARAM);
                return ok().body(userServices.fetchById(userId), RegisterData.class);
            })
            .GET(USER_BY_USERNAME, serverRequest -> {
                var userName = serverRequest.pathVariable(USER_NAME_PARAM);
                return ok().body(userServices.fetchByUsername(userName), RegisterData.class);
            })
            .GET(URI_ROOT, res -> ok().body(userServices.fetchAll(), RegisterData.class))
            .build();
    }
}
