package com.smartcity.cockpit.service;

import com.smartcity.cockpit.entity.TrafficFlow;
import com.smartcity.cockpit.entity.TrafficIncident;
import com.smartcity.cockpit.entity.SmartSignal;
import java.time.LocalDateTime;
import java.util.List;

public interface TrafficManagementService {
    
    // Traffic Flow methods
    TrafficFlow createTrafficFlow(TrafficFlow trafficFlow);
    List<TrafficFlow> getTrafficFlowsByRoadName(String roadName);
    List<TrafficFlow> getTrafficFlowsByTimeRange(LocalDateTime start, LocalDateTime end);
    List<TrafficFlow> getTrafficFlowsByLocationRange(Double latMin, Double latMax, Double lonMin, Double lonMax);
    List<TrafficFlow> getLatestTrafficFlows();
    
    // Traffic Incident methods
    TrafficIncident createTrafficIncident(TrafficIncident trafficIncident);
    List<TrafficIncident> getTrafficIncidentsByType(String incidentType);
    List<TrafficIncident> getTrafficIncidentsBySeverity(String severity);
    List<TrafficIncident> getUnresolvedTrafficIncidents();
    List<TrafficIncident> getTrafficIncidentsByTimeRange(LocalDateTime start, LocalDateTime end);
    TrafficIncident resolveTrafficIncident(Long id);
    
    // Smart Signal methods
    SmartSignal createSmartSignal(SmartSignal smartSignal);
    SmartSignal getSmartSignalBySignalId(String signalId);
    List<SmartSignal> getSmartSignalsByLocation(String location);
    List<SmartSignal> getSmartSignalsByPhase(String phase);
    SmartSignal updateSmartSignalTiming(String signalId, Integer greenTime, Integer redTime, Integer yellowTime);
}