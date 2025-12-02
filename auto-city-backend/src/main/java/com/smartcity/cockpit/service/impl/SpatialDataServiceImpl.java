package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.entity.SpatialData;
import com.smartcity.cockpit.repository.SpatialDataRepository;
import com.smartcity.cockpit.service.SpatialDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpatialDataServiceImpl implements SpatialDataService {
    
    private final SpatialDataRepository spatialDataRepository;
    
    @Override
public SpatialData createSpatialData(SpatialData spatialData) {
        return spatialDataRepository.save(spatialData);
    }
    
    @Override
    public List<SpatialData> getSpatialDataByTypeAndTimeRange(String dataType, LocalDateTime start, LocalDateTime end) {
        return spatialDataRepository.findByDataTypeAndTimestampBetween(dataType, start, end);
    }
    
    @Override
    public List<SpatialData> getSpatialDataByLocationRange(Double latMin, Double latMax, Double lonMin, Double lonMax) {
        return spatialDataRepository.findByLocationRange(latMin, latMax, lonMin, lonMax);
    }
    
    @Override
    public void deleteSpatialData(Long id) {
        spatialDataRepository.deleteById(id);
    }
}