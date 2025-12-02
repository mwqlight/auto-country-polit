package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "energy_alert")
public class EnergyAlert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "alert_type")
    private String alertType; // 警报类型: high_consumption, equipment_failure, low_generation, etc.
    
    @Column(name = "severity")
    private String severity; // 严重程度: low, medium, high, critical
    
    @Column(name = "message")
    private String message; // 警报消息
    
    @Column(name = "building_id")
    private String buildingId; // 建筑物ID
    
    @Column(name = "building_name")
    private String buildingName; // 建筑物名称
    
    @Column(name = "equipment_id")
    private String equipmentId; // 设备ID
    
    @Column(name = "location")
    private String location; // 位置
    
    @Column(name = "latitude")
    private Double latitude; // 纬度
    
    @Column(name = "longitude")
    private Double longitude; // 经度
    
    @Column(name = "alert_time")
    private LocalDateTime alertTime; // 警报时间
    
    @Column(name = "resolved_time")
    private LocalDateTime resolvedTime; // 解决时间
    
    @Column(name = "is_resolved")
    private Boolean isResolved = false; // 是否已解决
    
    @Column(name = "assigned_department")
    private String assignedDepartment; // 分配部门
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (isResolved == null) {
            isResolved = false;
        }
    }
}