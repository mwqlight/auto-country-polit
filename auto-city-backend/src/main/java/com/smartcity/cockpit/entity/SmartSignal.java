package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "smart_signal")
@Data
public class SmartSignal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String signalId;
    
    private String location;
    
    private Double latitude;
    
    private Double longitude;
    
    private String currentPhase;
    
    private Integer cycleDuration;
    
    private Integer greenTime;
    
    private Integer redTime;
    
    private Integer yellowTime;
    
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}