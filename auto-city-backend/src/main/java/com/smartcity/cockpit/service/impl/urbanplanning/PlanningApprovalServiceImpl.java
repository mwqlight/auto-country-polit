package com.smartcity.cockpit.service.impl.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.PlanningApproval;
import com.smartcity.cockpit.repository.urbanplanning.PlanningApprovalRepository;
import com.smartcity.cockpit.service.urbanplanning.PlanningApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PlanningApprovalServiceImpl implements PlanningApprovalService {

    @Autowired
    private PlanningApprovalRepository planningApprovalRepository;

    @Override
    public List<PlanningApproval> getAllPlanningApprovals() {
        return planningApprovalRepository.findAll();
    }

    @Override
    public PlanningApproval getPlanningApprovalById(Long id) {
        Optional<PlanningApproval> approval = planningApprovalRepository.findById(id);
        return approval.orElse(null);
    }

    @Override
    public PlanningApproval createPlanningApproval(PlanningApproval planningApproval) {
        planningApproval.setCreatedTime(LocalDateTime.now());
        planningApproval.setUpdatedTime(LocalDateTime.now());
        return planningApprovalRepository.save(planningApproval);
    }

    @Override
    public PlanningApproval updatePlanningApproval(Long id, PlanningApproval planningApproval) {
        Optional<PlanningApproval> existingApproval = planningApprovalRepository.findById(id);
        if (existingApproval.isPresent()) {
            PlanningApproval approval = existingApproval.get();
            approval.setApprovalItem(planningApproval.getApprovalItem());
            approval.setApplicant(planningApproval.getApplicant());
            approval.setApplicationDate(planningApproval.getApplicationDate());
            approval.setApprovalDate(planningApproval.getApprovalDate());
            approval.setApprovalStatus(planningApproval.getApprovalStatus());
            approval.setApprover(planningApproval.getApprover());
            approval.setComments(planningApproval.getComments());
            approval.setRelatedPlanId(planningApproval.getRelatedPlanId());
            approval.setRelatedProjectId(planningApproval.getRelatedProjectId());
            approval.setUpdatedTime(LocalDateTime.now());
            return planningApprovalRepository.save(approval);
        }
        return null;
    }

    @Override
    public void deletePlanningApproval(Long id) {
        planningApprovalRepository.deleteById(id);
    }

    @Override
    public List<PlanningApproval> getPlanningApprovalsByStatus(String approvalStatus) {
        return planningApprovalRepository.findByApprovalStatus(approvalStatus);
    }

    @Override
    public List<PlanningApproval> getPlanningApprovalsByApplicant(String applicant) {
        return planningApprovalRepository.findByApplicant(applicant);
    }
}