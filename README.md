# ai-chat 项目

## 项目简介
使用trae  2小时生成一个ai-chat聊天工具
这是一个前后端分离的项目，包含前端Vue应用和后端Spring Boot服务。
具体实现效果参考网站 https://freshman.top
![image](https://github.com/user-attachments/assets/08f74527-b2a6-4405-ba96-e42580d0ed6b)
![image](https://github.com/user-attachments/assets/1c1c8909-9ead-4ab5-beee-228196815912)


## 技术栈

### 前端
- Vue 3
- Vite
- Axios
- Vue Router
- Marked (Markdown解析)
- DOMPurify (HTML净化)

### 后端
- Spring Boot
- Tomcat

## 安装与运行

### 前端
1. 进入frontend目录
2. 运行 `npm install` 安装依赖
3. 运行 `npm run dev` 启动开发服务器

### 后端
1. 确保已安装Java和Maven
2. 进入backend目录
3. 运行 `mvn spring-boot:run` 启动服务

## 项目结构
```
ai-chat/
├── frontend/        # 前端Vue应用
│   ├── src/        # 源代码
│   ├── public/     # 静态资源
│   └── ...
└── backend/        # 后端Spring Boot服务
    ├── src/        # 源代码
    └── ...
```

## 配置
- 前端API基础URL: `https://ip:端口` (可在frontend/src/api.js中修改)
- 后端服务端口: xxxx (可在backend/src/main/resources/application.yml中修改)
