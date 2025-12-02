<script setup lang="ts">
import { ref, onMounted } from 'vue'

// 定义事件类型
interface Incident {
  id: number
  type: string
  severity: 'high' | 'medium' | 'low'
  location: string
  time: string
  status: '处理中' | '进行中' | '已结束' | '已处理'
  description: string
  reporter: string
}

// 事件数据
const incidents = ref<Incident[]>([
  {
    id: 1,
    type: '交通事故',
    severity: 'high',
    location: '中山路与解放大道交叉口',
    time: '2023-05-15 14:30',
    status: '处理中',
    description: '三车追尾事故，造成交通拥堵',
    reporter: '交通监控系统'
  },
  {
    id: 2,
    type: '道路施工',
    severity: 'medium',
    location: '长江隧道入口',
    time: '2023-05-15 09:15',
    status: '进行中',
    description: '隧道入口维修施工，车道临时封闭',
    reporter: '市政工程部'
  },
  {
    id: 3,
    type: '交通管制',
    severity: 'medium',
    location: '武汉大道',
    time: '2023-05-15 08:00',
    status: '已结束',
    description: '因大型活动实施临时交通管制',
    reporter: '交警支队'
  },
  {
    id: 4,
    type: '车辆故障',
    severity: 'low',
    location: '二环线路段',
    time: '2023-05-15 07:45',
    status: '已处理',
    description: '私家车抛锚，已拖离现场',
    reporter: '交通巡逻员'
  }
])

// 严重程度标签
const getSeverityLabel = (severity: 'high' | 'medium' | 'low') => {
  switch (severity) {
    case 'high': return '高'
    case 'medium': return '中'
    case 'low': return '低'
    default: return '未知'
  }
}

// 严重程度颜色
const getSeverityColor = (severity: 'high' | 'medium' | 'low') => {
  switch (severity) {
    case 'high': return '#ef4444'
    case 'medium': return '#f59e0b'
    case 'low': return '#10b981'
    default: return '#666'
  }
}

// 状态标签
const getStatusLabel = (status: '处理中' | '进行中' | '已结束' | '已处理') => {
  switch (status) {
    case '处理中': return 'processing'
    case '进行中': return 'processing'
    case '已结束': return 'completed'
    case '已处理': return 'completed'
    default: return 'pending'
  }
}

// 状态颜色
const getStatusColor = (status: '处理中' | '进行中' | '已结束' | '已处理') => {
  const label = getStatusLabel(status)
  switch (label) {
    case 'processing': return '#3b82f6'
    case 'completed': return '#10b981'
    default: return '#666'
  }
}

// 处理事件
const handleIncident = (id: number) => {
  const incident = incidents.value.find(i => i.id === id)
  if (incident) {
    incident.status = '处理中'
    console.log(`处理事件 ID: ${id}`)
  }
}

onMounted(() => {
  // 模拟数据加载
  console.log('Traffic incident dashboard mounted')
})
</script>

<template>
  <div class="traffic-incident-dashboard">
    <div class="dashboard-header">
      <h1>交通事件管理</h1>
      <div class="header-actions">
        <button class="btn btn-primary">新增事件</button>
        <button class="btn btn-secondary">刷新数据</button>
      </div>
    </div>
    
    <!-- 事件统计 -->
    <div class="stats-grid">
      <div class="stat-card">
        <div class="stat-icon" style="background-color: #fee2e2;">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#ef4444" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <circle cx="12" cy="12" r="10"></circle>
            <line x1="12" y1="8" x2="12" y2="12"></line>
            <line x1="12" y1="16" x2="12.01" y2="16"></line>
          </svg>
        </div>
        <div class="stat-content">
          <div class="stat-value">4</div>
          <div class="stat-label">今日事件</div>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon" style="background-color: #ffedd5;">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#f59e0b" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <polygon points="7.86 2 16.14 2 22 7.86 22 16.14 16.14 22 7.86 22 2 16.14 2 7.86 7.86 2"></polygon>
            <line x1="12" y1="8" x2="12" y2="12"></line>
            <line x1="12" y1="16" x2="12.01" y2="16"></line>
          </svg>
        </div>
        <div class="stat-content">
          <div class="stat-value">2</div>
          <div class="stat-label">待处理</div>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon" style="background-color: #dcfce7;">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#10b981" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
            <polyline points="22 4 12 14.01 9 11.01"></polyline>
          </svg>
        </div>
        <div class="stat-content">
          <div class="stat-value">2</div>
          <div class="stat-label">已处理</div>
        </div>
      </div>
    </div>
    
    <!-- 事件列表 -->
    <div class="incident-list-section">
      <h2>事件列表</h2>
      <div class="table-container">
        <table class="data-table">
          <thead>
            <tr>
              <th>事件类型</th>
              <th>严重程度</th>
              <th>位置</th>
              <th>时间</th>
              <th>状态</th>
              <th>描述</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="incident in incidents" :key="incident.id">
              <td>{{ incident.type }}</td>
              <td>
                <span 
                  class="severity-badge" 
                  :style="{ backgroundColor: getSeverityColor(incident.severity) }"
                >
                  {{ getSeverityLabel(incident.severity) }}
                </span>
              </td>
              <td>{{ incident.location }}</td>
              <td>{{ incident.time }}</td>
              <td>
                <span 
                  class="status-badge" 
                  :style="{ backgroundColor: getStatusColor(incident.status) }"
                >
                  {{ incident.status }}
                </span>
              </td>
              <td>{{ incident.description }}</td>
              <td>
                <button 
                  v-if="getStatusLabel(incident.status) === 'pending' || getStatusLabel(incident.status) === 'processing'" 
                  class="btn btn-sm btn-primary"
                  @click="handleIncident(incident.id)"
                >
                  处理
                </button>
                <span v-else>-</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
.traffic-incident-dashboard {
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

.btn-sm {
  padding: 4px 8px;
  font-size: 0.8rem;
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

/* 统计卡片 */
.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.stat-card {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  display: flex;
  align-items: center;
  gap: 16px;
}

.stat-icon {
  width: 50px;
  height: 50px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.stat-content {
  flex: 1;
}

.stat-value {
  font-size: 1.8rem;
  font-weight: 700;
  color: var(--text-primary);
  margin-bottom: 4px;
}

.stat-label {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

/* 事件列表 */
.incident-list-section {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.incident-list-section h2 {
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

.severity-badge,
.status-badge {
  padding: 4px 8px;
  border-radius: 12px;
  color: white;
  font-size: 0.8rem;
  font-weight: 500;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .traffic-incident-dashboard {
    padding: 15px;
  }
  
  .dashboard-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }
  
  .stats-grid {
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