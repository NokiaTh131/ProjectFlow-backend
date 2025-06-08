package com.pjmj.pjmanage.services;

import com.pjmj.pjmanage.dto.ProjectsDTO;
import com.pjmj.pjmanage.models.Project;
import com.pjmj.pjmanage.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
            return projectRepository.findAll().stream().map(p -> new ProjectsDTO(p.getId(), p.getName(), p.getDeadLine())).collect(Collectors.toList());
        }catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("get Project fail");
        }
    }

    public Optional<Project> getProjectById(Long id) {
        try {
            return projectRepository.findById(id);
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
}
