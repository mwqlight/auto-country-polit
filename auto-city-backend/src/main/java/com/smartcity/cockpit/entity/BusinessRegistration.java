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
@Table(name = "business_registration")
public class BusinessRegistration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "business_name")
    private String businessName; // 企业名称
    
    @Column(name = "registration_number")
    private String registrationNumber; // 注册号
    
    @Column(name = "legal_representative")
    private String legalRepresentative; // 法定代表人
    
    @Column(name = "business_type")
    private String businessType; // 企业类型: private, state_owned, partnership, etc.
    
    @Column(name = "industry_category")
    private String industryCategory; // 行业分类
    
    @Column(name = "registered_address")
    private String registeredAddress; // 注册地址
    
    @Column(name = "operating_address")
    private String operatingAddress; // 经营地址
    
    @Column(name = "registered_capital")
    private BigDecimal registeredCapital; // 注册资本
    
    @Column(name = "paid_in_capital")
    private BigDecimal paidInCapital; // 实收资本
    
    @Column(name = "employees_count")
    private Integer employeesCount; // 员工人数
    
    @Column(name = "contact_person")
    private String contactPerson; // 联系人
    
    @Column(name = "contact_phone")
    private String contactPhone; // 联系电话
    
    @Column(name = "email")
    private String email; // 邮箱
    
    @Column(name = "establishment_date")
    private LocalDateTime establishmentDate; // 成立日期
    
    @Column(name = "business_scope")
    private String businessScope; // 经营范围
    
    @Column(name = "status")
    private String status; // 状态: active, inactive, dissolved
    
    @Column(name = "annual_revenue")
    private BigDecimal annualRevenue; // 年营业额
    
    @Column(name = "tax_paid")
    private BigDecimal taxPaid; // 纳税额
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}