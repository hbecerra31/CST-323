package com.gcu.cst323;

import com.gcu.cst323.controllers.TaskController;
import com.gcu.cst323.models.Task;
import com.gcu.cst323.services.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Test
    public void testGetAllTasks() {
        // Arrange
        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(new Task(1L, "Task 1", "Description 1", "Pending"));
        mockTasks.add(new Task(2L, "Task 2", "Description 2", "Completed"));

        // Mock the service behavior
        when(taskService.getAllTasks()).thenReturn(mockTasks);

        // Act
        List<Task> tasks = taskController.getAllTasks();

        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getName());
        assertEquals("Completed", tasks.get(1).getStatus());
    }

    @Test
    public void testAddTask() {
        // Arrange
        Task newTask = new Task(3L, "Task 3", "Description 3", "Pending");

        // Mock the service behavior
        when(taskService.saveTask(newTask)).thenReturn(newTask);

        // Act
        Task savedTask = taskController.addTask(newTask);

        // Assert
        assertEquals("Task 3", savedTask.getName());
        assertEquals("Pending", savedTask.getStatus());
    }
}
