package com.semicolon.africa.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {
    private String message;
    private String email;
    private String id;
}
