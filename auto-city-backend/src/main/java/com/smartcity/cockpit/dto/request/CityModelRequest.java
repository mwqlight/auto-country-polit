package com.smartcity.cockpit.dto.request;

import lombok.Data;

@Data
public class CityModelRequest {
    private String name;
    private String description;
    private String modelData;
    private String version;
}