package com.smartcity.cockpit.service;

import com.smartcity.cockpit.dto.request.IotDeviceRequest;
import com.smartcity.cockpit.dto.response.IotDeviceResponse;
import java.util.List;

public interface IotDeviceService {
    IotDeviceResponse createIotDevice(IotDeviceRequest request);
    IotDeviceResponse getIotDeviceById(Long id);
    IotDeviceResponse getIotDeviceByDeviceId(String deviceId);
    List<IotDeviceResponse> getAllIotDevices();
    List<IotDeviceResponse> getIotDevicesByStatus(String status);
    List<IotDeviceResponse> getIotDevicesByType(String type);
    IotDeviceResponse updateIotDevice(Long id, IotDeviceRequest request);
    void deleteIotDevice(Long id);
}