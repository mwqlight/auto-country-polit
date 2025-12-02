package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.SmartSignal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SmartSignalRepository extends JpaRepository<SmartSignal, Long> {
    
    Optional<SmartSignal> findBySignalId(String signalId);
    
    List<SmartSignal> findByLocationContaining(String location);
    
    List<SmartSignal> findByCurrentPhase(String currentPhase);
}