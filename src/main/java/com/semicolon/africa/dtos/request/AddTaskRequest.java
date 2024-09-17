package com.semicolon.africa.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTaskRequest {
    private String title;
    private String description;
    private String userEmail;

}
