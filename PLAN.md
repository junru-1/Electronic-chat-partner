# AI电子对象网站开发计划 `PLAN.md`

## 1. 计划目标
本计划用于把当前项目从“Spring Boot 后端骨架”推进到“可自用、可演示、可持续迭代的 MVP”。

最终目标：
- 用户可登录
- 可创建 AI 对象
- 可导入聊天记录 / 文本资料
- 可自动抽取人物档案、关系记忆、关键事件
- 可编辑档案与记忆
- 可与对象持续聊天
- 可进行基础风险拦截
- 可保存聊天记录与提示词快照
- 有基础前端页面
- 可部署到测试环境

---

## 2. 当前项目状态
当前已经完成：
- Spring Boot 后端基础骨架
- JPA 实体与 Repository
- 基础 Controller / Service / DTO
- OpenAI 兼容 LLM 接入层骨架
- MySQL 主配置
- H2 测试配置
- 基础集成测试可通过
- `SPEC.md` 已定义产品范围、数据模型、API 与风险边界

当前还未完成：
- 抽取流程真实落库
- PromptBuilder
- 档案/记忆驱动的真实聊天链路
- 风险拦截真正接入聊天链路
- 前端页面
- 前后端联调
- 部署

---

## 3. 技术栈

### 3.1 后端
- `Java 17`
- `Spring Boot 4`
- `Spring Web`
- `Spring Data JPA`
- `Spring Validation`
- `Spring Mail`
- `MySQL`
- `H2`（测试环境）
- `Maven`

### 3.2 前端
- `React`
- `Vite`
- `TypeScript`
- `React Router`
- `TanStack Query`
- `Zustand` 或 `Context`
- `Tailwind CSS`

### 3.3 AI / 模型接入
- `OpenAI-compatible API`
- 支持可配置：
  - `baseUrl`
  - `apiKey`
  - `model`

### 3.4 工具链
- `IntelliJ IDEA`
- `Cursor`
- `Postman / Apifox`
- `Git`
- `DBeaver / Navicat`

---

## 4. 项目阶段划分

### Phase 1：后端从骨架到可用
目标：补齐后端主链路，让对象创建、资料导入、抽取、聊天具备最小可用能力。

### Phase 2：前端 MVP
目标：实现登录、对象管理、资料管理、聊天页。

### Phase 3：AI 质量与风控
目标：提升抽取质量、聊天一致性与安全边界。

### Phase 4：测试与部署
目标：联调、回归、部署到测试环境。

---

## 5. 4 周 / 28 天开发计划

## 第 1 周：后端核心业务打通
### Day 1
- 整理后端结构与命名
- 检查实体、DTO、Controller、Service 是否统一
- 删除明显多余文件
- 统一响应格式和异常策略

### Day 2
- 补全对象管理 API
- 实现：
  - `GET /api/characters/{id}`
  - `PUT /api/characters/{id}`
  - `DELETE /api/characters/{id}`
- 加入对象归属校验

### Day 3
- 补全资料管理 API
- 实现：
  - `DELETE /api/characters/{id}/sources/{sourceId}`
- 增强 source 列表查询
- 增加基础输入限制

### Day 4
- 实现真实的抽取结果落库
- 读取对象信息与资料
- 调用 LLM
- 保存人物档案与关键记忆

### Day 5
- 定义抽取 Prompt 与 JSON Schema
- 明确档案结构
- 明确记忆结构
- 明确失败兜底策略

### Day 6
- 实现记忆管理 API
- 实现：
  - `PUT /api/characters/{id}/profile`
  - `PUT /api/characters/{id}/memories/{memoryId}`
  - 建议补：`GET /api/characters/{id}/memory`

### Day 7
- 第 1 周联调与修复
- 用 Postman/Apifox 走通：
  - 创建对象
  - 导入资料
  - 触发抽取
  - 编辑记忆
- 修复异常与字段不一致问题

---

## 第 2 周：聊天链路与 AI 核心能力
### Day 8
- 设计并实现 `PromptBuilderService`

### Day 9
- 改造聊天上下文组装逻辑
- 接入人物档案、关系记忆、关键事件、最近聊天上下文

### Day 10
- 提示词快照落库
- 将 `prompt_snapshot_id` 关联到消息

### Day 11
- 将 `SafetyGuardService` 接入聊天流程
- 输入前检测、输出后检测、记录 `safety_events`

### Day 12
- 场景模式与情绪滑杆接入 PromptBuilder

### Day 13
- 测试聊天稳定性、场景切换与风控效果

### Day 14
- 补测试与重构
- 统一错误码、提炼重复逻辑

---

## 第 3 周：前端 MVP
### Day 15
- 初始化 React + Vite + TypeScript 项目

### Day 16
- 登录页与全局布局

### Day 17
- 对象列表页

### Day 18
- 创建对象页

### Day 19
- 记忆管理页

### Day 20
- 聊天页 UI

### Day 21
- 前后端联调

---

## 第 4 周：质量提升、风控、部署
### Day 22
- 优化抽取质量

### Day 23
- 优化聊天一致性

### Day 24
- 完善风险控制

### Day 25
- 模型设置页完善

### Day 26
- 准备部署配置

### Day 27
- 部署到测试环境

### Day 28
- 回归测试与下一阶段规划

---

## 6. 每周验收标准

### 第 1 周结束
- 可创建对象
- 可添加资料
- 可触发抽取
- 可保存并编辑人物档案和记忆

