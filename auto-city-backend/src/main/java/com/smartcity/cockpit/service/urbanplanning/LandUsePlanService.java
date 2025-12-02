package com.smartcity.cockpit.service.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.LandUsePlan;

import java.util.List;

public interface LandUsePlanService {
    List<LandUsePlan> getAllLandUsePlans();
    LandUsePlan getLandUsePlanById(Long id);
    LandUsePlan createLandUsePlan(LandUsePlan landUsePlan);
    LandUsePlan updateLandUsePlan(Long id, LandUsePlan landUsePlan);
    void deleteLandUsePlan(Long id);
    List<LandUsePlan> getLandUsePlansByType(String landUseType);
    List<LandUsePlan> getLandUsePlansByStatus(String approvalStatus);
}