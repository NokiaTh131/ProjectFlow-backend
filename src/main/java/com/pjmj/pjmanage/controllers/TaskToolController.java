package com.pjmj.pjmanage.controllers;

import com.pjmj.pjmanage.dto.TaskToolDTO;
import com.pjmj.pjmanage.models.Task;
import com.pjmj.pjmanage.models.TaskTool;
import com.pjmj.pjmanage.repositories.TaskRepository;
import com.pjmj.pjmanage.services.TaskToolService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/task_tool")
public class TaskToolController {
    private final TaskToolService taskToolService;
    private final TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<TaskTool> createTaskTool(@RequestBody TaskToolDTO request) {
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        TaskTool taskTool = new TaskTool();
        taskTool.setName(request.getName());
        taskTool.setNote(request.getNote());
        taskTool.setIconUrl(request.getIconUrl());
        taskTool.setLink(request.getLink());
        taskTool.setTasks(task);
        return ResponseEntity.ok(taskToolService.create(taskTool));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<TaskTool>> updateTaskTool(@PathVariable Long id, @RequestBody TaskToolDTO request) {
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        TaskTool taskTool = new TaskTool();
        taskTool.setName(request.getName());
        taskTool.setNote(request.getNote());
        taskTool.setIconUrl(request.getIconUrl());
        taskTool.setLink(request.getLink());
        taskTool.setTasks(task);
        return ResponseEntity.ok(taskToolService.update(id,taskTool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskTool(@PathVariable Long id) {
        taskToolService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
