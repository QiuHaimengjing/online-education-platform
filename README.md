# 在线教育平台

## 项目介绍
在线教育平台是一个参考尚硅谷谷粒学苑开发的项目。

以 Spring Cloud 搭建整个微服务架构，后台采用Spring Boot+MySQL+MyBatis-Plus+Redis，并且结合 Vue 前端框架，采用 Nuxt 服务端渲染技术来优化前端页面，运用阿里云视频点播技术。

在管理系统的后台中，运用 Spring Security 进行用户认证和授权，以确保对不同用户权限的细致划分。

在用户的登录系统方面，则采纳了手机验证码注册和登录方式，并运用 JWT 生成 Token 以实现便捷的单点登录。此外，用户通过微信支付来进行课程购买。

![](https://github.com/QiuHaimengjing/ImageStorage/blob/main/repositories/online-education-platform/online-education-platform01.png)

关于项目更详细的介绍可以访问我的文章：[在线教育平台](https://blog.invictusqiu.top/2024/05/27/OnlineEducation)

## 项目技术
### 后端
![JDK 1.8](https://img.shields.io/badge/JDK-1.8-007396?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.2.1-6DB33F?logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-6DB33F?logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![MyBatis-Plus](https://img.shields.io/badge/MyBatis--Plus-blue)
![Redis](https://img.shields.io/badge/Redis-DC382D?logo=redis&logoColor=white)
![EasyExcel](https://img.shields.io/badge/EasyExcel-orange)

### 前端
![Node.js](https://img.shields.io/badge/Node.js-14.21.3-339933?logo=nodedotjs&logoColor=white)
![Vue.js](https://img.shields.io/badge/Vue.js-2.5.17-4FC08D?logo=vue.js&logoColor=white)
![Nuxt.js](https://img.shields.io/badge/Nuxt.js-2.0.0-00DC82?logo=nuxt.js&logoColor=white)
![ElementUI](https://img.shields.io/badge/ElementUI-2.15.14-409EFF?logo=element&logoColor=white)
![Axios](https://img.shields.io/badge/Axios-5A29E4?logo=axios&logoColor=white)
![ECharts](https://img.shields.io/badge/ECharts-AA344D?logo=apacheecharts&logoColor=white)  
后台管理系统采用 [vue-admin-template 模板](https://github.com/PanJiaChen/vue-admin-template)

## 项目结构
```
online-education-platform
|—— install 项目启动工具
├── admin-frontend 后台管理系统前端
├── user-frontend 前台用户系统前端
├── online-education 系统后端
│   ├── sql 数据库脚本
│   ├── canal_clientedu canal客户端
│   ├── common 公共模块
│   ├── infrastructure 网关
│   ├── service 服务模块
│      ├── service_acl 权限管理
│      ├── service_cms 内容管理
│      ├── service_edu 后台管理
│      ├── service_msm 短信服务
│      ├── service_order 订单服务
│      ├── service_oss 文件服务
│      ├── service_statistics 统计服务
│      ├── service_ucenter 用户中心
│      ├── service_vod 视频点播
```

## 项目启动
### 后端
1. 创建数据库，导入`online-education/sql`目录下的数据库脚本;
2. 在安装依赖时可能会无法下载阿里云上传视频服务的 SDK，[阿里云官方](https://help.aliyun.com/zh/vod/developer-reference/sdk-overview-and-download#title-mbq-hhb-gy2)。要自行去官网下载，并安装进 maven 库中，选择历史版本 1.4.11。在此我在项目的`install`目录下已经提供了该 jar 包`aliyun-java-vod-upload-1.4.11.jar`，可以直接使用 maven 命令安装。  
安装步骤：将包复制到 maven 的 bin 目录下，然后打开 cmd 窗口运行以下代码:
```
mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-sdk-vod-upload -Dversion=1.4.11 -Dpackaging=jar  -Dfile=aliyun-java-vod-upload-1.4.11.jar
```
```
mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-java-vod-upload -Dversion=1.4.11 -Dpackaging=jar  -Dfile=aliyun-java-vod-upload-1.4.11.jar
```
3. 安装 Nacos 并启动`startup.cmd -m standalone`，在 Nacos 页面首先创建 dev 服务，然后在 Nocos 的配置列表选择 dev 空间导入配置（直接选择压缩包导入），压缩包在项目的`install`目录下`nacos_config_export.zip`；
4. 导入配置文件后修改`datasource.yml`和`redis.yml`的配置，其他配置如需要请自行修改；
5. 在 idea 中将每个项目模块的`resources`目录配置为资源目录（`test`目录应该也无法识别），否则无法正常启动；
6. 修改每个模块的`bootstrap.yml`的命名空间为 Nacos 命名空间 dev 的值（默认为public的）
7. 如需启用阿里云OSS、视频服务、短信服务，请自行注册阿里云账号并开通相应服务，然后在 Nacos 中配置相应的参数。

## 前端
后台管理系统安装依赖和启动，高版本 Node 可能会直接报错，建议使用 14.21.3 版本：
```
npm install -g cnpm@7.1.0 --registry=http://registry.npmmirror.com
cnpm install

npm start
```
前台用户系统安装依赖和启动：
```
npm install
npm run dev
```

## 📢 意见与支持
该项目仅作为学习项目，各个模块的功能并不完善，仅供参考。  如果你对这个项目有任何建议或问题，欢迎随时提 Issue 或提交 PR！你的每一个建议都对我非常重要 ❤️。

如果你觉得这个项目对你有帮助，不妨点亮一个 ⭐️ 支持我吧！  
