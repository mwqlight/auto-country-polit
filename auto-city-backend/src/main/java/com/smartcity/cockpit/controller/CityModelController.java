package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.dto.request.CityModelRequest;
import com.smartcity.cockpit.dto.response.CityModelResponse;
import com.smartcity.cockpit.service.CityModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city/digital-twin/models")
@RequiredArgsConstructor
public class CityModelController {
    
    private final CityModelService cityModelService;
    
    @PostMapping
    public ResponseEntity<CityModelResponse> createCityModel(@RequestBody CityModelRequest request) {
        CityModelResponse response = cityModelService.createCityModel(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CityModelResponse> getCityModelById(@PathVariable Long id) {
        CityModelResponse response = cityModelService.getCityModelById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<CityModelResponse>> getAllCityModels() {
        List<CityModelResponse> responses = cityModelService.getAllCityModels();
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CityModelResponse> updateCityModel(@PathVariable Long id, @RequestBody CityModelRequest request) {
        CityModelResponse response = cityModelService.updateCityModel(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCityModel(@PathVariable Long id) {
        cityModelService.deleteCityModel(id);
        return ResponseEntity.noContent().build();
    }
}