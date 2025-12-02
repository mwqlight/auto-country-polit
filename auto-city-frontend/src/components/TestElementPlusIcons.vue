<template>
  <div class="test-icons">
    <h2>Element Plus Icons Test</h2>
    <div class="icon-demo">
      <el-icon><Plus /></el-icon>
      <span>Plus Icon</span>
    </div>
    <div class="icon-demo">
      <el-icon><Minus /></el-icon>
      <span>Minus Icon</span>
    </div>
    <div class="icon-demo">
      <el-icon><Check /></el-icon>
      <span>Check Icon</span>
    </div>
    <div class="icon-demo">
      <el-icon><Close /></el-icon>
      <span>Close Icon</span>
    </div>
  </div>
</template>

<script setup lang="ts">
// Element Plus icons will be auto-imported
</script>

<style scoped>
.test-icons {
  padding: 20px;
}

.icon-demo {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.icon-demo span {
  margin-left: 10px;
}
</style>