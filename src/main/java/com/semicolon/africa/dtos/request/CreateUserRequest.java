package com.semicolon.africa.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class CreateUserResponse {
    private String name;
    private String email;
    private String password;
    private String phone;
}
