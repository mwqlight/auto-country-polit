package com.smartcity.cockpit.controller.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.PlanningApproval;
import com.smartcity.cockpit.service.urbanplanning.PlanningApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/urban-planning/planning-approvals")
@CrossOrigin(origins = "*")
public class PlanningApprovalController {

    @Autowired
    private PlanningApprovalService planningApprovalService;

    @GetMapping
    public ResponseEntity<List<PlanningApproval>> getAllPlanningApprovals() {
        List<PlanningApproval> approvals = planningApprovalService.getAllPlanningApprovals();
        return ResponseEntity.ok(approvals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanningApproval> getPlanningApprovalById(@PathVariable Long id) {
        PlanningApproval approval = planningApprovalService.getPlanningApprovalById(id);
        if (approval != null) {
            return ResponseEntity.ok(approval);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PlanningApproval> createPlanningApproval(@RequestBody PlanningApproval planningApproval) {
        PlanningApproval createdApproval = planningApprovalService.createPlanningApproval(planningApproval);
        return ResponseEntity.status(201).body(createdApproval);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanningApproval> updatePlanningApproval(@PathVariable Long id, @RequestBody PlanningApproval planningApproval) {
        PlanningApproval updatedApproval = planningApprovalService.updatePlanningApproval(id, planningApproval);
        if (updatedApproval != null) {
            return ResponseEntity.ok(updatedApproval);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanningApproval(@PathVariable Long id) {
        planningApprovalService.deletePlanningApproval(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{approvalStatus}")
    public ResponseEntity<List<PlanningApproval>> getPlanningApprovalsByStatus(@PathVariable String approvalStatus) {
        List<PlanningApproval> approvals = planningApprovalService.getPlanningApprovalsByStatus(approvalStatus);
        return ResponseEntity.ok(approvals);
    }

    @GetMapping("/applicant/{applicant}")
    public ResponseEntity<List<PlanningApproval>> getPlanningApprovalsByApplicant(@PathVariable String applicant) {
        List<PlanningApproval> approvals = planningApprovalService.getPlanningApprovalsByApplicant(applicant);
        return ResponseEntity.ok(approvals);
    }
}