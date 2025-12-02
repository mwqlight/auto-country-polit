package com.smartcity.cockpit.service;

import com.smartcity.cockpit.entity.SpatialData;
import java.time.LocalDateTime;
import java.util.List;

public interface SpatialDataService {
    SpatialData createSpatialData(SpatialData spatialData);
    List<SpatialData> getSpatialDataByTypeAndTimeRange(String dataType, LocalDateTime start, LocalDateTime end);
    List<SpatialData> getSpatialDataByLocationRange(Double latMin, Double latMax, Double lonMin, Double lonMax);
    void deleteSpatialData(Long id);
}