<template>
  <div class="icon-demo-page">
    <h1>Element Plus 图标演示</h1>
    
    <div class="section">
      <h2>基础图标</h2>
      <div class="icon-grid">
        <div class="icon-item">
          <el-icon><Plus /></el-icon>
          <span>Plus</span>
        </div>
        <div class="icon-item">
          <el-icon><Minus /></el-icon>
          <span>Minus</span>
        </div>
        <div class="icon-item">
          <el-icon><Check /></el-icon>
          <span>Check</span>
        </div>
        <div class="icon-item">
          <el-icon><Close /></el-icon>
          <span>Close</span>
        </div>
      </div>
    </div>

    <div class="section">
      <h2>常用图标</h2>
      <div class="icon-grid">
        <div class="icon-item">
          <el-icon><Search /></el-icon>
          <span>Search</span>
        </div>
        <div class="icon-item">
          <el-icon><Edit /></el-icon>
          <span>Edit</span>
        </div>
        <div class="icon-item">
          <el-icon><Delete /></el-icon>
          <span>Delete</span>
        </div>
        <div class="icon-item">
          <el-icon><Share /></el-icon>
          <span>Share</span>
        </div>
      </div>
    </div>

    <div class="section">
      <h2>不同尺寸</h2>
      <div class="icon-grid">
        <div class="icon-item">
          <el-icon :size="16"><Star /></el-icon>
          <span>16px</span>
        </div>
        <div class="icon-item">
          <el-icon :size="24"><Star /></el-icon>
          <span>24px</span>
        </div>
        <div class="icon-item">
          <el-icon :size="32"><Star /></el-icon>
          <span>32px</span>
        </div>
        <div class="icon-item">
          <el-icon :size="48"><Star /></el-icon>
          <span>48px</span>
        </div>
      </div>
    </div>

    <div class="section">
      <h2>不同颜色</h2>
      <div class="icon-grid">
        <div class="icon-item">
          <el-icon color="#409eff"><InfoFilled /></el-icon>
          <span>蓝色</span>
        </div>
        <div class="icon-item">
          <el-icon color="#67c23a"><SuccessFilled /></el-icon>
          <span>绿色</span>
        </div>
        <div class="icon-item">
          <el-icon color="#e6a23c"><WarningFilled /></el-icon>
          <span>黄色</span>
        </div>
        <div class="icon-item">
          <el-icon color="#f56c6c"><CircleCloseFilled /></el-icon>
          <span>红色</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Element Plus icons are auto-imported
</script>

<style scoped>
.icon-demo-page {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.section {
  margin-bottom: 40px;
}

.section h2 {
  margin-bottom: 20px;
  color: #333;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}

.icon-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
  gap: 20px;
}

.icon-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 15px;
  border: 1px solid #eee;
  border-radius: 8px;
  transition: all 0.3s;
}

.icon-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  border-color: #409eff;
}

.icon-item el-icon {
  margin-bottom: 10px;
}

.icon-item span {
  font-size: 14px;
  color: #666;
}
</style>