package com.newbiecoder45.task_manager.dto;

import com.newbiecoder45.task_manager.model.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {
    @NotBlank(message = "Task title cannot be empty")
    @Size(max = 150, message = "Title cannot exceed 150 characters.")
    private String title;

    @NotNull(message = "Status required")
    private TaskStatus status;
    
}
