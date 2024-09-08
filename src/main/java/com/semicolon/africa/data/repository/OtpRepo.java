package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepo extends MongoRepository<Otp, String> {
    Otp findByOtpCode(int otp);
}
