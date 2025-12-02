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
@Table(name = "policy_recommendation")
public class PolicyRecommendation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "recommendation_title")
    private String recommendationTitle; // 推荐标题
    
    @Column(name = "recommendation_content")
    private String recommendationContent; // 推荐内容
    
    @Column(name = "recommendation_type")
    private String recommendationType; // 推荐类型: traffic, environment, energy, economy, etc.
    
    @Column(name = "priority_level")
    private String priorityLevel; // 优先级: low, medium, high, critical
    
    @Column(name = "related_department")
    private String relatedDepartment; // 相关部门
    
    @Column(name = "implementation_cost")
    private String implementationCost; // 实施成本估算
    
    @Column(name = "expected_effect")
    private String expectedEffect; // 预期效果
    
    @Column(name = "based_on_prediction_id")
    private Long basedOnPredictionId; // 基于的预测ID
    
    @Column(name = "status")
    private String status; // 状态: proposed, approved, implemented, rejected
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}