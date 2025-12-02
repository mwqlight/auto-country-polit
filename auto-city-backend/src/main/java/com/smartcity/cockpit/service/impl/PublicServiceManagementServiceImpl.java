package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.entity.PublicService;
import com.smartcity.cockpit.entity.ServiceRequest;
import com.smartcity.cockpit.entity.CitizenFeedback;
import com.smartcity.cockpit.repository.PublicServiceRepository;
import com.smartcity.cockpit.repository.ServiceRequestRepository;
import com.smartcity.cockpit.repository.CitizenFeedbackRepository;
import com.smartcity.cockpit.service.PublicServiceManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicServiceManagementServiceImpl implements PublicServiceManagementService {
    
    private final PublicServiceRepository publicServiceRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final CitizenFeedbackRepository citizenFeedbackRepository;
    
    // Public Service methods
    @Override
    public PublicService createPublicService(PublicService publicService) {
        return publicServiceRepository.save(publicService);
    }
    
    @Override
    public List<PublicService> getPublicServicesByType(String serviceType) {
        return publicServiceRepository.findByServiceType(serviceType);
    }
    
    @Override
    public List<PublicService> getPublicServicesByStatus(String status) {
        return publicServiceRepository.findByStatus(status);
    }
    
    @Override
    public List<PublicService> getPublicServicesByArea(String serviceArea) {
        return publicServiceRepository.findByServiceArea(serviceArea);
    }
    
    @Override
    public List<PublicService> searchPublicServicesByName(String name) {
        return publicServiceRepository.findByNameContaining(name);
    }
    
    @Override
    public List<PublicService> getPublicServicesByLocationRange(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return publicServiceRepository.findByLocationRange(minLat, maxLat, minLng, maxLng);
    }
    
    @Override
    public PublicService updatePublicServiceStatus(Long id, String status) {
        PublicService publicService = publicServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Public service not found"));
        publicService.setStatus(status);
        return publicServiceRepository.save(publicService);
    }
    
    // Service Request methods
    @Override
    public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(serviceRequest);
    }
    
    @Override
    public List<ServiceRequest> getServiceRequestsByType(String requestType) {
        return serviceRequestRepository.findByRequestType(requestType);
    }
    
    @Override
    public List<ServiceRequest> getServiceRequestsByStatus(String status) {
        return serviceRequestRepository.findByStatus(status);
    }
    
    @Override
    public List<ServiceRequest> getServiceRequestsByCitizen(String citizenId) {
        return serviceRequestRepository.findByCitizenId(citizenId);
    }
    
    @Override
    public List<ServiceRequest> getServiceRequestsByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return serviceRequestRepository.findBySubmittedTimeBetween(startTime, endTime);
    }
    
    @Override
    public List<ServiceRequest> getRecentServiceRequestsByStatus(String status) {
        return serviceRequestRepository.findTop20ByStatusOrderBySubmittedTimeDesc(status);
    }
    
    @Override
    public ServiceRequest updateServiceRequestStatus(Long id, String status) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service request not found"));
        serviceRequest.setStatus(status);
        if ("resolved".equals(status) || "closed".equals(status)) {
            serviceRequest.setActualResolutionTime(LocalDateTime.now());
        }
        return serviceRequestRepository.save(serviceRequest);
    }
    
    // Citizen Feedback methods
    @Override
    public CitizenFeedback createCitizenFeedback(CitizenFeedback citizenFeedback) {
        return citizenFeedbackRepository.save(citizenFeedback);
    }
    
    @Override
    public List<CitizenFeedback> getCitizenFeedbackByType(String feedbackType) {
        return citizenFeedbackRepository.findByFeedbackType(feedbackType);
    }
    
    @Override
    public List<CitizenFeedback> getCitizenFeedbackByCategory(String category) {
        return citizenFeedbackRepository.findByCategory(category);
    }
    
    @Override
    public List<CitizenFeedback> getUnreviewedFeedback() {
        return citizenFeedbackRepository.findByStatus("submitted");
    }
    
    @Override
    public List<CitizenFeedback> getCitizenFeedbackByRating(Integer rating) {
        return citizenFeedbackRepository.findByRating(rating);
    }
    
    @Override
    public CitizenFeedback updateFeedbackStatus(Long id, String status) {
        CitizenFeedback citizenFeedback = citizenFeedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Citizen feedback not found"));
        citizenFeedback.setStatus(status);
        return citizenFeedbackRepository.save(citizenFeedback);
    }
    
    @Override
    public CitizenFeedback respondToFeedback(Long id, String responseContent) {
        CitizenFeedback citizenFeedback = citizenFeedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Citizen feedback not found"));
        citizenFeedback.setResponseContent(responseContent);
        citizenFeedback.setResponseTime(LocalDateTime.now());
        citizenFeedback.setStatus("responded");
        return citizenFeedbackRepository.save(citizenFeedback);
    }
}