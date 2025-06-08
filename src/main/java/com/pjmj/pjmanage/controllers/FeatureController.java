package com.pjmj.pjmanage.controllers;

import com.pjmj.pjmanage.dto.FeatureDTO;
import com.pjmj.pjmanage.models.Feature;
import com.pjmj.pjmanage.models.Project;
import com.pjmj.pjmanage.repositories.ProjectRepository;
import com.pjmj.pjmanage.services.FeatureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/features")
@RequiredArgsConstructor
public class FeatureController {
    private final FeatureService featureService;
    private final ProjectRepository projectRepository;
    @PostMapping
    public ResponseEntity<Feature> createFeature(@RequestBody FeatureDTO request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        Feature feature = new Feature();
        feature.setName(request.getName());
        feature.setProject(project);
        return ResponseEntity.ok(featureService.createFeature(feature));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<Feature>> getFeatures(@PathVariable Long projectId) {
        return ResponseEntity.ok(featureService.getFeatures(projectId));
    }

    @GetMapping("/feature/{id}")
    public ResponseEntity<Optional<Feature>> getFeature(@PathVariable Long id) {
        return ResponseEntity.ok(featureService.getFeature(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Feature>> updateFeature(@PathVariable Long id, @RequestBody FeatureDTO request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        Feature feature = new Feature();
        feature.setName(request.getName());
        feature.setProject(project);
        return ResponseEntity.ok(featureService.updateFeature(id,feature));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFeature(@PathVariable Long id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
