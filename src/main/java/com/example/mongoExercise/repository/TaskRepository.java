package com.example.mongoExercise.repository;

import com.example.mongoExercise.model.Task;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByCategory(String category);
    List<Task> findAllBy(TextCriteria criteria);
    @Query("{ 'subTasks.name': ?0}")
    List<Task> findBySubTaskName(String name);

    List<Task> findBydeadlineDateAfter(Date startDate);

}
