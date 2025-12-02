# Auto City 城市智能驾驶舱后端系统

基于 Spring Boot 3 + Java 17 的城市智能驾驶舱后端系统，为前端提供 RESTful API 接口，处理业务逻辑和数据持久化。

## 项目结构

```
src/main/java/com/smartcity/cockpit/
├── config/         # 配置类
├── controller/     # 控制层（RESTful API）
├── dto/            # 数据传输对象
│   ├── request/    # 请求DTO
│   └── response/   # 响应DTO
├── service/        # 业务逻辑
│   ├── impl/       # 实现类
│   └── Service.java # 接口
├── repository/     # 数据访问层
├── entity/         # 实体类
├── exception/      # 全局异常处理
└── util/           # 工具类
```

## 技术栈

- Spring Boot 3.0+
- Java 17
- Spring Data JPA
- Spring Security
- MySQL 8.0
- Redis
- Maven

## 功能模块

1. **用户管理** - 用户认证、授权和权限管理
2. **交通数据** - 实时交通流量数据处理
3. **环境监测** - 空气质量、噪音等环境数据
4. **能源管理** - 电力、燃气等能源消耗统计
5. **城市规划** - 土地利用、建筑规划数据
6. **数据可视化** - 为前端提供图表数据接口
7. **系统监控** - 系统性能和健康状态监控

## 开发指南

### 环境准备

- JDK 17
- Maven 3.8+
- MySQL 8.0
- Redis

### 配置文件

在 `src/main/resources/application.yml` 中配置数据库连接、Redis连接等信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/auto_city?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  redis:
    host: localhost
    port: 6379
    password: 
    database: 0
```

### 安装依赖

```sh
mvn clean install
```

### 启动开发服务器

```sh
mvn spring-boot:run
```

或者运行 `Application.java` 主类。

### 运行单元测试

```sh
mvn test
```

## API 文档

项目集成了 SpringDoc OpenAPI 3.0，启动服务后访问以下地址查看 API 文档：

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## 性能优化

### 数据库优化

1. **索引优化**：为常用查询字段添加合适的索引
2. **查询优化**：避免 N+1 查询问题，使用 JOIN 或批量查询
3. **分页查询**：大数据量接口实现分页查询
4. **读写分离**：使用读写分离提高数据库性能

### 缓存策略

1. **Redis 缓存**：热点数据缓存到 Redis 中
2. **本地缓存**：使用 Caffeine 实现本地缓存
3. **多级缓存**：结合本地缓存和 Redis 实现多级缓存

### 异步处理

1. **异步任务**：使用 `@Async` 注解实现异步处理
2. **消息队列**：集成 RabbitMQ/Kafka 处理耗时任务

## 部署指南

### 构建生产版本

```bash
mvn clean package
```

### 运行 jar 包

```bash
java -jar target/auto-city-backend-1.0.0.jar
```

### Docker 部署

项目支持 Docker 部署，构建镜像：

```bash
docker build -t auto-city-backend .
```

运行容器：

```bash
docker run -d -p 8080:8080 --name auto-city-backend auto-city-backend
```

### Docker Compose 部署

使用 docker-compose 一键部署整个应用：

```bash
docker-compose up -d
```

## 安全性

### 认证授权

1. **JWT Token**：基于 JWT 实现无状态认证
2. **RBAC 权限模型**：基于角色的访问控制
3. **方法级权限控制**：使用 `@PreAuthorize` 注解实现方法级权限控制

### 数据安全

1. **敏感数据加密**：使用 AES 加密算法加密敏感数据
2. **密码加密**：使用 BCrypt 算法加密用户密码
3. **SQL 注入防护**：使用预编译语句防止 SQL 注入

### 安全配置

```yaml
smartcity:
  security:
    jwt:
      secret: mySecretKey
      expiration: 86400 # 24小时
```

## 监控和日志

### Actuator 监控

项目集成了 Spring Boot Actuator，提供应用监控端点：

- Health: http://localhost:8080/actuator/health
- Info: http://localhost:8080/actuator/info
- Metrics: http://localhost:8080/actuator/metrics
- Loggers: http://localhost:8080/actuator/loggers

### 日志配置

使用 Logback 作为日志框架，配置文件位于 `src/main/resources/logback-spring.xml`：

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

## 测试

### 单元测试

使用 JUnit 5 和 Mockito 进行单元测试：

```java
@SpringBootTest
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void testGetUserById() {
        // 测试逻辑
    }
}
```

### 集成测试

使用 Testcontainers 进行集成测试：

```java
@SpringBootTest
@Testcontainers
class UserControllerIntegrationTest {
    
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");
    
    // 集成测试逻辑
}
```

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