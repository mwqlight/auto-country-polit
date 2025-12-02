<script setup lang="ts">
import { ref, onMounted } from 'vue'
import ModuleCard from '@/components/dashboard/ModuleCard.vue'
import { energyConsumptionApi as energyApi, renewableEnergyApi, energyAlertApi } from '@/api/modules/energy'
import type { EnergyAlert } from '@/types/dashboard'

// 能源数据
const totalConsumption = ref<number>(0)
const renewableRatio = ref<number>(0)
const alertCount = ref<number>(0)
const energyDistribution = ref<Array<{type: string, value: number, color: string}>>([
  { type: '太阳能', value: 0, color: '#f59e0b' },
  { type: '风能', value: 0, color: '#0ea5e9' },
  { type: '水电', value: 0, color: '#06b6d4' },
  { type: '天然气', value: 0, color: '#8b5cf6' },
  { type: '煤炭', value: 0, color: '#64748b' }
])
const gridStatus = ref<Array<{
  id: number
  name: string
  status: 'normal' | 'warning' | 'critical' | 'offline'
  load: number
  capacity: number
}>>([])
const consumptionTrend = ref({
  labels: [] as string[],
  datasets: [
    {
      label: '总能耗 (MW)',
      data: [] as number[],
      borderColor: '#3b82f6',
      backgroundColor: 'rgba(59, 130, 246, 0.1)',
      tension: 0.4,
      fill: true
    }
  ]
})

// 能源指标数据
const energyData = ref({
  totalConsumption: {
    value: 0,
    unit: 'MW',
    trend: 'up' as const,
    trendValue: '+2.3%'
  },
  renewableRatio: {
    value: 0,
    unit: '%',
    trend: 'up' as const,
    trendValue: '+5.1%'
  },
  efficiency: {
    value: 0,
    unit: '%',
    trend: 'up' as const
  },
  costSaving: {
    value: 0,
    unit: '万元',
    trend: 'down' as const,
    trendValue: '-1.2%'
  }
})

// 能源警报
const energyAlerts = ref<EnergyAlert[]>([])

// 加载能源概览数据
const loadEnergyOverview = async () => {
  try {
    // 获取最新的能源消耗数据
    const latestConsumption = await energyApi.getLatestEnergyData('total_consumption')
    if (latestConsumption && latestConsumption.value) {
      totalConsumption.value = latestConsumption.value || 0
    }
    
    // 获取最新的可再生能源数据
    const latestRenewable = await energyApi.getLatestEnergyData('renewable_ratio')
    if (latestRenewable && latestRenewable.value) {
      renewableRatio.value = latestRenewable.value || 0
    }
    
    // 获取未解决的能源警报数量
    const unresolvedAlerts = await energyAlertApi.getEnergyAlerts({ status: 'active' })
    if (unresolvedAlerts) {
      alertCount.value = unresolvedAlerts.length || 0
    }
    
    // 设置电网状态（这里可以根据实际业务逻辑设置）
    // gridStatus.value = alertCount.value > 0 ? '警告' : '正常'
    
    // 更新能源指标数据
    energyData.value.totalConsumption.value = totalConsumption.value
    energyData.value.renewableRatio.value = renewableRatio.value
    energyData.value.efficiency.value = 87
    energyData.value.costSaving.value = 125
    
    // 模拟能源分布数据（实际应用中应该从API获取）
    energyDistribution.value = [
      { type: '太阳能', value: 25, color: '#f59e0b' },
      { type: '风能', value: 20, color: '#0ea5e9' },
      { type: '水电', value: 15, color: '#06b6d4' },
      { type: '天然气', value: 25, color: '#8b5cf6' },
      { type: '煤炭', value: 15, color: '#64748b' }
    ]
    
    // 模拟能耗趋势数据（实际应用中应该从API获取）
    consumptionTrend.value = {
      labels: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00'],
      datasets: [
        {
          label: '总能耗 (MW)',
          data: [1200, 800, 1500, 1800, 1600, 1300],
          borderColor: '#3b82f6',
          backgroundColor: 'rgba(59, 130, 246, 0.1)',
          tension: 0.4,
          fill: true
        }
      ]
    }
  } catch (error) {
    console.error('加载能源数据失败:', error)
  }
}

// 加载能源分布数据
const loadEnergyDistribution = async () => {
  try {
    const response = await renewableEnergyApi.getRenewableEnergies({})
    const distribution: Record<string, number> = {}
    
    response.forEach(item => {
      const type = item.type
      if (!distribution[type]) {
        distribution[type] = 0
      }
      distribution[type] += item.capacity
    })
    
    // 更新能源分布数据
    energyDistribution.value = Object.keys(distribution).map(type => ({
      type,
      value: distribution[type] || 0,
      color: getColorByType(type)
    }))
  } catch (error) {
    console.error('Failed to load energy distribution data:', error)
  }
}

