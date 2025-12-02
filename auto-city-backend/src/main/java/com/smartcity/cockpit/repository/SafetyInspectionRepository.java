package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.SafetyInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface SafetyInspectionRepository extends JpaRepository<SafetyInspection, Long> {
    
    List<SafetyInspection> findByFacilityType(String facilityType);
    
    List<SafetyInspection> findByFacilityName(String facilityName);
    
    List<SafetyInspection> findByInspectionDateBetween(LocalDateTime start, LocalDateTime end);
    
    List<SafetyInspection> findByResult(String result);
    
    List<SafetyInspection> findByNextInspectionDateBefore(LocalDateTime date);
}