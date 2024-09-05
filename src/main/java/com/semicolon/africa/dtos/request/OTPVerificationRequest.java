package com.semicolon.africa.dtos.request;

import lombok.Data;

@Data
public class OTPVerificationRequest {
    private String email;
    private String body;
}
