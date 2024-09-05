package com.semicolon.africa.data.model;

import lombok.Data;

@Data
public class Otp {
    private String id;
    private int otpCode;
    private String userId;
    private String email;



}
