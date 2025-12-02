package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.entity.PublicService;
import com.smartcity.cockpit.entity.ServiceRequest;
import com.smartcity.cockpit.entity.CitizenFeedback;
import com.smartcity.cockpit.service.PublicServiceManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/city/public-service")
@RequiredArgsConstructor
public class PublicServiceManagementController {
    
    private final PublicServiceManagementService publicServiceManagementService;
    
    // Public Service endpoints
    @PostMapping("/service")
    public ResponseEntity<PublicService> createPublicService(@RequestBody PublicService publicService) {
        PublicService created = publicServiceManagementService.createPublicService(publicService);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/service/type/{serviceType}")
    public ResponseEntity<List<PublicService>> getPublicServicesByType(@PathVariable String serviceType) {
        List<PublicService> services = publicServiceManagementService.getPublicServicesByType(serviceType);
        return ResponseEntity.ok(services);
    }
    
    @GetMapping("/service/status/{status}")
    public ResponseEntity<List<PublicService>> getPublicServicesByStatus(@PathVariable String status) {
        List<PublicService> services = publicServiceManagementService.getPublicServicesByStatus(status);
        return ResponseEntity.ok(services);
    }
    
    @PutMapping("/service/{id}/status")
    public ResponseEntity<PublicService> updatePublicServiceStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        PublicService updated = publicServiceManagementService.updatePublicServiceStatus(id, status);
        return ResponseEntity.ok(updated);
    }
    
    // Service Request endpoints
    @PostMapping("/request")
    public ResponseEntity<ServiceRequest> createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        ServiceRequest created = publicServiceManagementService.createServiceRequest(serviceRequest);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/request/type/{requestType}")
    public ResponseEntity<List<ServiceRequest>> getServiceRequestsByType(@PathVariable String requestType) {
        List<ServiceRequest> requests = publicServiceManagementService.getServiceRequestsByType(requestType);
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/request/status/{status}")
    public ResponseEntity<List<ServiceRequest>> getServiceRequestsByStatus(@PathVariable String status) {
        List<ServiceRequest> requests = publicServiceManagementService.getServiceRequestsByStatus(status);
        return ResponseEntity.ok(requests);
    }
    
    @PutMapping("/request/{id}/status")
    public ResponseEntity<ServiceRequest> updateServiceRequestStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        ServiceRequest updated = publicServiceManagementService.updateServiceRequestStatus(id, status);
        return ResponseEntity.ok(updated);
    }
    
    // Citizen Feedback endpoints
    @PostMapping("/feedback")
    public ResponseEntity<CitizenFeedback> createCitizenFeedback(@RequestBody CitizenFeedback citizenFeedback) {
        CitizenFeedback created = publicServiceManagementService.createCitizenFeedback(citizenFeedback);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/feedback/type/{feedbackType}")
    public ResponseEntity<List<CitizenFeedback>> getCitizenFeedbackByType(@PathVariable String feedbackType) {
        List<CitizenFeedback> feedbacks = publicServiceManagementService.getCitizenFeedbackByType(feedbackType);
        return ResponseEntity.ok(feedbacks);
    }
    
    @GetMapping("/feedback/unreviewed")
    public ResponseEntity<List<CitizenFeedback>> getUnreviewedFeedback() {
        List<CitizenFeedback> feedbacks = publicServiceManagementService.getUnreviewedFeedback();
        return ResponseEntity.ok(feedbacks);
    }
    
    @PutMapping("/feedback/{id}/respond")
    public ResponseEntity<CitizenFeedback> respondToFeedback(
            @PathVariable Long id,
            @RequestParam String responseContent) {
        CitizenFeedback responded = publicServiceManagementService.respondToFeedback(id, responseContent);
        return ResponseEntity.ok(responded);
    }
}