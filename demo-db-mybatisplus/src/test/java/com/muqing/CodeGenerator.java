package com.muqing;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 代码生成器
 * Create by iFun on 2020/08/31
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 构建 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
        // 配置策略
        // ===== 1、全局配置 =====
        GlobalConfig globalConfig = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        globalConfig.setOutputDir(projectPath + "\\src\\main\\java");
        globalConfig.setOutputDir("E:\\java\\generator");
        globalConfig.setAuthor("MuQing");
        globalConfig.setOpen(false); // 是否打开资源管理器
        globalConfig.setFileOverride(true); // 是否覆盖
        globalConfig.setServiceName("%sService"); // 去Service的I前缀
        globalConfig.setIdType(IdType.ASSIGN_ID);
        globalConfig.setDateType(DateType.TIME_PACK);
//        globalConfig.setSwagger2(true);
        mpg.setGlobalConfig(globalConfig);

        // ===== 2、设置数据源 =====
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("mysql123456");
        dataSource.setDbType(DbType.MYSQL);
        mpg.setDataSource(dataSource);

        // ===== 3、包的配置 =====
        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName("demo-db-mybatisplus");
        packageConfig.setParent("com.muqing");
//        packageConfig.setEntity("entity");
//        packageConfig.setMapper("mapper");
//        packageConfig.setService("service");
//        packageConfig.setController("controller");
        mpg.setPackageInfo(packageConfig);

        // ===== 4、策略配置 =====
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("sys_user", "sys_role"); // 设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel); // 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动lombok；
        strategy.setLogicDeleteFieldName("deleted");
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 自动填充配置
        TableFill gmtCreate = new TableFill("createTime", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("updateTime", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        // 乐观锁
        strategy.setVersionFieldName("version");
        //controller
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2
        mpg.setStrategy(strategy);

        mpg.execute(); //执行
    }
}
