package com.newbiecoder45.task_manager.controllers;

import java.util.List;

import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.newbiecoder45.task_manager.dto.TaskRequest;
import com.newbiecoder45.task_manager.dto.TaskResponse;
import com.newbiecoder45.task_manager.service.TaskService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

    
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    /*
     * Why this matters:

        The @Valid annotation tells Spring to automatically trigger validation before 
        calling your service.

        If validation fails → an exception (MethodArgumentNotValidException) is 
        thrown automatically.
     */

    @PostMapping("addTask")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskRequest));
    }
    
    @GetMapping("getAllTasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.status(200).body(taskService.getAllTasks());
    }

    @GetMapping("getTaskById/{id}")
    public ResponseEntity<TaskResponse> getTaskByIdResponse(@Positive @PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.getTasksById(id));
    }

    @PutMapping("updateTask/{id}")
    public ResponseEntity<TaskResponse> updateTasksResp(@Positive @PathVariable Long id, 
        @Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok().body(taskService.updateTasksById(id, taskRequest));
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTasksResp(@Positive @PathVariable Long id){
        return ResponseEntity.ok().body(taskService.deleteTasksById(id));

    }


    // Pagination
    @GetMapping("/getAllTasks/{offset}/{pageSize}")
    public ResponseEntity<Page<TaskResponse>> getAllTasksByPagination(
        @PathVariable Integer offset, 
        @PathVariable Integer pageSize) {
        return ResponseEntity.ok().body(taskService.getAllTasksPage(offset, pageSize));
    }
    
    // Sorting
    @GetMapping("/getAllTasks/{field}")
    public ResponseEntity<List<TaskResponse>> getAllSortedTasks(
        @PathVariable String field
    ) {
        return ResponseEntity.ok().body(taskService.getAllSortedTasks(Sort.Direction.ASC, field));
    }
    
    // Pagination and Sorting
    @GetMapping("/getAllTasks/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<TaskResponse>> getAllTasksBySortingAndPagination(
        @PathVariable Integer offset,
        @PathVariable Integer pageSize,
        @PathVariable String field
    ) {
        return ResponseEntity.ok().body(taskService.getAllTasksPageAndSorted(offset, pageSize, field, Sort.Direction.ASC));
    }
    
}
