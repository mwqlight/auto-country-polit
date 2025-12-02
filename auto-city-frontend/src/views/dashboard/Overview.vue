<script setup lang="ts">
import { ref, onMounted } from 'vue'
import ModuleCard from '@/components/dashboard/ModuleCard.vue'

// 模块数据
const modules = ref([
  {
    id: 'traffic',
    title: '智慧交通',
    value: '畅通',
    status: 'success' as const,
    icon: 'M1 1h16M1 7h16M1 13h16',
    color: '#3b82f6',
    path: '/dashboard/traffic'
  },
  {
    id: 'environment',
    title: '环境保护',
    value: '良好',
    status: 'success' as const,
    icon: 'M4.052 20.013A11.97 11.97 0 012.01 12.015 12.01 12.01 0 014.052 4.013 11.97 11.97 0 0112.01 2.01c2.717 0 5.324.85 7.5 2.418m-8.5 15.975a11.968 11.968 0 01-7.958-2.995 12.01 12.01 0 01-2.042-8.002 11.969 11.969 0 012.995-7.958',
    color: '#10b981',
    path: '/dashboard/environment'
  },
  {
    id: 'energy',
    title: '智慧能源',
    value: '稳定',
    status: 'normal' as const,
    icon: 'M13 10V3L4 14h7v7l9-11h-7z',
    color: '#f59e0b',
    path: '/dashboard/energy'
  },
  {
    id: 'safety',
    title: '公共安全',
    value: '安全',
    status: 'success' as const,
    icon: 'M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z',
    color: '#8b5cf6',
    path: '/dashboard/safety'
  },
  {
    id: 'service',
    title: '民生服务',
    value: '高效',
    status: 'success' as const,
    icon: 'M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z',
    color: '#ec4899',
    path: '/dashboard/service'
  },
  {
    id: 'economic',
    title: '经济发展',
    value: '增长',
    status: 'success' as const,
    icon: 'M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z',
    color: '#06b6d4',
    path: '/dashboard/economic'
  },
  {
    id: 'ai-decision',
    title: 'AI决策支持',
    value: '智能',
    status: 'success' as const,
    icon: 'M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z',
    color: '#8b5cf6',
    path: '/dashboard/ai-decision'
  }
])

// 概览数据
const overviewData = ref({
  totalModules: 7,
  activeAlerts: 2,
  systemStatus: '正常',
  lastUpdate: new Date().toLocaleTimeString('zh-CN')
})

onMounted(() => {
  // 模拟数据加载
  console.log('Overview dashboard mounted')
})
</script>

<template>
  <div class="overview-dashboard">
    <div class="dashboard-header">
      <h1>城市运营总览</h1>
      <div class="header-info">
        <span class="status-indicator success"></span>
        <span class="status-text">系统状态: {{ overviewData.systemStatus }}</span>
        <span class="update-time">最后更新: {{ overviewData.lastUpdate }}</span>
      </div>
    </div>
    
    <div class="overview-stats">
      <div class="stat-card">
        <div class="stat-value">{{ overviewData.totalModules }}</div>
        <div class="stat-label">管理系统</div>
      </div>
      <div class="stat-card">
        <div class="stat-value">{{ overviewData.activeAlerts }}</div>
        <div class="stat-label">活跃警报</div>
      </div>
    </div>
    
    <div class="modules-grid">
      <ModuleCard
        v-for="module in modules"
        :key="module.id"
        :title="module.title"
        :value="module.value"
        :status="module.status"
        :icon="module.icon"
        :color="module.color"
        @click="$router.push(module.path)"
      />
    </div>
  </div>
</template>

<style scoped>
.overview-dashboard {
  padding: 20px;
}

.dashboard-header {
  margin-bottom: 30px;
}

.dashboard-header h1 {
  margin: 0 0 10px 0;
  font-size: 1.8rem;
  color: var(--text-primary);
}

.header-info {
  display: flex;
  align-items: center;
  gap: 20px;
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.status-indicator {
  display: inline-block;
  width: 10px;
  height: 10px;
  border-radius: 50%;
}

.status-indicator.success {
  background-color: var(--success-color);
}

.update-time {
  margin-left: auto;
}

.overview-stats {
  display: flex;
  gap: 20px;
  margin-bottom: 30px;
}

.stat-card {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  flex: 1;
  text-align: center;
}

.stat-value {
  font-size: 2rem;
  font-weight: 700;
  color: var(--primary-color);
  margin-bottom: 8px;
}

.stat-label {
  font-size: 1rem;
  color: var(--text-secondary);
}

.modules-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .overview-dashboard {
    padding: 15px;
  }
  
  .overview-stats {
    flex-direction: column;
    gap: 15px;
  }
  
  .modules-grid {
    grid-template-columns: 1fr;
  }
  
  .header-info {
    flex-wrap: wrap;
    gap: 10px;
  }
  
  .update-time {
    margin-left: 0;
    width: 100%;
  }
}
</style>