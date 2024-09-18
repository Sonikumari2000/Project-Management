package com.spring.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.projectmanagement.model.Project;
import com.spring.projectmanagement.model.Resource;
import com.spring.projectmanagement.model.Task;
import com.spring.projectmanagement.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public Project createProject(@RequestBody Project project) {
		return projectService.createProject(project);
	}
	
	@PutMapping("/{id}")
	public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
		return projectService.updateProject(id, project);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
	}
	
	@PostMapping("/{projectId}/tasks")
	public Task createTask(@PathVariable Long projectId, @RequestBody Task task) {
		return projectService.createTask(projectId, task);
	}
	
	@PostMapping("/tasks/{taskId}/resources/{resourceId}")
	public Resource allocateResource(@PathVariable Long taskId,@PathVariable Long resourceId) {
		return projectService.allocateResource(taskId,resourceId);
	}

}
