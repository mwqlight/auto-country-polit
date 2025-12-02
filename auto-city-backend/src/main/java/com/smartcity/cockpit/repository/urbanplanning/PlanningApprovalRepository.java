package com.smartcity.cockpit.repository.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.PlanningApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanningApprovalRepository extends JpaRepository<PlanningApproval, Long> {
    List<PlanningApproval> findByApprovalStatus(String approvalStatus);
    List<PlanningApproval> findByApplicant(String applicant);
    List<PlanningApproval> findByApprover(String approver);
}