### 第 2 周结束
- 聊天链路不再使用固定 prompt
- 已接入档案、记忆、场景模式、情绪滑杆
- 可保存提示词快照
- 基础风险拦截可用

### 第 3 周结束
- 前后端完整联调
- 可通过页面完成主要流程

### 第 4 周结束
- 可部署到测试环境
- MVP 可试用
- 有下一阶段 backlog

---

## 7. 开发优先级

### P0 必做
- 登录
- 创建对象
- 导入资料
- 抽取档案与记忆
- 编辑记忆
- 聊天
- 风险拦截
- 提示词快照
- 前后端联调

### P1 应做
- 模型设置页
- 资料删除
- 聊天历史管理
- 错误提示优化
- 部署

### P2 可延后
- 回复候选对比
- 用户改写训练
- 证据面板
- 更复杂记忆图谱
- 流式输出
- 分享对象

---

## 8. 第一周详细待办清单

## Day 1：结构整理与规范化
### 目标
把当前后端骨架整理成适合继续开发的形态。

### 待办
- [ ] 统一命名风格（重点检查 `CharacterProfileRoot` 是否要重命名）
- [ ] 检查 DTO / Entity / Service 命名是否一致
- [ ] 确定统一 API 返回结构
- [ ] 确定统一异常结构与错误码命名
- [ ] 删除明显无用文件和占位代码
- [ ] 补充包结构约定文档（可写在注释或开发规范中）

### 输出物
- 干净的后端工程结构
- 统一命名与异常规范

---

## Day 2：对象管理 API 补齐
### 目标
让角色对象的 CRUD 接近完整。

### 待办
- [ ] 实现 `GET /api/characters/{id}`
- [ ] 实现 `PUT /api/characters/{id}`
- [ ] 实现 `DELETE /api/characters/{id}`
- [ ] 加入用户归属校验
- [ ] 确定删除对象时是否级联删除关联资料 / 记忆 / 会话
- [ ] 为新增接口补 DTO
- [ ] 用 Postman/Apifox 验证接口

### 输出物
- 完整的对象管理 API

---

## Day 3：资料管理 API 补齐
### 目标
让导入资料形成完整生命周期。

### 待办
- [ ] 实现 `DELETE /api/characters/{id}/sources/{sourceId}`
- [ ] 完善资料列表查询
- [ ] 增加 source 类型校验
- [ ] 增加文本长度限制
- [ ] 决定删除策略：软删还是硬删
- [ ] 补基础测试

### 输出物
- 可增删查资料的接口组

---

## Day 4：实现抽取落库
### 目标
让抽取不再只是占位返回，而是真正落到数据库。

### 待办
- [ ] 读取角色基础信息
- [ ] 读取所有 source
- [ ] 组装抽取 prompt
- [ ] 调用 LLM 抽取
- [ ] 解析 LLM 返回 JSON
- [ ] 保存 `character_profiles`
- [ ] 保存 `character_memories`
- [ ] 考虑 profile version 递增策略

### 输出物
- 第一次可用的人设抽取链路

---

## Day 5：抽取协议稳定化
### 目标
让抽取结果更可控、更稳定。

### 待办
- [ ] 定义人物档案 JSON 结构
- [ ] 定义关系记忆 JSON 结构
- [ ] 定义关键事件 JSON 结构
- [ ] 定义 speaking dos / don’ts 生成方式
- [ ] 明确抽取失败时的兜底返回
- [ ] 为 JSON 解析失败增加错误处理
- [ ] 固化 Prompt 模板

### 输出物
- 稳定的抽取协议与 prompt 模板

---

## Day 6：记忆编辑接口
### 目标
让用户可以纠正 AI 抽取错误。

### 待办
- [ ] 实现 `PUT /api/characters/{id}/profile`
- [ ] 实现 `PUT /api/characters/{id}/memories/{memoryId}`
- [ ] 补 `GET /api/characters/{id}/memory` 聚合接口
- [ ] 明确 profile 更新与 memory 更新的数据模型
- [ ] 为编辑接口补校验
- [ ] 补接口测试

### 输出物
- 记忆管理接口完整可用

---

## Day 7：第一周联调与收口
### 目标
保证第一周成果真的可用，不只是代码存在。

### 待办
- [ ] 创建对象
- [ ] 添加多段资料
- [ ] 调用抽取接口
- [ ] 查看档案与记忆
- [ ] 修改档案与记忆
- [ ] 删除一条资料
- [ ] 修复字段名不一致问题
- [ ] 修复异常信息不清楚的问题
- [ ] 补缺失测试
- [ ] 整理第 2 周开始前的 bug list

### 输出物
- 第一周阶段验收版本

---

## 9. 建议执行顺序
如果要最快推进，建议严格按以下顺序：
1. 补对象详情 / 更新 / 删除接口
2. 补资料删除接口
3. 把抽取结果真正落库
4. 做记忆编辑接口
5. 做 PromptBuilder
6. 把聊天改成读取档案和记忆
7. 加 SafetyGuard 到聊天流程

---

## 10. 下一步建议
在完成本计划文件后，建议立刻开始执行：
- 第 1 周 Day 1 ~ Day 3
- 同时建立接口调试集合（Apifox / Postman）
- 每天结束时更新勾选进度

如果后续迭代需要，我可以继续把这份计划拆成：
- 更细的后端任务单
- 更细的前端页面任务单
- 每个接口的实现顺序表
- 数据库迁移计划
