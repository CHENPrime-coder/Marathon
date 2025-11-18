# Marathon 后端开发计划（Spring Boot 3 + MyBatis）

> 面向当前表结构与前端页面（登录/注册、跑者报名、管理员管理、成绩/展示）。与 `DEVELOPMENT_PLAN.md` 配合使用，偏重后端实现路径。

## 阶段与优先级
- **P0 基础设施**
  - 接入全局异常处理、统一响应模型（code/message/data）、全局参数校验（Jakarta Validation）。
  - 配置 CORS、时区、Jackson（日期格式）、MyBatis 驼峰映射；开启 SQL 日志仅在 dev。
  - 教学项目：密码可明文存储/返回，后续保留升级空间；不做 Flyway/Seed 脚本。
- **P1 鉴权与用户**
  - 接口：`POST /api/auth/login`（email + password），`POST /api/auth/password`（修改密码）。
  - Token：JWT（短效即可）；Handler 拦截鉴权；无需 RBAC，所有登录用户同一权限模型。
  - User CRUD：`GET/POST/PUT/DELETE /api/users`；Runner/Volunteer 创建时同步 user 记录。
- **P2 基础数据**
  - City 列表：`GET /api/cities`。
  - RaceKitOption 列表：`GET /api/race-kit-options`。
  - Competition：`GET /api/competitions`（公开/跑者），`POST/PUT/DELETE`（管理员）。
- **P3 跑者流程**
  - Runner 资料：`GET/PUT /api/runners/{email}`，`POST /api/runners`（注册时建档），头像改为文件上传，DB 仅存本地文件 URL，静态资源可由 Spring 静态目录或自定义 ResourceHandler 提供访问。
  - 报名：`POST /api/registrations`（校验唯一 `(Email, CompetitionId)`，计算总价 = RegCost + KitCost），`GET /api/registrations`（支持 email/competitionId 过滤）。无需退款/取消，删除接口可省略。
  - 总价计算与金额类型全链路使用 BigDecimal。
- **P4 成绩与历史**
  - RaceResult：`POST/PUT /api/results`（管理员导入/修正），`GET /api/results`（筛选 email/competitionId/status，跑者仅查自己的）。赛事状态仅需已完成/未完成，按结果或赛事表状态标记。
  - 如需“往期成绩”页面，提供公开/登录结果查询接口（可脱敏邮箱）。
- **P5 管理与报表**
  - Volunteer CRUD：`GET/POST/PUT/DELETE /api/volunteers`（按城市/性别/年龄筛选）。
  - 基础统计：报名人数、收入、完赛率等聚合接口（用于管理端仪表盘）。
- **P6 非功能与测试**
  - 日志脱敏（邮箱/密码），限流/验证码：接入 Kaptcha 生成验证码，验证码缓存使用 Caffeine（短 TTL），登录/注册时校验；输入合法校验（Email/Enum）。
  - 暂不做集成测试、OpenAPI/Swagger；保持 dev/prod 基础配置即可。

## 接口与前端对齐建议
- 登录页：`/api/auth/login` 返回 `{token, role, email}`；前端保存 Token/角色并路由跳转；验证码校验（Kaptcha + Caffeine）。
  - 未登录/401：后端返回统一错误码，前端拦截器跳转登录。
- 报名页：
  - `GET /api/competitions` -> 赛事列表（含价格）。
  - `GET /api/race-kit-options` -> 套餐列表（含价格）。
  - `POST /api/registrations` -> 提交报名；返回计算后的总价。
- 个人中心：`GET /api/runners/{email}` / `PUT` 更新；头像上传端点。
- 管理员端：
  - 跑者管理：`GET /api/runners` 带分页/搜索；`DELETE /api/runners/{email}`（可选软删）。
  - 志愿者管理：`GET/POST/PUT/DELETE /api/volunteers`。
  - 成绩管理：`POST/PUT /api/results` + `GET /api/results`。

## 代码结构建议
- `controller`：仅负责路由与 DTO 校验，避免业务逻辑下沉。
- `service`：封装权限校验、价格计算、唯一性检查、事务。
- `mapper/xml`：一表一 Mapper；跨表查询使用 DTO（如报名明细带赛事名、套餐名）。
- `dto`：请求/响应 DTO 与 `table` 实体分离；敏感字段（密码）不可回传。
- `security`：JWT 过滤器/拦截器 + 角色注解（或基于 HandlerMethod 的自定义鉴权）。

## 风险与待确认
- 密码是否允许复用现有明文数据？若否，需迁移或重置。
- 头像/文件存储位置（本地 vs 对象存储）与大小/格式限制。
- 是否需要验证码（登录/注册），对应前端已有验证码占位。
- 报名是否允许取消/退款逻辑；赛事状态（报名中/截止/已结束）字段设计。
