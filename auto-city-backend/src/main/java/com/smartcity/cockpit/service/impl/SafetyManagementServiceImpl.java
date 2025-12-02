package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.entity.EmergencyEvent;
import com.smartcity.cockpit.entity.SafetyInspection;
import com.smartcity.cockpit.entity.SecurityCamera;
import com.smartcity.cockpit.repository.EmergencyEventRepository;
import com.smartcity.cockpit.repository.SafetyInspectionRepository;
import com.smartcity.cockpit.repository.SecurityCameraRepository;
import com.smartcity.cockpit.service.SafetyManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SafetyManagementServiceImpl implements SafetyManagementService {
    
    private final EmergencyEventRepository emergencyEventRepository;
    private final SafetyInspectionRepository safetyInspectionRepository;
    private final SecurityCameraRepository securityCameraRepository;
    
    // Emergency Event methods
    @Override
    public EmergencyEvent createEmergencyEvent(EmergencyEvent emergencyEvent) {
        emergencyEvent.setEventTime(LocalDateTime.now());
        emergencyEvent.setIsResolved(false);
        return emergencyEventRepository.save(emergencyEvent);
    }
    
    @Override
    public List<EmergencyEvent> getEmergencyEventsByType(String eventType) {
        return emergencyEventRepository.findByEventType(eventType);
    }
    
    @Override
    public List<EmergencyEvent> getEmergencyEventsBySeverity(String severity) {
        return emergencyEventRepository.findBySeverity(severity);
    }
    
    @Override
    public List<EmergencyEvent> getUnresolvedEmergencyEvents() {
        return emergencyEventRepository.findTop10ByIsResolvedFalseOrderByEventTimeDesc();
    }
    
    @Override
    public List<EmergencyEvent> getEmergencyEventsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return emergencyEventRepository.findByEventTimeBetween(start, end);
    }
    
    @Override
    public List<EmergencyEvent> getEmergencyEventsByDepartment(String department) {
        return emergencyEventRepository.findByAssignedDepartment(department);
    }
    
    @Override
    public EmergencyEvent resolveEmergencyEvent(Long id) {
        Optional<EmergencyEvent> eventOptional = emergencyEventRepository.findById(id);
        if (eventOptional.isPresent()) {
            EmergencyEvent event = eventOptional.get();
            event.setIsResolved(true);
            event.setResolvedTime(LocalDateTime.now());
            return emergencyEventRepository.save(event);
        }
        throw new RuntimeException("Emergency event not found with id: " + id);
    }
    
    // Safety Inspection methods
    @Override
    public SafetyInspection createSafetyInspection(SafetyInspection safetyInspection) {
        safetyInspection.setInspectionDate(LocalDateTime.now());
        return safetyInspectionRepository.save(safetyInspection);
    }
    
    @Override
    public List<SafetyInspection> getSafetyInspectionsByFacilityType(String facilityType) {
        return safetyInspectionRepository.findByFacilityType(facilityType);
    }
    
    @Override
    public List<SafetyInspection> getSafetyInspectionsByFacilityName(String facilityName) {
        return safetyInspectionRepository.findByFacilityName(facilityName);
    }
    
    @Override
    public List<SafetyInspection> getSafetyInspectionsByDateRange(LocalDateTime start, LocalDateTime end) {
        return safetyInspectionRepository.findByInspectionDateBetween(start, end);
    }
    
    @Override
    public List<SafetyInspection> getSafetyInspectionsByResult(String result) {
        return safetyInspectionRepository.findByResult(result);
    }
    
    @Override
    public List<SafetyInspection> getOverdueSafetyInspections() {
        return safetyInspectionRepository.findByNextInspectionDateBefore(LocalDateTime.now());
    }
    
    // Security Camera methods
    @Override
    public SecurityCamera createSecurityCamera(SecurityCamera securityCamera) {
        return securityCameraRepository.save(securityCamera);
    }
    
    @Override
    public SecurityCamera getSecurityCameraByCameraId(String cameraId) {
        Optional<SecurityCamera> cameraOptional = securityCameraRepository.findByCameraId(cameraId);
        return cameraOptional.orElse(null);
    }
    
    @Override
    public List<SecurityCamera> getSecurityCamerasByLocation(String location) {
        return securityCameraRepository.findByLocationContaining(location);
    }
    
    @Override
    public List<SecurityCamera> getSecurityCamerasByStatus(String status) {
        return securityCameraRepository.findByStatus(status);
    }
    
    @Override
    public SecurityCamera updateSecurityCameraStatus(String cameraId, String status) {
        Optional<SecurityCamera> cameraOptional = securityCameraRepository.findByCameraId(cameraId);
        if (cameraOptional.isPresent()) {
            SecurityCamera camera = cameraOptional.get();
            camera.setStatus(status);
            return securityCameraRepository.save(camera);
        }
        throw new RuntimeException("Security camera not found with cameraId: " + cameraId);
    }
}