package com.pjmj.pjmanage.services;

import com.pjmj.pjmanage.models.Feature;
import com.pjmj.pjmanage.repositories.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;

    public Feature createFeature(Feature feature) {
        try{
            return featureRepository.save(feature);
        }catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to create feature.");
        }
    }

    public List<Feature> getFeatures(Long projectId) {
        try{
            return featureRepository.findByProjectId(projectId);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to get features");
        }
    }

    public Optional<Feature> getFeature(Long id) {
        try{
            return featureRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to get feature");
        }
    }

    public Optional<Feature> updateFeature(Long id, Feature feature) {
        try {
            return featureRepository.findById(id).map(exist -> {
                exist.setName(feature.getName());
                return featureRepository.save(exist);
            });
        }catch (RuntimeException e) {
            throw new RuntimeException("fail to update feature");
        }
    }

    public void deleteFeature(Long id) {
        try {
            featureRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("fail to delete feature");
        }
    }
}
