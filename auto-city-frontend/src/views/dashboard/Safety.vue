<script setup lang="ts">
import { ref, onMounted } from 'vue'
import ModuleCard from '@/components/dashboard/ModuleCard.vue'
import { emergencyEventApi, safetyInspectionApi, safetyCameraApi } from '@/api/modules/safety'
import type { EmergencyEvent } from '@/types/dashboard'

// 安全数据
const totalEvents = ref<number>(0)
const avgResponseTime = ref<number>(0)
const unresolvedCount = ref<number>(0)
const eventTypeStats = ref<{type: string, count: number}[]>([])
const resourceStatus = ref<{name: string, status: 'success' | 'warning' | 'danger' | 'normal' | 'available' | 'busy', count: number}[]>([])
const recentEvents = ref<Array<{
  id: number
  type: string
  time: string
  location: string
  status: '处理中' | '已完成' | '待处理' | '已关闭'
}>>([])

// 事件类型统计
const incidentTypes = ref<{type: string, count: number, color: string}[]>([])

// 应急资源状态
const emergencyResources = ref<Array<{
  id: number
  name: string
  status: 'available' | 'busy' | 'offline' | 'maintenance'
  location: string
  personnel: number
}>>([])

// 最近事件
const recentIncidents = ref<EmergencyEvent[]>([])

// 安全警报
const safetyAlerts = ref<EmergencyEvent[]>([])

// 加载安全概览数据
const loadSafetyOverview = async () => {
  try {
    // 获取紧急事件统计数据
    const events = await emergencyEventApi.getEmergencyEvents({})
    if (events) {
      totalEvents.value = events.length || 0
      
      // 计算未解决事件数量
      const unresolvedEvents = await emergencyEventApi.getUnresolvedEmergencyEvents()
      unresolvedCount.value = unresolvedEvents?.length || 0
      
      // 模拟平均响应时间计算（实际应用中应该从API获取）
      avgResponseTime.value = 15
      
      // 按类型统计事件数量
      const typeStats: {[key: string]: number} = {}
      events.forEach(event => {
        typeStats[event.type] = (typeStats[event.type] || 0) + 1
      })
      
      eventTypeStats.value = Object.keys(typeStats).map(type => ({
        type,
        count: typeStats[type] || 0
      }))
    }
    
    // 获取安全摄像头状态
    const cameras = await safetyCameraApi.getSafetyCameras({})
    if (cameras) {
      const onlineCameras = cameras.filter(camera => camera.status === '在线').length
      const offlineCameras = cameras.filter(camera => camera.status === '离线').length
      
      resourceStatus.value = [
        { name: '在线摄像头', status: 'success', count: onlineCameras },
        { name: '离线摄像头', status: offlineCameras > 0 ? 'danger' : 'success', count: offlineCameras },
        { name: '总摄像头数', status: 'normal', count: cameras.length }
      ]
    }
    
    // 获取最近的事件
    const recentEventData = await emergencyEventApi.getEmergencyEvents({ limit: 3 })
    if (recentEventData) {
      recentEvents.value = recentEventData.map(event => ({
        id: event.id,
        type: event.type,
        time: event.time || event.timestamp,
        location: event.location,
        status: event.status as '处理中' | '已完成' | '待处理' | '已关闭'
      }))
    }
  } catch (error) {
    console.error('加载安全数据失败:', error)
  }
}

// 加载事件类型统计
const loadIncidentTypes = async () => {
  try {
    const events = await emergencyEventApi.getEmergencyEvents({})
    const typeCount: Record<string, number> = {}
    
    events.forEach(event => {
      const type = event.type
      if (!typeCount[type]) {
        typeCount[type] = 0
      }
      typeCount[type]++
    })
    
    // 更新事件类型统计
    incidentTypes.value = Object.keys(typeCount).map((type, index) => ({
      type,
      count: typeCount[type] || 0,
      color: getColorByIndex(index) || '#3b82f6'
    }))
  } catch (error) {
    console.error('Failed to load incident types:', error)
  }
}

// 加载应急资源状态
const loadEmergencyResources = async () => {
  try {
    // 这里应该调用获取应急资源状态的API
    // 暂时使用模拟数据
    emergencyResources.value = [
      {
        id: 1,
        name: '消防队A',
        status: 'available',
        location: '市中心',
        personnel: 25
      },
      {
        id: 2,
        name: '警察局B',
        status: 'busy',
        location: '商业区',
        personnel: 42
      },
      {
        id: 3,
        name: '医院C',
        status: 'available',
        location: '东区',
        personnel: 120
      },
      {
        id: 4,
        name: '应急指挥中心',
        status: 'available',
        location: '市政府',
        personnel: 15
      }
    ]
  } catch (error) {
    console.error('Failed to load emergency resources:', error)
  }
}

// 加载最近事件
const loadRecentIncidents = async () => {
  try {
    const events = await emergencyEventApi.getEmergencyEvents({})
    recentIncidents.value = events.slice(0, 5) // 只显示最近5个事件
  } catch (error) {
    console.error('Failed to load recent incidents:', error)
  }
}

