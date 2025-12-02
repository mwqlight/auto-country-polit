# 城市智能驾驶舱系统数据库设计文档

## 1. 概述

本文档详细描述了城市智能驾驶舱系统的数据库设计方案。系统采用 MySQL 8.0 作为主要数据存储，Redis 作为缓存层。

## 2. 数据库环境

- 数据库类型: MySQL 8.0
- 字符集: utf8mb4
- 排序规则: utf8mb4_unicode_ci
- 存储引擎: InnoDB

## 3. 数据库表结构设计

### 3.1 用户表 (users)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 用户ID |
| username | VARCHAR(50) | UNIQUE, NOT NULL | 用户名 |
| email | VARCHAR(100) | UNIQUE, NOT NULL | 邮箱 |
| password_hash | VARCHAR(255) | NOT NULL | 密码哈希 |
| full_name | VARCHAR(100) | NOT NULL | 真实姓名 |
| role | VARCHAR(20) | NOT NULL | 用户角色 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

### 3.2 交通流量表 (traffic_flows)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 流量记录ID |
| road_name | VARCHAR(100) | NOT NULL | 道路名称 |
| traffic_volume | INT | NOT NULL | 交通流量(辆/小时) |
| average_speed | DECIMAL(5,2) | NOT NULL | 平均速度(km/h) |
| latitude | DECIMAL(10,8) | NOT NULL | 纬度 |
| longitude | DECIMAL(11,8) | NOT NULL | 经度 |
| record_time | TIMESTAMP | NOT NULL | 记录时间 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |

### 3.3 交通事件表 (traffic_incidents)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 事件ID |
| incident_type | VARCHAR(50) | NOT NULL | 事件类型 |
| location | VARCHAR(200) | NOT NULL | 事件地点 |
| severity | VARCHAR(20) | NOT NULL | 严重程度 |
| description | TEXT | NOT NULL | 事件描述 |
| incident_time | TIMESTAMP | NOT NULL | 事件发生时间 |
| is_resolved | BOOLEAN | DEFAULT FALSE | 是否已解决 |
| resolved_time | TIMESTAMP | NULL | 解决时间 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

### 3.4 环境监测数据表 (environmental_data)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 数据记录ID |
| location | VARCHAR(100) | NOT NULL | 监测点位置 |
| pm25 | INT | NOT NULL | PM2.5浓度(μg/m³) |
| pm10 | INT | NOT NULL | PM10浓度(μg/m³) |
| temperature | DECIMAL(5,2) | NOT NULL | 温度(°C) |
| humidity | DECIMAL(5,2) | NOT NULL | 湿度(%) |
| noise_level | DECIMAL(5,2) | NOT NULL | 噪声(dB) |
| record_time | TIMESTAMP | NOT NULL | 记录时间 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |

### 3.5 能源消耗表 (energy_consumption)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 消耗记录ID |
| energy_type | VARCHAR(50) | NOT NULL | 能源类型 |
| consumption_amount | DECIMAL(15,2) | NOT NULL | 消耗量(kWh) |
| cost | DECIMAL(10,2) | NOT NULL | 费用(元) |
| carbon_emission | DECIMAL(10,2) | NOT NULL | 碳排放量(kg) |
| location | VARCHAR(100) | NOT NULL | 位置 |
| record_date | DATE | NOT NULL | 记录日期 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |

### 3.6 安全事件表 (security_incidents)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 事件ID |
| incident_type | VARCHAR(50) | NOT NULL | 事件类型 |
| location | VARCHAR(200) | NOT NULL | 事件地点 |
| severity | VARCHAR(20) | NOT NULL | 严重程度 |
| description | TEXT | NOT NULL | 事件描述 |
| incident_time | TIMESTAMP | NOT NULL | 事件发生时间 |
| is_resolved | BOOLEAN | DEFAULT FALSE | 是否已解决 |
| resolved_time | TIMESTAMP | NULL | 解决时间 |
| assigned_department | VARCHAR(100) | NOT NULL | 负责部门 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

### 3.7 经济指标表 (economic_indicators)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 指标ID |
| indicator_name | VARCHAR(100) | NOT NULL | 指标名称 |
| indicator_value | DECIMAL(15,2) | NOT NULL | 指标值 |
| unit | VARCHAR(20) | NOT NULL | 单位 |
| record_date | DATE | NOT NULL | 记录日期 |
| region | VARCHAR(100) | NOT NULL | 地区 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |

### 3.8 民意调查表 (public_opinion_surveys)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 调查ID |
| survey_topic | VARCHAR(200) | NOT NULL | 调查主题 |
| satisfaction_score | DECIMAL(5,2) | NOT NULL | 满意度评分 |
| total_responses | INT | NOT NULL | 总响应数 |
| positive_responses | INT | NOT NULL | 正面响应数 |
| neutral_responses | INT | NOT NULL | 中性响应数 |
| negative_responses | INT | NOT NULL | 负面响应数 |
| survey_date | DATE | NOT NULL | 调查日期 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |

