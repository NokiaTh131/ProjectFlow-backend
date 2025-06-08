package com.pjmj.pjmanage.repositories;

import com.pjmj.pjmanage.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByFeatureId(Long featureId);
}
