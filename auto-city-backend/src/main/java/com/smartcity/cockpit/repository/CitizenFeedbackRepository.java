package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.CitizenFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitizenFeedbackRepository extends JpaRepository<CitizenFeedback, Long> {
    
    List<CitizenFeedback> findByFeedbackType(String feedbackType);
    
    List<CitizenFeedback> findByCategory(String category);
    
    List<CitizenFeedback> findByStatus(String status);
    
    List<CitizenFeedback> findByCitizenId(String citizenId);
    
    List<CitizenFeedback> findBySubmittedTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    List<CitizenFeedback> findByRating(Integer rating);
    
    List<CitizenFeedback> findByResponsibleDepartment(String responsibleDepartment);
    
    List<CitizenFeedback> findTop10ByStatusOrderBySubmittedTimeDesc(String status);
}