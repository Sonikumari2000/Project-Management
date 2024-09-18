package com.spring.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.projectmanagement.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
