// 经济相关接口
export interface EconomicIndicator {
  id: number
  name: string
  value: number
  unit: string
  region: string
  year: number
  type: string
  growthRate: number
  createTime: string
  updateTime: string
}

export interface EnterpriseRegistration {
  id: number
  name: string
  industry: string
  registeredCapital: number
  establishmentDate: string
  status: string
  legalPerson: string
  address: string
  businessScope: string
  createTime: string
  updateTime: string
}

export interface InvestmentProject {
  id: number
  name: string
  investor: string
  amount: number
  currency: string
  startDate: string
  endDate: string
  status: string
  location: string
  description: string
  createTime: string
  updateTime: string
}

// 环境相关接口
export interface EnvironmentalData {
  id: number
  type: string
  value: number
  unit: string
  location: string
  timestamp: string
  severity: string
  source: string
  createTime: string
  updateTime: string
}

export interface PollutionSource {
  id: number
  name: string
  type: string
  location: string
  emissionLevel: number
  status: string
  lastInspection: string
  nextInspection: string
  responsibleDept: string
  createTime: string
  updateTime: string
}

export interface EnvironmentalAlert {
  id: number
  type: string
  level: string
  description: string
  location: string
  timestamp: string
  status: string
  assignedTo: string
  resolvedAt: string
  resolution: string
  createTime: string
  updateTime: string
}

// 能源相关接口
export interface EnergyConsumption {
  id: number
  type: string
  value: number
  unit: string
  location: string
  timestamp: string
  source: string
  cost: number
  createTime: string
  updateTime: string
}

export interface RenewableEnergy {
  id: number
  type: string
  capacity: number
  unit: string
  location: string
  timestamp: string
  efficiency: number
  status: string
  createTime: string
  updateTime: string
}

export interface EnergyAlert {
  id: number
  type: string
  level: string
  description: string
  location: string
  timestamp: string
  status: string
  assignedTo: string
  resolvedAt: string
  resolution: string
  createTime: string
  updateTime: string
}

// 安全相关接口
export interface EmergencyEvent {
  id: number
  title: string
  type: string
  level: string
  description: string
  location: string
  time: string
  timestamp: string
  status: string
  assignedTo: string
  resolvedAt: string
  resolution: string
  createTime: string
  updateTime: string
}

export interface SafetyInspection {
  id: number
  facilityType: string
  location: string
  inspectionDate: string
  inspector: string
  status: string
  findings: string
  dueDate: string
  completedDate: string
  createTime: string
  updateTime: string
}

export interface SafetyCamera {
  id: number
  location: string
  status: string
  lastMaintenance: string
  nextMaintenance: string
  coverageArea: string
  ipAddress: string
  createTime: string
  updateTime: string
}

// 公共服务相关接口
export interface PublicService {
  id: number
  name: string
  type: string
  status: string
  location: string
  capacity: number
  currentUsage: number
  openHours: string
  contactInfo: string
  description: string
  createTime: string
  updateTime: string
}

export interface ServiceRequest {
  id: number
  type: string
  description: string
  requester: string
  location: string
  timestamp: string
  status: string
  assignedTo: string
  completedAt: string
  feedback: string
  createTime: string
  updateTime: string
}

export interface CitizenFeedback {
  id: number
  type: string
  content: string
  citizen: string
  contact: string
  timestamp: string
  status: string
  assignedTo: string
  repliedAt: string
  reply: string
  createTime: string
  updateTime: string
}

// 服务相关接口
export interface ServiceData {
  serviceSatisfaction: {
    value: number
    unit: string
    trend: 'up' | 'down' | 'stable'
    trendValue: string
  }
  onlineProcessing: {
    value: number
    unit: string
    trend: 'up' | 'down' | 'stable'
    trendValue: string
  }
  avgProcessingTime: {
    value: number
    unit: string
    trend: 'up' | 'down' | 'stable'
    trendValue: string
  }
  serviceCoverage: {
    value: number
    unit: string
    status: 'normal' | 'warning' | 'critical' | 'success'
  }
}

export interface ServiceType {
  type: string
  count: number
  satisfaction: number
  color: string
}

export interface PopularService {
  id: number
  name: string
  category: string
  processingTime: string
  satisfaction: number
}

export interface ServiceLocation {
  id: number
  name: string
  type: string
  address: string
  openHours: string
  phone: string
}

// 交通相关接口
export interface RoadCondition {
  id: number
  road: string
  flow: number
  speed: number
  status: 'congested' | 'normal' | 'smooth' | string
}

// AI决策支持相关接口
export interface PredictionResult {
  id: number
  modelName: string
  predictionType: string
  inputData: string
  outputData: string
  confidence: number
  timestamp: string
  accuracy: number
  createTime: string
  updateTime: string
}

export interface PolicyRecommendation {
  id: number
  title: string
  content: string
  recommendationType: string
  priorityLevel: string
  department: string
  status: string
  effectiveDate: string
  expirationDate: string
  impactAssessment: string
  createTime: string
  updateTime: string
}

export interface AIModel {
  id: number
  name: string
  version: string
  description: string
  trainingData: string
  accuracy: number
  lastTrained: string
  status: string
  owner: string
  createTime: string
  updateTime: string
}

// 城市规划相关接口
export interface LandUsePlan {
  id: number
  planName: string
  planDescription: string
  landUseType: 'residential' | 'commercial' | 'industrial' | 'public' | 'green' | string
  area: number
  location: string
  planner: string
  approvalStatus: 'pending' | 'approved' | 'rejected' | string
  createdTime: string
  updatedTime: string
}

export interface ConstructionProject {
  id: number
  projectName: string
  projectDescription: string
  projectType: string
  location: string
  budget: number
  startDate: string
  endDate: string
  progress: number
  manager: string
  status: 'planning' | 'constructing' | 'completed' | 'paused' | string
  createdTime: string
  updatedTime: string
}

export interface PlanningApproval {
  id: number
  approvalItem: string
  applicant: string
  applicationDate: string
  approvalDate: string
  approvalStatus: 'pending' | 'approved' | 'rejected' | string
  approver: string
  comments: string
  relatedPlanId: number
  relatedProjectId: number
  createdTime: string
  updatedTime: string
}

export interface ModuleCardProps {
  title: string
  value: number | string
  unit?: string
  trend?: 'up' | 'down' | 'stable'
  status?: 'normal' | 'warning' | 'danger' | 'success'
  icon: string
}

export interface OverviewData {
  trafficCongestionIndex: number
  airQualityIndex: number
  energyConsumption: number
  waterQualityIndex: number
  emergencyEvents: number
  publicSatisfaction: number
}