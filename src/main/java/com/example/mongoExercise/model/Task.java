package com.example.mongoExercise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("tasks")
public class Task {

    @Id
    private String id;
    private Date creationDate;
    private Date deadlineDate;
    private String name;
    @TextIndexed private String description;
    private String category;
    private List<SubTask> subTasks;

}
