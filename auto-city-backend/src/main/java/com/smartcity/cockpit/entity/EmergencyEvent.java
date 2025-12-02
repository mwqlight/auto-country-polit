package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergency_event")
@Data
public class EmergencyEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String eventType;
    
    private String description;
    
    private Double latitude;
    
    private Double longitude;
    
    private String location;
    
    private String severity;
    
    @Column(name = "event_time")
    private LocalDateTime eventTime;
    
    @Column(name = "resolved_time")
    private LocalDateTime resolvedTime;
    
    private Boolean isResolved;
    
    @Column(name = "assigned_department")
    private String assignedDepartment;
    
    @Column(name": "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}