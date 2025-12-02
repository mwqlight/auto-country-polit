package com.smartcity.cockpit.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class IotDeviceResponse {
    private Long id;
    private String deviceId;
    private String name;
    private String type;
    private String location;
    private String status;
    private Double latitude;
    private Double longitude;
    private LocalDateTime lastSeen;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}