<script setup lang="ts">
import { computed } from 'vue'

// 定义props
interface Module {
  id: string
  title: string
  description?: string
  icon: string
  color: string
  status: string
  path?: string
}

interface Props {
  title?: string
  value?: string | number
  unit?: string
  trend?: 'up' | 'down' | 'stable'
  trendValue?: string
  status?: 'normal' | 'warning' | 'critical' | 'success'
  icon?: string
  color?: string
  module?: Module
  onClick?: () => void
}

const props = withDefaults(defineProps<Props>(), {
  unit: '',
  trend: 'stable',
  trendValue: '',
  status: 'normal',
  onClick: () => {}
})

// 计算趋势图标
const trendIcon = computed(() => {
  switch (props.trend) {
    case 'up': return 'M7 17l9.2-9.2M17 17V7H7'
    case 'down': return 'M7 7l9.2 9.2M17 7v10H7'
    default: return ''
  }
})

// 计算状态颜色
const statusColor = computed(() => {
  switch (props.status) {
    case 'success': return '#10b981'
    case 'warning': return '#f59e0b'
    case 'critical': return '#ef4444'
    default: return props.color || (props.module ? props.module.color : '')
  }
})

// 计算显示的标题
const displayTitle = computed(() => {
  return props.title || (props.module ? props.module.title : '')
})

// 计算显示的图标
const displayIcon = computed(() => {
  return props.icon || (props.module ? props.module.icon : '')
})

// 计算显示的状态
const displayStatus = computed(() => {
  return props.status || (props.module ? props.module.status : 'normal')
})
</script>

<template>
  <div class="module-card" @click="onClick">
    <div class="card-header">
      <div 
        class="icon-wrapper" 
        :style="{ backgroundColor: (color || (module ? module.color : '')) + '20', color: (color || (module ? module.color : '')) }"
      >
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
          <path :d="displayIcon"></path>
        </svg>
      </div>
      <h3 class="card-title">{{ displayTitle }}</h3>
    </div>
    
    <div class="card-content">
      <div class="value-section">
        <span class="value">{{ value }}</span>
        <span v-if="unit" class="unit">{{ unit }}</span>
      </div>
      
      <div v-if="trendValue" class="trend-section" :class="trend">
        <svg 
          v-if="trend !== 'stable'"
          xmlns="http://www.w3.org/2000/svg" 
          width="16" 
          height="16" 
          viewBox="0 0 24 24" 
          fill="none" 
          stroke="currentColor" 
          stroke-width="2" 
          stroke-linecap="round" 
          stroke-linejoin="round"
        >
          <path :d="trendIcon"></path>
        </svg>
        <span class="trend-value">{{ trendValue }}</span>
      </div>
    </div>
    
    <div 
      class="status-indicator" 
      :style="{ backgroundColor: statusColor }"
    ></div>
  </div>
</template>

<style scoped>
.module-card {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
  cursor: pointer;
  border: 1px solid var(--border-color);
}

.module-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
  border-color: var(--primary-color);
}

.card-header {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.icon-wrapper {
  width: 48px;
  height: 48px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 12px;
  flex-shrink: 0;
}

.card-title {
  margin: 0;
  font-size: 1rem;
  font-weight: 600;
  color: var(--text-primary);
  flex: 1;
}

.card-content {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.value-section {
  display: flex;
  align-items: baseline;
  gap: 4px;
}

.value {
  font-size: 1.8rem;
  font-weight: 700;
  color: var(--text-primary);
}

.unit {
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.trend-section {
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 0.9rem;
  font-weight: 500;
}

.trend-section.up {
  color: var(--success-color);
}

.trend-section.down {
  color: var(--danger-color);
}

.trend-section.stable {
  color: var(--text-secondary);
}

.status-indicator {
  position: absolute;
  top: 0;
  right: 0;
  width: 8px;
  height: 100%;
  border-radius: 0 12px 12px 0;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .module-card {
    padding: 16px;
  }
  
  .icon-wrapper {
    width: 40px;
    height: 40px;
  }
  
  .value {
    font-size: 1.5rem;
  }
}
</style>