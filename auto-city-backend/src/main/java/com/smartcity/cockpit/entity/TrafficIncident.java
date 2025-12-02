package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "traffic_incident")
@Data
public class TrafficIncident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String incidentType;
    
    private String description;
    
    private Double latitude;
    
    private Double longitude;
    
    private String roadName;
    
    private String severity;
    
    @Column(name = "incident_time")
    private LocalDateTime incidentTime;
    
    @Column(name = "resolved_time")
    private LocalDateTime resolvedTime;
    
    private Boolean isResolved;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}