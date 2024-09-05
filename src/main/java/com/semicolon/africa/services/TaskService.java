package com.semicolon.africa.services;

import com.semicolon.africa.dtos.request.AddTaskRequest;
import com.semicolon.africa.dtos.response.AddTaskResponse;

public interface TaskService {
    AddTaskResponse addTask(AddTaskRequest addTaskRequest);
    

}
