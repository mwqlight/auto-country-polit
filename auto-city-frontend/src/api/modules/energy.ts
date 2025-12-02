import request from '@/utils/request'
import type { EnergyConsumption, RenewableEnergy, EnergyAlert } from '@/types/dashboard'

// 能源消耗相关API
export const energyConsumptionApi = {
  // 获取最新能源数据
  getLatestEnergyData(type: string) {
    return request<EnergyConsumption>({
      url: `/api/v1/city/energy/consumption/latest?type=${type}`,
      method: 'get'
    })
  },
  // 获取能源消耗列表
  getEnergyConsumptions(params?: any) {
    return request<EnergyConsumption[]>({
      url: '/api/v1/city/energy/consumption/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取能源消耗详情
  getEnergyConsumptionById(id: number) {
    return request<EnergyConsumption>({
      url: `/api/v1/city/energy/consumption/${id}`,
      method: 'get'
    })
  },

  // 创建能源消耗记录
  createEnergyConsumption(data: Partial<EnergyConsumption>) {
    return request<EnergyConsumption>({
      url: '/api/v1/city/energy/consumption',
      method: 'post',
      data
    })
  },

  // 更新能源消耗记录
  updateEnergyConsumption(id: number, data: Partial<EnergyConsumption>) {
    return request<EnergyConsumption>({
      url: `/api/v1/city/energy/consumption/${id}`,
      method: 'put',
      data
    })
  },

  // 删除能源消耗记录
  deleteEnergyConsumption(id: number) {
    return request<void>({
      url: `/api/v1/city/energy/consumption/${id}`,
      method: 'delete'
    })
  },

  // 获取最新的能源消耗数据
  getLatestEnergyConsumption() {
    return request<EnergyConsumption>({
      url: '/api/v1/city/energy/consumption/latest',
      method: 'get'
    })
  }
}

// 可再生能源相关API
export const renewableEnergyApi = {
  // 获取可再生能源列表
  getRenewableEnergies(params?: any) {
    return request<RenewableEnergy[]>({
      url: '/api/v1/city/energy/renewable/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取可再生能源详情
  getRenewableEnergyById(id: number) {
    return request<RenewableEnergy>({
      url: `/api/v1/city/energy/renewable/${id}`,
      method: 'get'
    })
  },

  // 创建可再生能源记录
  createRenewableEnergy(data: Partial<RenewableEnergy>) {
    return request<RenewableEnergy>({
      url: '/api/v1/city/energy/renewable',
      method: 'post',
      data
    })
  },

  // 更新可再生能源记录
  updateRenewableEnergy(id: number, data: Partial<RenewableEnergy>) {
    return request<RenewableEnergy>({
      url: `/api/v1/city/energy/renewable/${id}`,
      method: 'put',
      data
    })
  },

  // 删除可再生能源记录
  deleteRenewableEnergy(id: number) {
    return request<void>({
      url: `/api/v1/city/energy/renewable/${id}`,
      method: 'delete'
    })
  },

  // 获取最新的可再生能源数据
  getLatestRenewableEnergy() {
    return request<RenewableEnergy>({
      url: '/api/v1/city/energy/renewable/latest',
      method: 'get'
    })
  }
}

// 能源警报相关API
export const energyAlertApi = {
  // 获取能源警报列表
  getEnergyAlerts(params?: any) {
    return request<EnergyAlert[]>({
      url: '/api/v1/city/energy/alerts/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取能源警报详情
  getEnergyAlertById(id: number) {
    return request<EnergyAlert>({
      url: `/api/v1/city/energy/alerts/${id}`,
      method: 'get'
    })
  },

  // 创建能源警报
  createEnergyAlert(data: Partial<EnergyAlert>) {
    return request<EnergyAlert>({
      url: '/api/v1/city/energy/alerts',
      method: 'post',
      data
    })
  },

  // 获取未解决的能源警报
  getUnresolvedEnergyAlerts() {
    return request<EnergyAlert[]>({
      url: '/api/v1/city/energy/alerts/unresolved',
      method: 'get'
    })
  },

  // 解决能源警报
  resolveEnergyAlert(id: number) {
    return request<void>({
      url: `/api/v1/city/energy/alerts/${id}/resolve`,
      method: 'put'
    })
  }
}