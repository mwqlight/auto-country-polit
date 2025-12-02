# 城市智能驾驶舱系统部署指南

## 1. 概述

本文档详细描述了城市智能驾驶舱系统的部署流程和配置要求。系统采用前后端分离架构，后端基于 Spring Boot，前端基于 Vue 3。

## 2. 系统要求

### 2.1 后端环境要求
- JDK 17 或更高版本
- MySQL 8.0 或更高版本
- Redis 6.0 或更高版本
- Maven 3.8 或更高版本

### 2.2 前端环境要求
- Node.js 16 或更高版本
- npm 8 或更高版本
- 现代浏览器（Chrome, Firefox, Safari, Edge）

### 2.3 开发环境要求
- IDE: IntelliJ IDEA 或 VS Code
- 数据库管理工具: MySQL Workbench
- Redis 管理工具: Redis Desktop Manager

## 3. 环境准备

### 3.1 安装 JDK 17
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# CentOS/RHEL
sudo yum install java-17-openjdk-devel

# macOS (使用 Homebrew)
brew install openjdk@17
```

### 3.2 安装 MySQL 8.0
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install mysql-server

# 启动 MySQL 服务
sudo systemctl start mysql
sudo systemctl enable mysql

# 安全配置
sudo mysql_secure_installation
```

### 3.3 安装 Redis
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install redis-server

# 启动 Redis 服务
sudo systemctl start redis-server
sudo systemctl enable redis-server

# macOS (使用 Homebrew)
brew install redis
brew services start redis
```

### 3.4 安装 Node.js
```bash
# 使用 nvm 安装 Node.js (推荐)
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
source ~/.bashrc
nvm install 16
nvm use 16
```

### 3.5 安装 Maven
```bash
# 下载并安装 Maven
wget https://downloads.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz
tar -xzf apache-maven-3.8.4-bin.tar.gz
sudo mv apache-maven-3.8.4 /opt/maven

# 配置环境变量
echo 'export MAVEN_HOME=/opt/maven' >> ~/.bashrc
echo 'export PATH=$MAVEN_HOME/bin:$PATH' >> ~/.bashrc
source ~/.bashrc
```

## 4. 数据库配置

### 4.1 创建数据库
```sql
CREATE DATABASE auto_city CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'autocity'@'localhost' IDENTIFIED BY 'autocity123';
GRANT ALL PRIVILEGES ON auto_city.* TO 'autocity'@'localhost';
FLUSH PRIVILEGES;
```

### 4.2 初始化数据库表
系统会在首次启动时自动创建数据库表结构，无需手动执行初始化脚本。

## 5. 后端部署

### 5.1 克隆代码仓库
```bash
git clone <repository-url>
cd auto-city-backend
```

### 5.2 配置应用程序
编辑 `src/main/resources/application.properties` 文件：
```properties
# 服务器配置
server.port=8080

# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/auto_city?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=autocity
spring.datasource.password=autocity123

# Redis配置
spring.redis.host=localhost
spring.redis.port=6379

# 性能优化配置
# 服务器压缩
server.compression.enabled=true
server.compression.mime-types=application/json,application/xml,text/html,text/xml,text/plain,application/javascript,text/css

# 数据库连接池配置
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000

# Redis连接池配置
spring.redis.jedis.pool.max-active=20
spring.redis.jedis.pool.max-idle=10
spring.redis.jedis.pool.min-idle=5

# 缓存配置
spring.cache.type=redis
spring.cache.redis.time-to-live=600000

# 日志配置
logging.level.root=WARN
logging.level.com.smartcity.cockpit=INFO

# Actuator监控端点
management.endpoints.web.exposure.include=health,info,metrics,loggers
management.endpoint.health.show-details=always
```

### 5.3 构建项目
```bash
mvn clean package -DskipTests
```

### 5.4 运行应用程序
```bash
# 方式1: 使用 Maven 运行
mvn spring-boot:run

# 方式2: 使用 java 命令运行
java -jar target/auto-city-backend-0.0.1-SNAPSHOT.jar

# 方式3: 后台运行
nohup java -jar target/auto-city-backend-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

### 5.5 验证后端服务
```bash
# 检查应用是否启动成功
curl http://localhost:8080/actuator/health
```

## 6. 前端部署

### 6.1 克隆前端代码
```bash
cd ../auto-city-frontend
```

### 6.2 安装依赖
```bash
npm install
```

### 6.3 配置环境变量
创建 `.env.production` 文件：
```env
VITE_API_BASE_URL=http://localhost:8080
VITE_APP_TITLE=城市智能驾驶舱
```

### 6.4 构建生产版本
```bash
npm run build
```

### 6.5 部署前端应用
构建完成后，将 `dist` 目录中的文件部署到 Web 服务器（如 Nginx、Apache）：

