package com.winterchen.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisPlusGen {

    private static String projectPath =  System.getProperty("user.dir") + "/generator";
    private static String targetPath = projectPath + "/result/";
    
    //直接生成到桌面上
    public static void main(String[] args) {

        /*配置信息*/
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/medical_quality_control?autoReconnect=true&useSSL=false&characterEncoding=utf-8&useUnicode=true";
        String dbUsername = "root";
        String dbPassword = "root";

        String basePackage = "com.winterchen.nacos.entity";
        String basePackagePath = "com/winterchen/nacos/";

        //要处理的表名，为null时，处理全部的表
        String[] tableArray = new String[]{"export_job_config"};
        List<FileOutConfig> focList = new ArrayList<>();

        addFocList(focList, "template/Entity.ftl", basePackagePath+"domain/entity/${entityName}Entity.java");
        addFocList(focList, "template/Dto.ftl", basePackagePath+"domain/dto/${entityName}DTO.java");
        addFocList(focList, "template/Mapper.ftl", basePackagePath+"domain/mapper/${entityName}Mapper.java");
        addFocList(focList, "template/MapperXml.ftl", basePackagePath+"domain/mapperxml/${entityName}Mapper.xml");
        addFocList(focList, "template/Service.ftl", basePackagePath+"domain/service/${entityName}Service.java");
        addFocList(focList, "template/ServiceImpl.ftl", basePackagePath+"domain/service/${entityName}ServiceImpl.java");
        addFocList(focList, "template/Controller.ftl", basePackagePath+"domain/rest/${entityName}Controller.java");


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(projectPath + "/result");
        gc.setAuthor("mybatisPlusGen");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(jdbcUrl);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(dbUsername);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pathInfo设置为空Map，这样可以不生成自带的模板
        pc.setPathInfo(new HashMap<String, String>());
        pc.setParent(basePackage);

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("basePackage",basePackage);
                this.setMap(map);
            }
        };

        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            // 判断自定义文件夹是否需要创建
            return FileType.OTHER.equals(fileType);
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.ewell.common.entity.BaseEntity");
        strategy.setEntityLombokModel(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("create_time", "update_time", "del_flag");

        if(tableArray != null) {
            strategy.setInclude(tableArray);
        }
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);

        mpg.execute();
        
        System.out.println("生成结束!");

    }

    private static void addFocList(List<FileOutConfig> focList,String templatePath, String outputFullFilePath){

        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String realOuputFileName = outputFullFilePath.replace("${entityName}",tableInfo.getEntityName());

                realOuputFileName =
                        realOuputFileName.replace("${entityName?uncap_first}", StringUtils.uncapitalize(tableInfo.getEntityName()));

                List<TableField> fields = tableInfo.getFields();
                String parentDir = realOuputFileName.substring(0, realOuputFileName.lastIndexOf('/')+1);
                File file = new File(targetPath + parentDir);
                file.mkdirs();

                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return targetPath + realOuputFileName;
            }
        });

    }
}
