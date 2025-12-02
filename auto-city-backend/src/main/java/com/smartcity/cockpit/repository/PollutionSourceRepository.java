package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.PollutionSource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PollutionSourceRepository extends JpaRepository<PollutionSource, Long> {
    
    List<PollutionSource> findBySourceType(String sourceType);
    
    List<PollutionSource> findByEmissionType(String emissionType);
    
    List<PollutionSource> findByStatus(String status);
    
    List<PollutionSource> findByNameContaining(String name);
}