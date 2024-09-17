package com.semicolon.africa.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
