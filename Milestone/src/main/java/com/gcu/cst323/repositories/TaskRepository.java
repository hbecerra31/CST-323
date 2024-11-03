package com.gcu.cst323.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.models.UserModel;

/**
 * TaskRepository is a Spring Data JPA repository for managing TaskModel
 * entities.
 */
public interface TaskRepository extends JpaRepository<TaskModel, Long> {

	/**
	 * Finds tasks by the associated user.
	 *
	 * @param user the user whose tasks are to be found
	 * @return a list of tasks associated with the given user
	 */
	List<TaskModel> findByUser(UserModel user);

	/**
	 * Finds tasks by the associated user and status.
	 *
	 * @param user   the user whose tasks are to be found
	 * @param status the status of the tasks to be found
	 * @return a list of tasks associated with the given user and status
	 */
	List<TaskModel> findByUserAndStatus(UserModel user, TaskModel.Status status);

	/**
	 * Finds tasks by the associated user and priority.
	 *
	 * @param user     the user whose tasks are to be found
	 * @param priority the priority of the tasks to be found
	 * @return a list of tasks associated with the given user and priority
	 */
	List<TaskModel> findByUserAndPriority(UserModel user, TaskModel.Priority priority);
}
