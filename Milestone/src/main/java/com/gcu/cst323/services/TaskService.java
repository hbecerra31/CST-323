package com.gcu.cst323.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.models.UserModel;
import com.gcu.cst323.repositories.TaskRepository;
import com.gcu.cst323.repositories.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	public TaskModel createTask(TaskModel task, String username) {
		Optional<UserModel> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			task.setUser(user.get());
			return taskRepository.save(task);
		} else {
			throw new RuntimeException("User not found");
		}
	}

	public List<TaskModel> getTasksForUser(String username) {
		Optional<UserModel> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return taskRepository.findByUser(user.get());
		} else {
			throw new RuntimeException("User not found");
		}
	}

	public TaskModel updateTask(TaskModel task) {
		return taskRepository.save(task);
	}

	public void deleteTask(Long taskId) {
		taskRepository.deleteById(taskId);
	}

	public List<TaskModel> filterTasksByStatus(String username, TaskModel.Status status) {
		Optional<UserModel> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return taskRepository.findByUserAndStatus(user.get(), status);
		} else {
			throw new RuntimeException("User not found");
		}
	}
}