#### 使用 Nginx 部署
1. 安装 Nginx：
```bash
# Ubuntu/Debian
sudo apt install nginx

# CentOS/RHEL
sudo yum install nginx
```

2. 配置 Nginx：
```nginx
server {
    listen 80;
    server_name your-domain.com;
    
    location / {
        root /path/to/auto-city-frontend/dist;
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

3. 重启 Nginx：
```bash
sudo systemctl restart nginx
```

## 7. Docker 部署（可选）

### 7.1 后端 Docker 配置
创建 `Dockerfile`：
```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/auto-city-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

构建并运行 Docker 镜像：
```bash
docker build -t auto-city-backend .
docker run -d -p 8080:8080 --name auto-city-backend auto-city-backend
```

### 7.2 前端 Docker 配置
创建 `Dockerfile`：
```dockerfile
FROM node:16 AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

FROM nginx:alpine

COPY --from=build /app/dist /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
```

构建并运行 Docker 镜像：
```bash
docker build -t auto-city-frontend .
docker run -d -p 3000:80 --name auto-city-frontend auto-city-frontend
```

### 7.3 Docker Compose 部署
创建 `docker-compose.yml`：
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: auto-city-mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: auto_city
      MYSQL_USER: autocity
      MYSQL_PASSWORD: autocity123
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  redis:
    image: redis:6.0
    container_name: auto-city-redis
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data

  backend:
    build: ./auto-city-backend
    container_name: auto-city-backend
    ports:
      - "8080:8080"
    depends_on:
      - mysql
      - redis
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/auto_city
      SPRING_REDIS_HOST: redis

  frontend:
    build: ./auto-city-frontend
    container_name: auto-city-frontend
    ports:
      - "3000:80"
    depends_on:
      - backend

volumes:
  mysql_data:
  redis_data:
```

启动所有服务：
```bash
docker-compose up -d
```

## 8. 监控和日志

### 8.1 后端监控
系统集成了 Spring Boot Actuator，可以通过以下端点监控系统状态：
- `/actuator/health`: 健康检查
- `/actuator/info`: 应用信息
- `/actuator/metrics`: 系统指标
- `/actuator/loggers`: 日志配置

### 8.2 日志配置
日志文件位于 `logs` 目录下，包含以下类型的日志：
- `application.log`: 应用程序日志
- `error.log`: 错误日志
- `access.log`: 访问日志

### 8.3 性能监控
建议使用以下工具进行性能监控：
- Prometheus + Grafana: 系统指标监控
- ELK Stack: 日志分析
- Sentry: 前端错误监控

## 9. 安全配置

### 9.1 HTTPS 配置
建议在生产环境中启用 HTTPS，可以通过以下方式配置：

#### Nginx HTTPS 配置
```nginx
server {
    listen 443 ssl;
    server_name your-domain.com;
    
    ssl_certificate /path/to/certificate.crt;
    ssl_certificate_key /path/to/private.key;
    
    location / {
        root /path/to/auto-city-frontend/dist;
        index index.html;
        try_files $uri $uri/ /index.html;
    }
    
    location /api/ {
        proxy_pass http://localhost:8080/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

### 9.2 CORS 配置
在后端 `application.properties` 中配置 CORS：
```properties
# CORS 配置
cors.allowed-origins=https://your-domain.com
cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
cors.allowed-headers=*
cors.allow-credentials=true
```

## 10. 故障排除

### 10.1 常见问题

#### 数据库连接失败
1. 检查数据库服务是否启动
2. 验证数据库连接配置是否正确
3. 检查防火墙设置

#### Redis 连接失败
1. 检查 Redis 服务是否启动
2. 验证 Redis 配置是否正确
3. 检查网络连接

#### 前端无法访问后端 API
1. 检查后端服务是否正常运行
2. 验证 API 地址配置是否正确
3. 检查跨域配置

### 10.2 日志查看
```bash
# 查看后端日志
tail -f logs/application.log

# 查看前端日志（如果使用 Docker）
docker logs auto-city-frontend

# 查看后端日志（如果使用 Docker）
docker logs auto-city-backend
```

## 11. 升级和维护

### 11.1 系统升级
1. 备份当前系统和数据
2. 拉取最新代码
3. 重新构建和部署
4. 验证系统功能

### 11.2 数据备份
定期备份数据库和重要配置文件：
```bash
# MySQL 备份
mysqldump -u root -p auto_city > backup.sql

# 文件备份
tar -czf frontend-backup.tar.gz /path/to/auto-city-frontend
```

### 11.3 系统监控
建立监控告警机制：
- CPU 和内存使用率监控
- 磁盘空间监控
- 数据库连接数监控
- API 响应时间监控

## 12. 联系和支持

如有部署问题，请联系技术支持团队：
- 邮箱: support@smartcity.com
- 电话: 400-123-4567
- 在线支持: https://support.smartcity.com