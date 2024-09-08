package com.semicolon.africa.web;

import com.semicolon.africa.data.model.Otp;
import com.semicolon.africa.data.model.User;
import com.semicolon.africa.dtos.request.*;
import com.semicolon.africa.dtos.response.*;
import com.semicolon.africa.services.EmailSenderServices;
import com.semicolon.africa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
     private EmailSenderServices emailServices;
    @PostMapping("/signUp")

    public ResponseEntity<?> signUp(@RequestBody CreateUserRequest createUserRequest) {
        try {
            CreateUserResponse createUserResponse = userService.signUp(createUserRequest);
            return new ResponseEntity<> (new ApiResponse(true, createUserResponse), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<> (e.getMessage(),HttpStatus.BAD_REQUEST);

        }

    }
    @PatchMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        try {
            UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest);
         return new ResponseEntity<> (new ApiResponse(true, updateUserResponse), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<> (e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try{
            LoginResponse loginResponse = userService.login(loginRequest);
            return new ResponseEntity<> (new ApiResponse(true, loginResponse), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<> (e.getMessage(),HttpStatus.BAD_REQUEST);
        }
   }
   @DeleteMapping("/deleteUser{id}")
   public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try{
            DeleteUserResponse deleteUserResponse = userService.deleteUser(id);
            return new ResponseEntity<> (new ApiResponse(true, deleteUserResponse), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<> (e.getMessage(),HttpStatus.BAD_REQUEST);

        }
   }

    @PostMapping("/confirm")
    public String confirm(@RequestBody ConfirmTokenDto confirmTokenDto) {

         return  emailServices.confirmOTP(confirmTokenDto);

    }

}
