package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    
    List<ServiceRequest> findByRequestType(String requestType);
    
    List<ServiceRequest> findByStatus(String status);
    
    List<ServiceRequest> findByCitizenId(String citizenId);
    
    List<ServiceRequest> findByAssignedDepartment(String assignedDepartment);
    
    List<ServiceRequest> findBySubmittedTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    List<ServiceRequest> findByPriority(String priority);
    
    List<ServiceRequest> findTop20ByStatusOrderBySubmittedTimeDesc(String status);
}