//package com.chrisworks.reactive.spring.configurations;
//
//import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
//import io.r2dbc.postgresql.PostgresqlConnectionFactory;
//import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//
//@Profile("pg")
//@Configuration
//@EnableR2dbcRepositories
//public class PostgresConfig extends AbstractR2dbcConfiguration {
//
//    @Override
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return new PostgresqlConnectionFactory(
//                PostgresqlConnectionConfiguration.builder()
//                        .host("localhost")
//                        .port(5432)
//                        .username("postgres")
//                        .password("mysecretpassword")
//                        .database("myDatabase")
//                        .build());
//    }
//}
