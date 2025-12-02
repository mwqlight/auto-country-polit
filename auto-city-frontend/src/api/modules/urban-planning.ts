import request from '@/utils/request'
import type { LandUsePlan, ConstructionProject, PlanningApproval } from '@/types/dashboard'

// 土地利用规划 API
export const landUsePlanApi = {
  // 获取所有土地利用规划
  getAllLandUsePlans: () => {
    return request<LandUsePlan[]>({
      url: '/api/v1/urban-planning/land-use-plans',
      method: 'get'
    })
  },

  // 根据ID获取土地利用规划
  getLandUsePlanById: (id: number) => {
    return request<LandUsePlan>({
      url: `/api/v1/urban-planning/land-use-plans/${id}`,
      method: 'get'
    })
  },

  // 创建土地利用规划
  createLandUsePlan: (data: LandUsePlan) => {
    return request<LandUsePlan>({
      url: '/api/v1/urban-planning/land-use-plans',
      method: 'post',
      data
    })
  },

  // 更新土地利用规划
  updateLandUsePlan: (id: number, data: LandUsePlan) => {
    return request<LandUsePlan>({
      url: `/api/v1/urban-planning/land-use-plans/${id}`,
      method: 'put',
      data
    })
  },

  // 删除土地利用规划
  deleteLandUsePlan: (id: number) => {
    return request<void>({
      url: `/api/v1/urban-planning/land-use-plans/${id}`,
      method: 'delete'
    })
  },

  // 根据土地利用类型获取规划
  getLandUsePlansByType: (landUseType: string) => {
    return request<LandUsePlan[]>({
      url: `/api/v1/urban-planning/land-use-plans/type/${landUseType}`,
      method: 'get'
    })
  },

  // 根据审批状态获取规划
  getLandUsePlansByStatus: (approvalStatus: string) => {
    return request<LandUsePlan[]>({
      url: `/api/v1/urban-planning/land-use-plans/status/${approvalStatus}`,
      method: 'get'
    })
  }
}

// 建设工程 API
export const constructionProjectApi = {
  // 获取所有建设工程
  getAllConstructionProjects: () => {
    return request<ConstructionProject[]>({
      url: '/api/v1/urban-planning/construction-projects',
      method: 'get'
    })
  },

  // 根据ID获取建设工程
  getConstructionProjectById: (id: number) => {
    return request<ConstructionProject>({
      url: `/api/v1/urban-planning/construction-projects/${id}`,
      method: 'get'
    })
  },

  // 创建建设工程
  createConstructionProject: (data: ConstructionProject) => {
    return request<ConstructionProject>({
      url: '/api/v1/urban-planning/construction-projects',
      method: 'post',
      data
    })
  },

  // 更新建设工程
  updateConstructionProject: (id: number, data: ConstructionProject) => {
    return request<ConstructionProject>({
      url: `/api/v1/urban-planning/construction-projects/${id}`,
      method: 'put',
      data
    })
  },

  // 删除建设工程
  deleteConstructionProject: (id: number) => {
    return request<void>({
      url: `/api/v1/urban-planning/construction-projects/${id}`,
      method: 'delete'
    })
  },

  // 根据项目类型获取建设工程
  getConstructionProjectsByType: (projectType: string) => {
    return request<ConstructionProject[]>({
      url: `/api/v1/urban-planning/construction-projects/type/${projectType}`,
      method: 'get'
    })
  },

  // 根据状态获取建设工程
  getConstructionProjectsByStatus: (status: string) => {
    return request<ConstructionProject[]>({
      url: `/api/v1/urban-planning/construction-projects/status/${status}`,
      method: 'get'
    })
  }
}

// 规划审批 API
export const planningApprovalApi = {
  // 获取所有规划审批
  getAllPlanningApprovals: () => {
    return request<PlanningApproval[]>({
      url: '/api/v1/urban-planning/planning-approvals',
      method: 'get'
    })
  },

  // 根据ID获取规划审批
  getPlanningApprovalById: (id: number) => {
    return request<PlanningApproval>({
      url: `/api/v1/urban-planning/planning-approvals/${id}`,
      method: 'get'
    })
  },

  // 创建规划审批
  createPlanningApproval: (data: PlanningApproval) => {
    return request<PlanningApproval>({
      url: '/api/v1/urban-planning/planning-approvals',
      method: 'post',
      data
    })
  },

  // 更新规划审批
  updatePlanningApproval: (id: number, data: PlanningApproval) => {
    return request<PlanningApproval>({
      url: `/api/v1/urban-planning/planning-approvals/${id}`,
      method: 'put',
      data
    })
  },

  // 删除规划审批
  deletePlanningApproval: (id: number) => {
    return request<void>({
      url: `/api/v1/urban-planning/planning-approvals/${id}`,
      method: 'delete'
    })
  },

  // 根据审批状态获取规划审批
  getPlanningApprovalsByStatus: (approvalStatus: string) => {
    return request<PlanningApproval[]>({
      url: `/api/v1/urban-planning/planning-approvals/status/${approvalStatus}`,
      method: 'get'
    })
  },

  // 根据申请人获取规划审批
  getPlanningApprovalsByApplicant: (applicant: string) => {
    return request<PlanningApproval[]>({
      url: `/api/v1/urban-planning/planning-approvals/applicant/${applicant}`,
      method: 'get'
    })
  }
}