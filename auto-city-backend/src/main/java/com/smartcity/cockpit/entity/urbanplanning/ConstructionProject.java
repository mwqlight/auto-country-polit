package com.smartcity.cockpit.entity.urbanplanning;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "construction_project")
public class ConstructionProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_type")
    private String projectType;

    @Column(name = "location")
    private String location;

    @Column(name = "budget")
    private Double budget;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "progress")
    private Double progress;

    @Column(name = "manager")
    private String manager;

    @Column(name = "status")
    private String status;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}