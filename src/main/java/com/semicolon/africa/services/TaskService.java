package com.semicolon.africa.services;

import com.semicolon.africa.dtos.request.AddTaskRequest;
import com.semicolon.africa.dtos.request.UpdateTaskRequest;
import com.semicolon.africa.dtos.response.AddTaskResponse;
import com.semicolon.africa.dtos.response.DeleteTaskResponse;
import com.semicolon.africa.dtos.response.UpdateTaskResponse;

public interface TaskService {
    AddTaskResponse addTask(AddTaskRequest addTaskRequest);
    UpdateTaskResponse updateTask(UpdateTaskRequest updateTaskRequest);
    DeleteTaskResponse deleteTask(String taskId);
    

}
