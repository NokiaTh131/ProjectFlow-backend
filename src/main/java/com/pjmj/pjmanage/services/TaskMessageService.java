package com.pjmj.pjmanage.services;

import com.pjmj.pjmanage.models.TaskMessage;
import com.pjmj.pjmanage.models.TaskTool;
import com.pjmj.pjmanage.repositories.TaskMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskMessageService {
    private final TaskMessageRepository taskMessageRepository;

    public TaskMessage create(TaskMessage taskMessage) {
        try{
            return taskMessageRepository.save(taskMessage);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to create task message");
        }
    }

    public Optional<TaskMessage> update(Long id, TaskMessage taskMessage) {
        try{
            return taskMessageRepository.findById(id).map(exist -> {
                exist.setName(taskMessage.getName());
                exist.setLanguage(taskMessage.getLanguage());
                exist.setCode(taskMessage.getCode());
                return taskMessageRepository.save(exist);
            });
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to update task message");
        }
    }

    public void delete(Long id) {
        try{
            taskMessageRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to delete task message");
        }
    }
}
