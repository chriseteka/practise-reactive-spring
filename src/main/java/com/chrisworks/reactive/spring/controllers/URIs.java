package com.chrisworks.reactive.spring.controllers;

public class URIs {

    public final static String BASE_URI = "/api/rs";
    public final static String URI_ROOT = "";

    public final static String USER_URI = "/user";
    public final static String USER_LOGIN = "/login";
    public final static String USER_REGISTER = "/register";
    public final static String USER_BY_ID = "/{userId}";
    public final static String USER_BY_USERNAME = "/{userName}";

    final static String PAYMENT_URI = "/payment";
    public final static String PAYMENT_BY_ID = "/{paymentId}";

    final static String ORDER_URI = "/order";
    public final static String ORDER_BY_ID = "/{orderId}";
}
