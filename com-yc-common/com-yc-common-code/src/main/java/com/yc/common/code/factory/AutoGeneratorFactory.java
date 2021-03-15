package com.yc.common.code.factory;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String moduleName;                          //模块名
    private String tableName;                           //表名
    private String outputDir;                           //输出目录，默认前缀为执行文件的当前目录
    private String packageName;                         //包名
    private AutoGenerator autoGenerator;                //生成配置
    private GlobalConfig globalConfig;                  //全局配置
    private DataSourceConfig dataSourceConfig;          //数据源配置
    private PackageConfig packageConfig;                //包配置
    private InjectionConfig injectionConfig;            //自定义配置
    private List<FileOutConfig> list;                   //文件输出配置
    private StrategyConfig strategyConfig;              //策略配置
    private TemplateConfig templateConfig;              //默认配置
    private AbstractTemplateEngine templateEngine;      //模板引擎
    private String dtoSuffix = "DTO";                   //dto后缀
    private String entitySuffix = "";                   //entity后缀
    private String mapperSuffix = "Mapper";             //mapper后缀
    private String facadeSuffix = "Facade";             //facade后缀
    private String providerSuffix = "FacadeImpl"; //providerImpl后缀
    private String serviceSuffix = "Service";           //service后缀

    /**
     * 默认全局配置
     */
    public void defaultGlobalConfig(){
        this.globalConfig = new GlobalConfig();
        this.globalConfig.setOutputDir(System.getProperty("user.dir")+outputDir);
        this.globalConfig.setAuthor("chengjiaxiong");
        this.globalConfig.setOpen(false);
        this.globalConfig.setEntityName("%sEntity");
        this.globalConfig.setDateType(DateType.ONLY_DATE);
        this.globalConfig.setFileOverride(false);//覆盖已有文件
    }

    /**
     * 默认数据源配置
     */
    public void defaultDataSourceConfig() {
        this.dataSourceConfig = new DataSourceConfig();
        this.dataSourceConfig.setUrl("jdbc:mysql://youchuan-test-rds.c1eouiepocc7.rds.cn-north-1.amazonaws.com.cn:3306/yc-dev?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false");
        this.dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        this.dataSourceConfig.setUsername("project");
        this.dataSourceConfig.setPassword("lKURYZ7HQq8k4rCC");
    }

    /**
     * 默认包配置
     */
    public void defaultPackageConfig(){
        this.packageConfig = new PackageConfig();
        this.packageConfig.setParent(packageName);
    }

    /**
     * 默认自定义配置
     */
    public void defaultInjectionConfig(){
        this.injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("module", moduleName);
                map.put("dto", packageName+".dto."+moduleName);
                map.put("dtoSuffix", dtoSuffix);
                map.put("entity", packageName+".entity."+moduleName);
                map.put("entitySuffix", entitySuffix);
                map.put("mapper", packageName+".mapper."+moduleName);
                map.put("mapperSuffix", mapperSuffix);
                map.put("facade", packageName+".facade."+moduleName);
                map.put("facadeSuffix", facadeSuffix);
                map.put("provider", packageName+".provider."+moduleName);
                map.put("providerSuffix", providerSuffix);
                map.put("service", packageName+".service."+moduleName);
                map.put("serviceSuffix", serviceSuffix);
                this.setMap(map);
            }
        };
    }

    /**
     * 默认模板配置
     */
    public void defaultTemplateConfig(){
        this.templateConfig = new TemplateConfig();
        this.templateConfig.setController(null);
        this.templateConfig.setService(null);
        this.templateConfig.setServiceImpl(null);
        this.templateConfig.setEntity(null);
        this.templateConfig.setMapper(null);
        this.templateConfig.setXml(null);
    }

    /**
     * 默认模板引擎
     */
    public void defaultTemplateEngine() {
        this.templateEngine = new FreemarkerTemplateEngine();
    }

    /**
     * 对外提供执行生成facade包方法
     */
    public void executeFacade(){
        this.initData();
        //facade封装参数
        this.autoGenerator.execute();
    }

    /**
     * 对外提供执行生成core包方法
     */
    public void executeCore(){
        this.initData();
        //core封装参数
        this.autoGenerator.execute();
    }

    /**
     * 准备数据
     */
    private void initData(){
        if(StringUtils.isEmpty(this.tableName)){
            throw new RuntimeException("tableName is null.");
        }
        if(StringUtils.isEmpty(this.outputDir)){
            throw new RuntimeException("outputDir is null.");
        }
        if(StringUtils.isEmpty(this.packageName)){
            throw new RuntimeException("packageName is null.");
        }
        if(this.globalConfig==null){
            this.defaultGlobalConfig();
        }
        if (this.dataSourceConfig == null) {
            this.defaultDataSourceConfig();
        }
        if(this.packageConfig==null){
            this.defaultPackageConfig();
        }
        if (this.templateEngine == null) {
            this.defaultTemplateEngine();
        }
        this.autoGenerator.setGlobalConfig(this.globalConfig)
                .setDataSource(this.dataSourceConfig)
                .setPackageInfo(this.packageConfig)
                .setCfg(this.injectionConfig)
                .setStrategy(this.strategyConfig)
                .setTemplateEngine(this.templateEngine);
    }

    /**
     * 执行方法
     */
    public void execute() {
        this.initData();
        this.autoGenerator.execute();
    }
}
