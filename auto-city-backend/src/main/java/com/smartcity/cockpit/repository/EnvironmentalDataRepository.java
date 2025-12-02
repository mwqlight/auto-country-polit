package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.EnvironmentalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface EnvironmentalDataRepository extends JpaRepository<EnvironmentalData, Long> {
    
    List<EnvironmentalData> findByDataType(String dataType);
    
    List<EnvironmentalData> findByDataTypeAndRecordTimeBetween(String dataType, LocalDateTime start, LocalDateTime end);
    
    List<EnvironmentalData> findByQualityLevel(String qualityLevel);
    
    @Query("SELECT e FROM EnvironmentalData e WHERE e.latitude BETWEEN :latMin AND :latMax AND e.longitude BETWEEN :lonMin AND :lonMax")
    List<EnvironmentalData> findByLocationRange(@Param("latMin") Double latMin, @Param("latMax") Double latMax, 
                                             @Param("lonMin") Double lonMin, @Param("lonMax") Double lonMax);
    
    List<EnvironmentalData> findTop20ByOrderByRecordTimeDesc();
}