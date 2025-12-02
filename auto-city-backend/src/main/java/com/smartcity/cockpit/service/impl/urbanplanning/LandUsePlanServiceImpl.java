package com.smartcity.cockpit.service.impl.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.LandUsePlan;
import com.smartcity.cockpit.repository.urbanplanning.LandUsePlanRepository;
import com.smartcity.cockpit.service.urbanplanning.LandUsePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LandUsePlanServiceImpl implements LandUsePlanService {

    @Autowired
    private LandUsePlanRepository landUsePlanRepository;

    @Override
    public List<LandUsePlan> getAllLandUsePlans() {
        return landUsePlanRepository.findAll();
    }

    @Override
    public LandUsePlan getLandUsePlanById(Long id) {
        Optional<LandUsePlan> landUsePlan = landUsePlanRepository.findById(id);
        return landUsePlan.orElse(null);
    }

    @Override
    public LandUsePlan createLandUsePlan(LandUsePlan landUsePlan) {
        landUsePlan.setCreatedTime(LocalDateTime.now());
        landUsePlan.setUpdatedTime(LocalDateTime.now());
        return landUsePlanRepository.save(landUsePlan);
    }

    @Override
    public LandUsePlan updateLandUsePlan(Long id, LandUsePlan landUsePlan) {
        Optional<LandUsePlan> existingPlan = landUsePlanRepository.findById(id);
        if (existingPlan.isPresent()) {
            LandUsePlan plan = existingPlan.get();
            plan.setPlanName(landUsePlan.getPlanName());
            plan.setPlanDescription(landUsePlan.getPlanDescription());
            plan.setLandUseType(landUsePlan.getLandUseType());
            plan.setArea(landUsePlan.getArea());
            plan.setLocation(landUsePlan.getLocation());
            plan.setPlanner(landUsePlan.getPlanner());
            plan.setApprovalStatus(landUsePlan.getApprovalStatus());
            plan.setUpdatedTime(LocalDateTime.now());
            return landUsePlanRepository.save(plan);
        }
        return null;
    }

    @Override
    public void deleteLandUsePlan(Long id) {
        landUsePlanRepository.deleteById(id);
    }

    @Override
    public List<LandUsePlan> getLandUsePlansByType(String landUseType) {
        return landUsePlanRepository.findByLandUseType(landUseType);
    }

    @Override
    public List<LandUsePlan> getLandUsePlansByStatus(String approvalStatus) {
        return landUsePlanRepository.findByApprovalStatus(approvalStatus);
    }
}