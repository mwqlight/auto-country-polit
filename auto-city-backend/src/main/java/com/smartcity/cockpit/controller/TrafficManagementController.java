package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.entity.TrafficFlow;
import com.smartcity.cockpit.entity.TrafficIncident;
import com.smartcity.cockpit.entity.SmartSignal;
import com.smartcity.cockpit.service.TrafficManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/city/traffic")
@RequiredArgsConstructor
public class TrafficManagementController {
    
    private final TrafficManagementService trafficManagementService;
    
    // Traffic Flow endpoints
    @PostMapping("/flow")
    public ResponseEntity<TrafficFlow> createTrafficFlow(@RequestBody TrafficFlow trafficFlow) {
        TrafficFlow created = trafficManagementService.createTrafficFlow(trafficFlow);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/flow/road/{roadName}")
    public ResponseEntity<List<TrafficFlow>> getTrafficFlowsByRoadName(@PathVariable String roadName) {
        List<TrafficFlow> flows = trafficManagementService.getTrafficFlowsByRoadName(roadName);
        return ResponseEntity.ok(flows);
    }
    
    @GetMapping("/flow/time")
    public ResponseEntity<List<TrafficFlow>> getTrafficFlowsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<TrafficFlow> flows = trafficManagementService.getTrafficFlowsByTimeRange(start, end);
        return ResponseEntity.ok(flows);
    }
    
    @GetMapping("/flow/location")
    public ResponseEntity<List<TrafficFlow>> getTrafficFlowsByLocationRange(
            @RequestParam Double latMin,
            @RequestParam Double latMax,
            @RequestParam Double lonMin,
            @RequestParam Double lonMax) {
        List<TrafficFlow> flows = trafficManagementService.getTrafficFlowsByLocationRange(latMin, latMax, lonMin, lonMax);
        return ResponseEntity.ok(flows);
    }
    
    @GetMapping("/flow/latest")
    public ResponseEntity<List<TrafficFlow>> getLatestTrafficFlows() {
        List<TrafficFlow> flows = trafficManagementService.getLatestTrafficFlows();
        return ResponseEntity.ok(flows);
    }
    
    // Traffic Incident endpoints
    @PostMapping("/incident")
    public ResponseEntity<TrafficIncident> createTrafficIncident(@RequestBody TrafficIncident trafficIncident) {
        TrafficIncident created = trafficManagementService.createTrafficIncident(trafficIncident);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/incident/type/{incidentType}")
    public ResponseEntity<List<TrafficIncident>> getTrafficIncidentsByType(@PathVariable String incidentType) {
        List<TrafficIncident> incidents = trafficManagementService.getTrafficIncidentsByType(incidentType);
        return ResponseEntity.ok(incidents);
    }
    
    @GetMapping("/incident/severity/{severity}")
    public ResponseEntity<List<TrafficIncident>> getTrafficIncidentsBySeverity(@PathVariable String severity) {
        List<TrafficIncident> incidents = trafficManagementService.getTrafficIncidentsBySeverity(severity);
        return ResponseEntity.ok(incidents);
    }
    
    @GetMapping("/incident/unresolved")
    public ResponseEntity<List<TrafficIncident>> getUnresolvedTrafficIncidents() {
        List<TrafficIncident> incidents = trafficManagementService.getUnresolvedTrafficIncidents();
        return ResponseEntity.ok(incidents);
    }
    
    @PutMapping("/incident/{id}/resolve")
    public ResponseEntity<TrafficIncident> resolveTrafficIncident(@PathVariable Long id) {
        TrafficIncident resolved = trafficManagementService.resolveTrafficIncident(id);
        return ResponseEntity.ok(resolved);
    }
    
    // Smart Signal endpoints
    @PostMapping("/signal")
    public ResponseEntity<SmartSignal> createSmartSignal(@RequestBody SmartSignal smartSignal) {
        SmartSignal created = trafficManagementService.createSmartSignal(smartSignal);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/signal/{signalId}")
    public ResponseEntity<SmartSignal> getSmartSignalBySignalId(@PathVariable String signalId) {
        SmartSignal signal = trafficManagementService.getSmartSignalBySignalId(signalId);
        if (signal != null) {
            return ResponseEntity.ok(signal);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/signal/location")
    public ResponseEntity<List<SmartSignal>> getSmartSignalsByLocation(@RequestParam String location) {
        List<SmartSignal> signals = trafficManagementService.getSmartSignalsByLocation(location);
        return ResponseEntity.ok(signals);
    }
    
    @PutMapping("/signal/{signalId}/timing")
    public ResponseEntity<SmartSignal> updateSmartSignalTiming(
            @PathVariable String signalId,
            @RequestParam Integer greenTime,
            @RequestParam Integer redTime,
            @RequestParam Integer yellowTime) {
        SmartSignal updated = trafficManagementService.updateSmartSignalTiming(signalId, greenTime, redTime, yellowTime);
        return ResponseEntity.ok(updated);
    }
}