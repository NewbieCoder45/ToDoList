package com.newbiecoder45.task_manager.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newbiecoder45.task_manager.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long>{

    boolean existsByTitle(String title);

}
