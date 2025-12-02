<script setup lang="ts">
import { ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()

// 定义菜单项
const menuItems = ref([
  {
    id: 'overview',
    name: '总览',
    icon: 'M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6',
    path: '/dashboard/overview'
  },
  {
    id: 'traffic',
    name: '智慧交通',
    icon: 'M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z',
    path: '/dashboard/traffic',
    children: [
      { id: 'flow', name: '交通流量', path: '/dashboard/traffic/flow' },
      { id: 'incident', name: '交通事件', path: '/dashboard/traffic/incident' },
      { id: 'signal', name: '信号控制', path: '/dashboard/traffic/signal' }
    ]
  },
  {
    id: 'environment',
    name: '智慧环保',
    icon: 'M4.052 20.013A11.97 11.97 0 012.01 12.015 12.01 12.01 0 014.052 4.013 11.97 11.97 0 0112.01 2.01c2.717 0 5.324.85 7.5 2.418m-8.5 15.975a11.968 11.968 0 01-7.958-2.995 12.01 12.01 0 01-2.042-8.002 11.969 11.969 0 012.995-7.958M15 12a3 3 0 11-6 0 3 3 0 016 0z',
    path: '/dashboard/environment',
    children: [
      { id: 'monitoring', name: '环境监测', path: '/dashboard/environment/monitoring' },
      { id: 'pollution', name: '污染源', path: '/dashboard/environment/pollution' },
      { id: 'alerts', name: '环境警报', path: '/dashboard/environment/alerts' }
    ]
  },
  {
    id: 'energy',
    name: '智慧能源',
    icon: 'M13 10V3L4 14h7v7l9-11h-7z',
    path: '/dashboard/energy',
    children: [
      { id: 'consumption', name: '能耗分析', path: '/dashboard/energy/consumption' },
      { id: 'renewable', name: '可再生能源', path: '/dashboard/energy/renewable' },
      { id: 'alerts', name: '能源警报', path: '/dashboard/energy/alerts' }
    ]
  },
  {
    id: 'safety',
    name: '公共安全',
    icon: 'M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z',
    path: '/dashboard/safety',
    children: [
      { id: 'incidents', name: '紧急事件', path: '/dashboard/safety/incidents' },
      { id: 'inspection', name: '安全检查', path: '/dashboard/safety/inspection' },
      { id: 'cameras', name: '监控设备', path: '/dashboard/safety/cameras' }
    ]
  },
  {
    id: 'public-service',
    name: '民生服务',
    icon: 'M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z',
    path: '/dashboard/public-service',
    children: [
      { id: 'services', name: '服务管理', path: '/dashboard/public-service/services' },
      { id: 'requests', name: '服务请求', path: '/dashboard/public-service/requests' },
      { id: 'feedback', name: '市民反馈', path: '/dashboard/public-service/feedback' }
    ]
  },
  {
    id: 'economic',
    name: '经济发展',
    icon: 'M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z',
    path: '/dashboard/economic',
    children: [
      { id: 'indicators', name: '经济指标', path: '/dashboard/economic/indicators' },
      { id: 'enterprises', name: '企业注册', path: '/dashboard/economic/enterprises' },
      { id: 'investments', name: '投资项目', path: '/dashboard/economic/investments' }
    ]
  },
  {
    id: 'ai-decision',
    name: 'AI决策支持',
    icon: 'M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z',
    path: '/dashboard/ai-decision',
    children: [
      { id: 'predictions', name: '预测结果', path: '/dashboard/ai-decision/predictions' },
      { id: 'recommendations', name: '政策推荐', path: '/dashboard/ai-decision/recommendations' },
      { id: 'models', name: '模型管理', path: '/dashboard/ai-decision/models' }
    ]
  },
  {
    id: 'icon-demo',
    name: '图标演示',
    icon: 'M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z',
    path: '/dashboard/icon-demo'
  }
])

// 展开的菜单项
const expandedMenus = ref<string[]>([])

// 切换菜单展开状态
const toggleMenu = (menuId: string) => {
  if (expandedMenus.value.includes(menuId)) {
    expandedMenus.value = expandedMenus.value.filter(id => id !== menuId)
  } else {
    expandedMenus.value.push(menuId)
  }
}

// 检查菜单是否激活
const isActive = (path: string) => {
  return route.path === path
}

// 检查父级菜单是否激活
const isParentActive = (children: Array<{path: string}>) => {
  return children.some(child => route.path.startsWith(child.path))
}

// 导航到指定路径
const navigateTo = (path: string) => {
  router.push(path)
}
</script>

<template>
  <aside class="dashboard-sidebar">
    <nav class="sidebar-nav">
      <ul class="nav-list">
        <li 
          v-for="item in menuItems" 
          :key="item.id" 
          class="nav-item"
          :class="{ 
            active: isActive(item.path) || isParentActive(item.children || []),
            expanded: expandedMenus.includes(item.id)
          }"
        >
          <div 
            class="nav-link"
            @click="item.children ? toggleMenu(item.id) : navigateTo(item.path)"
          >
            <svg 
              xmlns="http://www.w3.org/2000/svg" 
              width="20" 
              height="20" 
              viewBox="0 0 24 24" 
              fill="none" 
              stroke="currentColor" 
              stroke-width="2" 
              stroke-linecap="round" 
              stroke-linejoin="round"
              class="nav-icon"
            >
              <path :d="item.icon"></path>
            </svg>
            <span class="nav-text">{{ item.name }}</span>
            <svg 
              v-if="item.children"
              xmlns="http://www.w3.org/2000/svg" 
              width="16" 
              height="16" 
              viewBox="0 0 24 24" 
              fill="none" 
              stroke="currentColor" 
              stroke-width="2" 
              stroke-linecap="round" 
              stroke-linejoin="round"
              class="expand-icon"
              :class="{ rotated: expandedMenus.includes(item.id) }"
            >
              <polyline points="6 9 12 15 18 9"></polyline>
            </svg>
          </div>
          
          <!-- 子菜单 -->
          <ul v-if="item.children" class="sub-nav-list">
            <li 
              v-for="subItem in item.children" 
              :key="subItem.id"
              class="sub-nav-item"
              :class="{ active: isActive(subItem.path) }"
            >
              <div 
                class="sub-nav-link" 
                @click="navigateTo(subItem.path)"
              >
                {{ subItem.name }}
              </div>
            </li>
          </ul>
        </li>
      </ul>
    </nav>
  </aside>
