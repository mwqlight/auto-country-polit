<script setup lang="ts">
import { ref } from 'vue'

const userInfo = ref({
  name: '管理员',
  avatar: '',
  role: '系统管理员'
})

const notifications = ref([
  { id: 1, message: '交通拥堵预警', time: '5分钟前', unread: true },
  { id: 2, message: '环境质量异常', time: '15分钟前', unread: true },
  { id: 3, message: '系统维护通知', time: '1小时前', unread: false }
])

const isNotificationOpen = ref(false)
const isUserMenuOpen = ref(false)

const toggleNotification = () => {
  isNotificationOpen.value = !isNotificationOpen.value
}

const toggleUserMenu = () => {
  isUserMenuOpen.value = !isUserMenuOpen.value
}

const markAsRead = (id: number) => {
  const notification = notifications.value.find(n => n.id === id)
  if (notification) {
    notification.unread = false
  }
}

const logout = () => {
  // 登出逻辑
  console.log('用户登出')
}
</script>

<template>
  <header class="dashboard-header">
    <div class="header-left">
      <div class="logo">
        <h1>智慧城市驾驶舱</h1>
      </div>
    </div>
    
    <div class="header-right">
      <!-- 通知 -->
      <div class="notification-area" @click="toggleNotification">
        <button class="icon-button notification-btn">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
            <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
          </svg>
          <span class="notification-badge" v-if="notifications.filter(n => n.unread).length > 0">
            {{ notifications.filter(n => n.unread).length }}
          </span>
        </button>
        
        <!-- 通知面板 -->
        <div v-if="isNotificationOpen" class="notification-panel">
          <div class="panel-header">
            <h3>通知</h3>
            <button class="close-btn" @click="toggleNotification">&times;</button>
          </div>
          <div class="notification-list">
            <div 
              v-for="notification in notifications" 
              :key="notification.id"
              class="notification-item"
              :class="{ unread: notification.unread }"
            >
              <div class="notification-content">
                <p>{{ notification.message }}</p>
                <span class="notification-time">{{ notification.time }}</span>
              </div>
              <button 
                v-if="notification.unread"
                class="mark-read-btn"
                @click.stop="markAsRead(notification.id)"
              >
                标记已读
              </button>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 用户菜单 -->
      <div class="user-menu" @click="toggleUserMenu">
        <button class="user-button">
          <div class="user-avatar">
            <span>{{ userInfo.name.charAt(0) }}</span>
          </div>
          <span class="user-name">{{ userInfo.name }}</span>
        </button>
        
        <!-- 用户菜单面板 -->
        <div v-if="isUserMenuOpen" class="user-menu-panel">
          <div class="user-info">
            <div class="user-avatar-large">
              <span>{{ userInfo.name.charAt(0) }}</span>
            </div>
            <div class="user-details">
              <p class="user-name">{{ userInfo.name }}</p>
              <p class="user-role">{{ userInfo.role }}</p>
            </div>
          </div>
          <div class="menu-items">
            <button class="menu-item">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                <circle cx="12" cy="7" r="4"></circle>
              </svg>
              个人中心
            </button>
            <button class="menu-item">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <circle cx="12" cy="12" r="3"></circle>
                <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path>
              </svg>
              系统设置
            </button>
            <button class="menu-item" @click="logout">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                <polyline points="16 17 21 12 16 7"></polyline>
                <line x1="21" y1="12" x2="9" y2="12"></line>
              </svg>
              退出登录
            </button>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<style scoped>
.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  height: 60px;
  background-color: var(--header-bg);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 100;
}

.header-left .logo h1 {
  font-size: 1.5rem;
  font-weight: 600;
  color: var(--primary-color);
  margin: 0;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 20px;
}

.icon-button {
  background: none;
  border: none;
  cursor: pointer;
  position: relative;
  padding: 8px;
  border-radius: 4px;
  color: var(--text-primary);
}

.icon-button:hover {
  background-color: var(--hover-bg);
}

.notification-btn {
  display: flex;
  align-items: center;
  justify-content: center;
}

.notification-badge {
  position: absolute;
  top: -5px;
  right: -5px;
  background-color: var(--danger-color);
  color: white;
  border-radius: 50%;
  width: 18px;
  height: 18px;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.notification-panel {
  position: absolute;
  top: 50px;
  right: 0;
  width: 320px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  z-index: 1000;
  border: 1px solid var(--border-color);
}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid var(--border-color);
}

.panel-header h3 {
  margin: 0;
  font-size: 1.1rem;
  color: var(--text-primary);
}

.close-btn {
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: var(--text-secondary);
}

.notification-list {
  max-height: 300px;
  overflow-y: auto;
}

.notification-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  border-bottom: 1px solid var(--border-color);
}

.notification-item:last-child {
  border-bottom: none;
}

.notification-item.unread {
  background-color: var(--unread-bg);
}

.notification-content p {
  margin: 0 0 4px 0;
  font-size: 0.9rem;
  color: var(--text-primary);
}

.notification-time {
  font-size: 0.8rem;
  color: var(--text-secondary);
}

.mark-read-btn {
  background: var(--primary-color);
  color: white;
  border: none;
  border-radius: 4px;
  padding: 4px 8px;
  font-size: 0.8rem;
  cursor: pointer;
}

.user-button {
  display: flex;
  align-items: center;
  background: none;
  border: none;
  cursor: pointer;
  gap: 8px;
}

.user-avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background-color: var(--primary-color);
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-weight: bold;
}

.user-name {
  color: var(--text-primary);
  font-weight: 500;
}

.user-menu-panel {
  position: absolute;
  top: 50px;
  right: 0;
  width: 280px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  z-index: 1000;
  border: 1px solid var(--border-color);
}

.user-info {
  display: flex;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid var(--border-color);
}

.user-avatar-large {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background-color: var(--primary-color);
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-weight: bold;
  font-size: 1.2rem;
  margin-right: 12px;
}

.user-details {
  flex: 1;
}

.user-name {
  margin: 0 0 4px 0;
  font-weight: 600;
  color: var(--text-primary);
}

.user-role {
  margin: 0;
  font-size: 0.9rem;
  color: var(--text-secondary);
}

.menu-items {
  padding: 8px 0;
}

.menu-item {
  display: flex;
  align-items: center;
  width: 100%;
  padding: 12px 16px;
  background: none;
  border: none;
  text-align: left;
  cursor: pointer;
  color: var(--text-primary);
  font-size: 0.9rem;
  gap: 8px;
}

.menu-item:hover {
  background-color: var(--hover-bg);
}

/* 点击外部关闭面板 */
.notification-area,
.user-menu {
  position: relative;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .dashboard-header {
    padding: 0 10px;
  }
  
  .notification-panel {
    right: -50px;
  }
  
  .user-menu-panel {
    right: -50px;
  }
}
</style>