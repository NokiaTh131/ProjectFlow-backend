package com.pjmj.pjmanage.repositories;

import com.pjmj.pjmanage.models.TaskDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDescriptionRepository extends JpaRepository<TaskDescription,Long> {
}
