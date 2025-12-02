package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.BusinessRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRegistrationRepository extends JpaRepository<BusinessRegistration, Long> {
    
    List<BusinessRegistration> findByBusinessType(String businessType);
    
    List<BusinessRegistration> findByIndustryCategory(String industryCategory);
    
    List<BusinessRegistration> findByStatus(String status);
    
    List<BusinessRegistration> findByBusinessNameContaining(String businessName);
    
    @Query("SELECT br FROM BusinessRegistration br WHERE br.establishmentDate >= :startDate AND br.establishmentDate <= :endDate")
    List<BusinessRegistration> findByEstablishmentDateRange(
        @Param("startDate") java.time.LocalDateTime startDate,
        @Param("endDate") java.time.LocalDateTime endDate
    );
    
    @Query("SELECT br FROM BusinessRegistration br WHERE br.registeredCapital >= :minCapital AND br.registeredCapital <= :maxCapital")
    List<BusinessRegistration> findByRegisteredCapitalRange(
        @Param("minCapital") java.math.BigDecimal minCapital,
        @Param("maxCapital") java.math.BigDecimal maxCapital
    );
}