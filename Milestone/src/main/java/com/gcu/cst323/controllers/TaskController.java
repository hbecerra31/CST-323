

package com.gcu.cst323.controllers;

import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.gcu.cst323.models.UserModel;
import com.gcu.cst323.services.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * TaskController is a Spring MVC controller that handles HTTP requests related
 * to tasks.
 * 
 */


@Controller
@RequestMapping("/tasks")
public class TaskController {


	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	/**
	 * Handles HTTP GET requests to show all tasks.
	 *
	 * @param model the Model object used to pass attributes to the view
	 * @return the name of the view to be rendered ("task/tasks")
	 */
	@GetMapping
	public String listTasks(Model model) {
		model.addAttribute("title", "Tasks"); // Add a title attribute to the model
		List<TaskModel> tasks = taskService.getAllTasks(); // Get all tasks from the service
		model.addAttribute("tasks", tasks); // Add the tasks
		return "task/tasks";
	}

	/**
	 * Handles HTTP GET requests to show the form for creating a new task.
	 *
	 * @param model the Model object used to pass attributes to the view
	 * @return the name of the view to be rendered ("task/task-form")
	 */
	@GetMapping("/new")
	public String showTaskForm(Model model) {
		model.addAttribute("title", "New Task"); // Add a title attribute to the model
		model.addAttribute("task", new TaskModel()); // Add an empty TaskModel to bind the

		// Add the list of user options to the model
		List<UserModel> userOptions = Arrays.asList(userService.getAllUsers().toArray(new UserModel[0]));
		model.addAttribute("userOptions", userOptions);

		// Add the list of priority options to the model
		List<TaskModel.Priority> priorityOptions = Arrays.asList(TaskModel.Priority.values());
		model.addAttribute("priorityOptions", priorityOptions);

		// Add the list of status options to the model
		List<TaskModel.Status> statusOptions = Arrays.asList(TaskModel.Status.values());
		model.addAttribute("statusOptions", statusOptions);

		return "task/task-form";
	}

	/**
	 * Handles HTTP POST requests to create a new task.
	 *
	 * @param task  the TaskModel object containing the task data
	 * @param model the Model object used to pass attributes to the view
	 * @return the name of the view to be rendered or a redirect URL
	 */
	@PostMapping("/new")
	public String createTask(@ModelAttribute TaskModel task, Model model) {
		// Validate the task data
		try {
			taskService.createTask(task);
			return "redirect:/tasks";
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "task/task-form";
		}
	}

	/**
	 * Handles HTTP GET requests to show the form for editing a task.
	 *
	 * @param taskId the ID of the task to be edited
	 * @param model  the Model object used to pass attributes to the view
	 * @return the name of the view to be rendered ("task/task-form")
	 */
	@GetMapping("/edit/{taskId}")
	public String showEditForm(@PathVariable Long taskId, Model model) {
		model.addAttribute("title", "Edit Task"); // Add a title attribute to the model
		TaskModel task = taskService.findTaskById(taskId); // Get the task by ID
		model.addAttribute("task", task); // Add the task to the model

		// Add the list of user options to the model
		List<UserModel> userOptions = Arrays.asList(userService.getAllUsers().toArray(new UserModel[0]));
		model.addAttribute("userOptions", userOptions);

		// Add the list of priority options to the model
		List<TaskModel.Priority> priorityOptions = Arrays.asList(TaskModel.Priority.values());
		model.addAttribute("priorityOptions", priorityOptions);

		// Add the list of status options to the model
		List<TaskModel.Status> statusOptions = Arrays.asList(TaskModel.Status.values());
		model.addAttribute("statusOptions", statusOptions);
		return "task/task-form";
	}

	/**
	 * Handles HTTP POST requests to update a task.
	 *
	 * @param task   the TaskModel object containing the updated task data
	 * @param taskId the ID of the task to be updated
	 * @return a redirect URL to the list of tasks
	 */
	@PostMapping("/edit/{taskId}")
	public String updateTask(@ModelAttribute TaskModel task, @PathVariable Long taskId) {
		taskService.updateTask(task);
		return "redirect:/tasks";
	}

	/**
	 * Handles HTTP POST requests to delete a task.
	 *
	 * @param taskId the ID of the task to be deleted
	 * @return a redirect URL to the list of tasks
	 */
	@PostMapping("/delete/{taskId}")
	public String deleteTask(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return "redirect:/tasks";
	}


	/// Ealona's code merge ///
	

    // Get all tasks
    @GetMapping
    public String getAllTasks(Model model) {
        List<TaskModel> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task/taskList";
    }

    // Display form to add a task
    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new TaskModel());
        return "task/addTask";
    }

    // Handle adding a new task
    @PostMapping("/add")
    public String addTask(@ModelAttribute("task") TaskModel taskModel) {
        taskService.saveTask(taskModel);
        return "redirect:/tasks";
    }

    // Display edit task form
    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable("id") Long id, Model model) {
        TaskModel task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task/editTask";
    }

    // Handle updating a task
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable("id") Long id, @ModelAttribute("task") TaskModel taskModel) {
        taskService.updateTask(taskModel);
        return "redirect:/tasks";
    }

}
