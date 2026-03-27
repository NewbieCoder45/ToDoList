package com.newbiecoder45.task_manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.newbiecoder45.task_manager.dto.TaskRequest;
import com.newbiecoder45.task_manager.dto.TaskResponse;

@Service
public interface TaskService {

    // CRUD Implementation methods:

    // 1. Create
    TaskResponse createTask(TaskRequest taskRequest);
    // 2. Read
    List<TaskResponse> getAllTasks();
    
    TaskResponse getTasksById(Long id);
    // 3. Update
    TaskResponse updateTasksById(Long id, TaskRequest taskRequest);
    // 4. Delete
    String deleteTasksById(Long id);

    // Lets create Pagination of GetAllTasks
    Page<TaskResponse> getAllTasksPage(Integer offset, Integer pageSize);

    // Lets create Sorting for getAllTasks
    List<TaskResponse> getAllSortedTasks(Sort.Direction dir, String field);

    //Pagination and Sorting for getAllTasks
    Page<TaskResponse> getAllTasksPageAndSorted(Integer offset, Integer pageSize, String field, Sort.Direction direction);
}
