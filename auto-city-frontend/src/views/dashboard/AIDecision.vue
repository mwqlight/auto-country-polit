<script setup lang="ts">
import { ref, onMounted } from 'vue'
import ModuleCard from '@/components/dashboard/ModuleCard.vue'

// AI分析数据
const aiAnalysis = ref({
  predictionAccuracy: {
    value: 92.5,
    unit: '%',
    status: 'success' as const
  },
  anomalyDetection: {
    value: 24,
    unit: '条',
    trend: 'up' as const,
    trendValue: '+5'
  },
  optimizationSuggestions: {
    value: 18,
    unit: '条',
    trend: 'up' as const,
    trendValue: '+3'
  },
  riskAssessment: {
    value: 87,
    unit: '分',
    status: 'warning' as const
  }
})

// 预测模型
const predictionModels = ref([
  { name: '交通流量预测', accuracy: 94.2, lastUpdate: '2023-06-15' },
  { name: '空气质量预测', accuracy: 89.7, lastUpdate: '2023-06-15' },
  { name: '能耗预测', accuracy: 91.5, lastUpdate: '2023-06-14' },
  { name: '人口流动预测', accuracy: 88.3, lastUpdate: '2023-06-14' }
])

// 异常检测
const anomalies = ref([
  {
    id: 1,
    type: '交通异常',
    location: '中山路与解放路交叉口',
    severity: '高',
    time: '2023-06-15 14:30',
    status: '待处理'
  },
  {
    id: 2,
    type: '能耗异常',
    location: '市政府大楼',
    severity: '中',
    time: '2023-06-15 12:15',
    status: '处理中'
  },
  {
    id: 3,
    type: '环境异常',
    location: '工业园区',
    severity: '高',
    time: '2023-06-15 10:45',
    status: '已处理'
  },
  {
    id: 4,
    type: '安防异常',
    location: '商业中心',
    severity: '低',
    time: '2023-06-15 09:20',
    status: '待处理'
  }
])

// 优化建议
const optimizationSuggestions = ref([
  {
    id: 1,
    category: '交通优化',
    suggestion: '调整中山路信号灯配时',
    impact: '预计通行效率提升15%',
    priority: '高'
  },
  {
    id: 2,
    category: '能源优化',
    suggestion: '优化市政府大楼空调运行策略',
    impact: '预计节能8%',
    priority: '中'
  },
  {
    id: 3,
    category: '环境优化',
    suggestion: '增加工业园区绿化覆盖率',
    impact: '预计PM2.5降低5%',
    priority: '中'
  }
])

// 风险评估
const riskAssessment = ref({
  overall: 87,
  categories: [
    { name: '自然灾害风险', score: 75, trend: 'down' },
    { name: '社会安全风险', score: 92, trend: 'stable' },
    { name: '经济风险', score: 85, trend: 'up' },
    { name: '技术风险', score: 68, trend: 'down' }
  ]
})

onMounted(() => {
  // 模拟数据加载
  console.log('AI Decision Support dashboard mounted')
})
</script>

