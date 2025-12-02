package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.entity.EnvironmentalData;
import com.smartcity.cockpit.entity.PollutionSource;
import com.smartcity.cockpit.entity.EnvironmentalAlert;
import com.smartcity.cockpit.service.EnvironmentalMonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/city/environment")
@RequiredArgsConstructor
public class EnvironmentalMonitoringController {
    
    private final EnvironmentalMonitoringService environmentalMonitoringService;
    
    // Environmental Data endpoints
    @PostMapping("/data")
    public ResponseEntity<EnvironmentalData> createEnvironmentalData(@RequestBody EnvironmentalData environmentalData) {
        EnvironmentalData created = environmentalMonitoringService.createEnvironmentalData(environmentalData);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/data/type/{dataType}")
    public ResponseEntity<List<EnvironmentalData>> getEnvironmentalDataByType(@PathVariable String dataType) {
        List<EnvironmentalData> data = environmentalMonitoringService.getEnvironmentalDataByType(dataType);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/data/latest")
    public ResponseEntity<List<EnvironmentalData>> getLatestEnvironmentalData() {
        List<EnvironmentalData> data = environmentalMonitoringService.getLatestEnvironmentalData();
        return ResponseEntity.ok(data);
    }
    
    // Pollution Source endpoints
    @PostMapping("/source")
    public ResponseEntity<PollutionSource> createPollutionSource(@RequestBody PollutionSource pollutionSource) {
        PollutionSource created = environmentalMonitoringService.createPollutionSource(pollutionSource);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/source/type/{sourceType}")
    public ResponseEntity<List<PollutionSource>> getPollutionSourcesByType(@PathVariable String sourceType) {
        List<PollutionSource> sources = environmentalMonitoringService.getPollutionSourcesByType(sourceType);
        return ResponseEntity.ok(sources);
    }
    
    @PutMapping("/source/{id}/status")
    public ResponseEntity<PollutionSource> updatePollutionSourceStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        PollutionSource updated = environmentalMonitoringService.updatePollutionSourceStatus(id, status);
        return ResponseEntity.ok(updated);
    }
    
    // Environmental Alert endpoints
    @PostMapping("/alert")
    public ResponseEntity<EnvironmentalAlert> createEnvironmentalAlert(@RequestBody EnvironmentalAlert environmentalAlert) {
        EnvironmentalAlert created = environmentalMonitoringService.createEnvironmentalAlert(environmentalAlert);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/alert/unresolved")
    public ResponseEntity<List<EnvironmentalAlert>> getUnresolvedEnvironmentalAlerts() {
        List<EnvironmentalAlert> alerts = environmentalMonitoringService.getUnresolvedEnvironmentalAlerts();
        return ResponseEntity.ok(alerts);
    }
    
    @PutMapping("/alert/{id}/resolve")
    public ResponseEntity<EnvironmentalAlert> resolveEnvironmentalAlert(@PathVariable Long id) {
        EnvironmentalAlert resolved = environmentalMonitoringService.resolveEnvironmentalAlert(id);
        return ResponseEntity.ok(resolved);
    }
}