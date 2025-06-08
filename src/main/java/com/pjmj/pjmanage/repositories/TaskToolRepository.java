package com.pjmj.pjmanage.repositories;

import com.pjmj.pjmanage.models.TaskTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskToolRepository extends JpaRepository<TaskTool,Long> {
}
