package com.smartcity.cockpit.service;

import com.smartcity.cockpit.entity.PublicService;
import com.smartcity.cockpit.entity.ServiceRequest;
import com.smartcity.cockpit.entity.CitizenFeedback;

import java.time.LocalDateTime;
import java.util.List;

public interface PublicServiceManagementService {
    
    // Public Service methods
    PublicService createPublicService(PublicService publicService);
    
    List<PublicService> getPublicServicesByType(String serviceType);
    
    List<PublicService> getPublicServicesByStatus(String status);
    
    List<PublicService> getPublicServicesByArea(String serviceArea);
    
    List<PublicService> searchPublicServicesByName(String name);
    
    List<PublicService> getPublicServicesByLocationRange(Double minLat, Double maxLat, Double minLng, Double maxLng);
    
    PublicService updatePublicServiceStatus(Long id, String status);
    
    // Service Request methods
    ServiceRequest createServiceRequest(ServiceRequest serviceRequest);
    
    List<ServiceRequest> getServiceRequestsByType(String requestType);
    
    List<ServiceRequest> getServiceRequestsByStatus(String status);
    
    List<ServiceRequest> getServiceRequestsByCitizen(String citizenId);
    
    List<ServiceRequest> getServiceRequestsByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    
    List<ServiceRequest> getRecentServiceRequestsByStatus(String status);
    
    ServiceRequest updateServiceRequestStatus(Long id, String status);
    
    // Citizen Feedback methods
    CitizenFeedback createCitizenFeedback(CitizenFeedback citizenFeedback);
    
    List<CitizenFeedback> getCitizenFeedbackByType(String feedbackType);
    
    List<CitizenFeedback> getCitizenFeedbackByCategory(String category);
    
    List<CitizenFeedback> getUnreviewedFeedback();
    
    List<CitizenFeedback> getCitizenFeedbackByRating(Integer rating);
    
    CitizenFeedback updateFeedbackStatus(Long id, String status);
    
    CitizenFeedback respondToFeedback(Long id, String responseContent);
}