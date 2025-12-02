package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "renewable_energy")
public class RenewableEnergy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "energy_source")
    private String energySource; // 能源来源: solar, wind, hydro, etc.
    
    @Column(name = "generation_capacity")
    private BigDecimal generationCapacity; // 发电容量 (kW)
    
    @Column(name = "current_output")
    private BigDecimal currentOutput; // 当前输出 (kW)
    
    @Column(name = "location")
    private String location; // 位置
    
    @Column(name = "latitude")
    private Double latitude; // 纬度
    
    @Column(name = "longitude")
    private Double longitude; // 经度
    
    @Column(name = "efficiency")
    private BigDecimal efficiency; // 效率百分比
    
    @Column(name = "status")
    private String status; // 状态: active, maintenance, offline
    
    @Column(name = "installation_date")
    private LocalDateTime installationDate; // 安装日期
    
    @Column(name = "last_maintenance")
    private LocalDateTime lastMaintenance; // 最后维护时间
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}