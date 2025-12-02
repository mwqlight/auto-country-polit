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
@Table(name = "investment_project")
public class InvestmentProject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "project_name")
    private String projectName; // 项目名称
    
    @Column(name = "project_type")
    private String projectType; // 项目类型: infrastructure, technology, manufacturing, etc.
    
    @Column(name = "investor")
    private String investor; // 投资方
    
    @Column(name = "investment_amount")
    private BigDecimal investmentAmount; // 投资金额
    
    @Column(name = "currency")
    private String currency; // 货币类型
    
    @Column(name = "project_description")
    private String projectDescription; // 项目描述
    
    @Column(name = "location")
    private String location; // 位置
    
    @Column(name = "latitude")
    private Double latitude; // 纬度
    
    @Column(name = "longitude")
    private Double longitude; // 经度
    
    @Column(name = "planned_start_date")
    private LocalDateTime plannedStartDate; // 计划开始日期
    
    @Column(name = "actual_start_date")
    private LocalDateTime actualStartDate; // 实际开始日期
    
    @Column(name = "planned_completion_date")
    private LocalDateTime plannedCompletionDate; // 计划完成日期
    
    @Column(name = "actual_completion_date")
    private LocalDateTime actualCompletionDate; // 实际完成日期
    
    @Column(name = "project_status")
    private String projectStatus; // 项目状态: planned, ongoing, completed, delayed, cancelled
    
    @Column(name = "progress_percentage")
    private BigDecimal progressPercentage; // 进度百分比
    
    @Column(name = "expected_jobs")
    private Integer expectedJobs; // 预期就业岗位
    
    @Column(name = "actual_jobs")
    private Integer actualJobs; // 实际就业岗位
    
    @Column(name = "environmental_impact")
    private String environmentalImpact; // 环境影响评估
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}