// 加载安全警报
const loadSafetyAlerts = async () => {
  try {
    const activeEvents = await emergencyEventApi.getEmergencyEvents({ status: 'active' })
    safetyAlerts.value = activeEvents.slice(0, 3) // 只显示最近3个活动警报
  } catch (error) {
    console.error('Failed to load safety alerts:', error)
  }
}

// 根据索引获取颜色
const getColorByIndex = (index: number) => {
  const colors = ['#3b82f6', '#f59e0b', '#ef4444', '#10b981', '#8b5cf6']
  return colors[index % colors.length]
}

// 获取状态文本
const getStatusText = (status: 'success' | 'warning' | 'danger' | 'normal' | 'available' | 'busy') => {
  const statusMap: Record<'success' | 'warning' | 'danger' | 'normal' | 'available' | 'busy', string> = {
    success: '正常',
    warning: '警告',
    danger: '异常',
    normal: '正常',
    available: '可用',
    busy: '忙碌'
  }
  return statusMap[status] || '未知'
}

// 获取事件级别文本
const getLevelText = (level: 'low' | 'medium' | 'high' | 'critical') => {
  const levelMap: Record<'low' | 'medium' | 'high' | 'critical', string> = {
    low: '一般',
    medium: '重要',
    high: '紧急',
    critical: '特急'
  }
  return levelMap[level] || '未知'
}

onMounted(() => {
  loadSafetyOverview()
  loadIncidentTypes()
  loadEmergencyResources()
  loadRecentIncidents()
  loadSafetyAlerts()
})
</script>

<template>
  <div class="safety-dashboard">
    <div class="dashboard-header">
      <h1>公共安全</h1>
      <div class="header-actions">
        <button class="btn btn-primary">新建事件</button>
        <button class="btn btn-secondary">应急预案</button>
      </div>
    </div>
    
    <!-- 安全指标概览 -->
    <div class="metrics-grid">
      <ModuleCard
        title="事件总数"
        :value="totalEvents"
        icon="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"
        color="#ef4444"
      />
      
      <ModuleCard
        title="平均响应时间"
        :value="avgResponseTime"
        unit="分钟"
        icon="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"
        color="#f59e0b"
      />
      
      <ModuleCard
        title="巡逻覆盖率"
        :value="85"
        unit="%"
        :trend="'up'"
        trend-value="+2%"
        icon="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"
        color="#10b981"
      />
      
      <ModuleCard
        title="应急资源可用率"
        :value="92"
        unit="%"
        :status="'success'"
        icon="M3 21v-4m0 0V5a2 2 0 012-2h6.5l1 1H21l-3 6 3 6h-8.5l-1-1H5a2 2 0 00-2 2zm9-13.5V9"
        color="#8b5cf6"
      />
    </div>
    
    <!-- 事件统计和应急资源 -->
    <div class="section-row">
      <!-- 事件类型统计 -->
      <div class="section half">
        <h2>事件类型统计</h2>
        <div class="chart-container">
          <div class="bar-chart">
            <div 
              v-for="(item, index) in incidentTypes" 
              :key="index"
              class="bar-item"
            >
              <div class="bar-label">{{ item.type }}</div>
              <div class="bar-wrapper">
                <div 
                  class="bar-fill" 
                  :style="{ 
                    width: `${(item.count / 10) * 100}%`,
                    backgroundColor: item.color
                  }"
                ></div>
                <div class="bar-count">{{ item.count }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 应急资源状态 -->
      <div class="section half">
        <h2>应急资源状态</h2>
        <div class="resources-grid">
          <div 
            v-for="resource in emergencyResources" 
            :key="resource.id"
            class="resource-card"
            :class="resource.status"
          >
            <div class="resource-header">
              <h3>{{ resource.name }}</h3>
              <span class="status-badge" :class="resource.status">
                {{ getResourceStatusText(resource.status) }}
              </span>
            </div>
            <div class="resource-details">
              <p><strong>位置:</strong> {{ resource.location }}</p>
              <p><strong>人员:</strong> {{ resource.personnel }}人</p>
            </div>
            <div class="resource-actions">
              <button class="btn btn-sm btn-outline">详情</button>
              <button class="btn btn-sm btn-primary">调度</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 最近事件 -->
    <div class="section">
      <h2>最近事件</h2>
      <div class="incidents-table">
        <div class="table-header">
          <div class="table-cell">事件标题</div>
          <div class="table-cell">类型</div>
          <div class="table-cell">等级</div>
          <div class="table-cell">时间</div>
          <div class="table-cell">地点</div>
          <div class="table-cell">状态</div>
          <div class="table-cell">操作</div>
        </div>
        <div 
          v-for="incident in recentIncidents" 
          :key="incident.id"
          class="table-row"
          :class="incident.level"
        >
          <div class="table-cell">{{ incident.title }}</div>
          <div class="table-cell">{{ incident.type }}</div>
          <div class="table-cell">
            <span class="level-badge" :class="incident.level">
              {{ incident.level }}
            </span>
          </div>
          <div class="table-cell">{{ incident.time }}</div>
          <div class="table-cell">{{ incident.location }}</div>
          <div class="table-cell">
            <span class="status-text" :class="incident.status">
              {{ incident.status }}
            </span>
          </div>
          <div class="table-cell">
            <button class="btn btn-sm btn-outline">详情</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  methods: {
    getResourceStatusText(status: string): string {
      switch (status) {
        case 'available': return '可用';
        case 'busy': return '忙碌';
        case 'maintenance': return '维护中';
        default: return '未知';
      }
    },
    getStatusText(status: string): string {
      switch (status) {
        case 'available': return '正常';
        case 'warning': return '警告';
        case 'critical': return '危险';
        default: return '未知';
      }
    },
    getLoadClass(load: number): string {
      if (load < 60) return 'low';
      if (load < 85) return 'medium';
      return 'high';
    },
    calculateOffset(index: number): number {
      // This is a simplified calculation for demo purposes
      return -25 * index;
    }
  }
}
</script>

