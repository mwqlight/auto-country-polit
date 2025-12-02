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
@Table(name = "ai_model")
public class AIModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "model_name")
    private String modelName; // 模型名称
    
    @Column(name = "model_version")
    private String modelVersion; // 模型版本
    
    @Column(name = "model_type")
    private String modelType; // 模型类型: regression, classification, clustering, deep_learning, etc.
    
    @Column(name = "domain_area")
    private String domainArea; // 领域: traffic, environment, energy, economy, etc.
    
    @Column(name = "algorithm_name")
    private String algorithmName; // 算法名称
    
    @Column(name = "training_data_source")
    private String trainingDataSource; // 训练数据源
    
    @Column(name = "model_accuracy")
    private Double modelAccuracy; // 模型准确率
    
    @Column(name = "last_training_time")
    private LocalDateTime lastTrainingTime; // 最后训练时间
    
    @Column(name = "model_file_path")
    private String modelFilePath; // 模型文件路径
    
    @Column(name = "model_parameters")
    private String modelParameters; // 模型参数（JSON格式）
    
    @Column(name = "performance_metrics")
    private String performanceMetrics; // 性能指标（JSON格式）
    
    @Column(name = "status")
    private String status; // 状态: active, inactive, deprecated
    
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