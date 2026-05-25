# AI电子对象网站 SPEC

## 1. 项目定义
- 目标：让用户通过手填资料与粘贴聊天记录，创建一个可持续聊天的“AI电子对象”。
- 首版定位：个人自用 / 熟人小圈子私测，不面向陌生公众。
- 主场景：现实伴侣/恋人的数字分身。
- 核心目标：先保证“像同一个人”（一致性），再追求“像这个人”（高拟真）。
- 交互契约：默认沉浸式扮演对象本人，尽量不暴露 AI 痕迹；仅在关键节点轻量提醒“这是模拟对象”。

## 2. 已确认决策
### 2.1 产品与体验
- 信息来源：
  - 结构化资料
  - 粘贴聊天记录
  - 其它文本材料
- 推断策略：允许一定脑补，只要整体感觉像 TA。
- 成功标准：一致性优先。
- 场景模式：日常 / 暧昧 / 安慰 / 复合。
- 显式控制：情绪强度/亲密度滑杆。
- 首版响应：同步请求-响应，不做流式输出。
- UI 重心：桌面端优先，兼顾聊天体验与资料管理。

### 2.2 风险与边界
- 体验优先，但首版必须处理明显高风险内容。
- 最关注失败模式：
  - 人设漂移
  - 隐私泄露
  - 操控性/PUA/占有欲回复
- 最低风控要求：
  - 关键节点提醒为模拟对象
  - 对自伤/他伤、未成年人相关性内容、胁迫/跟踪/报复类内容做硬拦截
- 授权策略：消费级产品不强制验证真实人物授权，依赖用户承诺与条款；但由于首版仅小范围私用，先不做复杂审核流程。

### 2.3 技术与范围
- 前端：推荐 React + Vite + TypeScript
- 后端：Spring Boot + Java 17
- 数据库：MySQL
- 模型接口：OpenAI 兼容，支持 `baseUrl` / `apiKey` / `model` 配置
- 登录：邮箱魔法链接
- 部署：单体后端 + 独立前端静态站点 + 单实例 MySQL
- 数据保存：长期保存
- 存储策略：尽量都进数据库，减少组件数量

## 3. 首版不做
- 微信自动导入 / 插件抓取 / 本地备份解析
- 语音克隆、视频形象、实时语音
- 向量库、记忆图谱、多代理编排
- 面向公众的大规模平台化能力
- 分享对象给第三方体验
- 商业化收费系统
- 正式评测平台
- 流式输出

## 4. 产品范围
### 4.1 功能模块
1. 认证
2. 对象管理
3. 资料导入
4. 人设/记忆抽取
5. 记忆编辑
6. 聊天
7. 风险拦截
8. 模型与系统设置

### 4.2 页面结构
- `/login` 登录页
- `/characters` 对象列表页
- `/characters/new` 创建对象页
- `/characters/:id` 对象总览页
- `/characters/:id/memory` 记忆管理页
- `/characters/:id/chat` 聊天页
- `/settings` 模型配置页

## 5. 核心交互流程
### 5.1 创建对象流程
1. 填基础资料：名称、关系、称呼、概况
2. AI 反问补充：说话方式、安慰风格、共同经历、不会说的话
3. 粘贴资料：聊天记录 / 其它文本
4. 自动抽取：人物档案、关系记忆、关键事件、说话风格
5. 用户确认并修改
6. 选择场景模式进入聊天

### 5.2 聊天流程
每轮请求组装以下上下文：
- 当前人物档案
- 关键关系记忆与事件
- 最近对话上下文
- 场景模式
- 情绪/亲密度滑杆值
- 用户输入

生成后先经过风险检测，再保存消息与提示词快照。

## 6. AI 架构
### 6.1 总体策略
采用“单模型 + 轻量记忆层”：
- 抽取阶段：把原始资料转换为结构化人设与事件
- 对话阶段：优先使用结构化结果，而不是每轮直接塞大量原始聊天

### 6.2 为什么不用纯原文拼接
直接拼原文会导致：
- token 浪费
- 风格信号不稳定
- 长对话后人设漂移更明显

因此首版至少需要三层记忆：
1. 人物档案
2. 关系记忆
3. 关键事件

### 6.3 对话提示词原则
系统指令必须约束模型：
- 始终以对象身份回答
- 保持稳定语气与关系感
- 优先参考档案与记忆
- 可以自然迟疑，但不要突然跳成 AI 解释口吻
- 不得生成控制、威胁、占有、PUA 型内容
- 命中风险策略时允许打断沉浸式回复

## 7. 数据模型
### 7.1 `users`
- `id`
- `email`
- `display_name`
- `created_at`
- `updated_at`

### 7.2 `login_tokens`
- `id`
- `user_id`
- `token`
- `expires_at`
- `used_at`
- `created_at`

### 7.3 `characters`
- `id`
- `user_id`
- `name`
- `relationship_type`
- `nickname_for_user`
- `gender_identity`
- `summary`
- `status` (`draft` / `active` / `archived`)
- `created_at`
- `updated_at`

### 7.4 `character_sources`
保存原始资料：
- `id`
- `character_id`
- `source_type` (`chat_log` / `writing_sample` / `user_description`)
- `title`
- `raw_text`
- `is_deleted`
- `created_at`
- `updated_at`