<style scoped>
.safety-dashboard {
  padding: 20px;
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.dashboard-header h1 {
  margin: 0;
  font-size: 1.8rem;
  color: var(--text-primary);
}

.metrics-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.section-row {
  display: flex;
  gap: 20px;
  margin-bottom: 30px;
}

.section {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  border: 1px solid var(--border-color);
}

.section.half {
  flex: 1;
}

.section h2 {
  font-size: 1.3rem;
  margin-top: 0;
  margin-bottom: 20px;
  color: var(--text-primary);
}

.chart-container {
  display: flex;
  align-items: center;
}

.bar-chart {
  width: 100%;
}

.bar-item {
  margin-bottom: 15px;
}

.bar-label {
  font-size: 0.9rem;
  color: var(--text-primary);
  margin-bottom: 5px;
}

.bar-wrapper {
  display: flex;
  align-items: center;
  gap: 10px;
}

.bar-fill {
  height: 20px;
  border-radius: 10px;
  transition: width 0.5s ease;
}

.bar-count {
  font-size: 0.9rem;
  font-weight: 600;
  color: var(--text-primary);
  min-width: 20px;
}

.resources-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 15px;
}

.resource-card {
  border-radius: 8px;
  padding: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.resource-card.available {
  border-left: 4px solid var(--success-color);
}

.resource-card.busy {
  border-left: 4px solid var(--warning-color);
}

.resource-card.maintenance {
  border-left: 4px solid var(--danger-color);
}

.resource-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.resource-header h3 {
  margin: 0;
  font-size: 1rem;
  color: var(--text-primary);
}

.status-badge {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  font-weight: 500;
}

.status-badge.available {
  background-color: var(--success-color-light);
  color: var(--success-color);
}

.status-badge.busy {
  background-color: var(--warning-color-light);
  color: var(--warning-color);
}

.status-badge.maintenance {
  background-color: var(--danger-color-light);
  color: var(--danger-color);
}

.resource-details {
  margin-bottom: 15px;
}

.resource-details p {
  margin: 5px 0;
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.resource-actions {
  display: flex;
  gap: 10px;
}

.incidents-table {
  background: var(--card-bg);
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  border: 1px solid var(--border-color);
}

.table-header {
  display: flex;
  background-color: var(--table-header-bg);
  font-weight: 600;
  color: var(--text-secondary);
}

.table-row {
  display: flex;
  border-bottom: 1px solid var(--border-color);
  transition: background-color 0.2s ease;
}

.table-row:hover {
  background-color: var(--hover-bg);
}

.table-cell {
  flex: 1;
  padding: 12px 16px;
  display: flex;
  align-items: center;
}

.table-header .table-cell {
  padding: 14px 16px;
}

.level-badge {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  font-weight: 500;
}

.level-badge.紧急 {
  background-color: var(--danger-color-light);
  color: var(--danger-color);
}

.level-badge.重要 {
  background-color: var(--warning-color-light);
  color: var(--warning-color);
}

.level-badge.一般 {
  background-color: var(--success-color-light);
  color: var(--success-color);
}

.status-text {
  font-weight: 500;
}

.status-text.已解决 {
  color: var(--success-color);
}

.status-text.处理中 {
  color: var(--warning-color);
}

.status-text.调查中 {
  color: var(--primary-color);
}

.btn {
  padding: 8px 16px;
  border-radius: 6px;
  border: none;
  font-size: 0.9rem;
  cursor: pointer;
  transition: all 0.2s ease;
}

.btn-primary {
  background-color: var(--primary-color);
  color: white;
}

.btn-secondary {
  background-color: var(--secondary-color);
  color: white;
}

.btn-outline {
  background-color: transparent;
  border: 1px solid var(--border-color);
  color: var(--text-primary);
}

.btn-sm {
  padding: 6px 12px;
  font-size: 0.8rem;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .safety-dashboard {
    padding: 15px;
  }
  
  .dashboard-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }
  
  .section-row {
    flex-direction: column;
  }
  
  .metrics-grid {
    grid-template-columns: 1fr;
  }
  
  .resources-grid {
    grid-template-columns: 1fr;
  }
  
  .table-row {
    flex-direction: column;
  }
  
  .table-cell {
    padding: 8px 12px;
  }
}
</style>