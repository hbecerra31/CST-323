package com.gcu.cst323.controllers;

import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // Allow all origins for development
public class TaskController {

    private static final Logger logger = Logger.getLogger(TaskController.class.getName());

    @Autowired
    private TaskService taskService;

    // Get all tasks
    @GetMapping
    public List<TaskModel> getAllTasks() {
        logger.info("Fetching all tasks");
        return taskService.getAllTasks();
    }

    // Get a specific task by ID
    @GetMapping("/{id}")
    public Optional<TaskModel> getTaskById(@PathVariable Long id) {
        logger.info("Fetching task with ID: " + id);
        return taskService.getTaskById(id);
    }

    // Create a new task
    @PostMapping
    public TaskModel createTask(@RequestBody TaskModel task) {
        logger.info("Creating new task: " + task);
        return taskService.saveTask(task);
    }

    // Update an existing task
    @PutMapping("/{id}")
    public Optional<TaskModel> updateTask(@PathVariable Long id, @RequestBody TaskModel task) {
        logger.info("Updating task with ID: " + id);
        return taskService.updateTask(id, task);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        logger.info("Deleting task with ID: " + id);
        taskService.deleteTask(id);
    }

    // Test endpoint to add a sample task
    @PostMapping("/add-sample")
    public TaskModel addSampleTask() {
        TaskModel task = new TaskModel();
        task.setName("Sample Task");
        task.setDescription("This is a sample task for testing purposes");
        task.setDueDate(LocalDate.now());
        task.setPriority(TaskModel.Priority.MEDIUM);
        task.setStatus(TaskModel.Status.PENDING);

        logger.info("Adding a sample task to the database");
        return taskService.saveTask(task);
    }
}



