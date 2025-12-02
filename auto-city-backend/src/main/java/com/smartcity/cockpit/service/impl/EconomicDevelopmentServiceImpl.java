package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.entity.EconomicIndicator;
import com.smartcity.cockpit.entity.BusinessRegistration;
import com.smartcity.cockpit.entity.InvestmentProject;
import com.smartcity.cockpit.repository.EconomicIndicatorRepository;
import com.smartcity.cockpit.repository.BusinessRegistrationRepository;
import com.smartcity.cockpit.repository.InvestmentProjectRepository;
import com.smartcity.cockpit.service.EconomicDevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EconomicDevelopmentServiceImpl implements EconomicDevelopmentService {
    
    @Autowired
    private EconomicIndicatorRepository economicIndicatorRepository;
    
    @Autowired
    private BusinessRegistrationRepository businessRegistrationRepository;
    
    @Autowired
    private InvestmentProjectRepository investmentProjectRepository;
    
    // Economic Indicator implementations
    @Override
    public EconomicIndicator createEconomicIndicator(EconomicIndicator indicator) {
        return economicIndicatorRepository.save(indicator);
    }
    
    @Override
    public List<EconomicIndicator> getAllEconomicIndicators() {
        return economicIndicatorRepository.findAll();
    }
    
    @Override
    public EconomicIndicator getEconomicIndicatorById(Long id) {
        Optional<EconomicIndicator> indicator = economicIndicatorRepository.findById(id);
        return indicator.orElse(null);
    }
    
    @Override
    public List<EconomicIndicator> getEconomicIndicatorsByType(String indicatorType) {
        return economicIndicatorRepository.findByIndicatorType(indicatorType);
    }
    
    @Override
    public List<EconomicIndicator> getEconomicIndicatorsByRegion(String region) {
        return economicIndicatorRepository.findByRegion(region);
    }
    
    @Override
    public List<EconomicIndicator> getEconomicIndicatorsByYear(Integer year) {
        return economicIndicatorRepository.findByYear(year);
    }
    
    @Override
    public List<EconomicIndicator> getEconomicIndicatorsByTypeAndRegion(String indicatorType, String region) {
        return economicIndicatorRepository.findByIndicatorTypeAndRegion(indicatorType, region);
    }
    
    @Override
    public List<EconomicIndicator> getEconomicIndicatorsByTypeAndRegionAndYear(String indicatorType, String region, Integer year) {
        return economicIndicatorRepository.findByIndicatorTypeAndRegionAndYear(indicatorType, region, year);
    }
    
    @Override
    public List<EconomicIndicator> getEconomicIndicatorsByTypeAndRegionAndYearRange(String indicatorType, String region, Integer startYear, Integer endYear) {
        return economicIndicatorRepository.findByIndicatorTypeAndRegionAndYearRange(indicatorType, region, startYear, endYear);
    }
    
    @Override
    public EconomicIndicator updateEconomicIndicator(Long id, EconomicIndicator indicatorDetails) {
        Optional<EconomicIndicator> optionalIndicator = economicIndicatorRepository.findById(id);
        if (optionalIndicator.isPresent()) {
            EconomicIndicator indicator = optionalIndicator.get();
            indicator.setIndicatorType(indicatorDetails.getIndicatorType());
            indicator.setIndicatorName(indicatorDetails.getIndicatorName());
            indicator.setValue(indicatorDetails.getValue());
            indicator.setUnit(indicatorDetails.getUnit());
            indicator.setRegion(indicatorDetails.getRegion());
            indicator.setYear(indicatorDetails.getYear());
            indicator.setQuarter(indicatorDetails.getQuarter());
            indicator.setMonth(indicatorDetails.getMonth());
            indicator.setGrowthRate(indicatorDetails.getGrowthRate());
            indicator.setTrend(indicatorDetails.getTrend());
            return economicIndicatorRepository.save(indicator);
        }
        return null;
    }
    
    @Override
    public void deleteEconomicIndicator(Long id) {
        economicIndicatorRepository.deleteById(id);
    }
    
    // Business Registration implementations
    @Override
    public BusinessRegistration createBusinessRegistration(BusinessRegistration business) {
        return businessRegistrationRepository.save(business);
    }
    
    @Override
    public List<BusinessRegistration> getAllBusinessRegistrations() {
        return businessRegistrationRepository.findAll();
    }
    
    @Override
    public BusinessRegistration getBusinessRegistrationById(Long id) {
        Optional<BusinessRegistration> business = businessRegistrationRepository.findById(id);
        return business.orElse(null);
    }
    
    @Override
    public List<BusinessRegistration> getBusinessRegistrationsByType(String businessType) {
        return businessRegistrationRepository.findByBusinessType(businessType);
    }
    
    @Override
    public List<BusinessRegistration> getBusinessRegistrationsByIndustry(String industryCategory) {
        return businessRegistrationRepository.findByIndustryCategory(industryCategory);
    }
    
    @Override
    public List<BusinessRegistration> getBusinessRegistrationsByStatus(String status) {
        return businessRegistrationRepository.findByStatus(status);
    }
    
    @Override
    public List<BusinessRegistration> searchBusinessRegistrationsByName(String businessName) {
        return businessRegistrationRepository.findByBusinessNameContaining(businessName);
    }
    
    @Override
    public List<BusinessRegistration> getBusinessRegistrationsByEstablishmentDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate) {
        return businessRegistrationRepository.findByEstablishmentDateRange(startDate, endDate);
    }
    
    @Override
    public List<BusinessRegistration> getBusinessRegistrationsByRegisteredCapitalRange(java.math.BigDecimal minCapital, java.math.BigDecimal maxCapital) {
        return businessRegistrationRepository.findByRegisteredCapitalRange(minCapital, maxCapital);
    }
    
    @Override
    public BusinessRegistration updateBusinessRegistration(Long id, BusinessRegistration businessDetails) {
        Optional<BusinessRegistration> optionalBusiness = businessRegistrationRepository.findById(id);
        if (optionalBusiness.isPresent()) {
            BusinessRegistration business = optionalBusiness.get();
            business.setBusinessName(businessDetails.getBusinessName());
            business.setRegistrationNumber(businessDetails.getRegistrationNumber());
            business.setLegalRepresentative(businessDetails.getLegalRepresentative());
            business.setBusinessType(businessDetails.getBusinessType());
            business.setIndustryCategory(businessDetails.getIndustryCategory());
            business.setRegisteredAddress(businessDetails.getRegisteredAddress());
            business.setOperatingAddress(businessDetails.getOperatingAddress());
            business.setRegisteredCapital(businessDetails.getRegisteredCapital());
            business.setPaidInCapital(businessDetails.getPaidInCapital());
            business.setEmployeesCount(businessDetails.getEmployeesCount());
            business.setContactPerson(businessDetails.getContactPerson());
            business.setContactPhone(businessDetails.getContactPhone());
            business.setEmail(businessDetails.getEmail());
            business.setEstablishmentDate(businessDetails.getEstablishmentDate());
            business.setBusinessScope(businessDetails.getBusinessScope());
            business.setStatus(businessDetails.getStatus());
            business.setAnnualRevenue(businessDetails.getAnnualRevenue());
            business.setTaxPaid(businessDetails.getTaxPaid());
            return businessRegistrationRepository.save(business);
        }
        return null;
    }
    
    @Override
    public void deleteBusinessRegistration(Long id) {
        businessRegistrationRepository.deleteById(id);
    }
    
    // Investment Project implementations
    @Override
    public InvestmentProject createInvestmentProject(InvestmentProject project) {
        return investmentProjectRepository.save(project);
    }
    
    @Override
    public List<InvestmentProject> getAllInvestmentProjects() {
        return investmentProjectRepository.findAll();
    }
    
    @Override
    public InvestmentProject getInvestmentProjectById(Long id) {
        Optional<InvestmentProject> project = investmentProjectRepository.findById(id);
        return project.orElse(null);
    }
    
    @Override
    public List<InvestmentProject> getInvestmentProjectsByType(String projectType) {
        return investmentProjectRepository.findByProjectType(projectType);
    }
    
    @Override
    public List<InvestmentProject> getInvestmentProjectsByInvestor(String investor) {
        return investmentProjectRepository.findByInvestor(investor);
    }
    
    @Override
    public List<InvestmentProject> getInvestmentProjectsByStatus(String projectStatus) {
        return investmentProjectRepository.findByProjectStatus(projectStatus);
    }
    
    @Override
    public List<InvestmentProject> getInvestmentProjectsByStartDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate) {
        return investmentProjectRepository.findByPlannedStartDateBetween(startDate, endDate);
    }
    
    @Override
    public List<InvestmentProject> getInvestmentProjectsByCompletionDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate) {
        return investmentProjectRepository.findByPlannedCompletionDateBetween(startDate, endDate);
    }
    
    @Override
    public List<InvestmentProject> searchInvestmentProjectsByLocation(String location) {
        return investmentProjectRepository.findByLocationContaining(location);
    }
    
    @Override
    public InvestmentProject updateInvestmentProject(Long id, InvestmentProject projectDetails) {
        Optional<InvestmentProject> optionalProject = investmentProjectRepository.findById(id);
        if (optionalProject.isPresent()) {
            InvestmentProject project = optionalProject.get();
            project.setProjectName(projectDetails.getProjectName());
            project.setProjectType(projectDetails.getProjectType());
            project.setInvestor(projectDetails.getInvestor());
            project.setInvestmentAmount(projectDetails.getInvestmentAmount());
            project.setCurrency(projectDetails.getCurrency());
            project.setProjectDescription(projectDetails.getProjectDescription());
            project.setLocation(projectDetails.getLocation());
            project.setLatitude(projectDetails.getLatitude());
            project.setLongitude(projectDetails.getLongitude());
            project.setPlannedStartDate(projectDetails.getPlannedStartDate());
            project.setActualStartDate(projectDetails.getActualStartDate());
            project.setPlannedCompletionDate(projectDetails.getPlannedCompletionDate());
            project.setActualCompletionDate(projectDetails.getActualCompletionDate());
            project.setProjectStatus(projectDetails.getProjectStatus());
            project.setProgressPercentage(projectDetails.getProgressPercentage());
            project.setExpectedJobs(projectDetails.getExpectedJobs());
            project.setActualJobs(projectDetails.getActualJobs());
            project.setEnvironmentalImpact(projectDetails.getEnvironmentalImpact());
            return investmentProjectRepository.save(project);
        }
        return null;
    }
    
    @Override
    public void deleteInvestmentProject(Long id) {
        investmentProjectRepository.deleteById(id);
    }
}