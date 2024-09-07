package com.semicolon.africa.web;

import com.semicolon.africa.dtos.request.AddTaskRequest;
import com.semicolon.africa.dtos.request.DeleteTaskRequest;
import com.semicolon.africa.dtos.request.UpdateTaskRequest;
import com.semicolon.africa.dtos.response.AddTaskResponse;
import com.semicolon.africa.dtos.response.ApiResponse;
import com.semicolon.africa.dtos.response.DeleteTaskResponse;
import com.semicolon.africa.dtos.response.UpdateTaskResponse;
import com.semicolon.africa.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping("/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
       try{
           UpdateTaskResponse updateTaskResponse = taskService.updateTask(updateTaskRequest);
           return new ResponseEntity<>(new ApiResponse(true, updateTaskResponse), HttpStatus.OK);
       }catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }


    }
    @DeleteMapping("/deleteTask/{Task_Id}")
    public ResponseEntity<?> deleteTask(@PathVariable ("Task_Id") String Task_Id ) {
        try{
            DeleteTaskResponse deleteTaskResponse = taskService.deleteTask(Task_Id);
            return new ResponseEntity<>(new ApiResponse(true, deleteTaskResponse), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

