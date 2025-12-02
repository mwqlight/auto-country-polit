package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "environmental_alert")
@Data
public class EnvironmentalAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String alertType; // AIR_POLLUTION, WATER_CONTAMINATION, NOISE_VIOLATION, etc.
    
    private String severity; // LOW, MEDIUM, HIGH, CRITICAL
    
    private String message;
    
    private Double latitude;
    
    private Double longitude;
    
    private String location;
    
    @Column(name = "alert_time")
    private LocalDateTime alertTime;
    
    @Column(name = "resolved_time")
    private LocalDateTime resolvedTime;
    
    private Boolean isResolved;
    
    @Column(name = "assigned_department")
    private String assignedDepartment;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        alertTime = LocalDateTime.now();
        isResolved = false;
    }
}