### 3.9 土地利用规划表 (land_use_plans)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 规划ID |
| area_name | VARCHAR(100) | NOT NULL | 区域名称 |
| planned_area | DECIMAL(15,2) | NOT NULL | 规划面积(平方米) |
| land_use_type | VARCHAR(50) | NOT NULL | 用地类型 |
| approval_status | VARCHAR(20) | NOT NULL | 审批状态 |
| planned_date | DATE | NOT NULL | 规划日期 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

### 3.10 AI预测结果表 (ai_predictions)

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 预测ID |
| prediction_type | VARCHAR(100) | NOT NULL | 预测类型 |
| predicted_value | DECIMAL(15,2) | NOT NULL | 预测值 |
| confidence | DECIMAL(5,4) | NOT NULL | 置信度 |
| prediction_time | TIMESTAMP | NOT NULL | 预测时间 |
| actual_value | DECIMAL(15,2) | NULL | 实际值 |
| actual_time | TIMESTAMP | NULL | 实际时间 |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | 创建时间 |

## 4. 索引设计

### 4.1 交通流量表索引
```sql
CREATE INDEX idx_traffic_flows_road_name ON traffic_flows(road_name);
CREATE INDEX idx_traffic_flows_record_time ON traffic_flows(record_time);
CREATE INDEX idx_traffic_flows_location ON traffic_flows(latitude, longitude);
```

### 4.2 交通事件表索引
```sql
CREATE INDEX idx_traffic_incidents_type ON traffic_incidents(incident_type);
CREATE INDEX idx_traffic_incidents_severity ON traffic_incidents(severity);
CREATE INDEX idx_traffic_incidents_time ON traffic_incidents(incident_time);
```

### 4.3 环境监测数据表索引
```sql
CREATE INDEX idx_environmental_data_location ON environmental_data(location);
CREATE INDEX idx_environmental_data_record_time ON environmental_data(record_time);
```

### 4.4 能源消耗表索引
```sql
CREATE INDEX idx_energy_consumption_type ON energy_consumption(energy_type);
CREATE INDEX idx_energy_consumption_date ON energy_consumption(record_date);
```

### 4.5 安全事件表索引
```sql
CREATE INDEX idx_security_incidents_type ON security_incidents(incident_type);
CREATE INDEX idx_security_incidents_severity ON security_incidents(severity);
CREATE INDEX idx_security_incidents_time ON security_incidents(incident_time);
```

### 4.6 经济指标表索引
```sql
CREATE INDEX idx_economic_indicators_name ON economic_indicators(indicator_name);
CREATE INDEX idx_economic_indicators_date ON economic_indicators(record_date);
```

### 4.7 民意调查表索引
```sql
CREATE INDEX idx_public_opinion_surveys_topic ON public_opinion_surveys(survey_topic);
CREATE INDEX idx_public_opinion_surveys_date ON public_opinion_surveys(survey_date);
```

### 4.8 土地利用规划表索引
```sql
CREATE INDEX idx_land_use_plans_type ON land_use_plans(land_use_type);
CREATE INDEX idx_land_use_plans_status ON land_use_plans(approval_status);
```

### 4.9 AI预测结果表索引
```sql
CREATE INDEX idx_ai_predictions_type ON ai_predictions(prediction_type);
CREATE INDEX idx_ai_predictions_time ON ai_predictions(prediction_time);
```

## 5. Redis缓存设计

### 5.1 缓存策略
- 使用 Redis 作为热点数据缓存
- 设置合理的过期时间(10分钟)
- 使用合适的缓存键命名规范

### 5.2 缓存键设计
```
# 交通数据缓存
traffic:flows:latest -> 最新交通流量数据
traffic:incidents:recent -> 最近交通事件

# 环境数据缓存
environment:data:latest -> 最新环境监测数据

# 能源数据缓存
energy:consumption:statistics -> 能源消耗统计

# 安全数据缓存
security:incidents:statistics -> 安全事件统计

# 经济数据缓存
economy:indicators:latest -> 最新经济指标

# 民意调查缓存
public:satisfaction:latest -> 最新满意度调查结果

# 规划数据缓存
urban:plans:approved -> 已批准规划

# AI预测缓存
ai:predictions:recent -> 最近预测结果
```

## 6. 数据库连接配置

### 6.1 连接池配置
```properties
# HikariCP 连接池配置
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.auto-commit=true
```

### 6.2 数据源配置
```properties
# 数据库连接配置
spring.datasource.url=jdbc:mysql://localhost:3306/auto_city?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## 7. 数据库备份策略

### 7.1 备份频率
- 每日全量备份
- 每小时增量备份
- 关键操作前手动备份

### 7.2 备份保留策略
- 全量备份保留30天
- 增量备份保留7天
- 归档备份永久保留

## 8. 性能优化建议

### 8.1 查询优化
- 对经常查询的字段建立索引
- 避免 SELECT * 查询
- 使用 LIMIT 分页查询
- 合理使用 JOIN 操作

### 8.2 表结构优化
- 使用合适的数据类型
- 避免过度规范化
- 定期清理历史数据
- 使用分区表处理大数据量

### 8.3 连接池优化
- 根据并发量调整连接池大小
- 设置合理的超时时间
- 监控连接池使用情况