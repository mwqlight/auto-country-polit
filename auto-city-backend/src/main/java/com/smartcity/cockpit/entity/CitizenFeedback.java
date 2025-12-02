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
@Table(name = "citizen_feedback")
public class CitizenFeedback {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "feedback_type")
    private String feedbackType; // 反馈类型: suggestion, complaint, praise, etc.
    
    @Column(name = "title")
    private String title; // 标题
    
    @Column(name = "content")
    private String content; // 内容
    
    @Column(name = "citizen_id")
    private String citizenId; // 市民ID
    
    @Column(name = "citizen_name")
    private String citizenName; // 市民姓名
    
    @Column(name = "contact_info")
    private String contactInfo; // 联系信息
    
    @Column(name = "service_id")
    private Long serviceId; // 服务ID
    
    @Column(name = "rating")
    private Integer rating; // 评分 1-5
    
    @Column(name = "category")
    private String category; // 分类: education, healthcare, transportation, etc.
    
    @Column(name = "is_anonymous")
    private Boolean isAnonymous = false; // 是否匿名
    
    @Column(name = "status")
    private String status; // 状态: submitted, reviewed, responded, closed
    
    @Column(name = "submitted_time")
    private LocalDateTime submittedTime; // 提交时间
    
    @Column(name = "response_content")
    private String responseContent; // 回复内容
    
    @Column(name = "response_time")
    private LocalDateTime responseTime; // 回复时间
    
    @Column(name = "responsible_department")
    private String responsibleDepartment; // 负责部门
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = "submitted";
        }
        if (submittedTime == null) {
            submittedTime = LocalDateTime.now();
        }
        if (isAnonymous == null) {
            isAnonymous = false;
        }
    }
}