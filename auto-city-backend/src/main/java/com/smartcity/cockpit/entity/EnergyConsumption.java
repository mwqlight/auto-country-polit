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
@Table(name = "energy_consumption")
public class EnergyConsumption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "energy_type")
    private String energyType; // 能源类型: electricity, gas, water, etc.
    
    @Column(name = "consumption_value")
    private BigDecimal consumptionValue; // 消耗量
    
    @Column(name = "unit")
    private String unit; // 单位: kWh, m³, etc.
    
    @Column(name = "building_id")
    private String buildingId; // 建筑物ID
    
    @Column(name = "building_name")
    private String buildingName; // 建筑物名称
    
    @Column(name = "area")
    private String area; // 区域
    
    @Column(name = "consumption_time")
    private LocalDateTime consumptionTime; // 消耗时间
    
    @Column(name = "cost")
    private BigDecimal cost; // 费用
    
    @Column(name = "efficiency_rating")
    private String efficiencyRating; // 效率评级: A+, A, B, C, D, E, F
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}