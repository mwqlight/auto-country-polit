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
@Table(name = "service_request")
public class ServiceRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "request_type")
    private String requestType; // 请求类型: complaint, inquiry, application, etc.
    
    @Column(name = "title")
    private String title; // 标题
    
    @Column(name = "description")
    private String description; // 描述
    
    @Column(name = "citizen_id")
    private String citizenId; // 市民ID
    
    @Column(name = "citizen_name")
    private String citizenName; // 市民姓名
    
    @Column(name = "contact_info")
    private String contactInfo; // 联系信息
    
    @Column(name = "service_id")
    private Long serviceId; // 服务ID
    
    @Column(name = "priority")
    private String priority; // 优先级: low, medium, high, urgent
    
    @Column(name = "status")
    private String status; // 状态: submitted, processing, resolved, closed
    
    @Column(name = "assigned_department")
    private String assignedDepartment; // 分配部门
    
    @Column(name = "assigned_staff")
    private String assignedStaff; // 分配员工
    
    @Column(name = "submitted_time")
    private LocalDateTime submittedTime; // 提交时间
    
    @Column(name": "expected_resolution_time")
    private LocalDateTime expectedResolutionTime; // 预期解决时间
    
    @Column(name = "actual_resolution_time")
    private LocalDateTime actualResolutionTime; // 实际解决时间
    
    @Column(name = "feedback")
    private String feedback; // 反馈
    
    @Column(name = "satisfaction_rating")
    private Integer satisfactionRating; // 满意度评分
    
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
    }
}