package com.semicolon.africa.services;

import com.semicolon.africa.data.model.Task;
import com.semicolon.africa.data.model.User;
import com.semicolon.africa.data.repository.TaskRepo;
import com.semicolon.africa.dtos.request.AddTaskRequest;
import com.semicolon.africa.dtos.request.FindAllRequest;
import com.semicolon.africa.dtos.request.UpdateTaskRequest;
import com.semicolon.africa.dtos.response.AddTaskResponse;
import com.semicolon.africa.dtos.response.DeleteTaskResponse;
import com.semicolon.africa.dtos.response.UpdateTaskResponse;
import com.semicolon.africa.exeception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskRepo taskRepository;


    @Override
    public AddTaskResponse addTask(AddTaskRequest addTaskRequest) {
        User user = findByEmail(addTaskRequest.getUserEmail());
        if (!user.isLoggedIn())
            throw new LoginException("User is not logged in");
        Optional<Task> taskOptional = Optional.ofNullable(taskRepository.findByTitle(addTaskRequest.getTitle()));
        if (taskOptional.isPresent()) {
            throw new LoginException("Task already exists");
        }

        Task newTask = new Task();
        newTask.setTitle(addTaskRequest.getTitle());
        newTask.setNote(addTaskRequest.getNote());
        newTask.setEmail(addTaskRequest.getUserEmail());
        newTask.setDueDate(LocalDate.now());
        newTask.setCompleted(true);
        taskRepository.save(newTask);
        AddTaskResponse addTaskResponse = new AddTaskResponse();
        addTaskResponse.setUserEmail(addTaskRequest.getUserEmail());
        addTaskResponse.setMessage("Task added successfully");
        return addTaskResponse;

    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskRequest updateTaskRequest) {
        Task newTask = findByTitle(updateTaskRequest.getTitle());
        if (newTask == null) {
            throw new RuntimeException("Task with" + updateTaskRequest.getTitle() + "not found");
        }
        newTask.setTitle(updateTaskRequest.getTitle());
        newTask.setNote(updateTaskRequest.getNote());
        newTask.setDueDate(LocalDate.now());
        newTask.setCompleted(true);
        taskRepository.save(newTask);
        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse();
        updateTaskResponse.setMessage("Task updated successfully");
        return updateTaskResponse;
    }

    @Override
    public DeleteTaskResponse deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        DeleteTaskResponse deleteTaskResponse = new DeleteTaskResponse();
        deleteTaskResponse.setMessage("Task deleted successfully");
        return deleteTaskResponse;
    }

    @Override
    public String FindAll(FindAllRequest findAllRequest) {
        Optional<User> userOptional = Optional.ofNullable(userService.findByEmail(findAllRequest.getEmail()));
        User user = userOptional.get();
        if (userOptional.isPresent()) {
            return taskRepository.findTaskByEmail(user.getEmail()).toString();
        }
        return null;
    }


    private User findByEmail(String email) {
        return userService.findByEmail(email);
    }

    private Task findByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    private Task findTaskByUserEmail(String email) {
        return taskRepository.findTaskByEmail(email)
                .orElseThrow(() -> new RuntimeException("Task with" + email + "not found"));
    }


}
