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
@Table(name = "public_service")
public class PublicService {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "service_type")
    private String serviceType; // 服务类型: education, healthcare, transportation, etc.
    
    @Column(name = "name")
    private String name; // 服务名称
    
    @Column(name = "description")
    private String description; // 描述
    
    @Column(name = "address")
    private String address; // 地址
    
    @Column(name = "latitude")
    private Double latitude; // 纬度
    
    @Column(name = "longitude")
    private Double longitude; // 经度
    
    @Column(name = "contact_info")
    private String contactInfo; // 联系信息
    
    @Column(name = "operating_hours")
    private String operatingHours; // 营业时间
    
    @Column(name = "rating")
    private Double rating; // 评分
    
    @Column(name = "capacity")
    private Integer capacity; // 容量
    
    @Column(name = "current_load")
    private Integer currentLoad; // 当前负载
    
    @Column(name = "status")
    private String status; // 状态: active, maintenance, closed
    
    @Column(name = "service_area")
    private String serviceArea; // 服务区域
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}