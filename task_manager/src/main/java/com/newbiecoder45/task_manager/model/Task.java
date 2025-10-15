package com.newbiecoder45.task_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name ="task_title")
    @NotBlank(message = "Title cannot be empty")
    private String title;
    
    @Column(nullable = false)
    private String status = "PENDING";

    public long getId() {
        return id;
    }

    public Task(String title, String status) {
        this.title = title;
        this.status = status;
    }

    public Task(){}

    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", status=" + status + "]";
    }

    public void setId(long id) {
        this.id = id;
    }

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
