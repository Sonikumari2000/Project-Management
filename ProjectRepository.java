package com.spring.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.projectmanagement.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
