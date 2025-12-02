import request from '@/utils/request'
import type { EmergencyEvent, SafetyInspection, SafetyCamera } from '@/types/dashboard'

// 紧急事件相关API
export const emergencyEventApi = {
  // 获取紧急事件列表
  getEmergencyEvents(params?: any) {
    return request<EmergencyEvent[]>({
      url: '/api/v1/city/safety/emergency-events/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取紧急事件详情
  getEmergencyEventById(id: number) {
    return request<EmergencyEvent>({
      url: `/api/v1/city/safety/emergency-events/${id}`,
      method: 'get'
    })
  },

  // 创建紧急事件
  createEmergencyEvent(data: Partial<EmergencyEvent>) {
    return request<EmergencyEvent>({
      url: '/api/v1/city/safety/emergency-events',
      method: 'post',
      data
    })
  },

  // 更新紧急事件
  updateEmergencyEvent(id: number, data: Partial<EmergencyEvent>) {
    return request<EmergencyEvent>({
      url: `/api/v1/city/safety/emergency-events/${id}`,
      method: 'put',
      data
    })
  },

  // 删除紧急事件
  deleteEmergencyEvent(id: number) {
    return request<void>({
      url: `/api/v1/city/safety/emergency-events/${id}`,
      method: 'delete'
    })
  },

  // 根据类型获取紧急事件
  getEmergencyEventsByType(type: string) {
    return request<EmergencyEvent[]>({
      url: `/api/v1/city/safety/emergency-events/type/${type}`,
      method: 'get'
    })
  },

  // 根据严重程度获取紧急事件
  getEmergencyEventsBySeverity(severity: string) {
    return request<EmergencyEvent[]>({
      url: `/api/v1/city/safety/emergency-events/severity/${severity}`,
      method: 'get'
    })
  },

  // 获取未解决的紧急事件
  getUnresolvedEmergencyEvents() {
    return request<EmergencyEvent[]>({
      url: '/api/v1/city/safety/emergency-events/unresolved',
      method: 'get'
    })
  },

  // 解决紧急事件
  resolveEmergencyEvent(id: number) {
    return request<void>({
      url: `/api/v1/city/safety/emergency-events/${id}/resolve`,
      method: 'put'
    })
  }
}

// 安全检查相关API
export const safetyInspectionApi = {
  // 获取安全检查列表
  getSafetyInspections(params?: any) {
    return request<SafetyInspection[]>({
      url: '/api/v1/city/safety/inspections/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取安全检查详情
  getSafetyInspectionById(id: number) {
    return request<SafetyInspection>({
      url: `/api/v1/city/safety/inspections/${id}`,
      method: 'get'
    })
  },

  // 创建安全检查
  createSafetyInspection(data: Partial<SafetyInspection>) {
    return request<SafetyInspection>({
      url: '/api/v1/city/safety/inspections',
      method: 'post',
      data
    })
  },

  // 更新安全检查
  updateSafetyInspection(id: number, data: Partial<SafetyInspection>) {
    return request<SafetyInspection>({
      url: `/api/v1/city/safety/inspections/${id}`,
      method: 'put',
      data
    })
  },

  // 删除安全检查
  deleteSafetyInspection(id: number) {
    return request<void>({
      url: `/api/v1/city/safety/inspections/${id}`,
      method: 'delete'
    })
  },

  // 根据设施类型获取安全检查
  getSafetyInspectionsByFacilityType(facilityType: string) {
    return request<SafetyInspection[]>({
      url: `/api/v1/city/safety/inspections/facility-type/${facilityType}`,
      method: 'get'
    })
  },

  // 获取逾期的安全检查
  getOverdueSafetyInspections() {
    return request<SafetyInspection[]>({
      url: '/api/v1/city/safety/inspections/overdue',
      method: 'get'
    })
  }
}

// 安全摄像头相关API
export const safetyCameraApi = {
  // 获取安全摄像头列表
  getSafetyCameras(params?: any) {
    return request<SafetyCamera[]>({
      url: '/api/v1/city/safety/cameras/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取安全摄像头详情
  getSafetyCameraById(id: number) {
    return request<SafetyCamera>({
      url: `/api/v1/city/safety/cameras/${id}`,
      method: 'get'
    })
  },

  // 创建安全摄像头
  createSafetyCamera(data: Partial<SafetyCamera>) {
    return request<SafetyCamera>({
      url: '/api/v1/city/safety/cameras',
      method: 'post',
      data
    })
  },

  // 更新安全摄像头状态
  updateSafetyCameraStatus(id: number, status: string) {
    return request<SafetyCamera>({
      url: `/api/v1/city/safety/cameras/${id}/status`,
      method: 'put',
      data: { status }
    })
  }
}