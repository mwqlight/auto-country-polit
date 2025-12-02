package com.smartcity.cockpit.service;

import com.smartcity.cockpit.entity.PredictionResult;
import com.smartcity.cockpit.entity.PolicyRecommendation;
import com.smartcity.cockpit.entity.AIModel;

import java.util.List;

public interface AIDecisionSupportService {
    
    // Prediction Result methods
    PredictionResult createPredictionResult(PredictionResult predictionResult);
    List<PredictionResult> getAllPredictionResults();
    PredictionResult getPredictionResultById(Long id);
    List<PredictionResult> getPredictionResultsByModelName(String modelName);
    List<PredictionResult> getPredictionResultsByPredictionType(String predictionType);
    List<PredictionResult> getPredictionResultsByDateRange(java.time.LocalDateTime startTime, java.time.LocalDateTime endTime);
    List<PredictionResult> getPredictionResultsByModelNameAndType(String modelName, String predictionType);
    List<PredictionResult> getPredictionResultsByTypeAndDateRangeOrderByConfidence(String predictionType, java.time.LocalDateTime startTime, java.time.LocalDateTime endTime);
    PredictionResult updatePredictionResult(Long id, PredictionResult predictionResultDetails);
    void deletePredictionResult(Long id);
    
    // Policy Recommendation methods
    PolicyRecommendation createPolicyRecommendation(PolicyRecommendation policyRecommendation);
    List<PolicyRecommendation> getAllPolicyRecommendations();
    PolicyRecommendation getPolicyRecommendationById(Long id);
    List<PolicyRecommendation> getPolicyRecommendationsByType(String recommendationType);
    List<PolicyRecommendation> getPolicyRecommendationsByPriority(String priorityLevel);
    List<PolicyRecommendation> getPolicyRecommendationsByDepartment(String department);
    List<PolicyRecommendation> getPolicyRecommendationsByStatus(String status);
    List<PolicyRecommendation> getPolicyRecommendationsByTypeAndPriority(String recommendationType, String priorityLevel);
    List<PolicyRecommendation> getPolicyRecommendationsByTypeAndStatus(String recommendationType, String status);
    PolicyRecommendation updatePolicyRecommendation(Long id, PolicyRecommendation policyRecommendationDetails);
    void deletePolicyRecommendation(Long id);
    
    // AI Model methods
    AIModel createAIModel(AIModel aiModel);
    List<AIModel> getAllAIModels();
    AIModel getAIModelById(Long id);
    List<AIModel> getAIModelsByModelName(String modelName);
    List<AIModel> getAIModelsByModelType(String modelType);
    List<AIModel> getAIModelsByDomainArea(String domainArea);
    List<AIModel> getAIModelsByStatus(String status);
    AIModel getAIModelByModelNameAndVersion(String modelName, String modelVersion);
    List<AIModel> getAIModelsByDomainAreaAndStatus(String domainArea, String status);
    AIModel updateAIModel(Long id, AIModel aiModelDetails);
    void deleteAIModel(Long id);
}