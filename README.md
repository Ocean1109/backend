# 通用后端
## 项目结构
common：个人构建的返回值架构，如果不符合使用者习惯可以删除.  
config：跨域问题和Spring Security配置.  
controller：测试controller.  
entity：数据库实体类型.  
mapper：持久化mapper.  
service：测试service层.  
application.yaml：工程配置文件.  
## 建表语句
```
CREATE TABLE `ocean_test`.`无标题`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  `user_account` varchar(32) NOT NULL COMMENT '用户账号',
  `user_password` varchar(16) NOT NULL COMMENT '用户密码',
  `user_role` varchar(30) NOT NULL COMMENT '用户权限',
  PRIMARY KEY (`id`)
);
```
