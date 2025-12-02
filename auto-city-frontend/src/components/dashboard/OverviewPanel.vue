<script setup lang="ts">
import { ref, onMounted } from 'vue'
import RealtimeChart from './RealtimeChart.vue'

// 定义关键指标数据
const kpiData = ref([
  {
    id: 'traffic',
    title: '交通拥堵指数',
    value: '1.2',
    change: '+0.1',
    status: 'normal' as const,
    icon: 'M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z'
  },
  {
    id: 'air-quality',
    title: '空气质量指数',
    value: '42',
    change: '-3',
    status: 'good' as const,
    icon: 'M4.052 20.013A11.97 11.97 0 012.01 12.015 12.01 12.01 0 014.052 4.013 11.97 11.97 0 0112.01 2.01c2.717 0 5.324.85 7.5 2.418m-8.5 15.975a11.968 11.968 0 01-7.958-2.995 12.01 12.01 0 01-2.042-8.002 11.969 11.969 0 012.995-7.958'
  },
  {
    id: 'energy',
    title: '能源消耗',
    value: '1.2M kWh',
    change: '+2.3%',
    status: 'warning' as const,
    icon: 'M13 10V3L4 14h7v7l9-11h-7z'
  },
  {
    id: 'incidents',
    title: '今日事件',
    value: '3',
    change: '+1',
    status: 'critical' as const,
    icon: 'M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z'
  }
])

// 最近活动数据
const recentActivities = ref([
  { id: 1, action: '交通拥堵预警', module: '智慧交通', time: '2分钟前', status: 'warning' as const },
  { id: 2, action: '空气质量改善', module: '智慧环保', time: '15分钟前', status: 'success' as const },
  { id: 3, action: '新能源发电增加', module: '智慧能源', time: '30分钟前', status: 'info' as const },
  { id: 4, action: '安全检查完成', module: '公共安全', time: '1小时前', status: 'success' as const },
  { id: 5, action: '市民服务请求', module: '民生服务', time: '2小时前', status: 'info' as const }
])

// 获取KPI状态类名
const getStatusClass = (status: 'good' | 'warning' | 'critical' | 'normal') => {
  switch (status) {
    case 'good': return 'kpi-good'
    case 'warning': return 'kpi-warning'
    case 'critical': return 'kpi-critical'
    default: return 'kpi-normal'
  }
}

// 获取活动状态类名
const getActivityStatusClass = (status: 'success' | 'warning' | 'critical' | 'info') => {
  switch (status) {
    case 'success': return 'activity-success'
    case 'warning': return 'activity-warning'
    case 'critical': return 'activity-critical'
    default: return 'activity-info'
  }
}

onMounted(() => {
  // 模拟数据更新
  setInterval(() => {
    // 这里可以添加实时数据更新逻辑
  }, 30000)
})
</script>

