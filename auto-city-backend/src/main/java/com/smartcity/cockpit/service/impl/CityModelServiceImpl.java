package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.dto.request.CityModelRequest;
import com.smartcity.cockpit.dto.response.CityModelResponse;
import com.smartcity.cockpit.entity.CityModel;
import com.smartcity.cockpit.repository.CityModelRepository;
import com.smartcity.cockpit.service.CityModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityModelServiceImpl implements CityModelService {
    
    private final CityModelRepository cityModelRepository;
    
    @Override
    public CityModelResponse createCityModel(CityModelRequest request) {
        CityModel cityModel = new CityModel();
        BeanUtils.copyProperties(request, cityModel);
        cityModel.setCreatedAt(LocalDateTime.now());
        cityModel.setUpdatedAt(LocalDateTime.now());
        
        CityModel saved = cityModelRepository.save(cityModel);
        
        CityModelResponse response = new CityModelResponse();
        BeanUtils.copyProperties(saved, response);
        return response;
    }
    
    @Override
    public CityModelResponse getCityModelById(Long id) {
        CityModel cityModel = cityModelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City model not found with id: " + id));
        
        CityModelResponse response = new CityModelResponse();
        BeanUtils.copyProperties(cityModel, response);
        return response;
    }
    
    @Override
    public List<CityModelResponse> getAllCityModels() {
        return cityModelRepository.findAll().stream()
                .map(cityModel -> {
                    CityModelResponse response = new CityModelResponse();
                    BeanUtils.copyProperties(cityModel, response);
                    return response;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public CityModelResponse updateCityModel(Long id, CityModelRequest request) {
        CityModel existing = cityModelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City model not found with id: " + id));
        
        BeanUtils.copyProperties(request, existing);
        existing.setUpdatedAt(LocalDateTime.now());
        
        CityModel updated = cityModelRepository.save(existing);
        
        CityModelResponse response = new CityModelResponse();
        BeanUtils.copyProperties(updated, response);
        return response;
    }
    
    @Override
    public void deleteCityModel(Long id) {
        if (!cityModelRepository.existsById(id)) {
            throw new RuntimeException("City model not found with id: " + id);
        }
        cityModelRepository.deleteById(id);
    }
}