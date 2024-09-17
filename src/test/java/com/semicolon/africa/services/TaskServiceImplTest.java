package com.semicolon.africa.services;

import com.semicolon.africa.data.model.Task;
import com.semicolon.africa.dtos.request.AddTaskRequest;
import com.semicolon.africa.dtos.request.DeleteTaskRequest;
import com.semicolon.africa.dtos.request.UpdateTaskRequest;
import com.semicolon.africa.dtos.response.AddTaskResponse;
import com.semicolon.africa.dtos.response.DeleteTaskResponse;
import com.semicolon.africa.dtos.response.UpdateTaskResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void testThatUserCanAddTask() {
        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setTitle("Cook");
        addTaskRequest.setDescription("process to be taken be cook in the Kitchen");
        addTaskRequest.setUserEmail("ola");
        AddTaskResponse addTaskResponse = taskService.addTask(addTaskRequest);
        assertNotNull(addTaskResponse);
        assertThat(addTaskResponse.getMessage()).isEqualTo("Task added successfully");
    }

    @Test
    public void testThatUserCanUpdateTask() {
        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        updateTaskRequest.setTitle("Cook");
        updateTaskRequest.setNote("process to be done in the Kitchen");
        UpdateTaskResponse updateTaskResponse = taskService.updateTask(updateTaskRequest);
        assertNotNull(updateTaskResponse);
        assertThat(updateTaskResponse.getMessage()).isEqualTo("Task updated successfully");
    }

    @Test
    public void testThaTaskCanDeleteTask() {
        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.setTitle("Cook");
        addTaskRequest.setDescription("process to be taken be cook in the Kitchen");
        addTaskRequest.setUserEmail("ola");
        AddTaskResponse addTaskResponse = taskService.addTask(addTaskRequest);
        DeleteTaskRequest deleteTaskRequest = new DeleteTaskRequest();
        deleteTaskRequest.setTaskId(addTaskResponse.getId());
        DeleteTaskResponse deleteTaskResponse = taskService.deleteTask("66dcb469cc7cec2ceb87b098");
        assertNotNull(deleteTaskResponse);
        assertThat(deleteTaskResponse.getMessage()).isEqualTo("Task deleted successfully");
    }

    @Test
    public void testUserCanGetTask(){
       List<Task> tasks =  taskService.findAll("Taye@gmail.com");
       assertThat(!tasks.isEmpty()).isFalse();
    }

}