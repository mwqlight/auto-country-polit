<script setup lang="ts">
import { ref, onMounted } from 'vue'

// 定义信号灯类型
interface Signal {
  id: string
  location: string
  status: '正常' | '异常'
  cycleTime: number
  greenTime: number
  yellowTime: number
  redTime: number
  nextChange: string
  lastUpdate: string
}

// 信号灯数据
const signals = ref<Signal[]>([
  {
    id: 'SIG001',
    location: '中山路与解放大道交叉口',
    status: '正常',
    cycleTime: 120,
    greenTime: 30,
    yellowTime: 5,
    redTime: 85,
    nextChange: '15秒后',
    lastUpdate: '2023-05-15 14:30:22'
  },
  {
    id: 'SIG002',
    location: '长江隧道入口',
    status: '正常',
    cycleTime: 90,
    greenTime: 25,
    yellowTime: 5,
    redTime: 60,
    nextChange: '8秒后',
    lastUpdate: '2023-05-15 14:30:18'
  },
  {
    id: 'SIG003',
    location: '武汉大道与二环线交叉口',
    status: '异常',
    cycleTime: 150,
    greenTime: 40,
    yellowTime: 5,
    redTime: 105,
    nextChange: '35秒后',
    lastUpdate: '2023-05-15 14:25:45'
  },
  {
    id: 'SIG004',
    location: '光谷广场',
    status: '正常',
    cycleTime: 100,
    greenTime: 35,
    yellowTime: 5,
    redTime: 60,
    nextChange: '22秒后',
    lastUpdate: '2023-05-15 14:30:30'
  }
])

// 获取状态颜色
const getStatusColor = (status: '正常' | '异常') => {
  return status === '正常' ? '#10b981' : '#ef4444'
}

// 调整信号灯参数
const adjustSignal = (id: string) => {
  console.log(`调整信号灯: ${id}`)
  // 这里可以打开模态框或跳转到详细配置页面
}

// 刷新数据
const refreshData = () => {
  console.log('刷新信号灯数据')
  // 模拟数据刷新
}

onMounted(() => {
  // 模拟数据加载
  console.log('Traffic signal dashboard mounted')
})
</script>

<template>
  <div class="traffic-signal-dashboard">
    <div class="dashboard-header">
      <h1>智能信号灯控制</h1>
      <div class="header-actions">
        <button class="btn btn-primary" @click="refreshData">刷新数据</button>
        <button class="btn btn-secondary">批量调整</button>
      </div>
    </div>
    
    <!-- 信号灯概览 -->
    <div class="signals-overview">
      <div class="overview-card">
        <div class="overview-icon" style="background-color: #dcfce7;">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#10b981" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <circle cx="12" cy="12" r="10"></circle>
            <path d="M12 6v6l4 2"></path>
          </svg>
        </div>
        <div class="overview-content">
          <div class="overview-value">4</div>
          <div class="overview-label">信号灯总数</div>
        </div>
      </div>
      
      <div class="overview-card">
        <div class="overview-icon" style="background-color: #dcfce7;">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#10b981" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline>
          </svg>
        </div>
        <div class="overview-content">
          <div class="overview-value">3</div>
          <div class="overview-label">正常运行</div>
        </div>
      </div>
      
      <div class="overview-card">
        <div class="overview-icon" style="background-color: #fee2e2;">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#ef4444" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"></path>
            <line x1="12" y1="9" x2="12" y2="13"></line>
            <line x1="12" y1="17" x2="12.01" y2="17"></line>
          </svg>
        </div>
        <div class="overview-content">
          <div class="overview-value">1</div>
          <div class="overview-label">异常设备</div>
        </div>
      </div>
    </div>
    
    <!-- 信号灯列表 -->
    <div class="signals-list-section">
      <h2>信号灯列表</h2>
      <div class="table-container">
        <table class="data-table">
          <thead>
            <tr>
              <th>信号灯ID</th>
              <th>位置</th>
              <th>状态</th>
              <th>周期时间</th>
              <th>绿灯时间</th>
              <th>黄灯时间</th>
              <th>红灯时间</th>
              <th>下次变化</th>
              <th>最后更新</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="signal in signals" :key="signal.id">
              <td>{{ signal.id }}</td>
              <td>{{ signal.location }}</td>
              <td>
                <span 
                  class="status-badge" 
                  :style="{ backgroundColor: getStatusColor(signal.status) }"
                >
                  {{ signal.status }}
                </span>
              </td>
              <td>{{ signal.cycleTime }}秒</td>
              <td>{{ signal.greenTime }}秒</td>
              <td>{{ signal.yellowTime }}秒</td>
              <td>{{ signal.redTime }}秒</td>
              <td>{{ signal.nextChange }}</td>
              <td>{{ signal.lastUpdate }}</td>
              <td>
                <button class="btn btn-sm btn-primary" @click="adjustSignal(signal.id)">
                  调整
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    
    <!-- 信号灯控制面板 -->
    <div class="control-panel">
      <h2>信号灯控制</h2>
      <div class="control-content">
        <div class="control-group">
          <label>选择信号灯:</label>
          <select class="form-select">
            <option v-for="signal in signals" :key="signal.id" :value="signal.id">
              {{ signal.id }} - {{ signal.location }}
            </option>
          </select>
        </div>
        
        <div class="control-group">
          <label>调整模式:</label>
          <div class="radio-group">
            <label class="radio-option">
              <input type="radio" name="mode" value="manual" checked>
              <span>手动控制</span>
            </label>
            <label class="radio-option">
              <input type="radio" name="mode" value="adaptive">
              <span>自适应调节</span>
            </label>
          </div>
        </div>
        
        <div class="control-actions">
          <button class="btn btn-primary">应用设置</button>
          <button class="btn btn-secondary">恢复默认</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.traffic-signal-dashboard {
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

/* 信号灯概览 */
.signals-overview {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.overview-card {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  display: flex;
  align-items: center;
  gap: 16px;
}

.overview-icon {
  width: 50px;
  height: 50px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.overview-content {
  flex: 1;
}

.overview-value {
  font-size: 1.8rem;
  font-weight: 700;
  color: var(--text-primary);
  margin-bottom: 4px;
}

.overview-label {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

/* 信号灯列表 */
.signals-list-section {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  margin-bottom: 30px;
}

.signals-list-section h2 {
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

/* 控制面板 */
.control-panel {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.control-panel h2 {
  margin-top: 0;
  margin-bottom: 20px;
  color: var(--text-primary);
}

.control-content {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
}

.control-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.control-group label {
  font-weight: 500;
  color: var(--text-primary);
}

.form-select {
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 6px;
  background-color: var(--input-bg);
  color: var(--text-primary);
}

.radio-group {
  display: flex;
  gap: 15px;
}

.radio-option {
  display: flex;
  align-items: center;
  gap: 5px;
}

.radio-option input[type="radio"] {
  accent-color: var(--primary-color);
}

.control-actions {
  display: flex;
  gap: 10px;
  align-items: flex-end;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .traffic-signal-dashboard {
    padding: 15px;
  }
  
  .dashboard-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }
  
  .signals-overview {
    grid-template-columns: 1fr;
  }
  
  .data-table {
    font-size: 0.9rem;
  }
  
  .data-table th,
  .data-table td {
    padding: 8px 12px;
  }
  
  .control-content {
    grid-template-columns: 1fr;
  }
  
  .control-actions {
    flex-direction: column;
  }
}
</style>