// 加载电网状态数据
const loadGridStatus = async () => {
  try {
    // 模拟API调用
    await new Promise(resolve => setTimeout(resolve, 300))
    
    // 模拟数据
    gridStatus.value = [
      {
        id: 1,
        name: '市中心变电站',
        status: 'normal',
        load: 75,
        capacity: 100
      },
      {
        id: 2,
        name: '高新区变电站',
        status: 'warning',
        load: 88,
        capacity: 100
      },
      {
        id: 3,
        name: '开发区变电站',
        status: 'critical',
        load: 95,
        capacity: 100
      }
    ]
  } catch (error) {
    console.error('加载电网状态数据失败:', error)
  }
}

// 加载能源警报数据
const loadEnergyAlerts = async () => {
  try {
    const response = await energyAlertApi.getEnergyAlerts({ status: 'active' })
    energyAlerts.value = response.slice(0, 5) // 只显示最近5条警报
  } catch (error) {
    console.error('Failed to load energy alerts:', error)
  }
}

// 根据能源类型获取颜色
const getColorByType = (type: string) => {
  const colors: Record<string, string> = {
    '太阳能': '#f59e0b',
    '风能': '#0ea5e9',
    '水电': '#06b6d4',
    '天然气': '#8b5cf6',
    '煤炭': '#64748b'
  }
  return colors[type] || '#94a3b8'
}

// 计算饼图偏移量
const calculateOffset = (index: number) => {
  let offset = 0
  for (let i = 0; i < index; i++) {
    offset += energyDistribution.value[i]?.value || 0
  }
  return -offset
}

// 获取状态文本
const getStatusText = (status: 'normal' | 'warning' | 'critical' | 'offline') => {
  const statusMap: Record<'normal' | 'warning' | 'critical' | 'offline', string> = {
    'normal': '正常',
    'warning': '警告',
    'critical': '危险',
    'offline': '离线'
  }
  return statusMap[status] || '未知'
}

// 根据负载获取类名
const getLoadClass = (load: number): string => {
  if (load < 60) return 'low';
  if (load < 85) return 'medium';
  return 'high';
}

onMounted(() => {
  loadEnergyOverview()
  loadEnergyDistribution()
  loadGridStatus()
  loadEnergyAlerts()
})
</script>

