package com.smartcity.cockpit.dto.request;

import lombok.Data;

@Data
public class IotDeviceRequest {
    private String deviceId;
    private String name;
    private String type;
    private String location;
    private String status;
    private Double latitude;
    private Double longitude;
}