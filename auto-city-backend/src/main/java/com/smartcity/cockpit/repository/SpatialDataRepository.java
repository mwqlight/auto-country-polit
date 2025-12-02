package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.SpatialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpatialDataRepository extends JpaRepository<SpatialData, Long> {
    List<SpatialData> findByDataTypeAndTimestampBetween(String dataType, LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT s FROM SpatialData s WHERE s.latitude BETWEEN ?1 AND ?2 AND s.longitude BETWEEN ?3 AND ?4")
    List<SpatialData> findByLocationRange(Double latMin, Double latMax, Double lonMin, Double lonMax);
}