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
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            System.out.println(task);
        }
        return tasks;
    }

    public List<Task> findAllOverdueTasks(){
        List<Task> tasks = taskRepository.findBydeadlineDateAfter(new Date());
        for (Task task : tasks) {
            System.out.println(task);
        }
        return tasks;
    }

    public List<Task> findTaskByCategory(String category){
        List<Task> tasks = taskRepository.findByCategory(category);
        for (Task task : tasks) {
            System.out.println(task);
        }
        return tasks;
    }

    public List<SubTask> findAllSubTaksByCategory(String category){
        List<Task> tasks = taskRepository.findByCategory(category);
        List<SubTask> subTasks = tasks
                .stream()
                .flatMap
                        (task -> task.getSubTasks().stream())
                .toList();
        for (SubTask subTask : subTasks) {
            System.out.println(subTask);
        }
        return subTasks;
    }

    public Task createTask(){
        Task task = taskRepository.save(generateTask());
        System.out.println(task);
        return task;
    }

    public Task updateTask(String id){
        generateTaskToUpdate().setId(id);
        Task task = taskRepository.save(generateTaskToUpdate());
        System.out.println(task);
        return task;
    }

    public String deleteTaskById(String id){
        taskRepository.deleteById(id);
        System.out.println("Deleted task with id " + id);
        return id;
    }

    public SubTask createSubTask(String id){
        Task task = taskRepository.findById(id).get();
        task.getSubTasks().add(generateSubTask());
        taskRepository.save(task);
        return generateSubTask();
    }

    public Task updateSubTask(String id){
        Task task = taskRepository.findById(id).get();
        List<SubTask> subTasks = task.getSubTasks();
        for (SubTask subTask : subTasks) {
            subTask.setName(generateSubTaskToUpdate().getName());
            subTask.setDescription(generateSubTaskToUpdate().getDescription());
            System.out.println(subTask);
        }
        taskRepository.save(task);
        return  task;
    }

    public String deleteAllSubtasks(String id){
        Task task = taskRepository.findById(id).get();
        task.getSubTasks().removeAll(task.getSubTasks());
        taskRepository.save(task);
        return id;
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

    public List<Task> findTaskBySubTaskName(String name){
        List<Task> tasks = taskRepository.findBySubTaskName(name);
        for (Task task : tasks) {
            System.out.println(task);
        }
        return tasks;
    }

    public List<Task> findTaskByKeywordInDescription(String keyword){
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(keyword);
        List<Task> tasks = taskRepository.findAllBy(criteria);
        for (Task task : tasks) {
            System.out.println(task);
        }
        return tasks;
    }
}
