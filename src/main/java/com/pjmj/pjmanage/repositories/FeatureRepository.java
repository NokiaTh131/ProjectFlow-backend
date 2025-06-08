package com.pjmj.pjmanage.repositories;

import com.pjmj.pjmanage.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature,Long> {
    List<Feature> findByProjectId(Long projectId);
}