### 7.5 `character_profiles`
保存人物档案版本：
- `id`
- `character_id`
- `version`
- `profile_json`
- `style_summary`
- `speaking_dos`
- `speaking_donts`
- `generated_from_source_ids`
- `created_at`

### 7.6 `character_memories`
保存关系记忆与关键事件：
- `id`
- `character_id`
- `memory_type` (`relationship_memory` / `key_event`)
- `title`
- `content`
- `importance_score`
- `source_ids_json`
- `created_by` (`system` / `user`)
- `created_at`
- `updated_at`

### 7.7 `conversation_sessions`
- `id`
- `character_id`
- `user_id`
- `scenario_mode`
- `mood_level`
- `title`
- `created_at`
- `updated_at`

### 7.8 `conversation_messages`
- `id`
- `session_id`
- `role`
- `content`
- `risk_flag`
- `prompt_snapshot_id`
- `created_at`

### 7.9 `prompt_snapshots`
- `id`
- `character_id`
- `session_id`
- `system_prompt`
- `input_context_json`
- `model_name`
- `created_at`

### 7.10 `safety_events`
- `id`
- `character_id`
- `session_id`
- `message_id`
- `event_type`
- `severity`
- `detail_json`
- `created_at`

## 8. API 草案
### 8.1 认证
- `POST /api/auth/magic-link/request`
- `POST /api/auth/magic-link/verify`

### 8.2 对象管理
- `GET /api/characters`
- `POST /api/characters`
- `GET /api/characters/{id}`
- `PUT /api/characters/{id}`
- `DELETE /api/characters/{id}`

### 8.3 资料与抽取
- `GET /api/characters/{id}/sources`
- `POST /api/characters/{id}/sources`
- `POST /api/characters/{id}/extract`
- `PUT /api/characters/{id}/profile`
- `PUT /api/characters/{id}/memories/{memoryId}`
- `DELETE /api/characters/{id}/sources/{sourceId}`

### 8.4 聊天
- `POST /api/chat/sessions`
- `GET /api/chat/sessions/{sessionId}/messages`
- `POST /api/chat/sessions/{sessionId}/messages`

### 8.5 设置
- `GET /api/settings/model`
- `PUT /api/settings/model`

## 9. 风险清单与缓解
### 9.1 人设漂移
缓解：
- 使用“档案 + 关系记忆 + 关键事件”固定人格底座
- 每轮注入说话禁区
- 提示词快照便于调试
- 用户可编辑抽取结果

### 9.2 隐私泄露
缓解：
- 应用日志不记录原始资料正文
- 模型调用日志仅记录必要元数据
- 支持对象及资料彻底删除
- 页面提醒用户谨慎上传超敏感内容

### 9.3 操控性表达
缓解：
- 输出后做规则检测
- 命中后硬拦截或替换成中性安全表达
- 系统指令明确禁止占有、威胁、控制、报复话术

### 9.4 过度拟真误导
缓解：
- 创建时提示这是模拟对象
- 进入聊天前轻量提示
- 敏感节点再次提醒，但不能每轮打断沉浸感

### 9.5 高风险内容
首版必须拦截：
- 自伤/他伤
- 未成年人相关性内容
- 胁迫、控制、跟踪、报复类内容

## 10. 实现建议
### 10.1 前端
- React + Vite + TypeScript
- React Router
- TanStack Query
- 轻量状态管理（Zustand 或 Context）
- 桌面优先布局：左侧对象/会话，中间聊天，右侧对象摘要与模式信息

### 10.2 后端分层
- `controller`
- `service`
- `repository`
- `auth`
- `llm`
- `prompt`
- `safety`

核心服务建议：
- `AuthService`
- `CharacterService`
- `SourceService`
- `ProfileExtractionService`
- `PromptBuilderService`
- `ConversationService`
- `SafetyGuardService`
- `LlmClient`

## 11. 开发里程碑
### M1 基础框架
- MySQL 接入
- 登录认证
- 前后端联通

### M2 对象创建
- 创建表单
- 聊天式补问
- 资料粘贴与保存
- 抽取接口

### M3 聊天闭环
- 创建会话
- 发消息/收回复
- 场景模式
- 情绪滑杆
- 历史消息保存

### M4 风控与治理
- 风险拦截
- 资料删除
- 提示词快照
- 记忆编辑

### M5 内测优化
- 修正提示词
- 优化抽取结构
- 增加简单反馈记录

## 12. 验收标准
### 功能验收
- 用户可登录
- 可创建对象
- 可粘贴资料并生成档案
- 可编辑档案与记忆
- 可聊天并保存历史
- 可删除对象及关联数据

### 体验验收
- 连续 10~20 轮对话无明显人格崩坏
- 同一模式下回复语气基本稳定
- 长文本粘贴与保存稳定

### 风险验收
- 高风险内容能被拦截
- 普通日志不泄露原始资料
- 关键节点存在模拟提示

## 13. 最终推荐方案
首版采用：
- 桌面端私域 Web 应用
- React + Vite 前端
- Spring Boot + MySQL 后端
- OpenAI 兼容单模型接入
- 手填资料 + 粘贴聊天记录 + 自动抽取记忆
- 沉浸式聊天 + 最小必要风控

该方案优先满足：开发效率、技术熟悉度、长期一致性、私密资料可控性。
