package com.smartcity.cockpit.service;

import com.smartcity.cockpit.entity.EconomicIndicator;
import com.smartcity.cockpit.entity.BusinessRegistration;
import com.smartcity.cockpit.entity.InvestmentProject;

import java.util.List;

public interface EconomicDevelopmentService {
    
    // Economic Indicator methods
    EconomicIndicator createEconomicIndicator(EconomicIndicator indicator);
    List<EconomicIndicator> getAllEconomicIndicators();
    EconomicIndicator getEconomicIndicatorById(Long id);
    List<EconomicIndicator> getEconomicIndicatorsByType(String indicatorType);
    List<EconomicIndicator> getEconomicIndicatorsByRegion(String region);
    List<EconomicIndicator> getEconomicIndicatorsByYear(Integer year);
    List<EconomicIndicator> getEconomicIndicatorsByTypeAndRegion(String indicatorType, String region);
    List<EconomicIndicator> getEconomicIndicatorsByTypeAndRegionAndYear(String indicatorType, String region, Integer year);
    List<EconomicIndicator> getEconomicIndicatorsByTypeAndRegionAndYearRange(String indicatorType, String region, Integer startYear, Integer endYear);
    EconomicIndicator updateEconomicIndicator(Long id, EconomicIndicator indicatorDetails);
    void deleteEconomicIndicator(Long id);
    
    // Business Registration methods
    BusinessRegistration createBusinessRegistration(BusinessRegistration business);
    List<BusinessRegistration> getAllBusinessRegistrations();
    BusinessRegistration getBusinessRegistrationById(Long id);
    List<BusinessRegistration> getBusinessRegistrationsByType(String businessType);
    List<BusinessRegistration> getBusinessRegistrationsByIndustry(String industryCategory);
    List<BusinessRegistration> getBusinessRegistrationsByStatus(String status);
    List<BusinessRegistration> searchBusinessRegistrationsByName(String businessName);
    List<BusinessRegistration> getBusinessRegistrationsByEstablishmentDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    List<BusinessRegistration> getBusinessRegistrationsByRegisteredCapitalRange(java.math.BigDecimal minCapital, java.math.BigDecimal maxCapital);
    BusinessRegistration updateBusinessRegistration(Long id, BusinessRegistration businessDetails);
    void deleteBusinessRegistration(Long id);
    
    // Investment Project methods
    InvestmentProject createInvestmentProject(InvestmentProject project);
    List<InvestmentProject> getAllInvestmentProjects();
    InvestmentProject getInvestmentProjectById(Long id);
    List<InvestmentProject> getInvestmentProjectsByType(String projectType);
    List<InvestmentProject> getInvestmentProjectsByInvestor(String investor);
    List<InvestmentProject> getInvestmentProjectsByStatus(String projectStatus);
    List<InvestmentProject> getInvestmentProjectsByStartDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    List<InvestmentProject> getInvestmentProjectsByCompletionDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    List<InvestmentProject> searchInvestmentProjectsByLocation(String location);
    InvestmentProject updateInvestmentProject(Long id, InvestmentProject projectDetails);
    void deleteInvestmentProject(Long id);
}