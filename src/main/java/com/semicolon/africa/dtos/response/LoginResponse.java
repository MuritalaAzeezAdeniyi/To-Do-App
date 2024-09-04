package com.semicolon.africa.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class LoginResponse {
    private String message;
}