<template>
  <div class="ai-decision-dashboard">
    <div class="dashboard-header">
      <h1>AI决策支持</h1>
      <div class="header-actions">
        <button class="btn btn-primary">模型训练</button>
        <button class="btn btn-secondary">参数调优</button>
      </div>
    </div>
    
    <!-- AI分析指标 -->
    <div class="metrics-grid">
      <ModuleCard
        title="预测准确率"
        :value="aiAnalysis.predictionAccuracy.value"
        :unit="aiAnalysis.predictionAccuracy.unit"
        :status="aiAnalysis.predictionAccuracy.status"
        icon="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"
        color="#10b981"
      />
      
      <ModuleCard
        title="异常检测"
        :value="aiAnalysis.anomalyDetection.value"
        :unit="aiAnalysis.anomalyDetection.unit"
        :trend="aiAnalysis.anomalyDetection.trend"
        :trend-value="aiAnalysis.anomalyDetection.trendValue"
        icon="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"
        color="#f59e0b"
      />
      
      <ModuleCard
        title="优化建议"
        :value="aiAnalysis.optimizationSuggestions.value"
        :unit="aiAnalysis.optimizationSuggestions.unit"
        :trend="aiAnalysis.optimizationSuggestions.trend"
        :trend-value="aiAnalysis.optimizationSuggestions.trendValue"
        icon="M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z"
        color="#3b82f6"
      />
      
      <ModuleCard
        title="风险评分"
        :value="aiAnalysis.riskAssessment.value"
        :unit="aiAnalysis.riskAssessment.unit"
        :status="aiAnalysis.riskAssessment.status"
        icon="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"
        color="#ef4444"
      />
    </div>
    
    <!-- 预测模型和异常检测 -->
    <div class="section-row">
      <!-- 预测模型 -->
      <div class="section half">
        <h2>预测模型</h2>
        <div class="models-table">
          <div class="table-header">
            <div class="header-cell">模型名称</div>
            <div class="header-cell">准确率</div>
            <div class="header-cell">最后更新</div>
          </div>
          <div 
            v-for="(model, index) in predictionModels" 
            :key="index"
            class="table-row"
          >
            <div class="cell">{{ model.name }}</div>
            <div class="cell">
              <div class="accuracy-bar">
                <div 
                  class="accuracy-fill" 
                  :style="{ width: `${model.accuracy}%` }"
                ></div>
                <span class="accuracy-text">{{ model.accuracy }}%</span>
              </div>
            </div>
            <div class="cell">{{ model.lastUpdate }}</div>
          </div>
        </div>
      </div>
      
      <!-- 异常检测 -->
      <div class="section half">
        <h2>异常检测</h2>
        <div class="anomalies-list">
          <div 
            v-for="anomaly in anomalies" 
            :key="anomaly.id"
            class="anomaly-item"
            :class="anomaly.status"
          >
            <div class="anomaly-header">
              <div class="anomaly-type">{{ anomaly.type }}</div>
              <div class="anomaly-severity" :class="anomaly.severity">
                {{ anomaly.severity }}风险
              </div>
            </div>
            <div class="anomaly-details">
              <div class="detail-item">
                <span class="label">位置:</span>
                <span>{{ anomaly.location }}</span>
              </div>
              <div class="detail-item">
                <span class="label">时间:</span>
                <span>{{ anomaly.time }}</span>
              </div>
            </div>
            <div class="anomaly-footer">
              <span class="status-badge" :class="anomaly.status">
                {{ anomaly.status }}
              </span>
              <button class="btn btn-sm btn-outline">查看详情</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 优化建议和风险评估 -->
    <div class="section-row">
      <!-- 优化建议 -->
      <div class="section half">
        <h2>优化建议</h2>
        <div class="suggestions-list">
          <div 
            v-for="suggestion in optimizationSuggestions" 
            :key="suggestion.id"
            class="suggestion-item"
          >
            <div class="suggestion-header">
              <div class="category-badge" :class="suggestion.category">
                {{ suggestion.category }}
              </div>
              <div class="priority-badge" :class="suggestion.priority">
                {{ suggestion.priority }}优先级
              </div>
            </div>
            <div class="suggestion-content">
              <h3>{{ suggestion.suggestion }}</h3>
              <p class="impact">{{ suggestion.impact }}</p>
            </div>
            <div class="suggestion-actions">
              <button class="btn btn-sm btn-primary">采纳</button>
              <button class="btn btn-sm btn-secondary">忽略</button>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 风险评估 -->
      <div class="section half">
        <h2>风险评估</h2>
        <div class="risk-overall">
          <div class="overall-score">
            <div class="score-value">{{ riskAssessment.overall }}</div>
            <div class="score-label">综合风险评分</div>
          </div>
          <div class="score-description">
            <p>当前系统整体风险处于中等水平，建议重点关注社会安全风险和技术风险。</p>
          </div>
        </div>
        
        <div class="risk-categories">
          <div 
            v-for="(category, index) in riskAssessment.categories" 
            :key="index"
            class="category-item"
          >
            <div class="category-header">
              <span>{{ category.name }}</span>
              <span class="trend" :class="category.trend">
                <span v-if="category.trend === 'up'">↑</span>
                <span v-else-if="category.trend === 'down'">↓</span>
                <span v-else>→</span>
              </span>
            </div>
            <div class="category-score">
              <div class="score-bar">
                <div 
                  class="score-fill" 
                  :style="{ width: `${category.score}%` }"
                  :class="getRiskClass(category.score)"
                ></div>
              </div>
              <div class="score-text">{{ category.score }}分</div>
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
    getRiskClass(score: number): string {
      if (score >= 80) return 'high-risk';
      if (score >= 60) return 'medium-risk';
      return 'low-risk';
    }
  }
}
</script>

