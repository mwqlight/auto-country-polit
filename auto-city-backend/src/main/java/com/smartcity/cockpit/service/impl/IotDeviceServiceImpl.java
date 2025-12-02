package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.dto.request.IotDeviceRequest;
import com.smartcity.cockpit.dto.response.IotDeviceResponse;
import com.smartcity.cockpit.entity.IotDevice;
import com.smartcity.cockpit.repository.IotDeviceRepository;
import com.smartcity.cockpit.service.IotDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IotDeviceServiceImpl implements IotDeviceService {
    
    private final IotDeviceRepository iotDeviceRepository;
    
    @Override
    public IotDeviceResponse createIotDevice(IotDeviceRequest request) {
        IotDevice iotDevice = new IotDevice();
        BeanUtils.copyProperties(request, iotDevice);
        iotDevice.setCreatedAt(LocalDateTime.now());
        iotDevice.setUpdatedAt(LocalDateTime.now());
        iotDevice.setLastSeen(LocalDateTime.now());
        
        IotDevice saved = iotDeviceRepository.save(iotDevice);
        
        IotDeviceResponse response = new IotDeviceResponse();
        BeanUtils.copyProperties(saved, response);
        return response;
    }
    
    @Override
    public IotDeviceResponse getIotDeviceById(Long id) {
        IotDevice iotDevice = iotDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IoT device not found with id: " + id));
        
        IotDeviceResponse response = new IotDeviceResponse();
        BeanUtils.copyProperties(iotDevice, response);
        return response;
    }
    
    @Override
    public IotDeviceResponse getIotDeviceByDeviceId(String deviceId) {
        IotDevice iotDevice = iotDeviceRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new RuntimeException("IoT device not found with device id: " + deviceId));
        
        IotDeviceResponse response = new IotDeviceResponse();
        BeanUtils.copyProperties(iotDevice, response);
        return response;
    }
    
    @Override
    public List<IotDeviceResponse> getAllIotDevices() {
        return iotDeviceRepository.findAll().stream()
                .map(device -> {
                    IotDeviceResponse response = new IotDeviceResponse();
                    BeanUtils.copyProperties(device, response);
                    return response;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public List<IotDeviceResponse> getIotDevicesByStatus(String status) {
        return iotDeviceRepository.findByStatus(status).stream()
                .map(device -> {
                    IotDeviceResponse response = new IotDeviceResponse();
                    BeanUtils.copyProperties(device, response);
                    return response;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public List<IotDeviceResponse> getIotDevicesByType(String type) {
        return iotDeviceRepository.findByType(type).stream()
                .map(device -> {
                    IotDeviceResponse response = new IotDeviceResponse();
                    BeanUtils.copyProperties(device, response);
                    return response;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public IotDeviceResponse updateIotDevice(Long id, IotDeviceRequest request) {
        IotDevice existing = iotDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IoT device not found with id: " + id));
        
        BeanUtils.copyProperties(request, existing);
        existing.setUpdatedAt(LocalDateTime.now());
        
        IotDevice updated = iotDeviceRepository.save(existing);
        
        IotDeviceResponse response = new IotDeviceResponse();
        BeanUtils.copyProperties(updated, response);
        return response;
    }
    
    @Override
    public void deleteIotDevice(Long id) {
        if (!iotDeviceRepository.existsById(id)) {
            throw new RuntimeException("IoT device not found with id: " + id);
        }
        iotDeviceRepository.deleteById(id);
    }
}