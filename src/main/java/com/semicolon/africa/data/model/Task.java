package com.semicolon.africa.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate dueDate;
    private String userEmail;

}
