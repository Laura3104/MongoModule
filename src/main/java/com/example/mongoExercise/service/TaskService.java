package com.example.mongoExercise.service;

import com.example.mongoExercise.model.SubTask;
import com.example.mongoExercise.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();
    List<Task> findAllOverdueTasks();
    List<Task> findTaskByCategory(String category);
    List<SubTask> findAllSubTaksByCategory(String category);
    Task createTask();
    Task updateTask(String id);
    String deleteTaskById(String id);
    SubTask createSubTask(String id);
    Task updateSubTask(String id);
    String deleteAllSubtasks(String id);
    Task generateTask();
    Task generateTaskToUpdate();
    SubTask generateSubTask();
    SubTask generateSubTaskToUpdate();
    List<Task> findTaskBySubTaskName(String name);
    List<Task>  findTaskByKeywordInDescription(String keyword);

}
