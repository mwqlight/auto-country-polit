package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.entity.EnvironmentalData;
import com.smartcity.cockpit.entity.PollutionSource;
import com.smartcity.cockpit.entity.EnvironmentalAlert;
import com.smartcity.cockpit.repository.EnvironmentalDataRepository;
import com.smartcity.cockpit.repository.PollutionSourceRepository;
import com.smartcity.cockpit.repository.EnvironmentalAlertRepository;
import com.smartcity.cockpit.service.EnvironmentalMonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnvironmentalMonitoringServiceImpl implements EnvironmentalMonitoringService {
    
    private final EnvironmentalDataRepository environmentalDataRepository;
    private final PollutionSourceRepository pollutionSourceRepository;
    private final EnvironmentalAlertRepository environmentalAlertRepository;
    
    // Environmental Data methods
    @Override
    public EnvironmentalData createEnvironmentalData(EnvironmentalData environmentalData) {
        environmentalData.setRecordTime(LocalDateTime.now());
        return environmentalDataRepository.save(environmentalData);
    }
    
    @Override
    public List<EnvironmentalData> getEnvironmentalDataByType(String dataType) {
        return environmentalDataRepository.findByDataType(dataType);
    }
    
    @Override
    public List<EnvironmentalData> getEnvironmentalDataByTypeAndTimeRange(String dataType, LocalDateTime start, LocalDateTime end) {
        return environmentalDataRepository.findByDataTypeAndRecordTimeBetween(dataType, start, end);
    }
    
    @Override
    public List<EnvironmentalData> getEnvironmentalDataByQualityLevel(String qualityLevel) {
        return environmentalDataRepository.findByQualityLevel(qualityLevel);
    }
    
    @Override
    public List<EnvironmentalData> getEnvironmentalDataByLocationRange(Double latMin, Double latMax, Double lonMin, Double lonMax) {
        return environmentalDataRepository.findByLocationRange(latMin, latMax, lonMin, lonMax);
    }
    
    @Override
    public List<EnvironmentalData> getLatestEnvironmentalData() {
        return environmentalDataRepository.findTop20ByOrderByRecordTimeDesc();
    }
    
    // Pollution Source methods
    @Override
    public PollutionSource createPollutionSource(PollutionSource pollutionSource) {
        pollutionSource.setInstallationDate(LocalDateTime.now());
        return pollutionSourceRepository.save(pollutionSource);
    }
    
    @Override
    public List<PollutionSource> getPollutionSourcesByType(String sourceType) {
        return pollutionSourceRepository.findBySourceType(sourceType);
    }
    
    @Override
    public List<PollutionSource> getPollutionSourcesByEmissionType(String emissionType) {
        return pollutionSourceRepository.findByEmissionType(emissionType);
    }
    
    @Override
    public List<PollutionSource> getPollutionSourcesByStatus(String status) {
        return pollutionSourceRepository.findByStatus(status);
    }
    
    @Override
    public PollutionSource updatePollutionSourceStatus(Long id, String status) {
        Optional<PollutionSource> sourceOptional = pollutionSourceRepository.findById(id);
        if (sourceOptional.isPresent()) {
            PollutionSource source = sourceOptional.get();
            source.setStatus(status);
            return pollutionSourceRepository.save(source);
        }
        throw new RuntimeException("Pollution source not found with id: " + id);
    }
    
    // Environmental Alert methods
    @Override
    public EnvironmentalAlert createEnvironmentalAlert(EnvironmentalAlert environmentalAlert) {
        return environmentalAlertRepository.save(environmentalAlert);
    }
    
    @Override
    public List<EnvironmentalAlert> getEnvironmentalAlertsByType(String alertType) {
        return environmentalAlertRepository.findByAlertType(alertType);
    }
    
    @Override
    public List<EnvironmentalAlert> getEnvironmentalAlertsBySeverity(String severity) {
        return environmentalAlertRepository.findBySeverity(severity);
    }
    
    @Override
    public List<EnvironmentalAlert> getUnresolvedEnvironmentalAlerts() {
        return environmentalAlertRepository.findTop10ByIsResolvedFalseOrderByAlertTimeDesc();
    }
    
    @Override
    public List<EnvironmentalAlert> getEnvironmentalAlertsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return environmentalAlertRepository.findByAlertTimeBetween(start, end);
    }
    
    @Override
    public EnvironmentalAlert resolveEnvironmentalAlert(Long id) {
        Optional<EnvironmentalAlert> alertOptional = environmentalAlertRepository.findById(id);
        if (alertOptional.isPresent()) {
            EnvironmentalAlert alert = alertOptional.get();
            alert.setIsResolved(true);
            alert.setResolvedTime(LocalDateTime.now());
            return environmentalAlertRepository.save(alert);
        }
        throw new RuntimeException("Environmental alert not found with id: " + id);
    }
}