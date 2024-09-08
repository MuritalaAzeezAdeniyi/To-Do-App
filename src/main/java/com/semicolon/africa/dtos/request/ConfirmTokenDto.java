package com.semicolon.africa.dtos.request;

import lombok.Data;
@Data
public class ConfirmTokenDto {
    private int token;
    private String email;
}
