package com.newbiecoder45.task_manager.dto;

import com.newbiecoder45.task_manager.model.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private TaskStatus status;
}
