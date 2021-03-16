package com.yc.common.code.factory;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.*;

import java.util.HashMap;

/**
 * @Author: ChengJiaXiong
 * @Description: 生成代码工厂类
 * @Date: Created in 18:43 2021/3/15
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoGeneratorFactory {
    private String packagePath;
    private String moduleName;
    private String[] tableName;

    /**
     * 默认数据源配置
     */
    private DataSourceConfig defaultDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        return dataSourceConfig;
    }

    //生成
    public void generator() {
        this.generatorFacade();
        this.generatorCore();
    }

    private void generatorFacade() {
        String baseName = "facade";
        String packagePath = this.packagePath + "." + this.moduleName + "." + baseName;
        String moduleName = this.moduleName;
        String parentModule = "\\com-yc-" + moduleName;
        String module = parentModule + parentModule + "-facade";
        String captureName = AutoGeneratorFactory.captureName(moduleName);
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //当前路径
        String projectPath = System.getProperty("user.dir");
        //输出路径
        gc.setOutputDir(projectPath + module + "/src/main/java");
        gc.setAuthor("chengjiaxiong");    //设置作者
        //生成代码后，是否打开文件夹
        gc.setOpen(false);
        gc.setFileOverride(false);  //是否覆盖原来代码，个人建议设置为false,别覆盖，危险系数太高
        gc.setServiceName("%sFacade");
        gc.setEntityName("%sDTO");
        gc.setServiceImplName(captureName + "Exception");
        gc.setDateType(DateType.ONLY_DATE);   //日期格式
        mpg.setGlobalConfig(gc);
        // 数据源配置
        mpg.setDataSource(this.defaultDataSourceConfig());
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(this.packagePath + "." + this.moduleName);   //自定义包的路径
        pc.setServiceImpl(baseName + ".exception");
        pc.setEntity(baseName + ".dto");
        mpg.setPackageInfo(pc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableName);    //设置映射的表名，可以设置多个表
        //模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("/templates/dto.java");
        templateConfig.setServiceImpl("/templates/exception.java");
        templateConfig.setService(null);
        templateConfig.setController(null);
        templateConfig.setMapper(null);
        templateConfig.setXml(null);
        templateConfig.setEntityKt(null);
        mpg.setTemplate(templateConfig);
        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                this.setMap(new HashMap<String, Object>() {{
                    put("packageDto", packagePath + ".dto");
                    put("packageException", packagePath + ".exception");
                    put("module", captureName);
                }});
            }
        };
        mpg.setCfg(cfg);
        //包的命名规则，使用驼峰规则
        strategy.setNaming(NamingStrategy.nochange);
        //列的名称，使用驼峰规则
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否使用lombok
        strategy.setEntityLombokModel(true);
        //驼峰命名
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    private void generatorCore() {
        String baseName = "core";
        String packagePath = this.packagePath + "." + this.moduleName;
        String moduleName = this.moduleName;
        String parentModule = "\\com-yc-" + moduleName;
        String module = parentModule + parentModule + "-" + baseName;
        String captureName = AutoGeneratorFactory.captureName(moduleName);
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //当前路径
        String projectPath = System.getProperty("user.dir");
        //输出路径
        gc.setOutputDir(projectPath + module + "/src/main/java");
        gc.setAuthor("chengjiaxiong");    //设置作者
        //生成代码后，是否打开文件夹
        gc.setOpen(false);
        gc.setFileOverride(false);  //是否覆盖原来代码，个人建议设置为false,别覆盖，危险系数太高
        gc.setServiceImplName("%sService");
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        gc.setDateType(DateType.ONLY_DATE);   //日期格式
        mpg.setGlobalConfig(gc);
        // 数据源配置
        mpg.setDataSource(this.defaultDataSourceConfig());
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packagePath);   //自定义包的路径
        pc.setModuleName(baseName);
        pc.setServiceImpl("service");
        mpg.setPackageInfo(pc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableName);    //设置映射的表名，可以设置多个表
        //模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        templateConfig.setEntityKt(null);
        templateConfig.setXml(null);
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
        templateConfig.setService(null);
        mpg.setTemplate(templateConfig);

        //包的命名规则，使用驼峰规则
        strategy.setNaming(NamingStrategy.nochange);
        //列的名称，使用驼峰规则
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否使用lombok
        strategy.setEntityLombokModel(true);
        //驼峰命名
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    public static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static void main(String[] args) {
        // 代码生成器
        String[] tableNames = {
                "organize"
        };
        AutoGeneratorFactory.builder().moduleName("organize")
                .tableName(tableNames)
                .packagePath("com.yc").build().generator();
    }
}
