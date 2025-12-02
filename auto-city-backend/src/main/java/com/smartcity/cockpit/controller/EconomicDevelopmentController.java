package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.entity.EconomicIndicator;
import com.smartcity.cockpit.entity.BusinessRegistration;
import com.smartcity.cockpit.entity.InvestmentProject;
import com.smartcity.cockpit.service.EconomicDevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/economic-development")
@CrossOrigin(origins = "*")
public class EconomicDevelopmentController {
    
    @Autowired
    private EconomicDevelopmentService economicDevelopmentService;
    
    // Economic Indicator endpoints
    @PostMapping("/indicators")
    public ResponseEntity<EconomicIndicator> createEconomicIndicator(@RequestBody EconomicIndicator indicator) {
        EconomicIndicator createdIndicator = economicDevelopmentService.createEconomicIndicator(indicator);
        return ResponseEntity.ok(createdIndicator);
    }
    
    @GetMapping("/indicators")
    public ResponseEntity<List<EconomicIndicator>> getAllEconomicIndicators() {
        List<EconomicIndicator> indicators = economicDevelopmentService.getAllEconomicIndicators();
        return ResponseEntity.ok(indicators);
    }
    
    @GetMapping("/indicators/{id}")
    public ResponseEntity<EconomicIndicator> getEconomicIndicatorById(@PathVariable Long id) {
        EconomicIndicator indicator = economicDevelopmentService.getEconomicIndicatorById(id);
        if (indicator != null) {
            return ResponseEntity.ok(indicator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/indicators/type/{indicatorType}")
    public ResponseEntity<List<EconomicIndicator>> getEconomicIndicatorsByType(@PathVariable String indicatorType) {
        List<EconomicIndicator> indicators = economicDevelopmentService.getEconomicIndicatorsByType(indicatorType);
        return ResponseEntity.ok(indicators);
    }
    
    @GetMapping("/indicators/region/{region}")
    public ResponseEntity<List<EconomicIndicator>> getEconomicIndicatorsByRegion(@PathVariable String region) {
        List<EconomicIndicator> indicators = economicDevelopmentService.getEconomicIndicatorsByRegion(region);
        return ResponseEntity.ok(indicators);
    }
    
    @GetMapping("/indicators/year/{year}")
    public ResponseEntity<List<EconomicIndicator>> getEconomicIndicatorsByYear(@PathVariable Integer year) {
        List<EconomicIndicator> indicators = economicDevelopmentService.getEconomicIndicatorsByYear(year);
        return ResponseEntity.ok(indicators);
    }
    
    @GetMapping("/indicators/type/{indicatorType}/region/{region}")
    public ResponseEntity<List<EconomicIndicator>> getEconomicIndicatorsByTypeAndRegion(
            @PathVariable String indicatorType, 
            @PathVariable String region) {
        List<EconomicIndicator> indicators = economicDevelopmentService.getEconomicIndicatorsByTypeAndRegion(indicatorType, region);
        return ResponseEntity.ok(indicators);
    }
    
    @GetMapping("/indicators/type/{indicatorType}/region/{region}/year/{year}")
    public ResponseEntity<List<EconomicIndicator>> getEconomicIndicatorsByTypeAndRegionAndYear(
            @PathVariable String indicatorType, 
            @PathVariable String region, 
            @PathVariable Integer year) {
        List<EconomicIndicator> indicators = economicDevelopmentService.getEconomicIndicatorsByTypeAndRegionAndYear(indicatorType, region, year);
        return ResponseEntity.ok(indicators);
    }
    
    @GetMapping("/indicators/type/{indicatorType}/region/{region}/year-range")
    public ResponseEntity<List<EconomicIndicator>> getEconomicIndicatorsByTypeAndRegionAndYearRange(
            @PathVariable String indicatorType, 
            @PathVariable String region,
            @RequestParam Integer startYear,
            @RequestParam Integer endYear) {
        List<EconomicIndicator> indicators = economicDevelopmentService.getEconomicIndicatorsByTypeAndRegionAndYearRange(indicatorType, region, startYear, endYear);
        return ResponseEntity.ok(indicators);
    }
    
    @PutMapping("/indicators/{id}")
    public ResponseEntity<EconomicIndicator> updateEconomicIndicator(@PathVariable Long id, @RequestBody EconomicIndicator indicatorDetails) {
        EconomicIndicator updatedIndicator = economicDevelopmentService.updateEconomicIndicator(id, indicatorDetails);
        if (updatedIndicator != null) {
            return ResponseEntity.ok(updatedIndicator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/indicators/{id}")
    public ResponseEntity<Void> deleteEconomicIndicator(@PathVariable Long id) {
        economicDevelopmentService.deleteEconomicIndicator(id);
        return ResponseEntity.ok().build();
    }
    
    // Business Registration endpoints
    @PostMapping("/businesses")
    public ResponseEntity<BusinessRegistration> createBusinessRegistration(@RequestBody BusinessRegistration business) {
        BusinessRegistration createdBusiness = economicDevelopmentService.createBusinessRegistration(business);
        return ResponseEntity.ok(createdBusiness);
    }
    
    @GetMapping("/businesses")
    public ResponseEntity<List<BusinessRegistration>> getAllBusinessRegistrations() {
        List<BusinessRegistration> businesses = economicDevelopmentService.getAllBusinessRegistrations();
        return ResponseEntity.ok(businesses);
    }
    
    @GetMapping("/businesses/{id}")
    public ResponseEntity<BusinessRegistration> getBusinessRegistrationById(@PathVariable Long id) {
        BusinessRegistration business = economicDevelopmentService.getBusinessRegistrationById(id);
        if (business != null) {
            return ResponseEntity.ok(business);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/businesses/type/{businessType}")
    public ResponseEntity<List<BusinessRegistration>> getBusinessRegistrationsByType(@PathVariable String businessType) {
        List<BusinessRegistration> businesses = economicDevelopmentService.getBusinessRegistrationsByType(businessType);
        return ResponseEntity.ok(businesses);
    }
    
    @GetMapping("/businesses/industry/{industryCategory}")
    public ResponseEntity<List<BusinessRegistration>> getBusinessRegistrationsByIndustry(@PathVariable String industryCategory) {
        List<BusinessRegistration> businesses = economicDevelopmentService.getBusinessRegistrationsByIndustry(industryCategory);
        return ResponseEntity.ok(businesses);
    }
    
    @GetMapping("/businesses/status/{status}")
    public ResponseEntity<List<BusinessRegistration>> getBusinessRegistrationsByStatus(@PathVariable String status) {
        List<BusinessRegistration> businesses = economicDevelopmentService.getBusinessRegistrationsByStatus(status);
        return ResponseEntity.ok(businesses);
    }
    
    @GetMapping("/businesses/search")
    public ResponseEntity<List<BusinessRegistration>> searchBusinessRegistrationsByName(@RequestParam String businessName) {
        List<BusinessRegistration> businesses = economicDevelopmentService.searchBusinessRegistrationsByName(businessName);
        return ResponseEntity.ok(businesses);
    }
    
    @GetMapping("/businesses/date-range")
    public ResponseEntity<List<BusinessRegistration>> getBusinessRegistrationsByEstablishmentDateRange(
            @RequestParam java.time.LocalDateTime startDate,
            @RequestParam java.time.LocalDateTime endDate) {
        List<BusinessRegistration> businesses = economicDevelopmentService.getBusinessRegistrationsByEstablishmentDateRange(startDate, endDate);
        return ResponseEntity.ok(businesses);
    }
    
    @GetMapping("/businesses/capital-range")
    public ResponseEntity<List<BusinessRegistration>> getBusinessRegistrationsByRegisteredCapitalRange(
            @RequestParam java.math.BigDecimal minCapital,
            @RequestParam java.math.BigDecimal maxCapital) {
        List<BusinessRegistration> businesses = economicDevelopmentService.getBusinessRegistrationsByRegisteredCapitalRange(minCapital, maxCapital);
        return ResponseEntity.ok(businesses);
    }
    
    @PutMapping("/businesses/{id}")
    public ResponseEntity<BusinessRegistration> updateBusinessRegistration(@PathVariable Long id, @RequestBody BusinessRegistration businessDetails) {
        BusinessRegistration updatedBusiness = economicDevelopmentService.updateBusinessRegistration(id, businessDetails);
        if (updatedBusiness != null) {
            return ResponseEntity.ok(updatedBusiness);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/businesses/{id}")
    public ResponseEntity<Void> deleteBusinessRegistration(@PathVariable Long id) {
        economicDevelopmentService.deleteBusinessRegistration(id);
        return ResponseEntity.ok().build();
    }
    
    // Investment Project endpoints
    @PostMapping("/projects")
    public ResponseEntity<InvestmentProject> createInvestmentProject(@RequestBody InvestmentProject project) {
        InvestmentProject createdProject = economicDevelopmentService.createInvestmentProject(project);
        return ResponseEntity.ok(createdProject);
    }
    
    @GetMapping("/projects")
    public ResponseEntity<List<InvestmentProject>> getAllInvestmentProjects() {
        List<InvestmentProject> projects = economicDevelopmentService.getAllInvestmentProjects();
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/projects/{id}")
    public ResponseEntity<InvestmentProject> getInvestmentProjectById(@PathVariable Long id) {
        InvestmentProject project = economicDevelopmentService.getInvestmentProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/projects/type/{projectType}")
    public ResponseEntity<List<InvestmentProject>> getInvestmentProjectsByType(@PathVariable String projectType) {
        List<InvestmentProject> projects = economicDevelopmentService.getInvestmentProjectsByType(projectType);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/projects/investor/{investor}")
    public ResponseEntity<List<InvestmentProject>> getInvestmentProjectsByInvestor(@PathVariable String investor) {
        List<InvestmentProject> projects = economicDevelopmentService.getInvestmentProjectsByInvestor(investor);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/projects/status/{projectStatus}")
    public ResponseEntity<List<InvestmentProject>> getInvestmentProjectsByStatus(@PathVariable String projectStatus) {
        List<InvestmentProject> projects = economicDevelopmentService.getInvestmentProjectsByStatus(projectStatus);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/projects/start-date-range")
    public ResponseEntity<List<InvestmentProject>> getInvestmentProjectsByStartDateRange(
            @RequestParam java.time.LocalDateTime startDate,
            @RequestParam java.time.LocalDateTime endDate) {
        List<InvestmentProject> projects = economicDevelopmentService.getInvestmentProjectsByStartDateRange(startDate, endDate);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/projects/completion-date-range")
    public ResponseEntity<List<InvestmentProject>> getInvestmentProjectsByCompletionDateRange(
            @RequestParam java.time.LocalDateTime startDate,
            @RequestParam java.time.LocalDateTime endDate) {
        List<InvestmentProject> projects = economicDevelopmentService.getInvestmentProjectsByCompletionDateRange(startDate, endDate);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/projects/search")
    public ResponseEntity<List<InvestmentProject>> searchInvestmentProjectsByLocation(@RequestParam String location) {
        List<InvestmentProject> projects = economicDevelopmentService.searchInvestmentProjectsByLocation(location);
        return ResponseEntity.ok(projects);
    }
    
    @PutMapping("/projects/{id}")
    public ResponseEntity<InvestmentProject> updateInvestmentProject(@PathVariable Long id, @RequestBody InvestmentProject projectDetails) {
        InvestmentProject updatedProject = economicDevelopmentService.updateInvestmentProject(id, projectDetails);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteInvestmentProject(@PathVariable Long id) {
        economicDevelopmentService.deleteInvestmentProject(id);
        return ResponseEntity.ok().build();
    }
}