<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue'

// 图表数据
const chartData = ref({
  labels: [] as string[],
  datasets: [
    {
      label: '交通流量',
      data: [] as number[],
      borderColor: '#3b82f6',
      backgroundColor: 'rgba(59, 130, 246, 0.1)',
      tension: 0.4,
      fill: true
    },
    {
      label: '能耗指数',
      data: [] as number[],
      borderColor: '#10b981',
      backgroundColor: 'rgba(16, 185, 129, 0.1)',
      tension: 0.4,
      fill: true
    },
    {
      label: '环境质量',
      data: [] as number[],
      borderColor: '#f59e0b',
      backgroundColor: 'rgba(245, 158, 11, 0.1)',
      tension: 0.4,
      fill: true
    }
  ]
})

// 生成随机数据
const generateRandomData = () => {
  const now = new Date()
  const timeLabel = `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}`
  
  // 添加新的时间标签
  chartData.value.labels.push(timeLabel)
  
  // 限制显示的数据点数量
  if (chartData.value.labels.length > 20) {
    chartData.value.labels.shift()
    chartData.value.datasets.forEach(dataset => {
      dataset.data.shift()
    })
  }
  
  // 为每个数据集生成随机值
  if (chartData.value.datasets[0]) {
    chartData.value.datasets[0].data.push(Math.floor(Math.random() * 100) + 50) // 交通流量
  }
  if (chartData.value.datasets[1]) {
    chartData.value.datasets[1].data.push(Math.floor(Math.random() * 100) + 30) // 能耗指数
  }
  if (chartData.value.datasets[2]) {
    chartData.value.datasets[2].data.push(Math.floor(Math.random() * 50) + 50)  // 玎境质量
  }
}

let intervalId: number | null = null

onMounted(() => {
  // 初始化一些数据点
  for (let i = 19; i >= 0; i--) {
    const pastTime = new Date(Date.now() - i * 60000)
    const timeLabel = `${pastTime.getHours().toString().padStart(2, '0')}:${pastTime.getMinutes().toString().padStart(2, '0')}`
    chartData.value.labels.push(timeLabel)
  }
  
  // 为每个数据集初始化数据
  chartData.value.datasets.forEach(dataset => {
    if (dataset) {
      dataset.data = Array.from({ length: 20 }, () => Math.floor(Math.random() * 100) + 30)
    }
  })
  
  // 设置定时器定期更新数据
  intervalId = window.setInterval(generateRandomData, 5000)
})

onBeforeUnmount(() => {
  if (intervalId) {
    clearInterval(intervalId)
  }
})
</script>

<template>
  <div class="chart-container">
    <div class="chart-wrapper">
      <!-- 使用原生SVG绘制折线图 -->
      <svg class="line-chart" viewBox="0 0 800 400" preserveAspectRatio="none">
        <!-- 背景网格 -->
        <defs>
          <pattern id="grid" width="50" height="50" patternUnits="userSpaceOnUse">
            <path d="M 50 0 L 0 0 0 50" fill="none" stroke="#f0f0f0" stroke-width="1"/>
          </pattern>
        </defs>
        <rect width="100%" height="100%" fill="url(#grid)" />
        
        <!-- Y轴标签 -->
        <g class="y-axis-labels" fill="#666" font-size="12">
          <text x="20" y="20">100</text>
          <text x="20" y="100">80</text>
          <text x="20" y="200">60</text>
          <text x="20" y="300">40</text>
          <text x="20" y="380">20</text>
        </g>
        
        <!-- 数据线条 -->
        <g v-for="(dataset, index) in chartData.datasets" :key="index" :stroke="dataset.borderColor" fill="none" stroke-width="2">
          <!-- 填充区域 -->
          <path 
            :d="generateAreaPath(dataset.data, index)" 
            :fill="dataset.backgroundColor" 
            fill-opacity="0.3"
          />
          
          <!-- 线条 -->
          <path 
            :d="generateLinePath(dataset.data)" 
            :stroke="dataset.borderColor" 
            fill="none" 
            stroke-width="3"
          />
        </g>
        
        <!-- X轴标签 -->
        <g class="x-axis-labels" fill="#666" font-size="12" text-anchor="middle">
          <text 
            v-for="(label, index) in chartData.labels.slice(-10)" 
            :key="index"
            :x="100 + index * 60" 
            :y="390"
          >
            {{ label }}
          </text>
        </g>
      </svg>
    </div>
    
    <!-- 图例 -->
    <div class="chart-legend">
      <div 
        v-for="(dataset, index) in chartData.datasets" 
        :key="index" 
        class="legend-item"
      >
        <div 
          class="legend-color" 
          :style="{ backgroundColor: dataset.borderColor }"
        ></div>
        <span class="legend-label">{{ dataset.label }}</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
// 计算折线路径的辅助函数
const generateLinePath = (data: number[]) => {
  if (data.length === 0) return ''
  
  const points = data.map((value, index) => {
    // 将数据值映射到SVG坐标系 (Y轴翻转，因为SVG的Y轴向下)
    const x = 100 + index * 35
    const y = 350 - (value * 3)
    return `${x},${y}`
  })
  
  return `M ${points.join(' L ')}`
}

// 计算填充区域路径的辅助函数
const generateAreaPath = (data: number[], index: number) => {
  if (data.length === 0) return ''
  
  const points = data.map((value, i) => {
    const x = 100 + i * 35
    const y = 350 - (value * 3)
    return `${x},${y}`
  })
  
  // 添加底部连接点形成封闭区域
  const lastPointX = 100 + (data.length - 1) * 35
  const firstPointX = 100
  return `M ${firstPointX},350 L ${points.join(' L ')} L ${lastPointX},350 Z`
}

export default {
  methods: {
    generateLinePath,
    generateAreaPath
  }
}
</script>

<style scoped>
.chart-container {
  width: 100%;
}

.chart-wrapper {
  width: 100%;
  height: 300px;
  position: relative;
}

.line-chart {
  width: 100%;
  height: 100%;
  background-color: white;
  border-radius: 8px;
}

.y-axis-labels, .x-axis-labels {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.chart-legend {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 15px;
  flex-wrap: wrap;
}

.legend-item {
  display: flex;
  align-items: center;
  gap: 6px;
}

.legend-color {
  width: 12px;
  height: 12px;
  border-radius: 50%;
}

.legend-label {
  font-size: 0.85rem;
  color: var(--text-secondary);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .chart-wrapper {
    height: 250px;
  }
  
  .chart-legend {
    gap: 12px;
  }
  
  .legend-label {
    font-size: 0.8rem;
  }
}
</style>