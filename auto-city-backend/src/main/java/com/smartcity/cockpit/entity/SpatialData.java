package com.smartcity.cockpit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "spatial_data")
public class SpatialData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_type", nullable = false)
    private String dataType;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "location")
    private String location;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "value")
    private Double value;

    @Column(name = "unit")
    private String unit;

    @Column(name = "source")
    private String source;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}