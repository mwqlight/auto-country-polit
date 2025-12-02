package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.entity.EnergyConsumption;
import com.smartcity.cockpit.entity.RenewableEnergy;
import com.smartcity.cockpit.entity.EnergyAlert;
import com.smartcity.cockpit.repository.EnergyConsumptionRepository;
import com.smartcity.cockpit.repository.RenewableEnergyRepository;
import com.smartcity.cockpit.repository.EnergyAlertRepository;
import com.smartcity.cockpit.service.EnergyManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnergyManagementServiceImpl implements EnergyManagementService {
    
    private final EnergyConsumptionRepository energyConsumptionRepository;
    private final RenewableEnergyRepository renewableEnergyRepository;
    private final EnergyAlertRepository energyAlertRepository;
    
    // Energy Consumption methods
    @Override
    public EnergyConsumption createEnergyConsumption(EnergyConsumption energyConsumption) {
        return energyConsumptionRepository.save(energyConsumption);
    }
    
    @Override
    public List<EnergyConsumption> getEnergyConsumptionByType(String energyType) {
        return energyConsumptionRepository.findByEnergyType(energyType);
    }
    
    @Override
    public List<EnergyConsumption> getEnergyConsumptionByBuilding(String buildingId) {
        return energyConsumptionRepository.findByBuildingId(buildingId);
    }
    
    @Override
    public List<EnergyConsumption> getEnergyConsumptionByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return energyConsumptionRepository.findByConsumptionTimeBetween(startTime, endTime);
    }
    
    @Override
    public List<EnergyConsumption> getLatestEnergyConsumption() {
        return energyConsumptionRepository.findTop20ByOrderByConsumptionTimeDesc();
    }
    
    @Override
    public List<EnergyConsumption> getEnergyConsumptionByBuildingAndTimeRange(String buildingId, LocalDateTime startTime, LocalDateTime endTime) {
        return energyConsumptionRepository.findByBuildingIdAndTimeRange(buildingId, startTime, endTime);
    }
    
    // Renewable Energy methods
    @Override
    public RenewableEnergy createRenewableEnergy(RenewableEnergy renewableEnergy) {
        return renewableEnergyRepository.save(renewableEnergy);
    }
    
    @Override
    public List<RenewableEnergy> getRenewableEnergyBySource(String energySource) {
        return renewableEnergyRepository.findByEnergySource(energySource);
    }
    
    @Override
    public List<RenewableEnergy> getRenewableEnergyByStatus(String status) {
        return renewableEnergyRepository.findByStatus(status);
    }
    
    @Override
    public List<RenewableEnergy> getTopEfficientRenewableEnergy(String status) {
        return renewableEnergyRepository.findTop10ByStatusOrderByEfficiencyDesc(status);
    }
    
    @Override
    public RenewableEnergy updateRenewableEnergyStatus(Long id, String status) {
        RenewableEnergy renewableEnergy = renewableEnergyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Renewable energy source not found"));
        renewableEnergy.setStatus(status);
        return renewableEnergyRepository.save(renewableEnergy);
    }
    
    // Energy Alert methods
    @Override
    public EnergyAlert createEnergyAlert(EnergyAlert energyAlert) {
        return energyAlertRepository.save(energyAlert);
    }
    
    @Override
    public List<EnergyAlert> getEnergyAlertsByType(String alertType) {
        return energyAlertRepository.findByAlertType(alertType);
    }
    
    @Override
    public List<EnergyAlert> getEnergyAlertsBySeverity(String severity) {
        return energyAlertRepository.findBySeverity(severity);
    }
    
    @Override
    public List<EnergyAlert> getUnresolvedEnergyAlerts() {
        return energyAlertRepository.findByIsResolved(false);
    }
    
    @Override
    public EnergyAlert resolveEnergyAlert(Long id) {
        EnergyAlert energyAlert = energyAlertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Energy alert not found"));
        energyAlert.setIsResolved(true);
        energyAlert.setResolvedTime(LocalDateTime.now());
        return energyAlertRepository.save(energyAlert);
    }
}