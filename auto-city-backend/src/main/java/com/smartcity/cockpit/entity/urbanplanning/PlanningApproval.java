package com.smartcity.cockpit.entity.urbanplanning;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "planning_approval")
public class PlanningApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approval_item")
    private String approvalItem;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "application_date")
    private LocalDateTime applicationDate;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "approver")
    private String approver;

    @Column(name = "comments")
    private String comments;

    @Column(name = "related_plan_id")
    private Long relatedPlanId;

    @Column(name = "related_project_id")
    private Long relatedProjectId;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}