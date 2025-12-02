package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.entity.EmergencyEvent;
import com.smartcity.cockpit.entity.SafetyInspection;
import com.smartcity.cockpit.entity.SecurityCamera;
import com.smartcity.cockpit.service.SafetyManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/city/safety")
@RequiredArgsConstructor
public class SafetyManagementController {
    
    private final SafetyManagementService safetyManagementService;
    
    // Emergency Event endpoints
    @PostMapping("/emergency")
    public ResponseEntity<EmergencyEvent> createEmergencyEvent(@RequestBody EmergencyEvent emergencyEvent) {
        EmergencyEvent created = safetyManagementService.createEmergencyEvent(emergencyEvent);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/emergency/type/{eventType}")
    public ResponseEntity<List<EmergencyEvent>> getEmergencyEventsByType(@PathVariable String eventType) {
        List<EmergencyEvent> events = safetyManagementService.getEmergencyEventsByType(eventType);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/emergency/severity/{severity}")
    public ResponseEntity<List<EmergencyEvent>> getEmergencyEventsBySeverity(@PathVariable String severity) {
        List<EmergencyEvent> events = safetyManagementService.getEmergencyEventsBySeverity(severity);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/emergency/unresolved")
    public ResponseEntity<List<EmergencyEvent>> getUnresolvedEmergencyEvents() {
        List<EmergencyEvent> events = safetyManagementService.getUnresolvedEmergencyEvents();
        return ResponseEntity.ok(events);
    }
    
    @PutMapping("/emergency/{id}/resolve")
    public ResponseEntity<EmergencyEvent> resolveEmergencyEvent(@PathVariable Long id) {
        EmergencyEvent resolved = safetyManagementService.resolveEmergencyEvent(id);
        return ResponseEntity.ok(resolved);
    }
    
    // Safety Inspection endpoints
    @PostMapping("/inspection")
    public ResponseEntity<SafetyInspection> createSafetyInspection(@RequestBody SafetyInspection safetyInspection) {
        SafetyInspection created = safetyManagementService.createSafetyInspection(safetyInspection);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/inspection/facility-type/{facilityType}")
    public ResponseEntity<List<SafetyInspection>> getSafetyInspectionsByFacilityType(@PathVariable String facilityType) {
        List<SafetyInspection> inspections = safetyManagementService.getSafetyInspectionsByFacilityType(facilityType);
        return ResponseEntity.ok(inspections);
    }
    
    @GetMapping("/inspection/overdue")
    public ResponseEntity<List<SafetyInspection>> getOverdueSafetyInspections() {
        List<SafetyInspection> inspections = safetyManagementService.getOverdueSafetyInspections();
        return ResponseEntity.ok(inspections);
    }
    
    // Security Camera endpoints
    @PostMapping("/camera")
    public ResponseEntity<SecurityCamera> createSecurityCamera(@RequestBody SecurityCamera securityCamera) {
        SecurityCamera created = safetyManagementService.createSecurityCamera(securityCamera);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/camera/{cameraId}")
    public ResponseEntity<SecurityCamera> getSecurityCameraByCameraId(@PathVariable String cameraId) {
        SecurityCamera camera = safetyManagementService.getSecurityCameraByCameraId(cameraId);
        if (camera != null) {
            return ResponseEntity.ok(camera);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/camera/{cameraId}/status")
    public ResponseEntity<SecurityCamera> updateSecurityCameraStatus(
            @PathVariable String cameraId,
            @RequestParam String status) {
        SecurityCamera updated = safetyManagementService.updateSecurityCameraStatus(cameraId, status);
        return ResponseEntity.ok(updated);
    }
}