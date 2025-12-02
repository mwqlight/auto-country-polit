package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.EmergencyEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EmergencyEventRepository extends JpaRepository<EmergencyEvent, Long> {
    
    List<EmergencyEvent> findByEventType(String eventType);
    
    List<EmergencyEvent> findBySeverity(String severity);
    
    List<EmergencyEvent> findByIsResolved(Boolean isResolved);
    
    List<EmergencyEvent> findByEventTimeBetween(LocalDateTime start, LocalDateTime end);
    
    List<EmergencyEvent> findByAssignedDepartment(String assignedDepartment);
    
    List<EmergencyEvent> findTop10ByIsResolvedFalseOrderByEventTimeDesc();
}