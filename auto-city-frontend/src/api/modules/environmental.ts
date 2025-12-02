import request from '@/utils/request'
import type { EnvironmentalData, PollutionSource, EnvironmentalAlert } from '@/types/dashboard'

// 环境数据相关API
export const environmentalApi = {
  // 获取环境数据列表
  getEnvironmentalData(params?: any) {
    return request<EnvironmentalData[]>({
      url: '/api/v1/city/environment/data/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取环境数据详情
  getEnvironmentalDataById(id: number) {
    return request<EnvironmentalData>({
      url: `/api/v1/city/environment/data/${id}`,
      method: 'get'
    })
  },

  // 创建环境数据
  createEnvironmentalData(data: Partial<EnvironmentalData>) {
    return request<EnvironmentalData>({
      url: '/api/v1/city/environment/data',
      method: 'post',
      data
    })
  },

  // 更新环境数据
  updateEnvironmentalData(id: number, data: Partial<EnvironmentalData>) {
    return request<EnvironmentalData>({
      url: `/api/v1/city/environment/data/${id}`,
      method: 'put',
      data
    })
  },

  // 删除环境数据
  deleteEnvironmentalData(id: number) {
    return request<void>({
      url: `/api/v1/city/environment/data/${id}`,
      method: 'delete'
    })
  },

  // 获取最新的环境数据
  getLatestEnvironmentalData(type: string) {
    return request<EnvironmentalData>({
      url: `/api/v1/city/environment/data/latest/${type}`,
      method: 'get'
    })
  }
}

// 污染源相关API
export const pollutionSourceApi = {
  // 获取污染源列表
  getPollutionSources(params?: any) {
    return request<PollutionSource[]>({
      url: '/api/v1/city/environment/pollution/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取污染源详情
  getPollutionSourceById(id: number) {
    return request<PollutionSource>({
      url: `/api/v1/city/environment/pollution/${id}`,
      method: 'get'
    })
  },

  // 创建污染源
  createPollutionSource(data: Partial<PollutionSource>) {
    return request<PollutionSource>({
      url: '/api/v1/city/environment/pollution',
      method: 'post',
      data
    })
  },

  // 更新污染源状态
  updatePollutionSourceStatus(id: number, status: string) {
    return request<PollutionSource>({
      url: `/api/v1/city/environment/pollution/${id}/status`,
      method: 'put',
      data: { status }
    })
  },

  // 根据类型获取污染源
  getPollutionSourcesByType(type: string) {
    return request<PollutionSource[]>({
      url: `/api/v1/city/environment/pollution/type/${type}`,
      method: 'get'
    })
  }
}

// 环境警报相关API
export const environmentalAlertApi = {
  // 获取环境警报列表
  getEnvironmentalAlerts(params?: any) {
    return request<EnvironmentalAlert[]>({
      url: '/api/v1/city/environment/alerts/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取环境警报详情
  getEnvironmentalAlertById(id: number) {
    return request<EnvironmentalAlert>({
      url: `/api/v1/city/environment/alerts/${id}`,
      method: 'get'
    })
  },

  // 创建环境警报
  createEnvironmentalAlert(data: Partial<EnvironmentalAlert>) {
    return request<EnvironmentalAlert>({
      url: '/api/v1/city/environment/alerts',
      method: 'post',
      data
    })
  },

  // 获取未解决的环境警报
  getUnresolvedEnvironmentalAlerts() {
    return request<EnvironmentalAlert[]>({
      url: '/api/v1/city/environment/alerts/unresolved',
      method: 'get'
    })
  },

  // 解决环境警报
  resolveEnvironmentalAlert(id: number) {
    return request<void>({
      url: `/api/v1/city/environment/alerts/${id}/resolve`,
      method: 'put'
    })
  }
}