<script setup lang="ts">
import { RouterView } from 'vue-router'
import DashboardHeader from './components/layout/DashboardHeader.vue'
import DashboardSidebar from './components/layout/DashboardSidebar.vue'
import TestElementPlusIcons from './components/TestElementPlusIcons.vue'
</script>

<template>
  <DashboardHeader />
  <div class="app-layout">
    <DashboardSidebar />
    <main class="main-content">
      <!-- Test Element Plus Icons -->
      <TestElementPlusIcons />
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
.app-layout {
  display: flex;
  min-height: calc(100vh - 60px);
}

.main-content {
  flex: 1;
  margin-left: 250px;
  margin-top: 60px;
  padding: 20px;
  background-color: var(--background-color);
  min-height: calc(100vh - 60px);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .main-content {
    margin-left: 70px;
    padding: 15px;
  }
}
</style>
