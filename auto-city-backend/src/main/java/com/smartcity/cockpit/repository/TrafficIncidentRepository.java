package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.TrafficIncident;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TrafficIncidentRepository extends JpaRepository<TrafficIncident, Long> {
    
    List<TrafficIncident> findByIncidentType(String incidentType);
    
    List<TrafficIncident> findBySeverity(String severity);
    
    List<TrafficIncident> findByIsResolved(Boolean isResolved);
    
    List<TrafficIncident> findByIncidentTimeBetween(LocalDateTime start, LocalDateTime end);
    
    List<TrafficIncident> findTop10ByIsResolvedFalseOrderByIncidentTimeDesc();
}