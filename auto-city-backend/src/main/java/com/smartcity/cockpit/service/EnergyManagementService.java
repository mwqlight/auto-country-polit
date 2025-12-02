package com.smartcity.cockpit.service;

import com.smartcity.cockpit.entity.EnergyConsumption;
import com.smartcity.cockpit.entity.RenewableEnergy;
import com.smartcity.cockpit.entity.EnergyAlert;

import java.time.LocalDateTime;
import java.util.List;

public interface EnergyManagementService {
    
    // Energy Consumption methods
    EnergyConsumption createEnergyConsumption(EnergyConsumption energyConsumption);
    
    List<EnergyConsumption> getEnergyConsumptionByType(String energyType);
    
    List<EnergyConsumption> getEnergyConsumptionByBuilding(String buildingId);
    
    List<EnergyConsumption> getEnergyConsumptionByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    
    List<EnergyConsumption> getLatestEnergyConsumption();
    
    List<EnergyConsumption> getEnergyConsumptionByBuildingAndTimeRange(String buildingId, LocalDateTime startTime, LocalDateTime endTime);
    
    // Renewable Energy methods
    RenewableEnergy createRenewableEnergy(RenewableEnergy renewableEnergy);
    
    List<RenewableEnergy> getRenewableEnergyBySource(String energySource);
    
    List<RenewableEnergy> getRenewableEnergyByStatus(String status);
    
    List<RenewableEnergy> getTopEfficientRenewableEnergy(String status);
    
    RenewableEnergy updateRenewableEnergyStatus(Long id, String status);
    
    // Energy Alert methods
    EnergyAlert createEnergyAlert(EnergyAlert energyAlert);
    
    List<EnergyAlert> getEnergyAlertsByType(String alertType);
    
    List<EnergyAlert> getEnergyAlertsBySeverity(String severity);
    
    List<EnergyAlert> getUnresolvedEnergyAlerts();
    
    EnergyAlert resolveEnergyAlert(Long id);
}