<style scoped>
.ai-decision-dashboard {
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

.models-table {
  border-radius: 8px;
  overflow: hidden;
  border: 1px solid var(--border-color);
}

.table-header {
  display: flex;
  background-color: var(--background-color);
  font-weight: 600;
  padding: 12px 16px;
}

.header-cell {
  flex: 1;
}

.table-row {
  display: flex;
  padding: 16px;
  border-bottom: 1px solid var(--border-color);
}

.table-row:last-child {
  border-bottom: none;
}

.cell {
  flex: 1;
  display: flex;
  align-items: center;
}

.accuracy-bar {
  position: relative;
  width: 100%;
  height: 20px;
  background-color: var(--background-color);
  border-radius: 10px;
  overflow: hidden;
}

.accuracy-fill {
  height: 100%;
  background: linear-gradient(90deg, var(--success-color), var(--primary-color));
  border-radius: 10px;
}

.accuracy-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 0.8rem;
  font-weight: 600;
  color: white;
}

.anomalies-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.anomaly-item {
  border-radius: 8px;
  padding: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.anomaly-item:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.anomaly-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.anomaly-type {
  font-weight: 600;
  color: var(--text-primary);
}

.anomaly-severity {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  font-weight: 500;
}

.anomaly-severity.高 {
  background-color: var(--danger-color-light);
  color: var(--danger-color);
}

.anomaly-severity.中 {
  background-color: var(--warning-color-light);
  color: var(--warning-color);
}

.anomaly-severity.低 {
  background-color: var(--success-color-light);
  color: var(--success-color);
}

.anomaly-details {
  margin-bottom: 15px;
}

.detail-item {
  display: flex;
  margin-bottom: 5px;
  font-size: 0.9rem;
}

.detail-item .label {
  width: 60px;
  color: var(--text-secondary);
}

.anomaly-footer {
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

.status-badge.待处理 {
  background-color: var(--warning-color-light);
  color: var(--warning-color);
}

.status-badge.处理中 {
  background-color: var(--primary-color-light);
  color: var(--primary-color);
}

.status-badge.已处理 {
  background-color: var(--success-color-light);
  color: var(--success-color);
}

.suggestions-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.suggestion-item {
  border-radius: 8px;
  padding: 16px;
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
}

.suggestion-item:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.suggestion-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 12px;
}

.category-badge {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  font-weight: 500;
}

.category-badge.交通优化 {
  background-color: var(--primary-color-light);
  color: var(--primary-color);
}

.category-badge.能源优化 {
  background-color: var(--success-color-light);
  color: var(--success-color);
}

.category-badge.环境优化 {
  background-color: var(--warning-color-light);
  color: var(--warning-color);
}

.priority-badge {
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  font-weight: 500;
}

.priority-badge.高 {
  background-color: var(--danger-color-light);
  color: var(--danger-color);
}

.priority-badge.中 {
  background-color: var(--warning-color-light);
  color: var(--warning-color);
}

.suggestion-content h3 {
  margin: 0 0 8px 0;
  font-size: 1.1rem;
  color: var(--text-primary);
}

.impact {
  margin: 0;
  color: var(--text-secondary);
  font-size: 0.9rem;
}

.suggestion-actions {
  display: flex;
  gap: 10px;
  margin-top: 15px;
}

.risk-overall {
  margin-bottom: 25px;
  padding-bottom: 25px;
  border-bottom: 1px solid var(--border-color);
}

.overall-score {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 15px;
}

.score-value {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background: conic-gradient(
    var(--danger-color) 0% 30%,
    var(--warning-color) 30% 70%,
    var(--success-color) 70% 100%
  );
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  font-weight: 700;
  color: white;
}

.score-label {
  font-size: 1.1rem;
  font-weight: 600;
  color: var(--text-primary);
}

.score-description p {
  margin: 0;
  color: var(--text-secondary);
  line-height: 1.5;
}

.risk-categories {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.category-item {
  padding: 12px;
  border-radius: 8px;
  border: 1px solid var(--border-color);
}

.category-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  font-weight: 500;
  color: var(--text-primary);
}

.trend.up {
  color: var(--danger-color);
}

.trend.down {
  color: var(--success-color);
}

.trend.stable {
  color: var(--text-secondary);
}

.category-score {
  display: flex;
  align-items: center;
  gap: 10px;
}

.score-bar {
  flex: 1;
  height: 8px;
  background-color: var(--background-color);
  border-radius: 4px;
  overflow: hidden;
}

.score-fill {
  height: 100%;
  border-radius: 4px;
}

.score-fill.high-risk {
  background-color: var(--danger-color);
}

.score-fill.medium-risk {
  background-color: var(--warning-color);
}

.score-fill.low-risk {
  background-color: var(--success-color);
}

.score-text {
  font-weight: 600;
  min-width: 50px;
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
  .ai-decision-dashboard {
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
}
</style>