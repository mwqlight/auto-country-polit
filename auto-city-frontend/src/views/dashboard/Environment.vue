<script setup lang="ts">
import { ref, onMounted } from 'vue'
import ModuleCard from '@/components/dashboard/ModuleCard.vue'
import { environmentalApi, pollutionSourceApi } from '@/api/modules/environmental'
import type { EnvironmentalData, PollutionSource } from '@/types/dashboard'

// 环境指标数据
const environmentData = ref({
  airQuality: {
    value: 0,
    status: 'success' as const,
    trend: 'stable' as const,
    unit: '指数'
  },
  waterQuality: {
    value: 0,
    status: 'success' as const,
    trend: 'stable' as const,
    unit: '指数'
  },
  noiseLevel: {
    value: 0,
    status: 'success' as const,
    trend: 'stable' as const,
    unit: 'dB'
  },
  wasteProcessing: {
    value: 0,
    status: 'success' as const,
    trend: 'stable' as const,
    unit: '%'
  }
})

// 监测点数据
const monitoringPoints = ref<Array<{
  id: number
  name: string
  airQuality: number
  waterQuality: number
  noise: number
  status: 'success' | 'warning' | 'normal' | 'critical'
}>>([])

// 污染源数据
const pollutionSources = ref<PollutionSource[]>([])

// 加载环境数据
const loadEnvironmentData = async () => {
  try {
    // 获取最新的空气质量数据
    const airQualityData = await environmentalApi.getLatestEnvironmentalData('air_quality')
    environmentData.value.airQuality.value = airQualityData.value
    
    // 获取最新的水质数据
    const waterQualityData = await environmentalApi.getLatestEnvironmentalData('water_quality')
    environmentData.value.waterQuality.value = waterQualityData.value
    
    // 获取最新的噪音数据
    const noiseData = await environmentalApi.getLatestEnvironmentalData('noise')
    environmentData.value.noiseLevel.value = noiseData.value
    
    // 获取最新的废物处理率数据
    const wasteData = await environmentalApi.getLatestEnvironmentalData('waste_processing')
    environmentData.value.wasteProcessing.value = wasteData.value
  } catch (error) {
    console.error('Failed to load environment data:', error)
  }
}

// 加载监测点数据
const loadMonitoringPoints = async () => {
  try {
    // 这里应该调用获取监测点数据的API
    // 暂时使用模拟数据
    monitoringPoints.value = [
      {
        id: 1,
        name: '市中心监测点',
        airQuality: 82,
        waterQuality: 88,
        noise: 48,
        status: 'normal'
      },
      {
        id: 2,
        name: '工业区监测点',
        airQuality: 75,
        waterQuality: 85,
        noise: 62,
        status: 'warning'
      },
      {
        id: 3,
        name: '居民区监测点',
        airQuality: 92,
        waterQuality: 95,
        noise: 38,
        status: 'success'
      },
      {
        id: 4,
        name: '公园监测点',
        airQuality: 95,
        waterQuality: 98,
        noise: 32,
        status: 'success'
      }
    ]
  } catch (error) {
    console.error('Failed to load monitoring points:', error)
  }
}

// 加载污染源数据
const loadPollutionSources = async () => {
  try {
    const response = await pollutionSourceApi.getPollutionSources({})
    pollutionSources.value = response
  } catch (error) {
    console.error('Failed to load pollution sources:', error)
  }
}

onMounted(() => {
  loadEnvironmentData()
  loadMonitoringPoints()
  loadPollutionSources()
})
</script>

