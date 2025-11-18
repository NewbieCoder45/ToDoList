package com.newbiecoder45.task_manager.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.newbiecoder45.task_manager.dto.TaskRequest;
import com.newbiecoder45.task_manager.dto.TaskResponse;
import com.newbiecoder45.task_manager.exceptions.DuplicateTaskException;
import com.newbiecoder45.task_manager.exceptions.TaskNotFoundException;
import com.newbiecoder45.task_manager.model.Task;
import com.newbiecoder45.task_manager.repos.TaskRepo;
import com.newbiecoder45.task_manager.service.TaskService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskImplementation implements TaskService{

    private final TaskRepo taskRepo;
    
    public TaskImplementation(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
    }

    @Override
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest) {

        if(taskRepo.existsByTitle(taskRequest.getTitle())){
            throw new DuplicateTaskException("Task with title " + taskRequest.getTitle() + " already exists.");
        }

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());    
        task.setStatus(taskRequest.getStatus());
        taskRepo.save(task);
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus());
    }

    @Override
    public List<TaskResponse> getAllTasks(){
        return taskRepo.findAll().stream()
                .map(t -> new TaskResponse(t.getId(), t.getTitle(), t.getStatus()))
                .toList();
    }

    @Override
    public TaskResponse getTasksById(Long id){
        Task task = taskRepo.findById(id)
                            .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus());
    }

    @Override
    public TaskResponse updateTasksById(Long id, TaskRequest taskRequest){
        Task task = taskRepo.findById(id)
                            .orElseThrow(() -> new TaskNotFoundException("No tasks associated with ID:" + id));
        task.setTitle(taskRequest.getTitle());
        task.setStatus(taskRequest.getStatus());
        taskRepo.save(task);
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus());
    }

    @Override
    public void deleteTasksById(Long id){
        Task task = taskRepo.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Task with ID " + id + " not found."));
        taskRepo.delete(task);
    }
}
