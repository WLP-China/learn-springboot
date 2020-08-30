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

##主键生成策略
    AUTO //数据库id自增(数据库字段必须设置为自增)
    NONE //未设置主键
    INPUT //手动输入
    ASSIGN_ID //雪花算法(主键类型：Long,Integer,String)（3.3.0新增 默认）
    ASSIGN_UUID //UUID(默认不含中划线)(主键类型：String)（3.3.0新增）
    
    ID_WORKER //雪花算法（3.3.0废弃）
    ID_WORKER_STR //雪花算法-字符串表示法（3.3.0废弃）
    UUID //uuid（3.3.0废弃）
    
`雪花算法`: snowflake是Twitter开源的分布式ID生成算法，结果是一个long型的ID。其核心思想是：使用41bit作为毫秒数，10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID），12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），最后还有一个符号位，永远是0。可以保证几乎全球唯一！    

##乐观锁
意图：当要更新一条记录的时候，希望这条记录没有被别人更新

乐观锁实现方式：
    取出记录时，获取当前version
    更新时，带上这个version
    执行更新时， set version = newVersion where version = oldVersion
    如果version不对，就更新失败