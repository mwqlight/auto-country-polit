package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.PolicyRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRecommendationRepository extends JpaRepository<PolicyRecommendation, Long> {
    
    List<PolicyRecommendation> findByRecommendationType(String recommendationType);
    
    List<PolicyRecommendation> findByPriorityLevel(String priorityLevel);
    
    List<PolicyRecommendation> findByRelatedDepartment(String relatedDepartment);
    
    List<PolicyRecommendation> findByStatus(String status);
    
    List<PolicyRecommendation> findByRecommendationTypeAndPriorityLevel(String recommendationType, String priorityLevel);
    
    List<PolicyRecommendation> findByRecommendationTypeAndStatus(String recommendationType, String status);
}