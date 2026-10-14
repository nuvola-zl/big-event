# big-event —— 大事件（文章管理系统后端）

跟练黑马程序员课程的文章管理项目：一个前后端分离的博客/资讯系统后端。
**如实标注：本项目为课程跟练作品**，在理解的基础上手敲实现。

## 功能

- 用户注册 / 登录（JWT 认证，双密钥区分管理员与读者）
- 文章分类管理
- 文章的发布、列表、详情、编辑、删除
- 头像上传（阿里云 OSS）

## 技术栈

Spring Boot · MyBatis（Mapper 接口 + XML）· MySQL · JWT · 阿里云 OSS（环境变量注入）· Maven

## 运行

1. 创建 MySQL 数据库 `big_event`（utf8mb4）；
2. 数据库密码默认通过环境变量 `MYSQL_PASSWORD` 注入（缺省值 `123456`，本地测试用）；
3. OSS 密钥通过环境变量 `ALIYUN_OSS_ENDPOINT` / `ALIYUN_ACCESS_KEY_ID` / `ALIYUN_ACCESS_KEY_SECRET` /
   `ALIYUN_OSS_BUCKET_NAME` 注入；
4. 运行 `BigEventApplication`，默认端口 8080。

## 分支说明

本仓库默认分支为 `main`（课程代码 + 规范化提交），历史分支 `master` 保留课程初始状态。
