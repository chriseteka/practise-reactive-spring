package com.chrisworks.reactive.spring.services.impl;

import com.chrisworks.reactive.spring.entities.User;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.RegisterData;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.LoginData;
import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.LoginResponseData;
import com.chrisworks.reactive.spring.repositories.UserRepository;
import com.chrisworks.reactive.spring.services.UserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.convertStringToUUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;

    @Override
    public Mono<RegisterData> fetchById(String userId) {

        return userRepository
                .findById(convertStringToUUID(userId))
                .map(User::userData);
    }

    @Override
    public Mono<RegisterData> fetchByUsername(String username) {

        return userRepository
                .fetchByUsername(username)
                .log()
                .map(User::userData)
                .elementAt(0);
    }

    @Override
    public Flux<RegisterData> fetchAll() {

        return userRepository.findAll().map(User::userData);
    }

    @Override
    public Mono<Void> deleteById(String userId) {

        return userRepository.deleteById(convertStringToUUID(userId));
    }

    @Override
    public Mono<Void> deleteByUsername(String username) {

        return userRepository.fetchByUsername(username)
                .elementAt(0)
                .flatMap(userRepository::delete);

    }

    @Override
    public Mono<Void> deleteAll() {

        return userRepository.deleteAll();
    }

    @Override
    public Mono<RegisterData> updateById(String userId, RegisterData userData) {

        return userRepository.findById(convertStringToUUID(userId))
                .flatMap(user -> {
                    var userUpdate = userData.toUser();
                    user.setAge(userUpdate.getAge());
                    user.setName(userUpdate.getName());
                    user.setState(userUpdate.getState());
                    return userRepository.save(user).map(User::userData);
                });
    }

    @Override
    public Mono<RegisterData> updateByUsername(String username, RegisterData userData) {

        return userRepository.fetchByUsername(username)
                .elementAt(0)
                .flatMap(user -> {
                    var userUpdate = userData.toUser();
                    user.setAge(userUpdate.getAge());
                    user.setName(userUpdate.getName());
                    user.setState(userUpdate.getState());
                    return userRepository.save(user).map(User::userData);
                });
    }

    @Override
    public Mono<RegisterData> signUp(RegisterData registerData) {

        //Little validation to disallow duplicate username
        return userRepository.fetchByUsername(registerData.getUsername())
                .map(user -> new Exception("Duplicate user: A user was found with same username"))
                .then(userRepository.save(registerData.toUser()))
                .map(User::userData);
    }

    @Override
    public Mono<LoginResponseData> signIn(LoginData loginData) {

        return userRepository.fetchByUsername(loginData.getUsername())
                .elementAt(0)
                .map(user -> {

                    //Case password match
                    if (user.getPassword().equalsIgnoreCase(loginData.getPassword()))
                        return LoginResponseData.builder()
                                .status("Success")
                                .user(user.userData())
                                .build();

                    //Case password mismatch
                    return LoginResponseData.builder()
                            .status("Failure")
                            .build();
                });
    }


}
