<script setup lang="ts">
import { ref, onMounted } from 'vue'
import RealtimeChart from '@/components/dashboard/RealtimeChart.vue'
import type { RoadCondition } from '@/types/dashboard'

// 交通数据
const trafficData = ref({
  currentFlow: 1240,
  averageSpeed: 32,
  congestionIndex: 1.2,
  roadConditions: [
    { id: 1, road: '中山路', flow: 1800, speed: 25, status: 'congested' },
    { id: 2, road: '解放大道', flow: 1200, speed: 35, status: 'normal' },
    { id: 3, road: '长江隧道', flow: 800, speed: 45, status: 'smooth' },
    { id: 4, road: '武汉大道', flow: 2100, speed: 20, status: 'congested' },
    { id: 5, road: '二环线', flow: 1500, speed: 30, status: 'normal' }
  ] as RoadCondition[]
})

// 获取状态标签
const getStatusLabel = (status: 'congested' | 'normal' | 'smooth' | string) => {
  switch (status) {
    case 'congested': return '拥堵'
    case 'normal': return '正常'
    case 'smooth': return '畅通'
    default: return '未知'
  }
}

// 获取状态颜色
const getStatusColor = (status: 'congested' | 'normal' | 'smooth' | string) => {
  switch (status) {
    case 'congested': return '#ef4444'
    case 'normal': return '#f59e0b'
    case 'smooth': return '#10b981'
    default: return '#666'
  }
}

onMounted(() => {
  // 模拟数据加载
  console.log('Traffic flow dashboard mounted')
})
</script>

<template>
  <div class="traffic-flow-dashboard">
    <div class="dashboard-header">
      <h1>交通流量监控</h1>
      <div class="header-actions">
        <button class="btn btn-primary">刷新数据</button>
        <button class="btn btn-secondary">导出报表</button>
      </div>
    </div>
    
    <!-- 关键指标 -->
    <div class="kpi-grid">
      <div class="kpi-card">
        <div class="kpi-title">当前车流量</div>
        <div class="kpi-value">{{ trafficData.currentFlow }} 辆/小时</div>
        <div class="kpi-change positive">+5.2%</div>
      </div>
      <div class="kpi-card">
        <div class="kpi-title">平均车速</div>
        <div class="kpi-value">{{ trafficData.averageSpeed }} km/h</div>
        <div class="kpi-change negative">-2.1%</div>
      </div>
      <div class="kpi-card">
        <div class="kpi-title">拥堵指数</div>
        <div class="kpi-value">{{ trafficData.congestionIndex }}</div>
        <div class="kpi-change positive">+0.1</div>
      </div>
    </div>
    
    <!-- 实时图表 -->
    <div class="chart-section">
      <h2>实时交通趋势</h2>
      <RealtimeChart />
    </div>
    
    <!-- 道路状况表格 -->
    <div class="road-table-section">
      <h2>主要道路状况</h2>
      <div class="table-container">
        <table class="data-table">
          <thead>
            <tr>
              <th>道路名称</th>
              <th>车流量 (辆/小时)</th>
              <th>平均速度 (km/h)</th>
              <th>状态</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="road in trafficData.roadConditions" :key="road.id">
              <td>{{ road.road }}</td>
              <td>{{ road.flow }}</td>
              <td>{{ road.speed }}</td>
              <td>
                <span 
                  class="status-badge" 
                  :style="{ backgroundColor: getStatusColor(road.status) }"
                >
                  {{ getStatusLabel(road.status) }}
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
.traffic-flow-dashboard {
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

.header-actions {
  display: flex;
  gap: 10px;
}

.btn {
  padding: 8px 16px;
  border-radius: 6px;
  border: none;
  cursor: pointer;
  font-size: 0.9rem;
  transition: all 0.2s ease;
}

.btn-primary {
  background-color: var(--primary-color);
  color: white;
}

.btn-primary:hover {
  background-color: var(--primary-dark);
}

.btn-secondary {
  background-color: var(--secondary-color);
  color: white;
}

.btn-secondary:hover {
  background-color: var(--secondary-dark);
}

/* 关键指标 */
.kpi-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.kpi-card {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.kpi-title {
  font-size: 0.9rem;
  color: var(--text-secondary);
  margin-bottom: 8px;
}

.kpi-value {
  font-size: 1.8rem;
  font-weight: 700;
  color: var(--text-primary);
  margin-bottom: 8px;
}

.kpi-change {
  font-size: 0.9rem;
  font-weight: 500;
}

.kpi-change.positive {
  color: var(--success-color);
}

.kpi-change.negative {
  color: var(--danger-color);
}

/* 图表区域 */
.chart-section {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  margin-bottom: 30px;
}

.chart-section h2 {
  margin-top: 0;
  margin-bottom: 20px;
  color: var(--text-primary);
}

/* 表格区域 */
.road-table-section {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.road-table-section h2 {
  margin-top: 0;
  margin-bottom: 20px;
  color: var(--text-primary);
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
  padding: 12px 16px;
  text-align: left;
  border-bottom: 1px solid var(--border-color);
}

.data-table th {
  background-color: var(--table-header-bg);
  font-weight: 600;
  color: var(--text-primary);
}

.data-table tbody tr:hover {
  background-color: var(--hover-bg);
}

.status-badge {
  padding: 4px 8px;
  border-radius: 12px;
  color: white;
  font-size: 0.8rem;
  font-weight: 500;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .traffic-flow-dashboard {
    padding: 15px;
  }
  
  .dashboard-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }
  
  .kpi-grid {
    grid-template-columns: 1fr;
  }
  
  .data-table {
    font-size: 0.9rem;
  }
  
  .data-table th,
  .data-table td {
    padding: 8px 12px;
  }
}
</style>