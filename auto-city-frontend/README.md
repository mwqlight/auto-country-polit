# Auto City 城市智能驾驶舱

基于 Vue 3 + TypeScript 的城市智能驾驶舱前端项目，用于展示城市各项关键指标和数据分析。

## 项目结构

```
src/
├── api/            # API接口封装
│   ├── modules/    # 按模块拆分
│   └── index.ts    # 统一出口
├── assets/         # 静态资源
├── components/     # 公共组件
│   ├── base/       # 基础组件
│   └── layout/     # 布局组件
├── composables/    # 组合式API
├── router/         # 路由配置
├── store/          # 状态管理
├── styles/         # 全局样式
├── types/          # TypeScript类型定义
├── utils/          # 工具函数
├── views/          # 页面视图
│   ├── dashboard/  # 驾驶舱模块
└── App.vue         # 根组件
```

## 功能模块

1. **城市运营总览** - 展示城市关键指标的综合视图
2. **智慧交通** - 实时交通状况监控
3. **智慧环保** - 环境质量监测
4. **智慧能源** - 能源消耗分析
5. **民生服务** - 公共服务全景
6. **城市规划** - 建设与发展数据

## 开发指南

### 环境准备

- Node.js >= 20.19.5
- npm >= 10.8.2

### 安装依赖

```sh
npm install
```

### 启动开发服务器

```sh
npm run dev
```

服务器将在 http://localhost:5173 启动

### 构建生产版本

```sh
npm run build
```

### 运行单元测试

```sh
npm run test:unit
```

### 代码检查

```sh
npm run lint
```

## 性能优化

### 路由懒加载
项目实现了路由懒加载，按需加载各个模块，减少初始包大小。

### 代码分割
使用 Vite 的代码分割功能，将第三方库和业务代码分离：
```ts
build: {
  rollupOptions: {
    output: {
      manualChunks: {
        'element-plus': ['element-plus'],
        'echarts': ['echarts'],
        'axios': ['axios']
      }
    }
  }
}
```

### 虚拟滚动
对于大数据列表展示，使用虚拟滚动技术优化渲染性能。

### 图片懒加载
实现图片懒加载，提升页面加载速度。

## 部署指南

### 构建生产版本
```bash
npm run build
```

### 部署到 Nginx
1. 将 `dist` 目录中的文件复制到 Nginx 服务器目录
2. 配置 Nginx：
```nginx
server {
    listen 80;
    server_name your-domain.com;
    
    location / {
        root /path/to/dist;
        index index.html;
        try_files $uri $uri/ /index.html;
    }
    
    location /api/ {
        proxy_pass http://localhost:8080/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

### Docker 部署
项目支持 Docker 部署，构建镜像：
```bash
docker build -t auto-city-frontend .
```

运行容器：
```bash
docker run -d -p 3000:80 --name auto-city-frontend auto-city-frontend
```

## 技术栈

- Vue 3 (Composition API)
- TypeScript
- Vite
- Vue Router
- Pinia (状态管理)
- Axios (HTTP客户端)
- Element Plus (UI组件库)
- ECharts (数据可视化)

## 项目规范

遵循 5A6S 开发规范：
- Architecture（清晰架构）
- API（统一接口规范）
- Automation（自动化工程）
- Assurance（质量保障）
- Agility（敏捷协作）
- Structure（目录结构标准）
- Standards（编码标准）
- Security（安全标准）
- Stability（稳定性标准）
- Scalability（可扩展标准）
- Sustainability（可持续维护）

## 监控和错误处理

### 前端监控
项目集成了性能监控和错误捕获机制：
- 页面加载性能监控
- JavaScript 错误捕获
- API 请求错误处理

### 错误边界
在关键组件中实现错误边界，防止页面崩溃：
```vue
<template>
  <ErrorBoundary>
    <DashboardComponent />
  </ErrorBoundary>
</template>
```

## 安全性

### XSS 防护
- 禁止使用 `v-html` 直接渲染用户输入
- 使用 DOMPurify 对第三方内容进行净化

### CSRF 防护
- Axios 自动携带 XSRF-TOKEN
- 关键操作进行二次验证

### 敏感信息保护
- 禁止在前端存储敏感信息
- 使用 Vite 的环境变量功能管理配置
