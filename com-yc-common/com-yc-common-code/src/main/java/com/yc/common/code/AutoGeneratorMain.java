package com.yc.common.code;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 16:39 2021/3/15
 */
@Slf4j
public class AutoGeneratorMain {
    private static final String tableName = "article_bind";
    private static final String moduleName = "article";
    private static final boolean createController = false;
    private static final String daoName = "api-dao";
    private static final String serviceName = "api-business";
    private static final String controllerName = "api-controller";
    private static String module;
    private static String projectName = "api-commons";
    private static final String commonModule = "domain";
    private static final String packageName = "com.ycandyz.uke";
    private static String parentPackageName;
    private static final String[] tablePrefix = new String[] {"ad_","tb_"};
    private static final String dtoSuffix = "DTO";
    private static final String voSuffix = "VO";
    private static final String querySuffix = "Query";
    private static final String mapperSuffix = "Dao";
    private static final String respSuffix = "Resp";
    private static final String modelSuffix = "Model";


    public static void main(String[] args) {
        run();
        projectName = daoName;
        module = packageName.substring(packageName.lastIndexOf('.')+1);
        parentPackageName = packageName.substring(0,packageName.lastIndexOf('.'));
        daoRun();
        projectName = serviceName;
//		serviceRun();
        if(createController){
            projectName = controllerName;
            controllerRun();
        }
    }

    public static DataSourceConfig getJdbc() {
        DataSourceConfig dsc = new DataSourceConfig();
        //dsc.setUrl("jdbc:mysql://localhost:3306/yc-dev?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false");
        dsc.setUrl("jdbc:mysql://youchuan-test-rds.c1eouiepocc7.rds.cn-north-1.amazonaws.com.cn:3306/yc-dev?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        //dsc.setUsername("root");
        //dsc.setPassword("root");
        dsc.setUsername("project");
        dsc.setPassword("lKURYZ7HQq8k4rCC");
        return dsc;
    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir");
        //String projectPath = path.substring(0,path.lastIndexOf('/')+1)+projectName;
        return path+"/"+projectName;
    }

    public static GlobalConfig getGlobalConfig(){
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(getProjectPath() + "/src/main/java");
        gc.setAuthor("hkh");
        gc.setOpen(false);
        gc.setEntityName("%s");
        //gc.setServiceImplName("%sService");//单机项目取消service接口配置，直接命名实现类为*Service
        //gc.setBaseResultMap(true);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        gc.setFileOverride(false);//覆盖已有文件
        return gc;
    }

    public static PackageConfig getPackageConfig(String moduleName){
        // 包配置
        PackageConfig pc = new PackageConfig();
        if(StringUtils.isNotEmpty(moduleName)){
            pc.setParent(packageName);
            pc.setModuleName(moduleName);
        }else{
            pc.setParent(parentPackageName);
            pc.setModuleName(module);
        }
        //pc.setModuleName(scanner("模块名"));
        return pc;
    }

    public static StrategyConfig getStrategyConfig(){
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setInclude(scanner("表名"));
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperEntityColumns("id");
        strategy.setEntityTableFieldAnnotationEnable(false);
        return strategy;
    }

    public static InjectionConfig getInjectionConfig(){
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("module", moduleName);
                map.put("dto", packageName+".dto."+moduleName);
                map.put("dtoSuffix", dtoSuffix);
                map.put("vo", packageName+".model."+moduleName);
                map.put("voSuffix", voSuffix);
                map.put("query", packageName+StringPool.DOT+commonModule+".query."+moduleName);
                map.put("querySuffix", querySuffix);
                map.put("resp", packageName+StringPool.DOT+commonModule+".response."+moduleName);
                map.put("respSuffix", respSuffix);
                map.put("model", packageName+StringPool.DOT+commonModule+".model."+moduleName);
                map.put("modelSuffix", modelSuffix);
                map.put("entity", packageName+".entities."+moduleName);
                map.put("Mapper", packageName+".dao."+moduleName);
                map.put("mapperSuffix", mapperSuffix);
                map.put("Service", packageName+".service."+moduleName);
                map.put("ServiceImpl", packageName+".service."+moduleName+".impl");
                map.put("Controller", packageName+".controller."+moduleName);
                this.setMap(map);
            }
        };
        return cfg;
    }
    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void controllerRun() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(getGlobalConfig());
        mpg.setDataSource(getJdbc());
        PackageConfig pc = getPackageConfig(null);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = getInjectionConfig();
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) +"/controller/"+moduleName+"/" +tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板
        TemplateConfig tc = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setEntity(null);
        tc.setMapper(null);
        tc.setXml(null);
        mpg.setTemplate(tc);

        mpg.setStrategy(getStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static void serviceRun() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(getGlobalConfig());
        mpg.setDataSource(getJdbc());
        PackageConfig pc = getPackageConfig(null);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = getInjectionConfig();
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
		/*focList.add(new FileOutConfig("/templates/vo.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) +"/model/"+moduleName+"/" +tableInfo.getEntityName()+ voSuffix + StringPool.DOT_JAVA;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);*/

        focList.add(new FileOutConfig("/templates/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) +"/service/"+moduleName+"/" +tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        focList.add(new FileOutConfig("/templates/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) +"/service/"+moduleName+"/impl/" +tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig tc = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setEntity(null);
        tc.setMapper(null);
        tc.setXml(null);
        mpg.setTemplate(tc);

        mpg.setStrategy(getStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static void daoRun() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(getGlobalConfig());
        mpg.setDataSource(getJdbc());
        PackageConfig pc = getPackageConfig(null);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = getInjectionConfig();
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return getProjectPath() + "/src/main/resources/mapper/" +moduleName+"/"+ tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        focList.add(new FileOutConfig("/templates/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) +"/dao/"+moduleName+"/" + tableInfo.getEntityName()+ mapperSuffix + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

		/*focList.add(new FileOutConfig("/templates/dto.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) + "/dto/"+moduleName+"/" +tableInfo.getEntityName()+ dtoSuffix + StringPool.DOT_JAVA;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);*/

        // 配置模板
        TemplateConfig tc = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setEntity(null);
        tc.setMapper(null);
        tc.setXml(null);
        mpg.setTemplate(tc);

        mpg.setStrategy(getStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    public static void run() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(getGlobalConfig());
        mpg.setDataSource(getJdbc());
        PackageConfig pc = getPackageConfig(commonModule);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = getInjectionConfig();
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出

        focList.add(new FileOutConfig("/templates/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getProjectPath() + "/src/main/java/" + packageName.replace(StringPool.DOT,StringPool.SLASH) +"/entities/"+moduleName+"/" +tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        focList.add(new FileOutConfig("/templates/query.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) + "/query/"+moduleName+"/" +tableInfo.getEntityName()+querySuffix + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        focList.add(new FileOutConfig("/templates/resp.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) + "/response/"+moduleName+"/" +tableInfo.getEntityName()+ respSuffix + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        focList.add(new FileOutConfig("/templates/model.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getProjectPath() + "/src/main/java/" + pc.getParent().replace(StringPool.DOT,StringPool.SLASH) + "/model/"+moduleName+"/" +tableInfo.getEntityName()+ modelSuffix + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig tc = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setEntity(null);
        tc.setMapper(null);
        tc.setXml(null);
        mpg.setTemplate(tc);
        mpg.setStrategy(getStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
