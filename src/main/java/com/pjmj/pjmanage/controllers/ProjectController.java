package com.pjmj.pjmanage.controllers;

import com.pjmj.pjmanage.dto.ProjectsDTO;
import com.pjmj.pjmanage.models.Project;
import com.pjmj.pjmanage.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @GetMapping
    public ResponseEntity<List<ProjectsDTO>> getProjects() {
        return ResponseEntity.ok(projectService.getProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Project>> updateProject(@PathVariable Long id, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(id,project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
