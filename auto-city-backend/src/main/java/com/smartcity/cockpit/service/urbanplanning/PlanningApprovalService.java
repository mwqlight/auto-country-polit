package com.smartcity.cockpit.service.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.PlanningApproval;

import java.util.List;

public interface PlanningApprovalService {
    List<PlanningApproval> getAllPlanningApprovals();
    PlanningApproval getPlanningApprovalById(Long id);
    PlanningApproval createPlanningApproval(PlanningApproval planningApproval);
    PlanningApproval updatePlanningApproval(Long id, PlanningApproval planningApproval);
    void deletePlanningApproval(Long id);
    List<PlanningApproval> getPlanningApprovalsByStatus(String approvalStatus);
    List<PlanningApproval> getPlanningApprovalsByApplicant(String applicant);
}