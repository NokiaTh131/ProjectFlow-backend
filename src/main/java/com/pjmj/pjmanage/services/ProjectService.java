package com.pjmj.pjmanage.services;

import com.pjmj.pjmanage.dto.*;
import com.pjmj.pjmanage.models.Project;
import com.pjmj.pjmanage.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project createProject(Project project) {
        try{
            return projectRepository.save(project);
        }catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("create project error");
        }
    }

    public List<ProjectsDTO> getProjects() {
        try {
            List<Project> projects = projectRepository.findAll();
            List<ProjectStatusDTO> p_status = projectRepository.findProjectsAndStatus();
            return mergeLists(projects,p_status);

        }catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("get Project fail");
        }
    }

    public Optional<ProjectResponseDTO> getProjectById(Long id) {
        try {
            return projectRepository.findById(id).map(p -> {
                List<FeatureResponseDTO> featureDTOs = p.getFeatures().stream()
                        .map(f -> {
                            List<TaskResponseDTO> taskDTOs = f.getTasks().stream()
                                    .map(t -> new TaskResponseDTO(t.getId(), t.getName(), t.getStatus())) // adjust fields as needed
                                    .collect(Collectors.toList());

                            return new FeatureResponseDTO(
                                    f.getId(),
                                    f.getName(),
                                    taskDTOs
                            );
                        })
                        .collect(Collectors.toList());

                return new ProjectResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getGithubUrl(),
                        p.getDeadLine(),
                        featureDTOs
                );
            });
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("get project fail");
        }
    }

    public Optional<Project> updateProject(Long id, Project project) {
        try{
            return projectRepository.findById(id).map(exist -> {
                exist.setName(project.getName());
                exist.setGithubUrl(project.getGithubUrl());
                return  projectRepository.save(exist);
            });
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to update project");
        }
    }

   public void deleteProject(Long id) {
       try{
           projectRepository.deleteById(id);
       } catch (RuntimeException e) {
           throw new RuntimeException("delete project fail");
       }
   }

    private List<ProjectsDTO> mergeLists(List<Project> list1, List<ProjectStatusDTO> list2) {
        Map<Long, List<ProjectStatusDTO>> statusMap = list2.stream()
                .collect(Collectors.groupingBy(ProjectStatusDTO::getId));

        return list1.stream()
                .map(project -> {
                    List<ProjectStatusDTO> statuses = statusMap.getOrDefault(project.getId(), Collections.emptyList());

                    // If all statuses are "done", then "finish", else "not-finish"
                    boolean allDone = !statuses.isEmpty() && statuses.stream()
                            .allMatch(status -> "done".equalsIgnoreCase(status.getStatus()));

                    String finalStatus = allDone ? "finish" : "not-finish";

                    return new ProjectsDTO(
                            project.getId(),
                            project.getName(),
                            project.getDeadLine(),
                            finalStatus
                    );
                })
                .collect(Collectors.toList());
    }


}
