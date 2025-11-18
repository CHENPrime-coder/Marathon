# 后端修改计划（根据 PROBLEM.md）

## 范围与原则
- 按原型移除不需要的接口；仅保留前端实际调用的查询/创建通道。
- 补足列表查询所需筛选条件，减少无用数据传输。
- 按原型补充志愿者业务功能。

## 变更点列表
1) **接口裁剪**
   - 删除/停用：`POST/PUT/DELETE /api/competitions`，`GET /api/registrations`，`POST/PUT /api/results`，`PUT/DELETE /api/volunteers/{id}`。
   - 调整 Auth 拦截白名单与路由，避免暴露被裁剪接口。
2) **比赛结果查询条件补充**
   - `GET /api/results` 增加必要的筛选参数（按原型约束，例如赛事、runner Email/姓名、状态），并在 Service/Mapper 中实现相应过滤。
3) **跑步者查询参数**
   - `GET /api/runners` 增加原型要求的查询条件（如城市、性别、姓名/邮箱关键字），并支持分页/排序（如原型需要）。
4) **志愿者模块补全**
   - 增加志愿者查询筛选参数（城市、性别、姓名关键字）及必要的新增/查询接口；根据原型，如果仅需新增+列表，则保留 `POST /api/volunteers` 与 `GET /api/volunteers`，移除更新/删除。
   - 增加志愿者 CSV 批量导入接口（上传文件，解析后批量插入），对重复数据的处理策略（跳过/覆盖）需按原型确认。

## 志愿者 CSV 样例
文件编码 UTF-8，无表头，每行格式：
```
姓名,城市名称,出生日期,性别
Alice,Suzhou,1990-05-06,Male
Bob,Nanjing,1985-12-01,Female
```

## 交付物
- 更新后的 Controller/Service/Mapper 代码。
- 如果接口被删除，补充说明或返回 404/405。
- 如有接口参数变化，更新前端联调说明（接口字段、查询条件）。
