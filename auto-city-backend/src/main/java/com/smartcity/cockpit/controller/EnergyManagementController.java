package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.entity.EnergyConsumption;
import com.smartcity.cockpit.entity.RenewableEnergy;
import com.smartcity.cockpit.entity.EnergyAlert;
import com.smartcity.cockpit.service.EnergyManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/city/energy")
@RequiredArgsConstructor
public class EnergyManagementController {
    
    private final EnergyManagementService energyManagementService;
    
    // Energy Consumption endpoints
    @PostMapping("/consumption")
    public ResponseEntity<EnergyConsumption> createEnergyConsumption(@RequestBody EnergyConsumption energyConsumption) {
        EnergyConsumption created = energyManagementService.createEnergyConsumption(energyConsumption);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/consumption/type/{energyType}")
    public ResponseEntity<List<EnergyConsumption>> getEnergyConsumptionByType(@PathVariable String energyType) {
        List<EnergyConsumption> data = energyManagementService.getEnergyConsumptionByType(energyType);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/consumption/building/{buildingId}")
    public ResponseEntity<List<EnergyConsumption>> getEnergyConsumptionByBuilding(@PathVariable String buildingId) {
        List<EnergyConsumption> data = energyManagementService.getEnergyConsumptionByBuilding(buildingId);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/consumption/latest")
    public ResponseEntity<List<EnergyConsumption>> getLatestEnergyConsumption() {
        List<EnergyConsumption> data = energyManagementService.getLatestEnergyConsumption();
        return ResponseEntity.ok(data);
    }
    
    // Renewable Energy endpoints
    @PostMapping("/renewable")
    public ResponseEntity<RenewableEnergy> createRenewableEnergy(@RequestBody RenewableEnergy renewableEnergy) {
        RenewableEnergy created = energyManagementService.createRenewableEnergy(renewableEnergy);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/renewable/source/{energySource}")
    public ResponseEntity<List<RenewableEnergy>> getRenewableEnergyBySource(@PathVariable String energySource) {
        List<RenewableEnergy> sources = energyManagementService.getRenewableEnergyBySource(energySource);
        return ResponseEntity.ok(sources);
    }
    
    @PutMapping("/renewable/{id}/status")
    public ResponseEntity<RenewableEnergy> updateRenewableEnergyStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        RenewableEnergy updated = energyManagementService.updateRenewableEnergyStatus(id, status);
        return ResponseEntity.ok(updated);
    }
    
    // Energy Alert endpoints
    @PostMapping("/alert")
    public ResponseEntity<EnergyAlert> createEnergyAlert(@RequestBody EnergyAlert energyAlert) {
        EnergyAlert created = energyManagementService.createEnergyAlert(energyAlert);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/alert/unresolved")
    public ResponseEntity<List<EnergyAlert>> getUnresolvedEnergyAlerts() {
        List<EnergyAlert> alerts = energyManagementService.getUnresolvedEnergyAlerts();
        return ResponseEntity.ok(alerts);
    }
    
    @PutMapping("/alert/{id}/resolve")
    public ResponseEntity<EnergyAlert> resolveEnergyAlert(@PathVariable Long id) {
        EnergyAlert resolved = energyManagementService.resolveEnergyAlert(id);
        return ResponseEntity.ok(resolved);
    }
}