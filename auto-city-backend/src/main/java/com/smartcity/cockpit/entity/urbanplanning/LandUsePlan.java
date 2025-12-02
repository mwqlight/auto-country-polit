package com.smartcity.cockpit.entity.urbanplanning;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "land_use_plan")
public class LandUsePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_description")
    private String planDescription;

    @Column(name = "area_size")
    private Double areaSize;

    @Column(name = "land_use_type")
    private String landUseType;

    @Column(name = "location")
    private String location;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "planner")
    private String planner;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}