<template>
  <div class="overview-panel">
    <div class="panel-header">
      <h2>城市运行总览</h2>
      <div class="date-display">
        {{ new Date().toLocaleDateString('zh-CN', { year: 'numeric', month: 'long', day: 'numeric', weekday: 'long' }) }}
      </div>
    </div>
    
    <!-- 关键指标 -->
    <div class="kpi-grid">
      <div 
        v-for="kpi in kpiData" 
        :key="kpi.id"
        class="kpi-card"
        :class="getStatusClass(kpi.status)"
      >
        <div class="kpi-icon">
          <svg 
            xmlns="http://www.w3.org/2000/svg" 
            width="24" 
            height="24" 
            viewBox="0 0 24 24" 
            fill="none" 
            stroke="currentColor" 
            stroke-width="2" 
            stroke-linecap="round" 
            stroke-linejoin="round"
          >
            <path :d="kpi.icon"></path>
          </svg>
        </div>
        <div class="kpi-content">
          <div class="kpi-title">{{ kpi.title }}</div>
          <div class="kpi-value">{{ kpi.value }}</div>
          <div class="kpi-change" :class="kpi.change.startsWith('+') ? 'positive' : 'negative'">
            {{ kpi.change }}
          </div>
        </div>
      </div>
    </div>
    
    <!-- 图表和活动区域 -->
    <div class="content-grid">
      <!-- 实时图表 -->
      <div class="chart-section">
        <h3>实时趋势</h3>
        <RealtimeChart />
      </div>
      
      <!-- 最近活动 -->
      <div class="activity-section">
        <h3>最近活动</h3>
        <div class="activity-list">
          <div 
            v-for="activity in recentActivities" 
            :key="activity.id"
            class="activity-item"
            :class="getActivityStatusClass(activity.status)"
          >
            <div class="activity-content">
              <div class="activity-action">{{ activity.action }}</div>
              <div class="activity-module">{{ activity.module }}</div>
            </div>
            <div class="activity-time">{{ activity.time }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.overview-panel {
  padding: 20px;
}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.panel-header h2 {
  margin: 0;
  font-size: 1.5rem;
  color: var(--text-primary);
}

.date-display {
  color: var(--text-secondary);
  font-size: 0.9rem;
}

/* 关键指标网格 */
.kpi-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.kpi-card {
  display: flex;
  align-items: center;
  padding: 20px;
  border-radius: 8px;
  background-color: var(--card-bg);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.kpi-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.kpi-icon {
  width: 50px;
  height: 50px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 16px;
  flex-shrink: 0;
}

.kpi-content {
  flex: 1;
}

.kpi-title {
  font-size: 0.9rem;
  color: var(--text-secondary);
  margin-bottom: 4px;
}

.kpi-value {
  font-size: 1.5rem;
  font-weight: 600;
  color: var(--text-primary);
  margin-bottom: 4px;
}

.kpi-change {
  font-size: 0.85rem;
}

.kpi-change.positive {
  color: var(--success-color);
}

.kpi-change.negative {
  color: var(--danger-color);
}

/* 不同状态的KPI卡片样式 */
.kpi-card.kpi-good .kpi-icon {
  background-color: var(--success-light);
  color: var(--success-color);
}

.kpi-card.kpi-warning .kpi-icon {
  background-color: var(--warning-light);
  color: var(--warning-color);
}

.kpi-card.kpi-critical .kpi-icon {
  background-color: var(--danger-light);
  color: var(--danger-color);
}

.kpi-card.kpi-normal .kpi-icon {
  background-color: var(--primary-light);
  color: var(--primary-color);
}

/* 内容网格 */
.content-grid {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 20px;
}

.chart-section, .activity-section {
  background-color: var(--card-bg);
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.chart-section h3, .activity-section h3 {
  margin-top: 0;
  margin-bottom: 16px;
  color: var(--text-primary);
  font-size: 1.1rem;
}

/* 活动列表 */
.activity-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.activity-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  border-radius: 6px;
  transition: background-color 0.2s ease;
}

.activity-item:hover {
  background-color: var(--hover-bg);
}

.activity-content {
  flex: 1;
}

.activity-action {
  font-weight: 500;
  color: var(--text-primary);
  margin-bottom: 4px;
}

.activity-module {
  font-size: 0.85rem;
  color: var(--text-secondary);
}

.activity-time {
  font-size: 0.8rem;
  color: var(--text-secondary);
  white-space: nowrap;
  margin-left: 12px;
}

/* 活动状态颜色 */
.activity-item.activity-success {
  border-left: 3px solid var(--success-color);
}

.activity-item.activity-warning {
  border-left: 3px solid var(--warning-color);
}

.activity-item.activity-critical {
  border-left: 3px solid var(--danger-color);
}

.activity-item.activity-info {
  border-left: 3px solid var(--primary-color);
}

/* 响应式设计 */
@media (max-width: 992px) {
  .content-grid {
    grid-template-columns: 1fr;
  }
  
  .kpi-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .overview-panel {
    padding: 15px;
  }
  
  .kpi-grid {
    grid-template-columns: 1fr;
  }
  
  .panel-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }
}
</style>