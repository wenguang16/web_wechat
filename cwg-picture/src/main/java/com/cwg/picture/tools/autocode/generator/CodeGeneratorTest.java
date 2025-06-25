package com.cwg.picture.tools.autocode.generator;

/**
 * @author : chenwg
 * @date : 2025/6/25
 */


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

/**
 * MyBatis-Plus 代码生成器测试类
 * 用于自动生成实体类、Mapper、Service、Controller等代码
 * 
 * @author: chenwg
 * @date: 2025/1/25
 * @version 1.0
 */
public class CodeGeneratorTest {


    public  static  void main(String[] args) {

        FastAutoGenerator.create(
                        //数据源配置，请根据实际情况修改数据库连接信息
                        new DataSourceConfig.Builder(
                                "jdbc:mysql://localhost:3306/yu_picture?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8",
                                "root",
                                "123456"
                        )
                                .dbQuery(new MySqlQuery())
                                .typeConvert(new MySqlTypeConvert())
                                .keyWordsHandler(new MySqlKeyWordsHandler())
                )

                // 全局配置
                .globalConfig(builder -> {
                    builder.author("chenwg") // 设置作者
                            .disableOpenDir() // 禁止打开输出目录
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })

                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.cwg.picture") // 设置父包名
                            .entity("model.entity")    // 实体类包名
                            .service("service")        // Service 接口包名
                            .serviceImpl("service.impl") // Service 实现类包名
                            .mapper("mapper")          // Mapper 接口包名
                            .controller("controller")  // Controller 包名
                            .pathInfo(Collections.singletonMap(
                                    OutputFile.xml, 
                                    System.getProperty("user.dir") + "/src/main/resources/mapper"
                            )); // 存放 mapper.xml 路径
                })

                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("") // 设置过滤表前缀
                            
                            // 实体类策略配置
                            .entityBuilder()
                            .enableLombok() // 使用 Lombok
                            .enableTableFieldAnnotation() // 实体类字段注解
                            .enableChainModel() // 链式模型
                            .enableRemoveIsPrefix() // 移除 is 前缀
                            .enableSerialVersionUID() // 序列化版本号
                            .logicDeleteColumnName("isDelete") // 逻辑删除字段名
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                             .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                            
                            // Controller 策略配置
                            .controllerBuilder()
                            .enableRestStyle() // 开启 RestController
                            .enableHyphenStyle() // 开启驼峰转连字符
                            
                            // Service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // Service 接口名格式
                            .formatServiceImplFileName("%sServiceImpl") // Service 实现类名格式
                            
                            // Mapper 策略配置
                            .mapperBuilder()
                            .enableMapperAnnotation() // 开启 @Mapper 注解
                            .enableBaseResultMap() // 启用 BaseResultMap 生成
                            .enableBaseColumnList(); // 启用 BaseColumnList
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

