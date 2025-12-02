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
@Table(name = "prediction_result")
public class PredictionResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "model_name")
    private String modelName; // 模型名称
    
    @Column(name = "prediction_type")
    private String predictionType; // 预测类型: traffic, environment, energy, economy, etc.
    
    @Column(name = "input_data")
    private String inputData; // 输入数据（JSON格式）
    
    @Column(name = "prediction_result")
    private String predictionResult; // 预测结果（JSON格式）
    
    @Column(name = "confidence_score")
    private Double confidenceScore; // 置信度分数
    
    @Column(name = "predicted_at")
    private LocalDateTime predictedAt; // 预测时间
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (predictedAt == null) {
            predictedAt = LocalDateTime.now();
        }
    }
}