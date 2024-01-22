package com.example.mongoExercise.service;

import com.example.mongoExercise.model.SubTask;
import com.example.mongoExercise.model.Task;
import com.example.mongoExercise.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void findAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void findAllOverdueTasks(){
        List<Task> tasks = taskRepository.findBydeadlineDateAfter(new Date());
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void findTaskByCategory(String category){
        List<Task> tasks = taskRepository.findByCategory(category);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void findAllSubTaksByCategory(String category){
        List<Task> tasks = taskRepository.findByCategory(category);
        List<SubTask> subTasks = tasks
                .stream()
                .flatMap
                        (task -> task.getSubTasks().stream())
                .toList();
        for (SubTask subTask : subTasks) {
            System.out.println(subTask);
        }
    }

    public void createTask(){
        Task task = taskRepository.save(generateTask());
        System.out.println(task);
    }

    public void updateTask(String id){
        generateTaskToUpdate().setId(id);
        Task task = taskRepository.save(generateTaskToUpdate());
        System.out.println(task);
    }

    public void deleteTaskById(String id){
        taskRepository.deleteById(id);
        System.out.println("Deleted task with id " + id);
    }

    public void createSubTask(String id){
        Task task = taskRepository.findById(id).get();
        task.getSubTasks().add(generateSubTask());
        taskRepository.save(task);

    }

    public void updateSubTask(String id){
        Task task = taskRepository.findById(id).get();
        List<SubTask> subTasks = task.getSubTasks();
        for (SubTask subTask : subTasks) {
            subTask.setName(generateSubTaskToUpdate().getName());
            subTask.setDescription(generateSubTaskToUpdate().getDescription());
            System.out.println(subTask);
        }
        taskRepository.save(task);
    }

    public void deleteAllSubtasks(String id){
        Task task = taskRepository.findById(id).get();
        task.getSubTasks().removeAll(task.getSubTasks());
        taskRepository.save(task);
    }

    public Task generateTask(){
        List<SubTask> subTasks =new ArrayList<>();
        subTasks.add(generateSubTask());
        return Task.builder()
                .name("Task1")
                .category("1")
                .creationDate(new Date())
                .deadlineDate(new Date())
                .description("This a new task")
                .subTasks(subTasks)
                .build();
    }

    public Task generateTaskToUpdate(){
        List<SubTask> subTasks =new ArrayList<>();
        subTasks.add(generateSubTask());
        return Task.builder()
                .name("Task1")
                .category("1")
                .creationDate(new Date())
                .deadlineDate(new Date())
                .description("This is an updated task")
                .subTasks(subTasks)
                .build();
    }

    public SubTask generateSubTask(){
        return SubTask.builder()
                .name("subTask")
                .description("This is a new subTask")
                .build();
    }

    public SubTask generateSubTaskToUpdate(){
        return SubTask.builder()
                .name("SubTask1")
                .description("This is an updated subTask")
                .build();
    }

    public void findTaskBySubTaskName(String name){
        List<Task> tasks = taskRepository.findBySubTaskName(name);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void findTaskByKeywordInDescription(String keyword){
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(keyword);
        List<Task> tasks = taskRepository.findAllBy(criteria);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
