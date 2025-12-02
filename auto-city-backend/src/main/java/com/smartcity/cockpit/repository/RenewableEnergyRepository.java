package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.RenewableEnergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RenewableEnergyRepository extends JpaRepository<RenewableEnergy, Long> {
    
    List<RenewableEnergy> findByEnergySource(String energySource);
    
    List<RenewableEnergy> findByStatus(String status);
    
    List<RenewableEnergy> findByLocationContaining(String location);
    
    List<RenewableEnergy> findTop10ByStatusOrderByEfficiencyDesc(String status);
}