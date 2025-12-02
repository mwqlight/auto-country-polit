package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.dto.request.IotDeviceRequest;
import com.smartcity.cockpit.dto.response.IotDeviceResponse;
import com.smartcity.cockpit.service.IotDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city/digital-twin/devices")
@RequiredArgsConstructor
public class IotDeviceController {
    
    private final IotDeviceService iotDeviceService;
    
    @PostMapping
    public ResponseEntity<IotDeviceResponse> createIotDevice(@RequestBody IotDeviceRequest request) {
        IotDeviceResponse response = iotDeviceService.createIotDevice(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<IotDeviceResponse> getIotDeviceById(@PathVariable Long id) {
        IotDeviceResponse response = iotDeviceService.getIotDeviceById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<IotDeviceResponse> getIotDeviceByDeviceId(@PathVariable String deviceId) {
        IotDeviceResponse response = iotDeviceService.getIotDeviceByDeviceId(deviceId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<IotDeviceResponse>> getAllIotDevices() {
        List<IotDeviceResponse> responses = iotDeviceService.getAllIotDevices();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<IotDeviceResponse>> getIotDevicesByStatus(@PathVariable String status) {
        List<IotDeviceResponse> responses = iotDeviceService.getIotDevicesByStatus(status);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<IotDeviceResponse>> getIotDevicesByType(@PathVariable String type) {
        List<IotDeviceResponse> responses = iotDeviceService.getIotDevicesByType(type);
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<IotDeviceResponse> updateIotDevice(@PathVariable Long id, @RequestBody IotDeviceRequest request) {
        IotDeviceResponse response = iotDeviceService.updateIotDevice(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIotDevice(@PathVariable Long id) {
        iotDeviceService.deleteIotDevice(id);
        return ResponseEntity.noContent().build();
    }
}