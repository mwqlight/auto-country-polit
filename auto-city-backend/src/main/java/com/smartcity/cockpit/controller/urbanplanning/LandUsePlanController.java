package com.smartcity.cockpit.controller.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.LandUsePlan;
import com.smartcity.cockpit.service.urbanplanning.LandUsePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/urban-planning/land-use-plans")
@CrossOrigin(origins = "*")
public class LandUsePlanController {

    @Autowired
    private LandUsePlanService landUsePlanService;

    @GetMapping
    public ResponseEntity<List<LandUsePlan>> getAllLandUsePlans() {
        List<LandUsePlan> plans = landUsePlanService.getAllLandUsePlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LandUsePlan> getLandUsePlanById(@PathVariable Long id) {
        LandUsePlan plan = landUsePlanService.getLandUsePlanById(id);
        if (plan != null) {
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LandUsePlan> createLandUsePlan(@RequestBody LandUsePlan landUsePlan) {
        LandUsePlan createdPlan = landUsePlanService.createLandUsePlan(landUsePlan);
        return ResponseEntity.status(201).body(createdPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandUsePlan> updateLandUsePlan(@PathVariable Long id, @RequestBody LandUsePlan landUsePlan) {
        LandUsePlan updatedPlan = landUsePlanService.updateLandUsePlan(id, landUsePlan);
        if (updatedPlan != null) {
            return ResponseEntity.ok(updatedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLandUsePlan(@PathVariable Long id) {
        landUsePlanService.deleteLandUsePlan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{landUseType}")
    public ResponseEntity<List<LandUsePlan>> getLandUsePlansByType(@PathVariable String landUseType) {
        List<LandUsePlan> plans = landUsePlanService.getLandUsePlansByType(landUseType);
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/status/{approvalStatus}")
    public ResponseEntity<List<LandUsePlan>> getLandUsePlansByStatus(@PathVariable String approvalStatus) {
        List<LandUsePlan> plans = landUsePlanService.getLandUsePlansByStatus(approvalStatus);
        return ResponseEntity.ok(plans);
    }
}