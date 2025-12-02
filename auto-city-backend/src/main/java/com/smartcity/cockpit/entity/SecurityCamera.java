package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "security_camera")
@Data
public class SecurityCamera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String cameraId;
    
    private String location;
    
    private Double latitude;
    
    private Double longitude;
    
    private String status;
    
    @Column(name = "ip_address")
    private String ipAddress;
    
    @Column(name = "last_maintenance")
    private LocalDateTime lastMaintenance;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}