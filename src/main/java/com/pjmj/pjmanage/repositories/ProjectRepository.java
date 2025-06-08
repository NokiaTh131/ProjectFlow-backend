package com.pjmj.pjmanage.repositories;

import com.pjmj.pjmanage.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}