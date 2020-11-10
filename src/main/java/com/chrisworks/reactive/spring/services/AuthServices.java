package com.chrisworks.reactive.spring.services;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.RegisterData;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.LoginResponseData;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.LoginData;
import reactor.core.publisher.Mono;

public interface AuthServices {

    Mono<RegisterData> signUp(RegisterData registerData);
    Mono<LoginResponseData> signIn(LoginData loginData);

}
