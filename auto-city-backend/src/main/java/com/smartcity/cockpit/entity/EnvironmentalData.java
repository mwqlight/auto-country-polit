package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "environmental_data")
@Data
public class EnvironmentalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String dataType; // air_quality, water_quality, noise_level, etc.
    
    private Double value;
    
    private String unit;
    
    private Double latitude;
    
    private Double longitude;
    
    private String location;
    
    private String qualityLevel; // GOOD, MODERATE, UNHEALTHY, etc.
    
    @Column(name = "record_time")
    private LocalDateTime recordTime;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}