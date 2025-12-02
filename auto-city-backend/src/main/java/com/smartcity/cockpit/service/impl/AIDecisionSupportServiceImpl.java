package com.smartcity.cockpit.service.impl;

import com.smartcity.cockpit.entity.PredictionResult;
import com.smartcity.cockpit.entity.PolicyRecommendation;
import com.smartcity.cockpit.entity.AIModel;
import com.smartcity.cockpit.repository.PredictionResultRepository;
import com.smartcity.cockpit.repository.PolicyRecommendationRepository;
import com.smartcity.cockpit.repository.AIModelRepository;
import com.smartcity.cockpit.service.AIDecisionSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AIDecisionSupportServiceImpl implements AIDecisionSupportService {
    
    @Autowired
    private PredictionResultRepository predictionResultRepository;
    
    @Autowired
    private PolicyRecommendationRepository policyRecommendationRepository;
    
    @Autowired
    private AIModelRepository aiModelRepository;
    
    // Prediction Result implementations
    @Override
    public PredictionResult createPredictionResult(PredictionResult predictionResult) {
        return predictionResultRepository.save(predictionResult);
    }
    
    @Override
    public List<PredictionResult> getAllPredictionResults() {
        return predictionResultRepository.findAll();
    }
    
    @Override
    public PredictionResult getPredictionResultById(Long id) {
        Optional<PredictionResult> predictionResult = predictionResultRepository.findById(id);
        return predictionResult.orElse(null);
    }
    
    @Override
    public List<PredictionResult> getPredictionResultsByModelName(String modelName) {
        return predictionResultRepository.findByModelName(modelName);
    }
    
    @Override
    public List<PredictionResult> getPredictionResultsByPredictionType(String predictionType) {
        return predictionResultRepository.findByPredictionType(predictionType);
    }
    
    @Override
    public List<PredictionResult> getPredictionResultsByDateRange(java.time.LocalDateTime startTime, java.time.LocalDateTime endTime) {
        return predictionResultRepository.findByPredictedAtBetween(startTime, endTime);
    }
    
    @Override
    public List<PredictionResult> getPredictionResultsByModelNameAndType(String modelName, String predictionType) {
        return predictionResultRepository.findByModelNameAndPredictionTypeOrderByPredictedAtDesc(modelName, predictionType);
    }
    
    @Override
    public List<PredictionResult> getPredictionResultsByTypeAndDateRangeOrderByConfidence(String predictionType, java.time.LocalDateTime startTime, java.time.LocalDateTime endTime) {
        return predictionResultRepository.findByPredictionTypeAndPredictedAtRangeOrderByConfidenceScoreDesc(predictionType, startTime, endTime);
    }
    
    @Override
    public PredictionResult updatePredictionResult(Long id, PredictionResult predictionResultDetails) {
        Optional<PredictionResult> optionalPredictionResult = predictionResultRepository.findById(id);
        if (optionalPredictionResult.isPresent()) {
            PredictionResult predictionResult = optionalPredictionResult.get();
            predictionResult.setModelName(predictionResultDetails.getModelName());
            predictionResult.setPredictionType(predictionResultDetails.getPredictionType());
            predictionResult.setInputData(predictionResultDetails.getInputData());
            predictionResult.setPredictionResult(predictionResultDetails.getPredictionResult());
            predictionResult.setConfidenceScore(predictionResultDetails.getConfidenceScore());
            predictionResult.setPredictedAt(predictionResultDetails.getPredictedAt());
            return predictionResultRepository.save(predictionResult);
        }
        return null;
    }
    
    @Override
    public void deletePredictionResult(Long id) {
        predictionResultRepository.deleteById(id);
    }
    
    // Policy Recommendation implementations
    @Override
    public PolicyRecommendation createPolicyRecommendation(PolicyRecommendation policyRecommendation) {
        return policyRecommendationRepository.save(policyRecommendation);
    }
    
    @Override
    public List<PolicyRecommendation> getAllPolicyRecommendations() {
        return policyRecommendationRepository.findAll();
    }
    
    @Override
    public PolicyRecommendation getPolicyRecommendationById(Long id) {
        Optional<PolicyRecommendation> policyRecommendation = policyRecommendationRepository.findById(id);
        return policyRecommendation.orElse(null);
    }
    
    @Override
    public List<PolicyRecommendation> getPolicyRecommendationsByType(String recommendationType) {
        return policyRecommendationRepository.findByRecommendationType(recommendationType);
    }
    
    @Override
    public List<PolicyRecommendation> getPolicyRecommendationsByPriority(String priorityLevel) {
        return policyRecommendationRepository.findByPriorityLevel(priorityLevel);
    }
    
    @Override
    public List<PolicyRecommendation> getPolicyRecommendationsByDepartment(String department) {
        return policyRecommendationRepository.findByRelatedDepartment(department);
    }
    
    @Override
    public List<PolicyRecommendation> getPolicyRecommendationsByStatus(String status) {
        return policyRecommendationRepository.findByStatus(status);
    }
    
    @Override
    public List<PolicyRecommendation> getPolicyRecommendationsByTypeAndPriority(String recommendationType, String priorityLevel) {
        return policyRecommendationRepository.findByRecommendationTypeAndPriorityLevel(recommendationType, priorityLevel);
    }
    
    @Override
    public List<PolicyRecommendation> getPolicyRecommendationsByTypeAndStatus(String recommendationType, String status) {
        return policyRecommendationRepository.findByRecommendationTypeAndStatus(recommendationType, status);
    }
    
    @Override
    public PolicyRecommendation updatePolicyRecommendation(Long id, PolicyRecommendation policyRecommendationDetails) {
        Optional<PolicyRecommendation> optionalPolicyRecommendation = policyRecommendationRepository.findById(id);
        if (optionalPolicyRecommendation.isPresent()) {
            PolicyRecommendation policyRecommendation = optionalPolicyRecommendation.get();
            policyRecommendation.setRecommendationTitle(policyRecommendationDetails.getRecommendationTitle());
            policyRecommendation.setRecommendationContent(policyRecommendationDetails.getRecommendationContent());
            policyRecommendation.setRecommendationType(policyRecommendationDetails.getRecommendationType());
            policyRecommendation.setPriorityLevel(policyRecommendationDetails.getPriorityLevel());
            policyRecommendation.setRelatedDepartment(policyRecommendationDetails.getRelatedDepartment());
            policyRecommendation.setImplementationCost(policyRecommendationDetails.getImplementationCost());
            policyRecommendation.setExpectedEffect(policyRecommendationDetails.getExpectedEffect());
            policyRecommendation.setBasedOnPredictionId(policyRecommendationDetails.getBasedOnPredictionId());
            policyRecommendation.setStatus(policyRecommendationDetails.getStatus());
            return policyRecommendationRepository.save(policyRecommendation);
        }
        return null;
    }
    
    @Override
    public void deletePolicyRecommendation(Long id) {
        policyRecommendationRepository.deleteById(id);
    }
    
    // AI Model implementations
    @Override
    public AIModel createAIModel(AIModel aiModel) {
        return aiModelRepository.save(aiModel);
    }
    
    @Override
    public List<AIModel> getAllAIModels() {
        return aiModelRepository.findAll();
    }
    
    @Override
    public AIModel getAIModelById(Long id) {
        Optional<AIModel> aiModel = aiModelRepository.findById(id);
        return aiModel.orElse(null);
    }
    
    @Override
    public List<AIModel> getAIModelsByModelName(String modelName) {
        return aiModelRepository.findByModelName(modelName);
    }
    
    @Override
    public List<AIModel> getAIModelsByModelType(String modelType) {
        return aiModelRepository.findByModelType(modelType);
    }
    
    @Override
    public List<AIModel> getAIModelsByDomainArea(String domainArea) {
        return aiModelRepository.findByDomainArea(domainArea);
    }
    
    @Override
    public List<AIModel> getAIModelsByStatus(String status) {
        return aiModelRepository.findByStatus(status);
    }
    
    @Override
    public AIModel getAIModelByModelNameAndVersion(String modelName, String modelVersion) {
        Optional<AIModel> aiModel = aiModelRepository.findByModelNameAndModelVersion(modelName, modelVersion);
        return aiModel.orElse(null);
    }
    
    @Override
    public List<AIModel> getAIModelsByDomainAreaAndStatus(String domainArea, String status) {
        return aiModelRepository.findByDomainAreaAndStatus(domainArea, status);
    }
    
    @Override
    public AIModel updateAIModel(Long id, AIModel aiModelDetails) {
        Optional<AIModel> optionalAIModel = aiModelRepository.findById(id);
        if (optionalAIModel.isPresent()) {
            AIModel aiModel = optionalAIModel.get();
            aiModel.setModelName(aiModelDetails.getModelName());
            aiModel.setModelVersion(aiModelDetails.getModelVersion());
            aiModel.setModelType(aiModelDetails.getModelType());
            aiModel.setDomainArea(aiModelDetails.getDomainArea());
            aiModel.setAlgorithmName(aiModelDetails.getAlgorithmName());
            aiModel.setTrainingDataSource(aiModelDetails.getTrainingDataSource());
            aiModel.setModelAccuracy(aiModelDetails.getModelAccuracy());
            aiModel.setLastTrainingTime(aiModelDetails.getLastTrainingTime());
            aiModel.setModelFilePath(aiModelDetails.getModelFilePath());
            aiModel.setModelParameters(aiModelDetails.getModelParameters());
            aiModel.setPerformanceMetrics(aiModelDetails.getPerformanceMetrics());
            aiModel.setStatus(aiModelDetails.getStatus());
            return aiModelRepository.save(aiModel);
        }
        return null;
    }
    
    @Override
    public void deleteAIModel(Long id) {
        aiModelRepository.deleteById(id);
    }
}