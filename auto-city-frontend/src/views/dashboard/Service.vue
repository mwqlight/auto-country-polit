<script setup lang="ts">
import { ref, onMounted } from 'vue'
import ModuleCard from '@/components/dashboard/ModuleCard.vue'
import type { ServiceData, ServiceType, PopularService, ServiceLocation } from '@/types/dashboard'

// 服务数据
const serviceData = ref<ServiceData>({
  serviceSatisfaction: {
    value: 92,
    unit: '%',
    trend: 'up' as const,
    trendValue: '+3%'
  },
  onlineProcessing: {
    value: 85,
    unit: '%',
    trend: 'up' as const,
    trendValue: '+5%'
  },
  avgProcessingTime: {
    value: 3.2,
    unit: '天',
    trend: 'down' as const,
    trendValue: '-0.5天'
  },
  serviceCoverage: {
    value: 98,
    unit: '%',
    status: 'success' as const
  }
})

// 服务类型统计
const serviceTypes = ref<ServiceType[]>([
  { type: '政务服务', count: 45, satisfaction: 95, color: '#3b82f6' },
  { type: '公共服务', count: 32, satisfaction: 88, color: '#10b981' },
  { type: '生活服务', count: 28, satisfaction: 92, color: '#f59e0b' },
  { type: '教育服务', count: 15, satisfaction: 89, color: '#8b5cf6' },
  { type: '医疗服务', count: 22, satisfaction: 91, color: '#ec4899' }
])

// 热门服务
const popularServices = ref<PopularService[]>([
  {
    id: 1,
    name: '身份证办理',
    category: '政务',
    processingTime: '3个工作日',
    satisfaction: 96
  },
  {
    id: 2,
    name: '社保缴费',
    category: '政务',
    processingTime: '即时',
    satisfaction: 92
  },
  {
    id: 3,
    name: '公交卡充值',
    category: '生活',
    processingTime: '即时',
    satisfaction: 88
  },
  {
    id: 4,
    name: '医保报销',
    category: '医疗',
    processingTime: '5个工作日',
    satisfaction: 85
  }
])

// 服务网点
const serviceLocations = ref<ServiceLocation[]>([
  {
    id: 1,
    name: '市民服务中心',
    type: '综合服务',
    address: '市中心大道123号',
    openHours: '9:00-17:00',
    phone: '010-12345678'
  },
  {
    id: 2,
    name: '社区服务站A',
    type: '便民服务',
    address: '朝阳区幸福路45号',
    openHours: '8:30-18:00',
    phone: '010-23456789'
  },
  {
    id: 3,
    name: '政务服务大厅B',
    type: '政务服务',
    address: '海淀区科技路67号',
    openHours: '9:00-17:30',
    phone: '010-34567890'
  }
])

onMounted(() => {
  // 模拟数据加载
  console.log('Service dashboard mounted')
})
</script>

