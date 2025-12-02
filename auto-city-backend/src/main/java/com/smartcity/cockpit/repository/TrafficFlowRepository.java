package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.TrafficFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TrafficFlowRepository extends JpaRepository<TrafficFlow, Long> {
    
    List<TrafficFlow> findByRoadName(String roadName);
    
    List<TrafficFlow> findByRecordTimeBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT t FROM TrafficFlow t WHERE t.latitude BETWEEN :latMin AND :latMax AND t.longitude BETWEEN :lonMin AND :lonMax")
    List<TrafficFlow> findByLocationRange(@Param("latMin") Double latMin, @Param("latMax") Double latMax, 
                                         @Param("lonMin") Double lonMin, @Param("lonMax") Double lonMax);
    
    List<TrafficFlow> findTop10ByOrderByRecordTimeDesc();
}