// Triggering CI/CD pipeline test

package com.gcu.cst323.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.models.UserModel;
import com.gcu.cst323.repositories.TaskRepository;
import com.gcu.cst323.repositories.UserRepository;

/**
 * This class is the service layer for the Task entity. It is responsible for
 * handling the business logic for the Task entity.
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


	@Autowired
	private UserRepository userRepository;
	
	/**
	 * This method returns all tasks in the database.
	 * 
	 * @return List<TaskModel> - a list of all tasks in the database
	 */
	public List<TaskModel> getAllTasks() {
		return taskRepository.findAll();
	}
	
	/**
	 * This method creates a new task in the database.
	 * 
	 * @param task - the task to be created
	 * @return TaskModel - the task that was created
	 */
	public TaskModel createTask(TaskModel task) {
		Optional<UserModel> user = userRepository.findById(task.getUser().getUserId());
		if (user.isPresent()) {	//if user is found
			task.setUser(user.get());//set the user
			return taskRepository.save(task);
		} else {
			throw new RuntimeException("User not found");
		}
	}	

	/**
	 * This method returns all tasks for a specific user.
	 * 
	 * @param username - the username of the user to get tasks for
	 * @return List<TaskModel> - a list of all tasks for the user
	 */
	public List<TaskModel> getTasksForUser(String username) {
		Optional<UserModel> user = userRepository.findByUsername(username);
		if (user.isPresent()) {	//if user is found
			return taskRepository.findByUser(user.get()); //return all tasks for the user
		} else {
			throw new RuntimeException("User not found");
		}
	}

	/**
	 * This method updates a task in the database.
	 * 
	 * @param task - the task to be updated
	 * @return TaskModel - the task that was updated
	 */
	public TaskModel updateTask(TaskModel task) {
		return taskRepository.save(task);
	}

	/**
	 * This method deletes a task from the database.
	 * 
	 * @param taskId - the id of the task to be deleted
	 */
	public void deleteTask(Long taskId) {
		taskRepository.deleteById(taskId);
	}

	/**
	 * This method returns all tasks for a specific user with a specific status.
	 * 
	 * @param username - the username of the user to get tasks for
	 * @param status   - the status of the tasks to get
	 * @return List<TaskModel> - a list of all tasks for the user with the specified
	 *         status
	 */
	public List<TaskModel> filterTasksByStatus(String username, TaskModel.Status status) {
		Optional<UserModel> user = userRepository.findByUsername(username);
		if (user.isPresent()) { //if user is found
			return taskRepository.findByUserAndStatus(user.get(), status); //return all tasks for the user with the specified status 
		} else {
			throw new RuntimeException("User not found");
		}
	}

	/**
	 * This method returns a task by its id.
	 * 
	 * @param taskId - the id of the task to get
	 * @return TaskModel - the task with the specified id
	 * @return null - if the task with the specified id does not exist
	 */
	public TaskModel findTaskById(Long taskId) {
		return taskRepository.findById(taskId).orElse(null);
	}

    @Autowired
    private UserRepository userRepository;

    // Save a new task
    public TaskModel saveTask(TaskModel task) {
        return taskRepository.save(task);
    }

    // Get task by ID
    public TaskModel getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // Update a task
    public TaskModel updateTask(TaskModel task) {
        return taskRepository.save(task);
    }

    // Delete a task by ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Create a task for a specific user
    public TaskModel createTask(TaskModel task, String username) {
        Optional<UserModel> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            task.setUser(user.get());
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Get all tasks for a specific user
    public List<TaskModel> getTasksForUser(String username) {
        Optional<UserModel> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return taskRepository.findByUser(user.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Get all tasks (for all users)
    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

}
