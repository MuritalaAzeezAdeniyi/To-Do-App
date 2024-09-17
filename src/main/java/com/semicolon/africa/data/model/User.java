package com.semicolon.africa.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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
     @Id
    private String id;
     @DBRef
    private List<Task> tasks = new ArrayList<>();


}
