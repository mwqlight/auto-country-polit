<script setup lang="ts">
import { ref, onMounted } from 'vue'
import DashboardHeader from '@/components/layout/DashboardHeader.vue'
import DashboardSidebar from '@/components/layout/DashboardSidebar.vue'
import OverviewPanel from '@/components/dashboard/OverviewPanel.vue'
import ModuleCard from '@/components/dashboard/ModuleCard.vue'
import RealtimeChart from '@/components/dashboard/RealtimeChart.vue'

// 模块数据
const modules = ref([
  {
    id: 'traffic',
    title: '智慧交通',
    description: '实时交通流量监控与智能信号调控',
    icon: 'M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z',
    color: 'blue',
    status: 'active'
  },
  {
    id: 'environment',
    title: '智慧环保',
    description: '环境质量监测与污染源管控',
    icon: 'M4.052 20.013A11.97 11.97 0 012.01 12.015 12.01 12.01 0 014.052 4.013 11.97 11.97 0 0112.01 2.01c2.717 0 5.324.85 7.5 2.418m-8.5 15.975a11.968 11.968 0 01-7.958-2.995 12.01 12.01 0 01-2.042-8.002 11.969 11.969 0 012.995-7.958M15 12a3 3 0 11-6 0 3 3 0 016 0z',
    color: 'green',
    status: 'active'
  },
  {
    id: 'energy',
    title: '智慧能源',
    description: '能耗监控与可再生能源管理',
    icon: 'M13 10V3L4 14h7v7l9-11h-7z',
    color: 'yellow',
    status: 'active'
  },
  {
    id: 'safety',
    title: '公共安全',
    description: '应急事件处理与安全巡检管理',
    icon: 'M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z',
    color: 'red',
    status: 'active'
  },
  {
    id: 'service',
    title: '民生服务',
    description: '政务服务与民生设施管理',
    icon: 'M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z',
    color: 'pink',
    status: 'active'
  },
  {
    id: 'economic',
    title: '经济发展',
    description: '经济指标分析与企业投资管理',
    icon: 'M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z',
    color: 'purple',
    status: 'active'
  },
  {
    id: 'ai-decision',
    title: 'AI决策支持',
    description: '智能预测与政策推荐系统',
    icon: 'M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z',
    color: 'indigo',
    status: 'active'
  }
])

// 概览数据
const overviewData = ref({
  totalModules: 7,
  activeAlerts: 3,
  systemHealth: 98,
  lastUpdated: new Date()
})

onMounted(() => {
  // 初始化数据获取
  console.log('Dashboard mounted')
})
</script>

<template>
  <div class="dashboard-container">
    <!-- 头部 -->
    <DashboardHeader />
    
    <div class="dashboard-content">
      <!-- 侧边栏 -->
      <DashboardSidebar />
      
      <!-- 主内容区 -->
      <main class="main-content">
        <!-- 概览面板 -->
        <OverviewPanel :data="overviewData" />
        
        <!-- 模块网格 -->
        <section class="modules-section">
          <h2>功能模块</h2>
          <div class="modules-grid">
            <ModuleCard 
              v-for="module in modules" 
              :key="module.id"
              :module="module"
              @click="$router.push('/dashboard/' + module.id)"
            />
          </div>
        </section>
        
        <!-- 实时图表 -->
        <section class="charts-section">
          <h2>实时数据</h2>
          <div class="charts-grid">
            <RealtimeChart chartType="traffic" />
            <RealtimeChart chartType="environment" />
            <RealtimeChart chartType="energy" />
          </div>
        </section>
      </main>
    </div>
  </div>
</template>

<style scoped>
.dashboard-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background-color: var(--background-color);
}

.dashboard-content {
  display: flex;
  flex: 1;
  overflow: hidden;
}

.main-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.modules-section {
  margin-bottom: 30px;
}

.modules-section h2 {
  font-size: 1.5rem;
  margin-bottom: 20px;
  color: var(--text-primary);
}

.modules-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.charts-section {
  margin-bottom: 30px;
}

.charts-section h2 {
  font-size: 1.5rem;
  margin-bottom: 20px;
  color: var(--text-primary);
}

.charts-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
  gap: 20px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .modules-grid {
    grid-template-columns: 1fr;
  }
  
  .charts-grid {
    grid-template-columns: 1fr;
  }
  
  .main-content {
    padding: 10px;
  }
}
</style>