package com.spring.projectmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.projectmanagement.model.Project;
import com.spring.projectmanagement.model.Resource;
import com.spring.projectmanagement.model.ResourceStatus;
import com.spring.projectmanagement.model.Task;
import com.spring.projectmanagement.repository.ProjectRepository;
import com.spring.projectmanagement.repository.ResourceRepository;
import com.spring.projectmanagement.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}
	
	public Project updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id)
            .map(project -> {
                project.setName(updatedProject.getName());
                return projectRepository.save(project);
            })
            .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Task createTask(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));
        task.setProject(project);
        return taskRepository.save(task);
    }

    public Resource allocateResource(Long taskId, Long resourceId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        Resource resource = resourceRepository.findById(resourceId)
            .orElseThrow(() -> new RuntimeException("Resource not found"));

        if (resource.getStatus() == ResourceStatus.UNAVAILABLE) {
            throw new RuntimeException("Resource is unavailable");
        }

        
        long allocatedTasksCount = task.getProject().getTasks().stream()
            .flatMap(t -> t.getResources().stream())
            .filter(r -> r.getId()==(resourceId))
            .count();
        
        if (allocatedTasksCount >= 2) {
            throw new RuntimeException("Resource is over-allocated");
        }

        task.getResources().add(resource);
        taskRepository.save(task);
        return resource;
    }

}
    
