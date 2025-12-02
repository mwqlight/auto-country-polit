package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.InvestmentProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvestmentProjectRepository extends JpaRepository<InvestmentProject, Long> {
    
    List<InvestmentProject> findByProjectType(String projectType);
    
    List<InvestmentProject> findByInvestor(String investor);
    
    List<InvestmentProject> findByProjectStatus(String projectStatus);
    
    List<InvestmentProject> findByPlannedStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<InvestmentProject> findByPlannedCompletionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<InvestmentProject> findByLocationContaining(String location);
}