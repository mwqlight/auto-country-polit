# 智慧城市管理系统

智慧城市管理系统是一个基于SpringBoot + Vue3的前后端分离项目，实现了城市管理的多个核心功能模块，包括数字孪生、交通管理、安全治理、环保监测、能源管理、经济发展、民生服务和AI决策支持等。

## 项目结构

```
auto-country-polit/
├── auto-city-backend/     # 后端SpringBoot项目
└── auto-city-frontend/    # 前端Vue3项目
```

## 核心功能模块

### 1. 城市数字孪生
- 城市三维可视化展示
- 实时数据监控面板

### 2. 智慧交通管理
- 交通流量监控
- 信号灯智能控制
- 交通事故处理

### 3. 城市安全治理
- 应急资源管理
- 安全事件监控
- 风险评估分析

### 4. 智慧环保监测
- 空气质量监控
- 水质监测
- 噪音水平检测
- 污染源追踪

### 5. 智慧能源管理
- 电力消耗监控
- 可再生能源利用
- 能源效率分析

### 6. 城市经济发展
- 经济指标展示
- 企业注册统计
- 投资项目跟踪

### 7. 民生服务全景
- 服务满意度监控
- 线上办理率统计
- 服务网点管理

### 8. AI决策支持
- 智能预警系统
- 数据分析报告
- 决策建议生成

## 技术栈

### 后端技术栈
- SpringBoot 3.x
- MySQL 8.x
- Redis
- Spring Security
- Spring Data JPA
- SpringDoc OpenAPI 3.0

### 前端技术栈
- Vue 3 + TypeScript
- Vite
- Pinia状态管理
- Vue Router
- Element Plus组件库
- ECharts数据可视化

## 开发规范

本项目遵循5A6S开发规范：

### 5A原则
- Architecture（清晰架构）
- API（统一接口规范）
- Automation（自动化工程）
- Assurance（质量保障）
- Agility（敏捷协作）

### 6S标准
- Structure（目录结构标准）
- Standards（编码标准）
- Security（安全标准）
- Stability（稳定性标准）
- Scalability（可扩展标准）
- Sustainability（可持续维护）

## 环境准备

1. Node.js (推荐使用nvm管理版本)
2. Java 17+
3. MySQL 8.x
4. Redis
5. npm/yarn包管理器

## 项目启动

### 前端启动
```bash
cd auto-city-frontend
npm install
npm run dev
```

### 后端启动
```bash
cd auto-city-backend
./gradlew bootRun
```

## 部署说明

### 前端部署
```bash
cd auto-city-frontend
npm run build
# 将dist目录部署到Nginx或其它Web服务器
```

### 后端部署
```bash
cd auto-city-backend
./gradlew build
# 将生成的jar包部署到服务器运行
```

## 文档资料

- 接口文档：通过Swagger UI访问 `/swagger-ui.html`
- 数据库设计文档：详见 `docs/database-design.md`
- 部署文档：详见 `docs/deployment-guide.md`

## 质量保障

- 单元测试覆盖率 ≥ 80%
- 代码检查通过SonarQube质量门禁
- CI/CD流水线自动化部署
- 性能测试达标（API响应时间 ≤ 300ms）

## 许可证

MIT License