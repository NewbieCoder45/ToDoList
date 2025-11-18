package com.newbiecoder45.task_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {
    @NotBlank(message = "Task title cannot be empty")
    @Size(max = 250, message = "Title cannot exceed 250 characters.")
    private String title;

    @NotBlank(message = "Status required")
    private String status;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
