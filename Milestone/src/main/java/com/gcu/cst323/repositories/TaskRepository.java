package com.gcu.cst323.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gcu.cst323.models.TaskModel;
import com.gcu.cst323.models.UserModel;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByUser(UserModel user);

    List<TaskModel> findByUserAndStatus(UserModel user, TaskModel.Status status);

    List<TaskModel> findByUserAndPriority(UserModel user, TaskModel.Priority priority);
}
