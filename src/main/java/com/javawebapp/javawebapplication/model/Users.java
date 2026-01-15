package com.javawebapp.javawebapplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class Users {
    @Id
    private String id;
    private String email;
    private String password;
    @Indexed(unique = true)
    private String username;

}
