package com.chrisworks.reactive.spring.entities;

import com.chrisworks.reactive.spring.entities.dtos.RequestResponseObject.RegisterData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(value = "Users")
public class User implements Persistable<String> {

    @Id
    private String userId;
    private String name;
    private String state;
    private int age;
    private String username;
    private String password;
    private UserType userType;

    @Override
    public String getId() {
        return this.userId;
    }

    @Override
    public boolean isNew() {
        return this.userId == null;
    }


    public enum UserType {
        CUSTOMER, ADMIN, SELLER
    }

    public RegisterData userData(){
        return RegisterData.builder()
                .id(this.userId.toString())
                .name(this.name)
                .age(this.age)
                .username(this.username)
                .state(this.state)
                .build();
    }
}
