package com.semicolon.africa.web;

import com.semicolon.africa.dtos.request.AddTaskRequest;
import com.semicolon.africa.dtos.response.AddTaskResponse;
import com.semicolon.africa.dtos.response.ApiResponse;
import com.semicolon.africa.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequest addTaskRequest) {
        try {
            AddTaskResponse addTaskResponse = taskService.addTask(addTaskRequest);
            return new ResponseEntity<>(new ApiResponse(true, addTaskResponse), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