<template>
  <div class="energy-dashboard">
    <div class="dashboard-header">
      <h1>智慧能源</h1>
      <div class="header-actions">
        <button class="btn btn-primary">能源调度</button>
        <button class="btn btn-secondary">报表导出</button>
      </div>
    </div>
    
    <!-- 能源指标概览 -->
    <div class="metrics-grid">
      <ModuleCard
        title="总能耗"
        :value="energyData.totalConsumption.value"
        :unit="energyData.totalConsumption.unit"
        :trend="energyData.totalConsumption.trend"
        :trend-value="energyData.totalConsumption.trendValue"
        icon="M13 10V3L4 14h7v7l9-11h-7z"
        color="#f59e0b"
      />
      
      <ModuleCard
        title="可再生能源占比"
        :value="energyData.renewableRatio.value"
        :unit="energyData.renewableRatio.unit"
        :trend="energyData.renewableRatio.trend"
        :trend-value="energyData.renewableRatio.trendValue"
        icon="M12 3v1m0 16v1m9-9h-1M4 12H3m15.364 6.364l-.707-.707M6.343 6.343l-.707-.707m12.728 0l-.707.707M6.343 17.657l-.707.707M16 12a4 4 0 11-8 0 4 4 0 018 0z"
        color="#10b981"
      />
      
      <ModuleCard
        title="能源效率"
        :value="energyData.efficiency.value"
        :unit="energyData.efficiency.unit"
        :trend="energyData.efficiency.trend"
        icon="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"
        color="#0ea5e9"
      />
      
      <ModuleCard
        title="节约成本"
        :value="energyData.costSaving.value"
        :unit="energyData.costSaving.unit"
        :trend="energyData.costSaving.trend"
        :trend-value="energyData.costSaving.trendValue"
        icon="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
        color="#8b5cf6"
      />
    </div>
    
    <!-- 能源分布和电网状态 -->
    <div class="section-row">
      <!-- 能源分布饼图 -->
      <div class="section half">
        <h2>能源类型分布</h2>
        <div class="chart-container">
          <div class="pie-chart">
            <svg viewBox="0 0 100 100" class="pie-svg">
              <circle cx="50" cy="50" r="40" fill="none" stroke="#eee" stroke-width="20" />
              <circle 
                v-for="(item, index) in energyDistribution" 
                :key="index"
                cx="50" 
                cy="50" 
                r="40" 
                fill="none" 
                :stroke="item.color" 
                stroke-width="20"
                :stroke-dasharray="`${item.value} ${100 - item.value}`"
                :stroke-dashoffset="calculateOffset(index)"
                class="pie-segment"
              />
            </svg>
            <div class="chart-center">
              <div class="center-value">100%</div>
              <div class="center-label">总装机容量</div>
            </div>
          </div>
          <div class="legend">
            <div 
              v-for="(item, index) in energyDistribution" 
              :key="index"
              class="legend-item"
            >
              <div class="legend-color" :style="{ backgroundColor: item.color }"></div>
              <div class="legend-label">{{ item.type }}: {{ item.value }}%</div>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 电网状态 -->
      <div class="section half">
        <h2>电网状态</h2>
        <div class="grid-status">
          <div 
            v-for="station in gridStatus" 
            :key="station.id"
            class="station-card"
            :class="station.status"
          >
            <div class="station-header">
              <h3>{{ station.name }}</h3>
              <span class="status-badge" :class="station.status">
                {{ getStatusText(station.status) }}
              </span>
            </div>
            <div class="station-content">
              <div class="load-meter">
                <div class="load-bar">
                  <div 
                    class="load-fill" 
                    :style="{ width: `${station.load}%` }"
                    :class="getLoadClass(station.load)"
                  ></div>
                </div>
                <div class="load-text">{{ station.load }}% / {{ station.capacity }}MW</div>
              </div>
              <div class="station-actions">
                <button class="btn btn-sm btn-outline">详情</button>
                <button class="btn btn-sm btn-primary">调度</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 能耗趋势 -->
    <div class="section">
      <h2>能耗趋势</h2>
      <div class="chart-placeholder">
        <div class="chart-info">
          <h3>能耗变化趋势图</h3>
          <p>展示过去6个月的能耗变化情况</p>
        </div>
        <!-- 在实际项目中，这里会集成真正的图表库如Chart.js -->
        <div class="trend-bars">
          <div 
            v-for="(value, index) in consumptionTrend.datasets[0]?.data || []" 
            :key="index"
            class="bar-container"
          >
            <div 
              class="bar" 
              :style="{ height: `${(value / 1500) * 200}px` }"
              :class="index === (consumptionTrend.datasets[0]?.data?.length || 0) - 1 ? 'current' : ''"
            ></div>
            <div class="bar-label">{{ consumptionTrend.labels[index] }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.energy-dashboard {
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
  gap: 20px;
  align-items: center;
}

.pie-chart {
  position: relative;
  width: 200px;
  height: 200px;
}

.pie-svg {
  width: 100%;
  height: 100%;
  transform: rotate(-90deg);
}

.pie-segment {
  transition: stroke-dasharray 0.3s ease;
}

.chart-center {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.center-value {
  font-size: 1.5rem;
  font-weight: 700;
  color: var(--text-primary);
}

.center-label {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.legend {
  flex: 1;
}

.legend-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.legend-color {
  width: 20px;
  height: 20px;
  border-radius: 4px;
  margin-right: 10px;
}

.legend-label {
  font-size: 0.9rem;
  color: var(--text-primary);
}

.grid-status {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.station-card {
  border-radius: 8px;
  padding: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.station-card.normal {
  border-left: 4px solid var(--warning-color);
}

.station-card.success {
  border-left: 4px solid var(--success-color);
}

.station-card.warning {
  border-left: 4px solid var(--danger-color);
}

.station-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.station-header h3 {
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

.status-badge.normal {
  background-color: var(--warning-color-light);
  color: var(--warning-color);
}

.status-badge.success {
  background-color: var(--success-color-light);
  color: var(--success-color);
}

.status-badge.warning {
  background-color: var(--danger-color-light);
  color: var(--danger-color);
}

.load-meter {
  margin-bottom: 15px;
}

.load-bar {
  height: 10px;
  background-color: var(--background-color);
  border-radius: 5px;
  overflow: hidden;
  margin-bottom: 8px;
}

.load-fill {
  height: 100%;
  border-radius: 5px;
  transition: width 0.5s ease;
}

.load-fill.low {
  background-color: var(--success-color);
}

.load-fill.medium {
  background-color: var(--warning-color);
}

.load-fill.high {
  background-color: var(--danger-color);
}

.load-text {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.station-actions {
  display: flex;
  gap: 10px;
}

.chart-placeholder {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  border: 1px solid var(--border-color);
}

.chart-info {
  margin-bottom: 20px;
}

.chart-info h3 {
  margin: 0 0 8px 0;
  font-size: 1.2rem;
  color: var(--text-primary);
}

.chart-info p {
  margin: 0;
  color: var(--text-secondary);
  font-size: 0.9rem;
}

.trend-bars {
  display: flex;
  align-items: flex-end;
  height: 220px;
  gap: 20px;
  padding: 20px 0;
}

.bar-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex: 1;
}

.bar {
  width: 40px;
  background-color: var(--primary-color-light);
  border-radius: 4px 4px 0 0;
  transition: height 0.5s ease;
}

.bar.current {
  background-color: var(--primary-color);
}

.bar-label {
  margin-top: 10px;
  font-size: 0.8rem;
  color: var(--text-secondary);
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
  .energy-dashboard {
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
  
  .chart-container {
    flex-direction: column;
  }
  
  .trend-bars {
    gap: 10px;
  }
  
  .bar {
    width: 20px;
  }
}
</style>