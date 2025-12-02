package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.EnergyAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EnergyAlertRepository extends JpaRepository<EnergyAlert, Long> {
    
    List<EnergyAlert> findByAlertType(String alertType);
    
    List<EnergyAlert> findBySeverity(String severity);
    
    List<EnergyAlert> findByIsResolved(Boolean isResolved);
    
    List<EnergyAlert> findByAlertTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    List<EnergyAlert> findByAssignedDepartment(String assignedDepartment);
    
    List<EnergyAlert> findTop10ByIsResolvedFalseOrderByAlertTimeDesc();
}