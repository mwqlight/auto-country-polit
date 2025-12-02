package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.entity.TrafficFlow;
import com.smartcity.cockpit.entity.TrafficIncident;
import com.smartcity.cockpit.entity.SmartSignal;
import com.smartcity.cockpit.repository.TrafficFlowRepository;
import com.smartcity.cockpit.repository.TrafficIncidentRepository;
import com.smartcity.cockpit.repository.SmartSignalRepository;
import com.smartcity.cockpit.service.TrafficManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrafficManagementServiceImpl implements TrafficManagementService {
    
    private final TrafficFlowRepository trafficFlowRepository;
    private final TrafficIncidentRepository trafficIncidentRepository;
    private final SmartSignalRepository smartSignalRepository;
    
    // Traffic Flow methods
    @Override
    public TrafficFlow createTrafficFlow(TrafficFlow trafficFlow) {
        trafficFlow.setRecordTime(LocalDateTime.now());
        return trafficFlowRepository.save(trafficFlow);
    }
    
    @Override
    public List<TrafficFlow> getTrafficFlowsByRoadName(String roadName) {
        return trafficFlowRepository.findByRoadName(roadName);
    }
    
    @Override
    public List<TrafficFlow> getTrafficFlowsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return trafficFlowRepository.findByRecordTimeBetween(start, end);
    }
    
    @Override
    public List<TrafficFlow> getTrafficFlowsByLocationRange(Double latMin, Double latMax, Double lonMin, Double lonMax) {
        return trafficFlowRepository.findByLocationRange(latMin, latMax, lonMin, lonMax);
    }
    
    @Override
    public List<TrafficFlow> getLatestTrafficFlows() {
        return trafficFlowRepository.findTop10ByOrderByRecordTimeDesc();
    }
    
    // Traffic Incident methods
    @Override
    public TrafficIncident createTrafficIncident(TrafficIncident trafficIncident) {
        trafficIncident.setIncidentTime(LocalDateTime.now());
        trafficIncident.setIsResolved(false);
        return trafficIncidentRepository.save(trafficIncident);
    }
    
    @Override
    public List<TrafficIncident> getTrafficIncidentsByType(String incidentType) {
        return trafficIncidentRepository.findByIncidentType(incidentType);
    }
    
    @Override
    public List<TrafficIncident> getTrafficIncidentsBySeverity(String severity) {
        return trafficIncidentRepository.findBySeverity(severity);
    }
    
    @Override
    public List<TrafficIncident> getUnresolvedTrafficIncidents() {
        return trafficIncidentRepository.findTop10ByIsResolvedFalseOrderByIncidentTimeDesc();
    }
    
    @Override
    public List<TrafficIncident> getTrafficIncidentsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return trafficIncidentRepository.findByIncidentTimeBetween(start, end);
    }
    
    @Override
    public TrafficIncident resolveTrafficIncident(Long id) {
        Optional<TrafficIncident> incidentOptional = trafficIncidentRepository.findById(id);
        if (incidentOptional.isPresent()) {
            TrafficIncident incident = incidentOptional.get();
            incident.setIsResolved(true);
            incident.setResolvedTime(LocalDateTime.now());
            return trafficIncidentRepository.save(incident);
        }
        throw new RuntimeException("Traffic incident not found with id: " + id);
    }
    
    // Smart Signal methods
    @Override
    public SmartSignal createSmartSignal(SmartSignal smartSignal) {
        return smartSignalRepository.save(smartSignal);
    }
    
    @Override
    public SmartSignal getSmartSignalBySignalId(String signalId) {
        Optional<SmartSignal> smartSignalOptional = smartSignalRepository.findBySignalId(signalId);
        return smartSignalOptional.orElse(null);
    }
    
    @Override
    public List<SmartSignal> getSmartSignalsByLocation(String location) {
        return smartSignalRepository.findByLocationContaining(location);
    }
    
    @Override
    public List<SmartSignal> getSmartSignalsByPhase(String phase) {
        return smartSignalRepository.findByCurrentPhase(phase);
    }
    
    @Override
    public SmartSignal updateSmartSignalTiming(String signalId, Integer greenTime, Integer redTime, Integer yellowTime) {
        Optional<SmartSignal> smartSignalOptional = smartSignalRepository.findBySignalId(signalId);
        if (smartSignalOptional.isPresent()) {
            SmartSignal smartSignal = smartSignalOptional.get();
            smartSignal.setGreenTime(greenTime);
            smartSignal.setRedTime(redTime);
            smartSignal.setYellowTime(yellowTime);
            return smartSignalRepository.save(smartSignal);
        }
        throw new RuntimeException("Smart signal not found with signalId: " + signalId);
    }
}