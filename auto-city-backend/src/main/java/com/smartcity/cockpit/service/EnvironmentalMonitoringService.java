package com.smartcity.cockpit.service;

import com.smartcity.cockpit.entity.EnvironmentalData;
import com.smartcity.cockpit.entity.PollutionSource;
import com.smartcity.cockpit.entity.EnvironmentalAlert;
import java.time.LocalDateTime;
import java.util.List;

public interface EnvironmentalMonitoringService {
    
    // Environmental Data methods
    EnvironmentalData createEnvironmentalData(EnvironmentalData environmentalData);
    List<EnvironmentalData> getEnvironmentalDataByType(String dataType);
    List<EnvironmentalData> getEnvironmentalDataByTypeAndTimeRange(String dataType, LocalDateTime start, LocalDateTime end);
    List<EnvironmentalData> getEnvironmentalDataByQualityLevel(String qualityLevel);
    List<EnvironmentalData> getEnvironmentalDataByLocationRange(Double latMin, Double latMax, Double lonMin, Double lonMax);
    List<EnvironmentalData> getLatestEnvironmentalData();
    
    // Pollution Source methods
    PollutionSource createPollutionSource(PollutionSource pollutionSource);
    List<PollutionSource> getPollutionSourcesByType(String sourceType);
    List<PollutionSource> getPollutionSourcesByEmissionType(String emissionType);
    List<PollutionSource> getPollutionSourcesByStatus(String status);
    PollutionSource updatePollutionSourceStatus(Long id, String status);
    
    // Environmental Alert methods
    EnvironmentalAlert createEnvironmentalAlert(EnvironmentalAlert environmentalAlert);
    List<EnvironmentalAlert> getEnvironmentalAlertsByType(String alertType);
    List<EnvironmentalAlert> getEnvironmentalAlertsBySeverity(String severity);
    List<EnvironmentalAlert> getUnresolvedEnvironmentalAlerts();
    List<EnvironmentalAlert> getEnvironmentalAlertsByTimeRange(LocalDateTime start, LocalDateTime end);
    EnvironmentalAlert resolveEnvironmentalAlert(Long id);
}