<template>
  <div class="environment-dashboard">
    <div class="dashboard-header">
      <h1>智慧环保</h1>
      <div class="header-actions">
        <button class="btn btn-primary">添加监测点</button>
        <button class="btn btn-secondary">导出数据</button>
      </div>
    </div>
    
    <!-- 环境指标概览 -->
    <div class="metrics-grid">
      <ModuleCard
        title="空气质量"
        :value="environmentData.airQuality.value"
        :unit="environmentData.airQuality.unit"
        :trend="environmentData.airQuality.trend"
        :status="environmentData.airQuality.status"
        icon="M4.052 20.013A11.97 11.97 0 012.01 12.015 12.01 12.01 0 014.052 4.013 11.97 11.97 0 0112.01 2.01c2.717 0 5.324.85 7.5 2.418m-8.5 15.975a11.968 11.968 0 01-7.958-2.995 12.01 12.01 0 01-2.042-8.002 11.969 11.969 0 012.995-7.958"
        color="#10b981"
      />
      
      <ModuleCard
        title="水质指数"
        :value="environmentData.waterQuality.value"
        :unit="environmentData.waterQuality.unit"
        :trend="environmentData.waterQuality.trend"
        :status="environmentData.waterQuality.status"
        icon="M19.428 15.428a2 2 0 00-1.022-.547l-2.387-.477a6 6 0 00-3.86.517l-.318.158a6 6 0 01-3.86.517L6.05 15.21a2 2 0 00-1.806.547M8 4h8l-1 1v5.172a2 2 0 00.586 1.414l5 5c1.26 1.26.367 3.414-1.415 3.414H4.828c-1.782 0-2.674-2.154-1.414-3.414l5-5A2 2 0 009 10.172V5L8 4z"
        color="#0ea5e9"
      />
      
      <ModuleCard
        title="噪音水平"
        :value="environmentData.noiseLevel.value"
        :unit="environmentData.noiseLevel.unit"
        :trend="environmentData.noiseLevel.trend"
        :status="environmentData.noiseLevel.status"
        icon="M15.536 8.464a5 5 0 010 7.072m2.828-9.9a9 9 0 010 12.728M5.586 15.536a5 5 0 001.414 1.414m-2.828-9.9a9 9 0 012.828-2.828"
        color="#f59e0b"
      />
      
      <ModuleCard
        title="废物处理率"
        :value="environmentData.wasteProcessing.value"
        :unit="environmentData.wasteProcessing.unit"
        :trend="environmentData.wasteProcessing.trend"
        :status="environmentData.wasteProcessing.status"
        icon="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
        color="#8b5cf6"
      />
    </div>
    
    <!-- 监测点列表 -->
    <div class="section">
      <h2>环境监测点</h2>
      <div class="table-container">
        <table class="data-table">
          <thead>
            <tr>
              <th>监测点名称</th>
              <th>空气质量</th>
              <th>水质指数</th>
              <th>噪音(dB)</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="point in monitoringPoints" :key="point.id">
              <td>{{ point.name }}</td>
              <td>
                <span class="metric-value" :class="point.airQuality >= 90 ? 'good' : point.airQuality >= 70 ? 'warning' : 'poor'">
                  {{ point.airQuality }}
                </span>
              </td>
              <td>
                <span class="metric-value" :class="point.waterQuality >= 90 ? 'good' : point.waterQuality >= 70 ? 'warning' : 'poor'">
                  {{ point.waterQuality }}
                </span>
              </td>
              <td>
                <span class="metric-value" :class="point.noise <= 40 ? 'good' : point.noise <= 60 ? 'warning' : 'poor'">
                  {{ point.noise }}
                </span>
              </td>
              <td>
                <span class="status-badge" :class="point.status">
                  {{ point.status === 'success' ? '正常' : point.status === 'warning' ? '警告' : '异常' }}
                </span>
              </td>
              <td>
                <button class="btn btn-sm btn-outline">详情</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    
    <!-- 污染源监控 -->
    <div class="section">
      <h2>污染源监控</h2>
      <div class="table-container">
        <table class="data-table">
          <thead>
            <tr>
              <th>污染源名称</th>
              <th>类型</th>
              <th>排放等级</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="source in pollutionSources" :key="source.id">
              <td>{{ source.name }}</td>
              <td>{{ source.type }}</td>
              <td>{{ source.emissionLevel }}</td>
              <td>
                <span class="status-badge" :class="source.status">
                  {{ source.status === 'controlled' ? '受控' : source.status === 'monitored' ? '监控中' : '异常' }}
                </span>
              </td>
              <td>
                <button class="btn btn-sm btn-outline">详情</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
.environment-dashboard {
  padding: 20px;
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.dashboard-header h1 {
  font-size: 24px;
  font-weight: 600;
  color: #1f2937;
}

.header-actions {
  display: flex;
  gap: 10px;
}

.metrics-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.section {
  background: white;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin-bottom: 20px;
}

.section h2 {
  font-size: 18px;
  font-weight: 600;
  color: #1f2937;
  margin-bottom: 15px;
}

.table-container {
  overflow-x: auto;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
}

.data-table th,
.data-table td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #e5e7eb;
}

.data-table th {
  background-color: #f9fafb;
  font-weight: 600;
  color: #6b7280;
}

.metric-value.good {
  color: #10b981;
}

.metric-value.warning {
  color: #f59e0b;
}

.metric-value.poor {
  color: #ef4444;
}

.status-badge {
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.status-badge.success {
  background-color: #dcfce7;
  color: #166534;
}

.status-badge.warning {
  background-color: #fef3c7;
  color: #92400e;
}

.status-badge.normal {
  background-color: #dbeafe;
  color: #1e40af;
}

.btn {
  padding: 8px 16px;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  border: none;
  transition: all 0.2s;
}

.btn-primary {
  background-color: #3b82f6;
  color: white;
}

.btn-secondary {
  background-color: #6b7280;
  color: white;
}

.btn-outline {
  background-color: transparent;
  border: 1px solid #d1d5db;
  color: #374151;
}

.btn-sm {
  padding: 4px 8px;
  font-size: 12px;
}
</style>