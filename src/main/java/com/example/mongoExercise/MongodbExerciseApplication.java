package com.example.mongoExercise;

import com.example.mongoExercise.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MongodbExerciseApplication implements CommandLineRunner {
    @Autowired
	private TaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(MongodbExerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			displayMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					taskService.findAllTasks();
					break;
				case 2:
					taskService.findAllOverdueTasks();
					break;
				case 3:
					System.out.println("Enter category: ");
					String category = scanner.nextLine();
					taskService.findTaskByCategory(category);
					break;
				case 4:
					System.out.println("Enter category: ");
					String categoryForSubTask = scanner.nextLine();
					taskService.findAllSubTaksByCategory(categoryForSubTask);
					break;
				case 5:
					taskService.createTask();
					break;
				case 6:
					System.out.println("Enter Task id to update: ");
					String id = scanner.nextLine();
					taskService.updateTask(id);
					break;
				case 7:
					System.out.println("Enter Task id to delete: ");
					String idToDelete = scanner.nextLine();
					taskService.deleteTaskById(idToDelete);
					break;
				case 8:
					System.out.println("Enter Task id: ");
					String taskId = scanner.nextLine();
					taskService.createSubTask(taskId);
					break;
				case 9:
					System.out.println("Enter Task id: ");
					String taskIdToUpdate = scanner.nextLine();
					taskService.updateSubTask(taskIdToUpdate);
					break;
				case 10:
					System.out.println("Enter Task id: ");
					String taskid = scanner.nextLine();
					taskService.deleteAllSubtasks(taskid);
					break;
				case 11:
					System.out.println("Enter keyword: ");
					String keyword = scanner.nextLine();
					taskService.findTaskByKeywordInDescription(keyword);
					break;
				case 12:
					System.out.println("Enter subTask name: ");
					String subTaskName = scanner.nextLine();
					taskService.findTaskBySubTaskName(subTaskName);
					break;
				case 0:
					System.out.println("Exiting the application.");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void displayMenu() {
		System.out.println("Task Manager Menu:");
		System.out.println("1. Display all tasks");
		System.out.println("2. Display overdue tasks");
		System.out.println("3. Display tasks by category");
		System.out.println("4. Display subtasks by category");
		System.out.println("5. Insert task");
		System.out.println("6. Update task");
		System.out.println("7. Delete task");
		System.out.println("8. Insert Subtask for a Task");
		System.out.println("9. Update subtask");
		System.out.println("10. Delete all subtasks");
		System.out.println("11. Search by keyword in task description");
		System.out.println("12. Search by subtask name");
		System.out.println("0. Exit");
		System.out.print("Enter your choice: ");
	}
}
