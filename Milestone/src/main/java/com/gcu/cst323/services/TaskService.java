package com.gcu.cst323.services;

import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Method to get all tasks
    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

    // Method to get a task by ID
    public Optional<TaskModel> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Method to save a new task
    public TaskModel saveTask(TaskModel task) {
        return taskRepository.save(task);
    }

    // Method to update an existing task
    public Optional<TaskModel> updateTask(Long id, TaskModel task) {
        if (taskRepository.existsById(id)) {
            task.setId(id);
            return Optional.of(taskRepository.save(task));
        }
        return Optional.empty();
    }

    // Method to delete a task
    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("Task with ID " + id + " does not exist.");
        }
    }
}


