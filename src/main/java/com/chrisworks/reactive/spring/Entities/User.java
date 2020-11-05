package com.chrisworks.reactive.spring.Entities;

import com.chrisworks.reactive.spring.Entities.DTOs.RequestResponseObject.RegisterData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(value = "Users")
public class User {

    @Id
    private String userId;
    private String name;
    private String state;
    private int age;
    private String username;
    private String password;
    @Builder.Default
    private UserType userType = UserType.CUSTOMER;


    public enum UserType {
        CUSTOMER, ADMIN, SELLER
    }

    public RegisterData userData(){
        return RegisterData.builder()
                .id(this.userId)
                .name(this.name)
                .age(this.age)
                .username(this.username)
                .state(this.state)
                .build();
    }
}
