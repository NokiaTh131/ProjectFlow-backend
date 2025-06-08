package com.pjmj.pjmanage.services;

import com.pjmj.pjmanage.models.Task;
import com.pjmj.pjmanage.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to create task");
        }
    }

    public List<Task> getTasks(Long featureId) {
        try {
            return taskRepository.findByFeatureId(featureId);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to get tasks");
        }
    }

    public Optional<Task> getTask(Long id) {
        try {
            return taskRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to get task");
        }
    }

    public Optional<Task> updateTask(Long id, Task task) {
        try {
            return taskRepository.findById(id).map(exist -> {
                exist.setStatus(task.getStatus());
                exist.setName(task.getName());
                return taskRepository.save(exist);
            });
        }catch (RuntimeException e) {
            throw new RuntimeException("fail to update feature");
        }
    }

    public void deleteTask(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to delete task");
        }
    }
}
