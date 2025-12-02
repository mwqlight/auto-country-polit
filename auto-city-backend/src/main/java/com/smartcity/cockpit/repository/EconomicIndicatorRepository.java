package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.EconomicIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EconomicIndicatorRepository extends JpaRepository<EconomicIndicator, Long> {
    
    List<EconomicIndicator> findByIndicatorType(String indicatorType);
    
    List<EconomicIndicator> findByRegion(String region);
    
    List<EconomicIndicator> findByYear(Integer year);
    
    List<EconomicIndicator> findByIndicatorTypeAndRegion(String indicatorType, String region);
    
    @Query("SELECT ei FROM EconomicIndicator ei WHERE ei.indicatorType = :indicatorType AND ei.region = :region AND ei.year = :year ORDER BY ei.recordedTime DESC")
    List<EconomicIndicator> findByIndicatorTypeAndRegionAndYear(
        @Param("indicatorType") String indicatorType,
        @Param("region") String region,
        @Param("year") Integer year
    );
    
    @Query("SELECT ei FROM EconomicIndicator ei WHERE ei.indicatorType = :indicatorType AND ei.region = :region AND ei.year >= :startYear AND ei.year <= :endYear ORDER BY ei.year, ei.quarter, ei.month")
    List<EconomicIndicator> findByIndicatorTypeAndRegionAndYearRange(
        @Param("indicatorType") String indicatorType,
        @Param("region") String region,
        @Param("startYear") Integer startYear,
        @Param("endYear") Integer endYear
    );
}