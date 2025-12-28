package com.javawebapp.javawebapplication.repository;

import com.javawebapp.javawebapplication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
