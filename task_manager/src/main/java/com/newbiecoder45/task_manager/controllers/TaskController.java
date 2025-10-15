package com.newbiecoder45.task_manager.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.newbiecoder45.task_manager.dto.TaskRequest;
import com.newbiecoder45.task_manager.model.Task;
import com.newbiecoder45.task_manager.repos.TaskRepo;
import com.newbiecoder45.task_manager.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RequestMapping("/tasks")
public class TaskController {

    
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    // @GetMapping("/tasks")
    // public List<Task> getAllTasks() {
    //     return repo.findAll();
    // }

    // @GetMapping("/tasks/{id}")
    // public Task getTaskById(@PathVariable Long id) {
    //     return repo.findById(id).get();
    // }

    // @PostMapping("/post_tasks")
    // @ResponseStatus(code = HttpStatus.CREATED)
    // public void addTasks(@RequestBody Task task) {
    //    repo.save(task);
    // }
    
    // @PutMapping("task/edit/{id}")
    // public Task editTaskById(@PathVariable Long id, @RequestBody Task newTask) {
    //     Task oldTask = repo.findById(id).get();
    //     oldTask.setTitle(newTask.getTitle());
    //     oldTask.setStatus(newTask.getStatus());
    //     return newTask;
    // }
    
    // @DeleteMapping("task/delete/{id}")
    // public String deleteTaskById(@PathVariable Long id) throws Exception{
    //     repo.delete(repo.findById(id).get());

    //     if(repo.findById(id).isEmpty()){
    //         return "Task successfully deleted";
    //     }else{
    //         throw new Exception("An error occured");
    //     }
    // }


    @PostMapping("addTasks")
    public ResponseEntity createTaskResponse(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(201).body(taskService.createTask(taskRequest));
    }
    
    @GetMapping("getAllTasks")
    public ResponseEntity getAllTasksResponse() {
        return ResponseEntity.status(200).body(taskService.getAllTasks());
    }

    @GetMapping("getTaskById/{id}")
    public ResponseEntity getTaskByIdResponse(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTasksById(id));
    }

    @PutMapping("updateTasks/{id}")
    public ResponseEntity updateTasksResp( @PathVariable Long id, 
        @Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.updateTasksById(id, taskRequest));
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTasksResp(@Valid @PathVariable Long id){
        taskService.deleteTasksById(id);
        return ResponseEntity.noContent().build();
    }
    
}
