package com.pjmj.pjmanage.repositories;

import com.pjmj.pjmanage.models.TaskMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMessageRepository extends JpaRepository<TaskMessage,Long> {
}
