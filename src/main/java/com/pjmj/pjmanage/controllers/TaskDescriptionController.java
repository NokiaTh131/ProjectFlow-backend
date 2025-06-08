package com.pjmj.pjmanage.controllers;


import com.pjmj.pjmanage.dto.TaskDescriptionDTO;
import com.pjmj.pjmanage.models.Task;
import com.pjmj.pjmanage.models.TaskDescription;
import com.pjmj.pjmanage.repositories.TaskRepository;
import com.pjmj.pjmanage.services.TaskDescriptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/task_description")
public class TaskDescriptionController {

    private final TaskDescriptionService taskDescriptionService;
    private final TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<TaskDescription> createTaskDescription(@RequestBody TaskDescriptionDTO request) {
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        TaskDescription taskDescription = new TaskDescription();
        taskDescription.setTasks(task);
        taskDescription.setDescription(request.getDescription());
        return ResponseEntity.ok(taskDescriptionService.createDescription(taskDescription));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<TaskDescription>> updateTaskDescription(@PathVariable Long id, @RequestBody TaskDescriptionDTO request) {
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        TaskDescription taskDescription = new TaskDescription();
        taskDescription.setTasks(task);
        taskDescription.setDescription(request.getDescription());
        return ResponseEntity.ok(taskDescriptionService.updateDescription(id,taskDescription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskDescription(@PathVariable Long id) {
        taskDescriptionService.deleteDescription(id);
        return ResponseEntity.noContent().build();
    }
}
