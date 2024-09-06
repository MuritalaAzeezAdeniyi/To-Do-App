package com.semicolon.africa.services;

import com.semicolon.africa.data.model.Task;
import com.semicolon.africa.data.model.User;
import com.semicolon.africa.data.repository.TaskRepo;
import com.semicolon.africa.data.repository.UserRepos;
import com.semicolon.africa.dtos.request.AddTaskRequest;
import com.semicolon.africa.dtos.response.AddTaskResponse;
import com.semicolon.africa.exeception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private UserRepos  userRepos ;
    @Autowired
    private TaskRepo taskRepository ;

    @Override
    public AddTaskResponse addTask(AddTaskRequest addTaskRequest) {
        try {
            User user = userRepos.findUsersByEmail(addTaskRequest.getUseEmail());
            if (user != null && user.isLoggedIn()) {
                Task newTask = new Task();
                newTask.setTitle(addTaskRequest.getTitle());
                newTask.setNote(addTaskRequest.getNote());
                newTask.setEmail(addTaskRequest.getUseEmail());
                newTask.setDueDate(LocalDate.now());
                newTask.setCompleted(true);
                taskRepository.save(newTask);
                AddTaskResponse addTaskResponse = new AddTaskResponse();
                addTaskResponse.setUserEmail(addTaskRequest.getUseEmail());
                addTaskResponse.setMessage("Task added successfully");
                return addTaskResponse;

            }

        }catch (LoginException ignored) {

        }
        throw new LoginException("User is not logged in");
    }
}
