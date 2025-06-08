package com.pjmj.pjmanage.services;

import com.pjmj.pjmanage.models.TaskDescription;
import com.pjmj.pjmanage.repositories.TaskDescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskDescriptionService {
    private final TaskDescriptionRepository taskDescriptionRepository;

    public TaskDescription createDescription(TaskDescription description) {
            try {
                return taskDescriptionRepository.save(description);
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new RuntimeException("fail to create description");
            }
    }

    public Optional<TaskDescription> updateDescription(Long id, TaskDescription description) {
        try {
            return taskDescriptionRepository.findById(id).map(exist -> {
                        exist.setDescription(description.getDescription());
                        return taskDescriptionRepository.save(exist);
                    });
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to update description");
        }
    }

    public void deleteDescription(Long id) {
        try{
            taskDescriptionRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to delete description");
        }
    }
}
