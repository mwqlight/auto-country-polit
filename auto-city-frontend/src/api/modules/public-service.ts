import request from '@/utils/request'
import type { PublicService, ServiceRequest, CitizenFeedback } from '@/types/dashboard'

// 公共服务相关API
export const publicServiceApi = {
  // 获取公共服务列表
  getPublicServices(params?: any) {
    return request<PublicService[]>({
      url: '/api/v1/city/public-service/services/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取公共服务详情
  getPublicServiceById(id: number) {
    return request<PublicService>({
      url: `/api/v1/city/public-service/services/${id}`,
      method: 'get'
    })
  },

  // 创建公共服务
  createPublicService(data: Partial<PublicService>) {
    return request<PublicService>({
      url: '/api/v1/city/public-service/services',
      method: 'post',
      data
    })
  },

  // 更新公共服务状态
  updatePublicServiceStatus(id: number, status: string) {
    return request<PublicService>({
      url: `/api/v1/city/public-service/services/${id}/status`,
      method: 'put',
      data: { status }
    })
  },

  // 根据类型获取公共服务
  getPublicServicesByType(type: string) {
    return request<PublicService[]>({
      url: `/api/v1/city/public-service/services/type/${type}`,
      method: 'get'
    })
  },

  // 根据状态获取公共服务
  getPublicServicesByStatus(status: string) {
    return request<PublicService[]>({
      url: `/api/v1/city/public-service/services/status/${status}`,
      method: 'get'
    })
  }
}

// 服务请求相关API
export const serviceRequestApi = {
  // 获取服务请求列表
  getServiceRequests(params?: any) {
    return request<ServiceRequest[]>({
      url: '/api/v1/city/public-service/requests/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取服务请求详情
  getServiceRequestById(id: number) {
    return request<ServiceRequest>({
      url: `/api/v1/city/public-service/requests/${id}`,
      method: 'get'
    })
  },

  // 创建服务请求
  createServiceRequest(data: Partial<ServiceRequest>) {
    return request<ServiceRequest>({
      url: '/api/v1/city/public-service/requests',
      method: 'post',
      data
    })
  },

  // 更新服务请求状态
  updateServiceRequestStatus(id: number, status: string) {
    return request<ServiceRequest>({
      url: `/api/v1/city/public-service/requests/${id}/status`,
      method: 'put',
      data: { status }
    })
  },

  // 根据类型获取服务请求
  getServiceRequestsByType(type: string) {
    return request<ServiceRequest[]>({
      url: `/api/v1/city/public-service/requests/type/${type}`,
      method: 'get'
    })
  },

  // 根据状态获取服务请求
  getServiceRequestsByStatus(status: string) {
    return request<ServiceRequest[]>({
      url: `/api/v1/city/public-service/requests/status/${status}`,
      method: 'get'
    })
  }
}

// 市民反馈相关API
export const citizenFeedbackApi = {
  // 获取市民反馈列表
  getCitizenFeedbacks(params?: any) {
    return request<CitizenFeedback[]>({
      url: '/api/v1/city/public-service/feedback/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取市民反馈详情
  getCitizenFeedbackById(id: number) {
    return request<CitizenFeedback>({
      url: `/api/v1/city/public-service/feedback/${id}`,
      method: 'get'
    })
  },

  // 创建市民反馈
  createCitizenFeedback(data: Partial<CitizenFeedback>) {
    return request<CitizenFeedback>({
      url: '/api/v1/city/public-service/feedback',
      method: 'post',
      data
    })
  },

  // 回复市民反馈
  replyToCitizenFeedback(id: number, reply: string) {
    return request<CitizenFeedback>({
      url: `/api/v1/city/public-service/feedback/${id}/reply`,
      method: 'put',
      data: { reply }
    })
  },

  // 根据类型获取市民反馈
  getCitizenFeedbacksByType(type: string) {
    return request<CitizenFeedback[]>({
      url: `/api/v1/city/public-service/feedback/type/${type}`,
      method: 'get'
    })
  },

  // 获取未审核的市民反馈
  getUnreviewedCitizenFeedbacks() {
    return request<CitizenFeedback[]>({
      url: '/api/v1/city/public-service/feedback/unreviewed',
      method: 'get'
    })
  }
}