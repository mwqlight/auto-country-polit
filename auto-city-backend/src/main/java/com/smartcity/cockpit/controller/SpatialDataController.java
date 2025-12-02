package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.entity.SpatialData;
import com.smartcity.cockpit.service.SpatialDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/city/digital-twin/spatial-data")
@RequiredArgsConstructor
public class SpatialDataController {
    
    private final SpatialDataService spatialDataService;
    
    @PostMapping
    public ResponseEntity<SpatialData> createSpatialData(@RequestBody SpatialData spatialData) {
        SpatialData created = spatialDataService.createSpatialData(spatialData);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/type/{dataType}")
    public ResponseEntity<List<SpatialData>> getSpatialDataByTypeAndTimeRange(
            @PathVariable String dataType,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<SpatialData> data = spatialDataService.getSpatialDataByTypeAndTimeRange(dataType, start, end);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/location")
    public ResponseEntity<List<SpatialData>> getSpatialDataByLocationRange(
            @RequestParam Double latMin,
            @RequestParam Double latMax,
            @RequestParam Double lonMin,
            @RequestParam Double lonMax) {
        List<SpatialData> data = spatialDataService.getSpatialDataByLocationRange(latMin, latMax, lonMin, lonMax);
        return ResponseEntity.ok(data);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpatialData(@PathVariable Long id) {
        spatialDataService.deleteSpatialData(id);
        return ResponseEntity.noContent().build();
    }
}