package com.smartcity.cockpit.repository.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.LandUsePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandUsePlanRepository extends JpaRepository<LandUsePlan, Long> {
    List<LandUsePlan> findByLandUseType(String landUseType);
    List<LandUsePlan> findByApprovalStatus(String approvalStatus);
    List<LandUsePlan> findByPlanner(String planner);
}