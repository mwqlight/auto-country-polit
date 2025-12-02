package com.smartcity.cockpit.service;

import com.smartcity.cockpit.dto.request.CityModelRequest;
import com.smartcity.cockpit.dto.response.CityModelResponse;
import java.util.List;

public interface CityModelService {
    CityModelResponse createCityModel(CityModelRequest request);
    CityModelResponse getCityModelById(Long id);
    List<CityModelResponse> getAllCityModels();
    CityModelResponse updateCityModel(Long id, CityModelRequest request);
    void deleteCityModel(Long id);
}