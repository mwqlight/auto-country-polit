package com.smartcity.cockpit.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CityModelResponse {
    private Long id;
    private String name;
    private String description;
    private String modelData;
    private String version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}