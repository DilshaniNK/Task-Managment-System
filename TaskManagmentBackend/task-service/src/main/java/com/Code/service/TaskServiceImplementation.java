package com.Code.service;

import com.Code.modal.Task;
import com.Code.modal.TaskStatus;
import com.Code.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task createTask(Task task, String requestedRole) throws Exception {
        if(!requestedRole.equals("ROLE_ADMIN")){
            throw new Exception("only admin can create the task");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()->new Exception("Task not found"));
    }

    @Override
    public List<Task> getAllTasks(TaskStatus status) {
        List<Task> allTasks =  taskRepository.findAll();

        List<Task> filteredTasks = allTasks.stream().filter(
                task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());

        return filteredTasks;
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
        Task existingTask = getTaskById(id);
        if(updatedTask.getTitle() != null){
            existingTask.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getImage() != null){
            existingTask.setImage(updatedTask.getImage());
        }
        if(updatedTask.getDescription() != null){
            existingTask.setDescription(updatedTask.getDescription());

        }
        if(updatedTask.getStatus() != null){
            existingTask.setStatus(updatedTask.getStatus());
        }
        if(updatedTask.getDeadline() != null){
            existingTask.setDeadline(updatedTask.getDeadline());
        }
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTaskById(Long id) throws Exception {
        Task existingTask = getTaskById(id);
        taskRepository.deleteById(id);


    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {
        Task existingTask = getTaskById(taskId);
        existingTask.setAssignUserId(userId);
        existingTask.setStatus(TaskStatus.DONE);
        return taskRepository.save(existingTask);
    }

    @Override
    public List<Task> assignedUsersTask(Long userId, TaskStatus status) {

        List<Task> allTasks =  taskRepository.findByAssignUserId(userId);
        List<Task> filteredTasks = allTasks.stream().filter(
                task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());

        return filteredTasks;
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);

        return taskRepository.save(task);
    }
}
