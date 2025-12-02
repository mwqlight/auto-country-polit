<script setup lang="ts">
import { ref, onMounted } from 'vue'
import ModuleCard from '@/components/dashboard/ModuleCard.vue'

// 经济指标数据
const economicData = ref({
  gdpGrowth: {
    value: 6.8,
    unit: '%',
    trend: 'up' as const,
    trendValue: '+0.3%'
  },
  industrialOutput: {
    value: 1250,
    unit: '亿元',
    trend: 'up' as const,
    trendValue: '+2.1%'
  },
  foreignInvestment: {
    value: 85,
    unit: '亿美元',
    trend: 'up' as const,
    trendValue: '+12%'
  },
  employmentRate: {
    value: 96.5,
    unit: '%',
    status: 'success' as const
  }
})

// 产业分布
const industryDistribution = ref([
  { sector: '制造业', value: 35, growth: 5.2, color: '#3b82f6' },
  { sector: '服务业', value: 45, growth: 7.8, color: '#10b981' },
  { sector: '农业', value: 8, growth: 2.1, color: '#f59e0b' },
  { sector: '高新技术', value: 12, growth: 15.3, color: '#8b5cf6' }
])

// 重点项目
const keyProjects = ref([
  {
    id: 1,
    name: '智能制造产业园',
    investment: 50,
    progress: 75,
    status: '进行中'
  },
  {
    id: 2,
    name: '城市综合体',
    investment: 30,
    progress: 90,
    status: '收尾阶段'
  },
  {
    id: 3,
    name: '科技创新中心',
    investment: 25,
    progress: 40,
    status: '建设中'
  },
  {
    id: 4,
    name: '物流枢纽',
    investment: 20,
    progress: 60,
    status: '进行中'
  }
])

// 经济指标趋势
const economicTrends = ref({
  labels: ['1月', '2月', '3月', '4月', '5月', '6月'],
  datasets: [
    {
      label: 'GDP增长率 (%)',
      data: [6.2, 6.5, 6.7, 6.8, 6.9, 6.8],
      borderColor: '#3b82f6',
      backgroundColor: 'rgba(59, 130, 246, 0.1)',
      tension: 0.4,
      fill: true
    },
    {
      label: '工业产值 (亿元)',
      data: [1150, 1180, 1200, 1220, 1240, 1250],
      borderColor: '#10b981',
      backgroundColor: 'rgba(16, 185, 129, 0.1)',
      tension: 0.4,
      fill: true
    }
  ]
})

onMounted(() => {
  // 模拟数据加载
  console.log('Economic dashboard mounted')
})
</script>

