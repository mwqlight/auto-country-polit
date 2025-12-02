package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.PredictionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PredictionResultRepository extends JpaRepository<PredictionResult, Long> {
    
    List<PredictionResult> findByModelName(String modelName);
    
    List<PredictionResult> findByPredictionType(String predictionType);
    
    List<PredictionResult> findByPredictedAtBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    @Query("SELECT pr FROM PredictionResult pr WHERE pr.modelName = :modelName AND pr.predictionType = :predictionType ORDER BY pr.predictedAt DESC")
    List<PredictionResult> findByModelNameAndPredictionTypeOrderByPredictedAtDesc(
        @Param("modelName") String modelName, 
        @Param("predictionType") String predictionType
    );
    
    @Query("SELECT pr FROM PredictionResult pr WHERE pr.predictionType = :predictionType AND pr.predictedAt >= :startTime AND pr.predictedAt <= :endTime ORDER BY pr.confidenceScore DESC")
    List<PredictionResult> findByPredictionTypeAndPredictedAtRangeOrderByConfidenceScoreDesc(
        @Param("predictionType") String predictionType,
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime
    );
}