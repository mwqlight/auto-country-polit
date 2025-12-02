import request from '@/utils/request'
import type { PredictionResult, PolicyRecommendation, AIModel } from '@/types/dashboard'

// 预测结果相关API
export const predictionApi = {
  // 获取预测结果列表
  getPredictions(params?: any) {
    return request<PredictionResult[]>({
      url: '/api/ai-decision-support/predictions/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取预测结果详情
  getPredictionById(id: number) {
    return request<PredictionResult>({
      url: `/api/ai-decision-support/predictions/${id}`,
      method: 'get'
    })
  },

  // 创建预测结果
  createPrediction(data: Partial<PredictionResult>) {
    return request<PredictionResult>({
      url: '/api/ai-decision-support/predictions',
      method: 'post',
      data
    })
  },

  // 更新预测结果
  updatePrediction(id: number, data: Partial<PredictionResult>) {
    return request<PredictionResult>({
      url: `/api/ai-decision-support/predictions/${id}`,
      method: 'put',
      data
    })
  },

  // 删除预测结果
  deletePrediction(id: number) {
    return request<void>({
      url: `/api/ai-decision-support/predictions/${id}`,
      method: 'delete'
    })
  },

  // 根据模型名称获取预测结果
  getPredictionsByModelName(modelName: string) {
    return request<PredictionResult[]>({
      url: `/api/ai-decision-support/predictions/model/${modelName}`,
      method: 'get'
    })
  },

  // 根据预测类型获取预测结果
  getPredictionsByType(predictionType: string) {
    return request<PredictionResult[]>({
      url: `/api/ai-decision-support/predictions/type/${predictionType}`,
      method: 'get'
    })
  },

  // 获取指定时间段内的预测结果
  getPredictionsByDateRange(startTime: string, endTime: string) {
    return request<PredictionResult[]>({
      url: '/api/ai-decision-support/predictions/date-range',
      method: 'get',
      params: { startTime, endTime }
    })
  }
}

// 政策推荐相关API
export const policyRecommendationApi = {
  // 获取政策推荐列表
  getPolicyRecommendations(params?: any) {
    return request<PolicyRecommendation[]>({
      url: '/api/ai-decision-support/recommendations/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取政策推荐详情
  getPolicyRecommendationById(id: number) {
    return request<PolicyRecommendation>({
      url: `/api/ai-decision-support/recommendations/${id}`,
      method: 'get'
    })
  },

  // 创建政策推荐
  createPolicyRecommendation(data: Partial<PolicyRecommendation>) {
    return request<PolicyRecommendation>({
      url: '/api/ai-decision-support/recommendations',
      method: 'post',
      data
    })
  },

  // 更新政策推荐
  updatePolicyRecommendation(id: number, data: Partial<PolicyRecommendation>) {
    return request<PolicyRecommendation>({
      url: `/api/ai-decision-support/recommendations/${id}`,
      method: 'put',
      data
    })
  },

  // 删除政策推荐
  deletePolicyRecommendation(id: number) {
    return request<void>({
      url: `/api/ai-decision-support/recommendations/${id}`,
      method: 'delete'
    })
  },

  // 根据类型获取政策推荐
  getPolicyRecommendationsByType(recommendationType: string) {
    return request<PolicyRecommendation[]>({
      url: `/api/ai-decision-support/recommendations/type/${recommendationType}`,
      method: 'get'
    })
  },

  // 根据优先级获取政策推荐
  getPolicyRecommendationsByPriority(priorityLevel: string) {
    return request<PolicyRecommendation[]>({
      url: `/api/ai-decision-support/recommendations/priority/${priorityLevel}`,
      method: 'get'
    })
  },

  // 根据部门获取政策推荐
  getPolicyRecommendationsByDepartment(department: string) {
    return request<PolicyRecommendation[]>({
      url: `/api/ai-decision-support/recommendations/department/${department}`,
      method: 'get'
    })
  }
}

// AI模型相关API
export const aiModelApi = {
  // 获取AI模型列表
  getAIModels(params?: any) {
    return request<AIModel[]>({
      url: '/api/ai-decision-support/models/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取AI模型详情
  getAIModelById(id: number) {
    return request<AIModel>({
      url: `/api/ai-decision-support/models/${id}`,
      method: 'get'
    })
  },

  // 创建AI模型
  createAIModel(data: Partial<AIModel>) {
    return request<AIModel>({
      url: '/api/ai-decision-support/models',
      method: 'post',
      data
    })
  },

  // 更新AI模型
  updateAIModel(id: number, data: Partial<AIModel>) {
    return request<AIModel>({
      url: `/api/ai-decision-support/models/${id}`,
      method: 'put',
      data
    })
  },

  // 删除AI模型
  deleteAIModel(id: number) {
    return request<void>({
      url: `/api/ai-decision-support/models/${id}`,
      method: 'delete'
    })
  },

  // 根据模型名称获取AI模型
  getAIModelsByModelName(modelName: string) {
    return request<AIModel[]>({
      url: `/api/ai-decision-support/models/name/${modelName}`,
      method: 'get'
    })
  }
}