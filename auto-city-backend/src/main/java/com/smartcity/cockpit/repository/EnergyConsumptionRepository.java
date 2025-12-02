package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, Long> {
    
    List<EnergyConsumption> findByEnergyType(String energyType);
    
    List<EnergyConsumption> findByBuildingId(String buildingId);
    
    List<EnergyConsumption> findByConsumptionTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    List<EnergyConsumption> findByArea(String area);
    
    List<EnergyConsumption> findTop20ByOrderByConsumptionTimeDesc();
    
    @Query("SELECT ec FROM EnergyConsumption ec WHERE ec.buildingId = :buildingId AND ec.consumptionTime BETWEEN :startTime AND :endTime")
    List<EnergyConsumption> findByBuildingIdAndTimeRange(
        @Param("buildingId") String buildingId,
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime
    );
    
    @Query("SELECT ec FROM EnergyConsumption ec WHERE ec.area = :area AND ec.energyType = :energyType AND ec.consumptionTime BETWEEN :startTime AND :endTime")
    List<EnergyConsumption> findByAreaAndEnergyTypeAndTimeRange(
        @Param("area") String area,
        @Param("energyType") String energyType,
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime
    );
}