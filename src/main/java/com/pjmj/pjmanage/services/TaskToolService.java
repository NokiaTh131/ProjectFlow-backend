package com.pjmj.pjmanage.services;


import com.pjmj.pjmanage.models.TaskTool;
import com.pjmj.pjmanage.repositories.TaskToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskToolService {
    private final TaskToolRepository taskToolRepository;

    public TaskTool create(TaskTool taskTool) {
        try{
            return taskToolRepository.save(taskTool);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to create task tool");
        }
    }

    public Optional<TaskTool> update(Long id, TaskTool taskTool) {
        try{
            return taskToolRepository.findById(id).map(exist -> {
                exist.setName(taskTool.getName());
                exist.setNote(taskTool.getNote());
                exist.setIconUrl(taskTool.getIconUrl());
                exist.setLink(taskTool.getLink());
                return taskToolRepository.save(exist);
            });
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to update task tool");
        }
    }

    public void delete(Long id) {
        try{
            taskToolRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to delete task tool");
        }
    }
}
