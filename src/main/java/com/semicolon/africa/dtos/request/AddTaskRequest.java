package com.semicolon.africa.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
public class AddTaskRequest {
    private String title;
    private String note;
    private String UseEmail;

}
