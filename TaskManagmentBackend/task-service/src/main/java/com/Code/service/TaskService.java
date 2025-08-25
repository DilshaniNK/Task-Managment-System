package com.Code.service;

import com.Code.modal.Task;
import com.Code.modal.TaskStatus;

import java.util.List;

public interface TaskService {
    Task createTask(Task task, String requestedRole) throws Exception;
    Task getTaskById(Long id) throws Exception;
    List<Task> getAllTasks(TaskStatus status);
    Task updateTask(Long id, Task updatedTask, Long userId) throws Exception;

    void deleteTaskById(Long id) throws Exception;
    Task assignedToUser(Long userId, Long taskId) throws Exception;
    List<Task> assignedUsersTask(Long userId,TaskStatus status);
    Task completeTask(Long taskId) throws Exception;



}
