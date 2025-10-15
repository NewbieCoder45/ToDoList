package com.newbiecoder45.task_manager.dto;

public class TaskResponse {
    private Long id;
    private String title;
    private String status;
    public TaskResponse(long id2, String title2, String status2) {
        this.id = id2;
        this.title = title2;
        this.status = status2;
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getStatus() {
        return status;
    }
}