<template>
  <div class="economic-dashboard">
    <div class="dashboard-header">
      <h1>经济发展</h1>
      <div class="header-actions">
        <button class="btn btn-primary">项目申报</button>
        <button class="btn btn-secondary">经济分析</button>
      </div>
    </div>
    
    <!-- 经济指标概览 -->
    <div class="metrics-grid">
      <ModuleCard
        title="GDP增长率"
        :value="economicData.gdpGrowth.value"
        :unit="economicData.gdpGrowth.unit"
        :trend="economicData.gdpGrowth.trend"
        :trend-value="economicData.gdpGrowth.trendValue"
        icon="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
        color="#3b82f6"
      />
      
      <ModuleCard
        title="工业总产值"
        :value="economicData.industrialOutput.value"
        :unit="economicData.industrialOutput.unit"
        :trend="economicData.industrialOutput.trend"
        :trend-value="economicData.industrialOutput.trendValue"
        icon="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"
        color="#10b981"
      />
      
      <ModuleCard
        title="外商投资"
        :value="economicData.foreignInvestment.value"
        :unit="economicData.foreignInvestment.unit"
        :trend="economicData.foreignInvestment.trend"
        :trend-value="economicData.foreignInvestment.trendValue"
        icon="M3 6l3 1m0 0l-3 9a5.002 5.002 0 006.001 0M6 7l3 9M6 7l6-2m6 2l3-1m-3 1l-3 9a5.002 5.002 0 006.001 0M18 7l3 9m-3-9l-6-2m0-2v2m0 16V5m0 16H9m3 0h3"
        color="#f59e0b"
      />
      
      <ModuleCard
        title="就业率"
        :value="economicData.employmentRate.value"
        :unit="economicData.employmentRate.unit"
        :status="economicData.employmentRate.status"
        icon="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"
        color="#8b5cf6"
      />
    </div>
    
    <!-- 产业分布和重点项目 -->
    <div class="section-row">
      <!-- 产业分布 -->
      <div class="section half">
        <h2>产业分布</h2>
        <div class="chart-container">
          <div class="industry-chart">
            <div 
              v-for="(item, index) in industryDistribution" 
              :key="index"
              class="industry-item"
            >
              <div class="industry-header">
                <div class="industry-info">
                  <div 
                    class="industry-color" 
                    :style="{ backgroundColor: item.color }"
                  ></div>
                  <h3>{{ item.sector }}</h3>
                </div>
                <div class="industry-value">{{ item.value }}%</div>
              </div>
              <div class="industry-progress">
                <div class="progress-bar">
                  <div 
                    class="progress-fill" 
                    :style="{ 
                      width: `${item.value}%`,
                      backgroundColor: item.color
                    }"
                  ></div>
                </div>
              </div>
              <div class="industry-growth">
                增长率: 
                <span class="growth-value" :class="item.growth > 0 ? 'positive' : 'negative'">
                  {{ item.growth > 0 ? '+' : '' }}{{ item.growth }}%
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 重点项目 -->
      <div class="section half">
        <h2>重点项目</h2>
        <div class="projects-list">
          <div 
            v-for="project in keyProjects" 
            :key="project.id"
            class="project-item"
          >
            <div class="project-header">
              <h3>{{ project.name }}</h3>
              <span class="investment">投资: {{ project.investment }}亿元</span>
            </div>
            <div class="project-progress">
              <div class="progress-text">进度: {{ project.progress }}%</div>
              <div class="progress-bar">
                <div 
                  class="progress-fill" 
                  :style="{ width: `${project.progress}%` }"
                  :class="getProgressClass(project.progress)"
                ></div>
              </div>
            </div>
            <div class="project-status">
              <span class="status-badge" :class="project.status">
                {{ project.status }}
              </span>
              <button class="btn btn-sm btn-outline">详情</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 经济趋势 -->
    <div class="section">
      <h2>经济趋势</h2>
      <div class="chart-placeholder">
        <div class="chart-info">
          <h3>经济发展趋势图</h3>
          <p>展示GDP增长率和工业产值的变化趋势</p>
        </div>
        <!-- 在实际项目中，这里会集成真正的图表库如Chart.js -->
        <div class="trend-charts">
          <div 
            v-for="(dataset, index) in economicTrends.datasets" 
            :key="index"
            class="trend-chart"
          >
            <h4>{{ dataset.label }}</h4>
            <div class="trend-line">
              <div 
                v-for="(value, i) in dataset.data" 
                :key="i"
                class="data-point"
                :style="{ 
                  left: `${(i / (dataset.data.length - 1)) * 100}%`,
                  bottom: `${(value / Math.max(...dataset.data)) * 80}%`
                }"
              ></div>
              <svg class="line-svg" viewBox="0 0 100 100">
                <path 
                  :d="generatePath(dataset.data)" 
                  :stroke="dataset.borderColor" 
                  stroke-width="2" 
                  fill="none"
                />
                <path 
                  :d="generateAreaPath(dataset.data)" 
                  :fill="dataset.backgroundColor"
                />
              </svg>
            </div>
            <div class="trend-labels">
              <div 
                v-for="(label, i) in economicTrends.labels" 
                :key="i"
                class="label-item"
                :style="{ left: `${(i / (economicTrends.labels.length - 1)) * 100}%` }"
              >
                {{ label }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  methods: {
    getProgressClass(progress: number): string {
      if (progress >= 80) return 'high';
      if (progress >= 50) return 'medium';
      return 'low';
    },
    generatePath(data: number[]): string {
      const points = data.map((value, index) => {
        const x = (index / (data.length - 1)) * 100;
        const y = 100 - (value / Math.max(...data)) * 80;
        return `${x},${y}`;
      });
      return `M${points.join(' L')}`;
    },
    generateAreaPath(data: number[]): string {
      const points = data.map((value, index) => {
        const x = (index / (data.length - 1)) * 100;
        const y = 100 - (value / Math.max(...data)) * 80;
        return `${x},${y}`;
      });
      return `M${points.join(' L')} L100,100 L0,100 Z`;
    }
  }
}
</script>

