package com.smartcity.cockpit.controller;

import com.smartcity.cockpit.entity.PredictionResult;
import com.smartcity.cockpit.entity.PolicyRecommendation;
import com.smartcity.cockpit.entity.AIModel;
import com.smartcity.cockpit.service.AIDecisionSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai-decision-support")
@CrossOrigin(origins = "*")
public class AIDecisionSupportController {
    
    @Autowired
    private AIDecisionSupportService aiDecisionSupportService;
    
    // Prediction Result endpoints
    @PostMapping("/predictions")
    public ResponseEntity<PredictionResult> createPredictionResult(@RequestBody PredictionResult predictionResult) {
        PredictionResult createdPrediction = aiDecisionSupportService.createPredictionResult(predictionResult);
        return ResponseEntity.ok(createdPrediction);
    }
    
    @GetMapping("/predictions")
    public ResponseEntity<List<PredictionResult>> getAllPredictionResults() {
        List<PredictionResult> predictions = aiDecisionSupportService.getAllPredictionResults();
        return ResponseEntity.ok(predictions);
    }
    
    @GetMapping("/predictions/{id}")
    public ResponseEntity<PredictionResult> getPredictionResultById(@PathVariable Long id) {
        PredictionResult prediction = aiDecisionSupportService.getPredictionResultById(id);
        if (prediction != null) {
            return ResponseEntity.ok(prediction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/predictions/model/{modelName}")
    public ResponseEntity<List<PredictionResult>> getPredictionResultsByModelName(@PathVariable String modelName) {
        List<PredictionResult> predictions = aiDecisionSupportService.getPredictionResultsByModelName(modelName);
        return ResponseEntity.ok(predictions);
    }
    
    @GetMapping("/predictions/type/{predictionType}")
    public ResponseEntity<List<PredictionResult>> getPredictionResultsByPredictionType(@PathVariable String predictionType) {
        List<PredictionResult> predictions = aiDecisionSupportService.getPredictionResultsByPredictionType(predictionType);
        return ResponseEntity.ok(predictions);
    }
    
    @GetMapping("/predictions/date-range")
    public ResponseEntity<List<PredictionResult>> getPredictionResultsByDateRange(
            @RequestParam java.time.LocalDateTime startTime,
            @RequestParam java.time.LocalDateTime endTime) {
        List<PredictionResult> predictions = aiDecisionSupportService.getPredictionResultsByDateRange(startTime, endTime);
        return ResponseEntity.ok(predictions);
    }
    
    @GetMapping("/predictions/model/{modelName}/type/{predictionType}")
    public ResponseEntity<List<PredictionResult>> getPredictionResultsByModelNameAndType(
            @PathVariable String modelName,
            @PathVariable String predictionType) {
        List<PredictionResult> predictions = aiDecisionSupportService.getPredictionResultsByModelNameAndType(modelName, predictionType);
        return ResponseEntity.ok(predictions);
    }
    
    @GetMapping("/predictions/type/{predictionType}/date-range/confidence")
    public ResponseEntity<List<PredictionResult>> getPredictionResultsByTypeAndDateRangeOrderByConfidence(
            @PathVariable String predictionType,
            @RequestParam java.time.LocalDateTime startTime,
            @RequestParam java.time.LocalDateTime endTime) {
        List<PredictionResult> predictions = aiDecisionSupportService.getPredictionResultsByTypeAndDateRangeOrderByConfidence(predictionType, startTime, endTime);
        return ResponseEntity.ok(predictions);
    }
    
    @PutMapping("/predictions/{id}")
    public ResponseEntity<PredictionResult> updatePredictionResult(@PathVariable Long id, @RequestBody PredictionResult predictionResultDetails) {
        PredictionResult updatedPrediction = aiDecisionSupportService.updatePredictionResult(id, predictionResultDetails);
        if (updatedPrediction != null) {
            return ResponseEntity.ok(updatedPrediction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/predictions/{id}")
    public ResponseEntity<Void> deletePredictionResult(@PathVariable Long id) {
        aiDecisionSupportService.deletePredictionResult(id);
        return ResponseEntity.ok().build();
    }
    
    // Policy Recommendation endpoints
    @PostMapping("/recommendations")
    public ResponseEntity<PolicyRecommendation> createPolicyRecommendation(@RequestBody PolicyRecommendation policyRecommendation) {
        PolicyRecommendation createdRecommendation = aiDecisionSupportService.createPolicyRecommendation(policyRecommendation);
        return ResponseEntity.ok(createdRecommendation);
    }
    
    @GetMapping("/recommendations")
    public ResponseEntity<List<PolicyRecommendation>> getAllPolicyRecommendations() {
        List<PolicyRecommendation> recommendations = aiDecisionSupportService.getAllPolicyRecommendations();
        return ResponseEntity.ok(recommendations);
    }
    
    @GetMapping("/recommendations/{id}")
    public ResponseEntity<PolicyRecommendation> getPolicyRecommendationById(@PathVariable Long id) {
        PolicyRecommendation recommendation = aiDecisionSupportService.getPolicyRecommendationById(id);
        if (recommendation != null) {
            return ResponseEntity.ok(recommendation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/recommendations/type/{recommendationType}")
    public ResponseEntity<List<PolicyRecommendation>> getPolicyRecommendationsByType(@PathVariable String recommendationType) {
        List<PolicyRecommendation> recommendations = aiDecisionSupportService.getPolicyRecommendationsByType(recommendationType);
        return ResponseEntity.ok(recommendations);
    }
    
    @GetMapping("/recommendations/priority/{priorityLevel}")
    public ResponseEntity<List<PolicyRecommendation>> getPolicyRecommendationsByPriority(@PathVariable String priorityLevel) {
        List<PolicyRecommendation> recommendations = aiDecisionSupportService.getPolicyRecommendationsByPriority(priorityLevel);
        return ResponseEntity.ok(recommendations);
    }
    
    @GetMapping("/recommendations/department/{department}")
    public ResponseEntity<List<PolicyRecommendation>> getPolicyRecommendationsByDepartment(@PathVariable String department) {
        List<PolicyRecommendation> recommendations = aiDecisionSupportService.getPolicyRecommendationsByDepartment(department);
        return ResponseEntity.ok(recommendations);
    }
    
    @GetMapping("/recommendations/status/{status}")
    public ResponseEntity<List<PolicyRecommendation>> getPolicyRecommendationsByStatus(@PathVariable String status) {
        List<PolicyRecommendation> recommendations = aiDecisionSupportService.getPolicyRecommendationsByStatus(status);
        return ResponseEntity.ok(recommendations);
    }
    
    @GetMapping("/recommendations/type/{recommendationType}/priority/{priorityLevel}")
    public ResponseEntity<List<PolicyRecommendation>> getPolicyRecommendationsByTypeAndPriority(
            @PathVariable String recommendationType,
            @PathVariable String priorityLevel) {
        List<PolicyRecommendation> recommendations = aiDecisionSupportService.getPolicyRecommendationsByTypeAndPriority(recommendationType, priorityLevel);
        return ResponseEntity.ok(recommendations);
    }
    
    @GetMapping("/recommendations/type/{recommendationType}/status/{status}")
    public ResponseEntity<List<PolicyRecommendation>> getPolicyRecommendationsByTypeAndStatus(
            @PathVariable String recommendationType,
            @PathVariable String status) {
        List<PolicyRecommendation> recommendations = aiDecisionSupportService.getPolicyRecommendationsByTypeAndStatus(recommendationType, status);
        return ResponseEntity.ok(recommendations);
    }
    
    @PutMapping("/recommendations/{id}")
    public ResponseEntity<PolicyRecommendation> updatePolicyRecommendation(@PathVariable Long id, @RequestBody PolicyRecommendation policyRecommendationDetails) {
        PolicyRecommendation updatedRecommendation = aiDecisionSupportService.updatePolicyRecommendation(id, policyRecommendationDetails);
        if (updatedRecommendation != null) {
            return ResponseEntity.ok(updatedRecommendation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/recommendations/{id}")
    public ResponseEntity<Void> deletePolicyRecommendation(@PathVariable Long id) {
        aiDecisionSupportService.deletePolicyRecommendation(id);
        return ResponseEntity.ok().build();
    }
    
    // AI Model endpoints
    @PostMapping("/models")
    public ResponseEntity<AIModel> createAIModel(@RequestBody AIModel aiModel) {
        AIModel createdModel = aiDecisionSupportService.createAIModel(aiModel);
        return ResponseEntity.ok(createdModel);
    }
    
    @GetMapping("/models")
    public ResponseEntity<List<AIModel>> getAllAIModels() {
        List<AIModel> models = aiDecisionSupportService.getAllAIModels();
        return ResponseEntity.ok(models);
    }
    
    @GetMapping("/models/{id}")
    public ResponseEntity<AIModel> getAIModelById(@PathVariable Long id) {
        AIModel model = aiDecisionSupportService.getAIModelById(id);
        if (model != null) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/models/name/{modelName}")
    public ResponseEntity<List<AIModel>> getAIModelsByModelName(@PathVariable String modelName) {
        List<AIModel> models = aiDecisionSupportService.getAIModelsByModelName(modelName);
        return ResponseEntity.ok(models);
    }
    
    @GetMapping("/models/type/{modelType}")
    public ResponseEntity<List<AIModel>> getAIModelsByModelType(@PathVariable String modelType) {
        List<AIModel> models = aiDecisionSupportService.getAIModelsByModelType(modelType);
        return ResponseEntity.ok(models);
    }
    
    @GetMapping("/models/domain/{domainArea}")
    public ResponseEntity<List<AIModel>> getAIModelsByDomainArea(@PathVariable String domainArea) {
        List<AIModel> models = aiDecisionSupportService.getAIModelsByDomainArea(domainArea);
        return ResponseEntity.ok(models);
    }
    
    @GetMapping("/models/status/{status}")
    public ResponseEntity<List<AIModel>> getAIModelsByStatus(@PathVariable String status) {
        List<AIModel> models = aiDecisionSupportService.getAIModelsByStatus(status);
        return ResponseEntity.ok(models);
    }
    
    @GetMapping("/models/name/{modelName}/version/{modelVersion}")
    public ResponseEntity<AIModel> getAIModelByModelNameAndVersion(
            @PathVariable String modelName,
            @PathVariable String modelVersion) {
        AIModel model = aiDecisionSupportService.getAIModelByModelNameAndVersion(modelName, modelVersion);
        if (model != null) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/models/domain/{domainArea}/status/{status}")
    public ResponseEntity<List<AIModel>> getAIModelsByDomainAreaAndStatus(
            @PathVariable String domainArea,
            @PathVariable String status) {
        List<AIModel> models = aiDecisionSupportService.getAIModelsByDomainAreaAndStatus(domainArea, status);
        return ResponseEntity.ok(models);
    }
    
    @PutMapping("/models/{id}")
    public ResponseEntity<AIModel> updateAIModel(@PathVariable Long id, @RequestBody AIModel aiModelDetails) {
        AIModel updatedModel = aiDecisionSupportService.updateAIModel(id, aiModelDetails);
        if (updatedModel != null) {
            return ResponseEntity.ok(updatedModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/models/{id}")
    public ResponseEntity<Void> deleteAIModel(@PathVariable Long id) {
        aiDecisionSupportService.deleteAIModel(id);
        return ResponseEntity.ok().build();
    }
}