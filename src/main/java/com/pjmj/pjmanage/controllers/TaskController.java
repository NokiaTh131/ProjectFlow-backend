package com.pjmj.pjmanage.controllers;

import com.pjmj.pjmanage.dto.TaskDTO;
import com.pjmj.pjmanage.models.Feature;
import com.pjmj.pjmanage.models.Task;
import com.pjmj.pjmanage.repositories.FeatureRepository;
import com.pjmj.pjmanage.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private final FeatureRepository featureRepository;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO request) {
        Feature feature = featureRepository.findById(request.getFeatureId()).orElseThrow(() -> new EntityNotFoundException("Feature not found"));
        Task task = new Task();
        task.setName(request.getName());
        task.setStatus(request.getStatus());
        task.setFeature(feature);
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/{featureId}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long featureId) {
        return ResponseEntity.ok(taskService.getTasks(featureId));
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Optional<Task>> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Task>> updateTask(@PathVariable Long id, @RequestBody TaskDTO request) {
        Feature feature = featureRepository.findById(request.getFeatureId()).orElseThrow(() -> new EntityNotFoundException("Feature not found"));
        Task task = new Task();
        task.setName(request.getName());
        task.setStatus(request.getStatus());
        task.setFeature(feature);
        return ResponseEntity.ok(taskService.updateTask(id,task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
