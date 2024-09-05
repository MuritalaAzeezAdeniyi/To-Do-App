package com.semicolon.africa.services;

import com.semicolon.africa.dtos.request.AddTaskRequest;
import com.semicolon.africa.dtos.response.AddTaskResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;
    @Test
    public void testThatUserCanAddTask() {
        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setTitle("Cook");
        addTaskRequest.setNote("process to be taken be cook in the Kitchen");
        addTaskRequest.setUseEmail("azeez");
        AddTaskResponse addTaskResponse = taskService.addTask(addTaskRequest);
        assertNotNull(addTaskResponse);
        assertThat(addTaskResponse.getMessage()).isEqualTo("Task added successfully");
    }

}