<template>
  <div class="service-dashboard">
    <div class="dashboard-header">
      <h1>民生服务</h1>
      <div class="header-actions">
        <button class="btn btn-primary">新增服务</button>
        <button class="btn btn-secondary">服务统计</button>
      </div>
    </div>
    
    <!-- 服务指标概览 -->
    <div class="metrics-grid">
      <ModuleCard
        title="服务满意度"
        :value="serviceData.serviceSatisfaction.value"
        :unit="serviceData.serviceSatisfaction.unit"
        :trend="serviceData.serviceSatisfaction.trend"
        :trend-value="serviceData.serviceSatisfaction.trendValue"
        icon="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"
        color="#10b981"
      />
      
      <ModuleCard
        title="线上办理率"
        :value="serviceData.onlineProcessing.value"
        :unit="serviceData.onlineProcessing.unit"
        :trend="serviceData.onlineProcessing.trend"
        :trend-value="serviceData.onlineProcessing.trendValue"
        icon="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"
        color="#3b82f6"
      />
      
      <ModuleCard
        title="平均办理时间"
        :value="serviceData.avgProcessingTime.value"
        :unit="serviceData.avgProcessingTime.unit"
        :trend="serviceData.avgProcessingTime.trend"
        :trend-value="serviceData.avgProcessingTime.trendValue"
        icon="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"
        color="#f59e0b"
      />
      
      <ModuleCard
        title="服务覆盖率"
        :value="serviceData.serviceCoverage.value"
        :unit="serviceData.serviceCoverage.unit"
        :status="serviceData.serviceCoverage.status"
        icon="M3.055 11H5a2 2 0 012 2v1a2 2 0 002 2 2 2 0 012 2v2.945M8 3.935V5.5A2.5 2.5 0 0010.5 8h.5a2 2 0 012 2 2 2 0 104 0 2 2 0 012-2h1.064M15 20.488V18a2 2 0 012-2h3.064M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
        color="#8b5cf6"
      />
    </div>
    
    <!-- 服务统计和热门服务 -->
    <div class="section-row">
      <!-- 服务类型统计 -->
      <div class="section half">
        <h2>服务类型统计</h2>
        <div class="chart-container">
          <div class="stats-grid">
            <div 
              v-for="(item, index) in serviceTypes" 
              :key="index"
              class="stat-card"
            >
              <div class="stat-header">
                <div 
                  class="stat-icon" 
                  :style="{ backgroundColor: item.color + '20', color: item.color }"
                >
                  {{ item.type.charAt(0) }}
                </div>
                <div class="stat-info">
                  <h3>{{ item.type }}</h3>
                  <p>{{ item.count }}项服务</p>
                </div>
              </div>
              <div class="stat-progress">
                <div class="progress-bar">
                  <div 
                    class="progress-fill" 
                    :style="{ 
                      width: `${item.satisfaction}%`,
                      backgroundColor: item.color
                    }"
                  ></div>
                </div>
                <div class="progress-text">满意度: {{ item.satisfaction }}%</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 热门服务 -->
      <div class="section half">
        <h2>热门服务</h2>
        <div class="services-list">
          <div 
            v-for="service in popularServices" 
            :key="service.id"
            class="service-item"
          >
            <div class="service-header">
              <h3>{{ service.name }}</h3>
              <span class="category-badge">{{ service.category }}</span>
            </div>
            <div class="service-details">
              <p><strong>办理时间:</strong> {{ service.processingTime }}</p>
              <p><strong>满意度:</strong> 
                <span class="satisfaction-score" :class="getSatisfactionClass(service.satisfaction)">
                  {{ service.satisfaction }}%
                </span>
              </p>
            </div>
            <div class="service-actions">
              <button class="btn btn-sm btn-outline">详情</button>
              <button class="btn btn-sm btn-primary">立即办理</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 服务网点 -->
    <div class="section">
      <h2>服务网点</h2>
      <div class="locations-grid">
        <div 
          v-for="location in serviceLocations" 
          :key="location.id"
          class="location-card"
        >
          <div class="location-header">
            <h3>{{ location.name }}</h3>
            <span class="type-badge">{{ location.type }}</span>
          </div>
          <div class="location-details">
            <p><strong>地址:</strong> {{ location.address }}</p>
            <p><strong>营业时间:</strong> {{ location.openHours }}</p>
            <p><strong>联系电话:</strong> {{ location.phone }}</p>
          </div>
          <div class="location-actions">
            <button class="btn btn-sm btn-outline">导航</button>
            <button class="btn btn-sm btn-primary">预约</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  methods: {
    getSatisfactionClass(score: number): string {
      if (score >= 90) return 'high';
      if (score >= 80) return 'medium';
      return 'low';
    }
  }
}
</script>

<style scoped>
.service-dashboard {
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

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 15px;
}

.stat-card {
  border-radius: 8px;
  padding: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.stat-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.stat-header {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.stat-icon {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  margin-right: 12px;
  flex-shrink: 0;
}

.stat-info h3 {
  margin: 0 0 4px 0;
  font-size: 1rem;
  color: var(--text-primary);
}

.stat-info p {
  margin: 0;
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.stat-progress {
  margin-top: 10px;
}

.progress-bar {
  height: 8px;
  background-color: var(--background-color);
  border-radius: 4px;
  overflow: hidden;
  margin-bottom: 8px;
}

.progress-fill {
  height: 100%;
  border-radius: 4px;
  transition: width 0.5s ease;
}

.progress-text {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.services-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.service-item {
  border-radius: 8px;
  padding: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.service-item:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.service-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.service-header h3 {
  margin: 0;
  font-size: 1.1rem;
  color: var(--text-primary);
}

.category-badge {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  background-color: var(--primary-color-light);
  color: var(--primary-color);
}

.service-details {
  margin-bottom: 15px;
}

.service-details p {
  margin: 5px 0;
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.satisfaction-score.high {
  color: var(--success-color);
  font-weight: 600;
}

.satisfaction-score.medium {
  color: var(--warning-color);
  font-weight: 600;
}

.satisfaction-score.low {
  color: var(--danger-color);
  font-weight: 600;
}

.service-actions {
  display: flex;
  gap: 10px;
}

.locations-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.location-card {
  border-radius: 8px;
  padding: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.location-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.location-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.location-header h3 {
  margin: 0;
  font-size: 1.1rem;
  color: var(--text-primary);
}

.type-badge {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  background-color: var(--secondary-color-light);
  color: var(--secondary-color);
}

.location-details {
  margin-bottom: 15px;
}

.location-details p {
  margin: 5px 0;
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.location-actions {
  display: flex;
  gap: 10px;
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
  .service-dashboard {
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
  
  .stats-grid {
    grid-template-columns: 1fr;
  }
  
  .locations-grid {
    grid-template-columns: 1fr;
  }
}
</style>