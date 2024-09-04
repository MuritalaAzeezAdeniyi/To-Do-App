package com.semicolon.africa.services;

import com.semicolon.africa.data.repository.UserRepos;
import com.semicolon.africa.dtos.request.CreateUserRequest;
import com.semicolon.africa.dtos.request.DeleteUserRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.UpdateUserRequest;
import com.semicolon.africa.dtos.response.CreateUserResponse;
import com.semicolon.africa.dtos.response.DeleteUserResponse;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.UpdateUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserRepos userRepos;
    @Autowired
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
     userRepos.deleteAll();
    }

    @Test
    public void checkIfUserCanRegisterUser() {
        CreateUserResponse createUserResponse = registerUse();
        assertNotNull(createUserResponse);
        assertThat(createUserResponse.getMessage()).isEqualTo("Successfully registered!");
    }

    private CreateUserResponse registerUse() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Murital");
        createUserRequest.setLastName("Muritual");
        createUserRequest.setEmail("murital@gmail.com");
        createUserRequest.setPassword("Murital");
        createUserRequest.setPhone("O8109643956");
        CreateUserResponse createUserResponse = userService.signUp(createUserRequest);
        return createUserResponse;
    }

    @Test
    public void testIfUserExists() {
        CreateUserResponse createUserResponse = registerUse();
        assertThat(createUserResponse);


    }

    @Test
    public void testIfUserCanBeUpdated(){
        CreateUserResponse response = registerUse();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setFirstName("Mali");
        updateUserRequest.setLastName("Muritala");
        updateUserRequest.setEmail(response.getEmail());
        updateUserRequest.setPassword("Mali");
        updateUserRequest.setPhone("O8109643912");
        UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest);
        updateUserResponse.setMessage("Successfully updated!");
        assertThat(updateUserResponse.getMessage()).isEqualTo("Successfully updated!");

    }

    @Test
    public void checkIfUserCanLogIn(){
        registerUse();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("murital@gmail.com");
        loginRequest.setPassword("Murital");
        LoginResponse loginResponse = userService.login(loginRequest);
        loginResponse.setMessage("Successfully logged in!");
    }

    @Test
    public void testIfUserCanDelete(){
        CreateUserResponse response = registerUse();
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setId(response.getId());
        DeleteUserResponse deleteUserResponse = userService.deleteUser("66d7a3ff58090c40371e1110");
        assertThat(deleteUserResponse.getMessage()).isEqualTo("User successfully deleted!");



    }

    }