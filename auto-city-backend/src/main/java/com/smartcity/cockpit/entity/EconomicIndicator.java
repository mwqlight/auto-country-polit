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
@Table(name = "economic_indicator")
public class EconomicIndicator {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "indicator_type")
    private String indicatorType; // 指标类型: GDP, unemployment_rate, inflation_rate, etc.
    
    @Column(name = "indicator_name")
    private String indicatorName; // 指标名称
    
    @Column(name = "value")
    private BigDecimal value; // 指标值
    
    @Column(name = "unit")
    private String unit; // 单位
    
    @Column(name = "region")
    private String region; // 区域
    
    @Column(name = "time_period")
    private String timePeriod; // 时间周期: monthly, quarterly, yearly
    
    @Column(name = "year")
    private Integer year; // 年份
    
    @Column(name = "quarter")
    private Integer quarter; // 季度
    
    @Column(name = "month")
    private Integer month; // 月份
    
    @Column(name = "growth_rate")
    private BigDecimal growthRate; // 增长率
    
    @Column(name = "trend")
    private String trend; // 趋势: increasing, decreasing, stable
    
    @Column(name = "recorded_time")
    private LocalDateTime recordedTime; // 记录时间
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}