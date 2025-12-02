package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "traffic_flow")
@Data
public class TrafficFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String roadName;
    
    private Double latitude;
    
    private Double longitude;
    
    private Integer vehicleCount;
    
    private Double averageSpeed;
    
    private String congestionLevel;
    
    @Column(name = "record_time")
    private LocalDateTime recordTime;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}