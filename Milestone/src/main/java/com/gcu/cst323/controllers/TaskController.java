package com.gcu.cst323.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.services.TaskService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Show all tasks for the logged-in user
    @GetMapping
    public String listTasks(Model model, Principal principal) {
        String username = principal.getName();
        List<TaskModel> tasks = taskService.getTasksForUser(username);
        model.addAttribute("tasks", tasks);
        return "task/tasks";
    }

    // Show the form for creating a new task
    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new TaskModel());
        return "task/task-form";
    }

    // Handle the task creation
    @PostMapping("/new")
    public String createTask(@ModelAttribute TaskModel task, Principal principal, Model model) {
        try {
            String username = principal.getName();
            taskService.createTask(task, username);
            return "redirect:/tasks";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "task/task-form";
        }
    }

    // Show the form for editing a task
//    @GetMapping("/edit/{taskId}")
//    public String showEditForm(@PathVariable Long taskId, Model model) {
//    	TaskModel task = taskService.findTaskById(taskId);
//        model.addAttribute("task", task);
//        return "task/task-form";
//    }

    // Handle the task update
    @PostMapping("/edit/{taskId}")
    public String updateTask(@ModelAttribute TaskModel task, @PathVariable Long taskId) {
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    // Handle task deletion
    @PostMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks";
    }
}
