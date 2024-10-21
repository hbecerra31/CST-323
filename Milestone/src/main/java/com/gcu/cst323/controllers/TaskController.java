package com.gcu.cst323.controllers;

import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks") // Updated to match the front-end endpoint
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get all tasks
    @GetMapping
    public List<TaskModel> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get a specific task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable Long id) {
        Optional<TaskModel> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new task
    @PostMapping
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel task) {
        TaskModel savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(savedTask);
    }

    // Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable Long id, @RequestBody TaskModel task) {
        Optional<TaskModel> updatedTask = taskService.updateTask(id, task);
        return updatedTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.getTaskById(id).isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

