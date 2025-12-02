package com.smartcity.cockpit.service;

import com.smartcity.cockpit.entity.EmergencyEvent;
import com.smartcity.cockpit.entity.SafetyInspection;
import com.smartcity.cockpit.entity.SecurityCamera;
import java.time.LocalDateTime;
import java.util.List;

public interface SafetyManagementService {
    
    // Emergency Event methods
    EmergencyEvent createEmergencyEvent(EmergencyEvent emergencyEvent);
    List<EmergencyEvent> getEmergencyEventsByType(String eventType);
    List<EmergencyEvent> getEmergencyEventsBySeverity(String severity);
    List<EmergencyEvent> getUnresolvedEmergencyEvents();
    List<EmergencyEvent> getEmergencyEventsByTimeRange(LocalDateTime start, LocalDateTime end);
    List<EmergencyEvent> getEmergencyEventsByDepartment(String department);
    EmergencyEvent resolveEmergencyEvent(Long id);
    
    // Safety Inspection methods
    SafetyInspection createSafetyInspection(SafetyInspection safetyInspection);
    List<SafetyInspection> getSafetyInspectionsByFacilityType(String facilityType);
    List<SafetyInspection> getSafetyInspectionsByFacilityName(String facilityName);
    List<SafetyInspection> getSafetyInspectionsByDateRange(LocalDateTime start, LocalDateTime end);
    List<SafetyInspection> getSafetyInspectionsByResult(String result);
    List<SafetyInspection> getOverdueSafetyInspections();
    
    // Security Camera methods
    SecurityCamera createSecurityCamera(SecurityCamera securityCamera);
    SecurityCamera getSecurityCameraByCameraId(String cameraId);
    List<SecurityCamera> getSecurityCamerasByLocation(String location);
    List<SecurityCamera> getSecurityCamerasByStatus(String status);
    SecurityCamera updateSecurityCameraStatus(String cameraId, String status);
}