<style scoped>
.economic-dashboard {
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

.industry-chart {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.industry-item {
  padding: 16px;
  border-radius: 8px;
  border: 1px solid var(--border-color);
}

.industry-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.industry-info {
  display: flex;
  align-items: center;
  gap: 10px;
}

.industry-color {
  width: 12px;
  height: 12px;
  border-radius: 50%;
}

.industry-header h3 {
  margin: 0;
  font-size: 1rem;
  color: var(--text-primary);
}

.industry-value {
  font-weight: 600;
  color: var(--text-primary);
}

.industry-progress {
  margin-bottom: 10px;
}

.progress-bar {
  height: 8px;
  background-color: var(--background-color);
  border-radius: 4px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  border-radius: 4px;
  transition: width 0.5s ease;
}

.progress-fill.high {
  background-color: var(--success-color);
}

.progress-fill.medium {
  background-color: var(--warning-color);
}

.progress-fill.low {
  background-color: var(--danger-color);
}

.industry-growth {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.growth-value.positive {
  color: var(--success-color);
  font-weight: 600;
}

.growth-value.negative {
  color: var(--danger-color);
  font-weight: 600;
}

.projects-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.project-item {
  border-radius: 8px;
  padding: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.project-item:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.project-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.project-header h3 {
  margin: 0;
  font-size: 1.1rem;
  color: var(--text-primary);
}

.investment {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.project-progress {
  margin-bottom: 15px;
}

.progress-text {
  font-size: 0.9rem;
  color: var(--text-secondary);
  margin-bottom: 5px;
}

.project-status {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.status-badge {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  font-weight: 500;
}

.status-badge.进行中 {
  background-color: var(--warning-color-light);
  color: var(--warning-color);
}

.status-badge.收尾阶段 {
  background-color: var(--success-color-light);
  color: var(--success-color);
}

.status-badge.建设中 {
  background-color: var(--primary-color-light);
  color: var(--primary-color);
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

.trend-charts {
  display: flex;
  gap: 30px;
}

.trend-chart {
  flex: 1;
}

.trend-chart h4 {
  margin: 0 0 15px 0;
  font-size: 1rem;
  color: var(--text-primary);
}

.trend-line {
  position: relative;
  height: 200px;
  background-color: var(--background-color);
  border-radius: 8px;
  margin-bottom: 20px;
  overflow: hidden;
}

.data-point {
  position: absolute;
  width: 8px;
  height: 8px;
  background-color: var(--primary-color);
  border-radius: 50%;
  transform: translate(-50%, 50%);
}

.line-svg {
  width: 100%;
  height: 100%;
}

.trend-labels {
  position: relative;
  height: 20px;
}

.label-item {
  position: absolute;
  transform: translateX(-50%);
  font-size: 0.8rem;
  color: var(--text-secondary);
  white-space: nowrap;
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
  .economic-dashboard {
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
  
  .trend-charts {
    flex-direction: column;
    gap: 20px;
  }
}
</style>