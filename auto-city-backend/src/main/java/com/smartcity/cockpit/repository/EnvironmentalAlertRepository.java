package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.EnvironmentalAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EnvironmentalAlertRepository extends JpaRepository<EnvironmentalAlert, Long> {
    
    List<EnvironmentalAlert> findByAlertType(String alertType);
    
    List<EnvironmentalAlert> findBySeverity(String severity);
    
    List<EnvironmentalAlert> findByIsResolved(Boolean isResolved);
    
    List<EnvironmentalAlert> findByAlertTimeBetween(LocalDateTime start, LocalDateTime end);
    
    List<EnvironmentalAlert> findByAssignedDepartment(String assignedDepartment);
    
    List<EnvironmentalAlert> findTop10ByIsResolvedFalseOrderByAlertTimeDesc();
}