package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.Task;
import com.semicolon.africa.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends MongoRepository<Task, String> {
     Task findByTitle(String title);
     Optional<Task> findTaskByEmail(String email);


}
