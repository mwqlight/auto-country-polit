package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "safety_inspection")
@Data
public class SafetyInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String facilityType;
    
    private String facilityName;
    
    private String inspectorName;
    
    private Double latitude;
    
    private Double longitude;
    
    private String location;
    
    @Column(name = "inspection_date")
    private LocalDateTime inspectionDate;
    
    private String result;
    
    private String remarks;
    
    @Column(name = "next_inspection_date")
    private LocalDateTime nextInspectionDate;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}