</template>

<style scoped>
.dashboard-sidebar {
  width: 250px;
  height: calc(100vh - 60px);
  background-color: var(--sidebar-bg);
  border-right: 1px solid var(--border-color);
  overflow-y: auto;
  position: fixed;
  top: 60px;
  left: 0;
  z-index: 90;
  transition: all 0.3s ease;
}

.sidebar-nav {
  padding: 16px 0;
}

.nav-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.nav-item {
  margin-bottom: 4px;
}

.nav-link {
  display: flex;
  align-items: center;
  padding: 12px 20px;
  cursor: pointer;
  color: var(--text-secondary);
  transition: all 0.2s ease;
  position: relative;
}

.nav-link:hover {
  background-color: var(--hover-bg);
  color: var(--text-primary);
}

.nav-item.active > .nav-link {
  background-color: var(--primary-light);
  color: var(--primary-color);
  font-weight: 500;
}

.nav-icon {
  margin-right: 12px;
  flex-shrink: 0;
}

.nav-text {
  flex: 1;
  text-align: left;
}

.expand-icon {
  transition: transform 0.2s ease;
}

.expand-icon.rotated {
  transform: rotate(180deg);
}

/* 子菜单样式 */
.sub-nav-list {
  list-style: none;
  padding: 0;
  margin: 0;
  background-color: var(--submenu-bg);
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.3s ease;
}

.nav-item.expanded .sub-nav-list {
  max-height: 500px;
}

.sub-nav-item {
  border-left: 3px solid transparent;
}

.sub-nav-item.active {
  border-left-color: var(--primary-color);
}

.sub-nav-link {
  display: block;
  padding: 10px 20px 10px 52px;
  cursor: pointer;
  color: var(--text-secondary);
  transition: all 0.2s ease;
  font-size: 0.9rem;
}

.sub-nav-link:hover {
  background-color: var(--hover-bg);
  color: var(--text-primary);
}

.sub-nav-item.active .sub-nav-link {
  background-color: var(--primary-light);
  color: var(--primary-color);
  font-weight: 500;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .dashboard-sidebar {
    width: 70px;
  }
  
  .nav-text,
  .expand-icon {
    display: none;
  }
  
  .nav-link {
    justify-content: center;
    padding: 16px 0;
  }
  
  .nav-icon {
    margin-right: 0;
  }
  
  /* 在移动端保持子菜单可见 */
  .sub-nav-list {
    position: absolute;
    left: 70px;
    top: 0;
    background-color: var(--sidebar-bg);
    min-width: 180px;
    box-shadow: 2px 0 8px rgba(0, 0, 0, 0.1);
    max-height: unset;
    display: none;
  }
  
  .nav-item.expanded .sub-nav-list {
    display: block;
  }
}
</style>