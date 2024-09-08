package com.semicolon.africa.services;

import com.semicolon.africa.data.model.User;
import com.semicolon.africa.data.repository.UserRepos;
import com.semicolon.africa.dtos.request.CreateUserRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.UpdateUserRequest;
import com.semicolon.africa.dtos.response.CreateUserResponse;
import com.semicolon.africa.dtos.response.DeleteUserResponse;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.UpdateUserResponse;
import com.semicolon.africa.exeception.EmailAlreadyExistException;
import com.semicolon.africa.exeception.InvalidPasswordException;
import com.semicolon.africa.exeception.RegisterValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepos userRepository;
    @Autowired
     private EmailSenderServices emailSenderServices;

    @Override
    public CreateUserResponse signUp(CreateUserRequest createUserRequest) {
        EmailVerification(createUserRequest.getEmail());
        RegisterValidation(createUserRequest);
        phoneNumberValidation(createUserRequest.getPhone());
        User user = new User();
        user.setFirsName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setPassword(hashPassword(createUserRequest.getPassword()));
        user.setPhone(createUserRequest.getPhone());
        userRepository.save(user);
        String userId = user.getId();
        emailSenderServices.sendOTPVerificationMail(userId, createUserRequest.getEmail());
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setEmail(createUserRequest.getEmail());
        createUserResponse.setMessage("Token sent to mail successfully");
        return createUserResponse;
    }


    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = userRepository.findUserByEmail(updateUserRequest.getEmail());
        if (user != null) {
            user.setFirsName(updateUserRequest.getFirstName());
            user.setLastName(updateUserRequest.getLastName());
            user.setPassword(hashPassword(updateUserRequest.getPassword()));
            user.setPhone(updateUserRequest.getPhone());
            userRepository.save(user);
        }
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setEmail(updateUserRequest.getEmail());
        updateUserResponse.setPhone(updateUserRequest.getPhone());
        updateUserResponse.setFirstName(updateUserRequest.getFirstName());
        updateUserResponse.setLastName(updateUserRequest.getLastName());
        updateUserResponse.setMessage("Successfully updated!");
        return updateUserResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = findByEmail(loginRequest.getEmail());
        passwordValidation(user, loginRequest.getPassword());
        LoginResponse loginResponse = new LoginResponse();
        user.setLoggedIn(true);
        userRepository.save(user);

        loginResponse.setMessage("Successfully logged in!");
        return loginResponse;
    }

    @Override
    public DeleteUserResponse deleteUser(String id) {
        userRepository.deleteById(id);
        DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
        deleteUserResponse.setMessage("User successfully deleted!");
        return deleteUserResponse;
    }


    public void EmailVerification(String email) {
        boolean existByEmail = userRepository.existsByEmail(email);
        if (existByEmail) throw new EmailAlreadyExistException("User already exist!");

    }

    private void phoneNumberValidation(String phone) {
        if (phone.length() != 11) {
            throw new RegisterValidationException("Phone number must be 11 digits!");
        }
    }

    private void RegisterValidation(CreateUserRequest createUserRequest) {
        if (createUserRequest.getLastName().trim().isEmpty() ||
                createUserRequest.getPassword().trim().isEmpty() ||
                createUserRequest.getEmail().trim().isEmpty() ||
                createUserRequest.getPhone().trim().isEmpty() ||
                createUserRequest.getFirstName().trim().isEmpty()) {
            throw new RegisterValidationException("Wrong detail entered!");
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailAlreadyExistException(email + " does not exist"));

    }


    private void passwordValidation(User user, String password) {
        boolean isPasswordMatch =
                passwordEncoder.matches(password, user.getPassword()
                );
        if (!isPasswordMatch) {
            throw new InvalidPasswordException("invalid credentials!");
        }
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }



}

