package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.AIModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AIModelRepository extends JpaRepository<AIModel, Long> {
    
    List<AIModel> findByModelName(String modelName);
    
    List<AIModel> findByModelType(String modelType);
    
    List<AIModel> findByDomainArea(String domainArea);
    
    List<AIModel> findByStatus(String status);
    
    Optional<AIModel> findByModelNameAndModelVersion(String modelName, String modelVersion);
    
    List<AIModel> findByDomainAreaAndStatus(String domainArea, String status);
}