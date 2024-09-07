package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepos extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
   Optional<User> findByEmail(String email);
   User findUserByEmail(String email);

}
