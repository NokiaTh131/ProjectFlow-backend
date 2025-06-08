package com.pjmj.pjmanage.controllers;

import com.pjmj.pjmanage.dto.TaskMessageDTO;
import com.pjmj.pjmanage.models.Task;
import com.pjmj.pjmanage.models.TaskMessage;
import com.pjmj.pjmanage.repositories.TaskRepository;
import com.pjmj.pjmanage.services.TaskMessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/task_message")
public class TaskMessageController {
    private final TaskMessageService taskMessageService;
    private final TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<TaskMessage> createTaskMessage(@RequestBody TaskMessageDTO request) {
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        TaskMessage taskMessage = new TaskMessage();
        taskMessage.setName(request.getName());
        taskMessage.setLanguage(request.getLanguage());
        taskMessage.setCode(request.getCode());
        taskMessage.setTasks(task);
        return ResponseEntity.ok(taskMessageService.create(taskMessage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<TaskMessage>> updateTaskMessage(@PathVariable Long id, @RequestBody TaskMessageDTO request) {
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        TaskMessage taskMessage = new TaskMessage();
        taskMessage.setName(request.getName());
        taskMessage.setLanguage(request.getLanguage());
        taskMessage.setCode(request.getCode());
        taskMessage.setTasks(task);
        return ResponseEntity.ok(taskMessageService.update(id,taskMessage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskTool(@PathVariable Long id) {
        taskMessageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
