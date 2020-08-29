```
CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);
-- 真实开发中，version(乐观锁)、deleted(逻辑删除)、gmt_credit、gmt_modified
```

`jdbc:mysql://localhost:3306/db_name?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false`
useUnicode=true
characterEncoding=utf-8 字符集编码
serverTimezone=Asia/Shanghai 时区配置(mysql8需增加时区配置)
useSSL=false 是否使用安全链接
