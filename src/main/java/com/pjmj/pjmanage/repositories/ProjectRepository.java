package com.pjmj.pjmanage.repositories;


import com.pjmj.pjmanage.dto.ProjectStatusDTO;

import com.pjmj.pjmanage.models.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("SELECT NEW com.pjmj.pjmanage.dto.ProjectStatusDTO(p.id, t.status) " +
            "FROM Project p " +
            "JOIN p.features f " +
            "JOIN f.tasks t")
    List<ProjectStatusDTO> findProjectsAndStatus();
}