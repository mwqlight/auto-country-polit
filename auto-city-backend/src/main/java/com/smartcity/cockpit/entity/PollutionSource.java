package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "pollution_source")
@Data
public class PollutionSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String sourceType; // factory, vehicle, construction, etc.
    
    private String name;
    
    private Double latitude;
    
    private Double longitude;
    
    private String location;
    
    private String emissionType; // CO2, SO2, NOx, PM2.5, etc.
    
    private Double emissionRate;
    
    private String status; // ACTIVE, INACTIVE, MONITORING
    
    @Column(name = "installation_date")
    private LocalDateTime installationDate;
    
    @Column(name = "last_inspection")
    private LocalDateTime lastInspection;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}