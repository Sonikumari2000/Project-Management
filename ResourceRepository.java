package com.spring.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.projectmanagement.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>{

}
