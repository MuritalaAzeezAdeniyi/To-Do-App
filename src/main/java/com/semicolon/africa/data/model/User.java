package com.semicolon.africa.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {
    private String firsName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private boolean isLoggedIn;

}
