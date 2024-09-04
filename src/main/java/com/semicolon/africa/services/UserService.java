package com.semicolon.africa.services;

import com.semicolon.africa.dtos.request.CreateUserRequest;
import com.semicolon.africa.dtos.request.DeleteUserRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.UpdateUserRequest;
import com.semicolon.africa.dtos.response.CreateUserResponse;
import com.semicolon.africa.dtos.response.DeleteUserResponse;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.UpdateUserResponse;

public interface UserService {
    CreateUserResponse signUp(CreateUserRequest createUserRequest);
    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);
    LoginResponse login(LoginRequest loginRequest);
    DeleteUserResponse deleteUser(String id);



}
