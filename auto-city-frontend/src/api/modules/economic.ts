import request from '@/utils/request'
import type { EconomicIndicator, EnterpriseRegistration, InvestmentProject } from '@/types/dashboard'

// 经济指标相关API
export const economicApi = {
  // 获取经济指标列表
  getEconomicIndicators(params?: any) {
    return request<EconomicIndicator[]>({
      url: '/api/economic-development/indicators/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取经济指标详情
  getEconomicIndicatorById(id: number) {
    return request<EconomicIndicator>({
      url: `/api/economic-development/indicators/${id}`,
      method: 'get'
    })
  },

  // 创建经济指标
  createEconomicIndicator(data: Partial<EconomicIndicator>) {
    return request<EconomicIndicator>({
      url: '/api/economic-development/indicators',
      method: 'post',
      data
    })
  },

  // 更新经济指标
  updateEconomicIndicator(id: number, data: Partial<EconomicIndicator>) {
    return request<EconomicIndicator>({
      url: `/api/economic-development/indicators/${id}`,
      method: 'put',
      data
    })
  },

  // 删除经济指标
  deleteEconomicIndicator(id: number) {
    return request<void>({
      url: `/api/economic-development/indicators/${id}`,
      method: 'delete'
    })
  }
}

// 企业注册相关API
export const enterpriseApi = {
  // 获取企业注册列表
  getEnterprises(params?: any) {
    return request<EnterpriseRegistration[]>({
      url: '/api/economic-development/enterprises/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取企业详情
  getEnterpriseById(id: number) {
    return request<EnterpriseRegistration>({
      url: `/api/economic-development/enterprises/${id}`,
      method: 'get'
    })
  },

  // 创建企业注册
  createEnterprise(data: Partial<EnterpriseRegistration>) {
    return request<EnterpriseRegistration>({
      url: '/api/economic-development/enterprises',
      method: 'post',
      data
    })
  },

  // 更新企业注册
  updateEnterprise(id: number, data: Partial<EnterpriseRegistration>) {
    return request<EnterpriseRegistration>({
      url: `/api/economic-development/enterprises/${id}`,
      method: 'put',
      data
    })
  },

  // 删除企业注册
  deleteEnterprise(id: number) {
    return request<void>({
      url: `/api/economic-development/enterprises/${id}`,
      method: 'delete'
    })
  }
}

// 投资项目相关API
export const investmentApi = {
  // 获取投资项目列表
  getInvestmentProjects(params?: any) {
    return request<InvestmentProject[]>({
      url: '/api/economic-development/investments/search',
      method: 'post',
      data: params || {}
    })
  },

  // 根据ID获取投资项目详情
  getInvestmentProjectById(id: number) {
    return request<InvestmentProject>({
      url: `/api/economic-development/investments/${id}`,
      method: 'get'
    })
  },

  // 创建投资项目
  createInvestmentProject(data: Partial<InvestmentProject>) {
    return request<InvestmentProject>({
      url: '/api/economic-development/investments',
      method: 'post',
      data
    })
  },

  // 更新投资项目
  updateInvestmentProject(id: number, data: Partial<InvestmentProject>) {
    return request<InvestmentProject>({
      url: `/api/economic-development/investments/${id}`,
      method: 'put',
      data
    })
  },

  // 删除投资项目
  deleteInvestmentProject(id: number) {
    return request<void>({
      url: `/api/economic-development/investments/${id}`,
      method: 'delete'
    })
  }
}