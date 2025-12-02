# 城市智能驾驶舱系统 API 文档

## 1. 概述

本文档详细描述了城市智能驾驶舱系统的 RESTful API 接口规范。系统基于 Spring Boot 3.0+ 构建，遵循 RESTful 设计原则。

## 2. 技术规范

- API 版本: v1
- 基础路径: `/api/v1`
- 认证方式: JWT Token
- 数据格式: JSON
- 字符编码: UTF-8

## 3. 统一响应格式

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {},
  "timestamp": 1650000000000
}
```

## 4. 错误码规范

| 范围 | 说明 | 示例 |
|------|------|------|
| 200-299 | 成功 | 200: 操作成功 |
| 400-499 | 客户端错误 | 401: 未认证 |
| 500-599 | 服务端错误 | 500: 系统异常 |
| 10000-19999 | 业务错误码 | 10001: 用户不存在 |

## 5. 模块接口详情

### 5.1 智慧交通管理模块

#### 获取最新交通流量数据
- **URL**: `/api/v1/traffic/flows/latest`
- **Method**: GET
- **Description**: 获取最新的交通流量数据
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "roadName": "中山路",
      "trafficVolume": 1200,
      "averageSpeed": 35.5,
      "recordTime": "2023-12-01T08:00:00"
    }
  ],
  "timestamp": 1650000000000
}
```

#### 获取交通事件列表
- **URL**: `/api/v1/traffic/incidents`
- **Method**: GET
- **Description**: 获取交通事件列表
- **Parameters**:
  - incidentType (optional): 事件类型
  - severity (optional): 严重程度
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "incidentType": "交通事故",
      "location": "中山路与解放路交叉口",
      "severity": "严重",
      "description": "三车追尾事故",
      "incidentTime": "2023-12-01T08:30:00",
      "isResolved": false
    }
  ],
  "timestamp": 1650000000000
}
```

### 5.2 智慧环保监测模块

#### 获取环境监测数据
- **URL**: `/api/v1/environment/monitoring/data`
- **Method**: GET
- **Description**: 获取环境监测数据
- **Parameters**:
  - startTime (optional): 开始时间
  - endTime (optional): 结束时间
  - location (optional): 监测点位置
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "location": "市中心监测站",
      "pm25": 35,
      "pm10": 50,
      "temperature": 22.5,
      "humidity": 65,
      "recordTime": "2023-12-01T08:00:00"
    }
  ],
  "timestamp": 1650000000000
}
```

### 5.3 智慧能源管理模块

#### 获取能源消耗统计
- **URL**: `/api/v1/energy/consumption/statistics`
- **Method**: GET
- **Description**: 获取能源消耗统计数据
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "totalConsumption": 1250000,
    "renewablePercentage": 25.5,
    "peakHourConsumption": 85000,
    "carbonEmission": 625000
  },
  "timestamp": 1650000000000
}
```

### 5.4 城市安全治理模块

#### 获取安全事件统计
- **URL**: `/api/v1/security/incidents/statistics`
- **Method**: GET
- **Description**: 获取安全事件统计数据
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "totalIncidents": 125,
    "resolvedIncidents": 110,
    "unresolvedIncidents": 15,
    "highRiskIncidents": 5
  },
  "timestamp": 1650000000000
}
```

### 5.5 城市经济发展模块

#### 获取经济指标数据
- **URL**: `/api/v1/economy/indicators`
- **Method**: GET
- **Description**: 获取城市经济指标数据
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "gdpGrowthRate": 6.8,
    "industrialOutput": 1250000,
    "retailSales": 850000,
    "foreignInvestment": 125000
  },
  "timestamp": 1650000000000
}
```

### 5.6 民生服务全景模块

#### 获取公共服务满意度调查结果
- **URL**: `/api/v1/public-service/satisfaction`
- **Method**: GET
- **Description**: 获取公共服务满意度调查结果
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "overallSatisfaction": 85.5,
    "educationSatisfaction": 88.2,
    "healthcareSatisfaction": 82.7,
    "transportationSatisfaction": 80.1
  },
  "timestamp": 1650000000000
}
```

### 5.7 城市规划与建设模块

#### 获取土地利用规划
- **URL**: `/api/v1/urban-planning/land-use-plans`
- **Method**: GET
- **Description**: 获取土地利用规划信息
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "areaName": "高新技术开发区",
      "plannedArea": 5000,
      "landUseType": "工业用地",
      "approvalStatus": "已批准",
      "plannedDate": "2023-12-01"
    }
  ],
  "timestamp": 1650000000000
}
```

### 5.8 AI决策支持模块

#### 获取预测结果
- **URL**: `/api/ai-decision-support/predictions`
- **Method**: GET
- **Description**: 获取AI预测结果
- **Parameters**:
  - predictionType (optional): 预测类型
  - startTime (optional): 开始时间
  - endTime (optional): 结束时间
- **Response**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "predictionType": "人口流动预测",
      "predictedValue": 1250000,
      "confidence": 0.85,
      "predictionTime": "2023-12-01T08:00:00",
      "actualValue": 1200000
    }
  ],
  "timestamp": 1650000000000
}
```

## 6. 部署说明

### 6.1 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 6.0+
- Node.js 16+

### 6.2 配置文件
在 `application.properties` 中配置以下参数：
```properties
# 服务器配置
server.port=8080

# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/auto_city?useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username=root
spring.datasource.password=root

# Redis配置
spring.redis.host=localhost
spring.redis.port=6379
```

## 7. 性能优化配置

### 7.1 数据库连接池配置
```properties
# HikariCP 连接池配置
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

### 7.2 Redis 缓存配置
```properties
# Redis 连接池配置
spring.redis.jedis.pool.max-active=20
spring.redis.jedis.pool.max-idle=10
spring.redis.jedis.pool.min-idle=5
```

## 8. 监控端点

系统集成了 Spring Boot Actuator，可通过以下端点监控系统状态：
- `/actuator/health`: 健康检查
- `/actuator/info`: 应用信息
- `/actuator/metrics`: 系统指标
- `/actuator/loggers`: 日志配置

## 9. 安全配置

系统采用 JWT Token 进行身份验证，请在请求头中添加：
```
Authorization: Bearer <token>
```