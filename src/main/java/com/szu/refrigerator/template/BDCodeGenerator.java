package com.szu.refrigerator.template;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 *  46
 */
public class BDCodeGenerator {




    //项目地址
    public static String projectPath = System.getProperty("user.dir");

    //公司名
    public static String COMPANY_NAME = "szu";

    // 项目名
    public static String PROJECT_NAME = "refrigerator";

    //数据库名
    public static String DATABASE_PACKAGE = "refrigerator";


    /**
     * 控制是否生成mapper和xml
     */
    public  static  HashSet<String> controlSet = new HashSet<>();
    public static final String mapper = "mapper";
    public static final String xml = "xml";

    // 根据,分割生成需要的表，比如要生成table1和table2和table3，就使用下面的字符串
    public static String CREATE_TABLES = "object_type";//brand,model
   static  {
        /**
         * 控制是否生成mapper和xml
         */
        controlSet.add(mapper);
        controlSet.add(xml);
    }
    public static String URL = "jdbc:mysql://139.9.214.186:3306/refrigerator?useUnicode=true&characterEncoding=utf8&useSSL=false";
    public static String USERNAME = "xhc";
    // 数据库密码，这里填数据库的密码
    public static String DATABASE_MIMA = "123456";


    // 数据库驱动，默认是mysql的驱动，如果要改oracle的驱动，一般修改为  oracle.jdbc.driver.OracleDriver
    public static String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    // 是否生成控制层模板
    public static boolean CREATE_CONTROLLER = false;

    // 是否生成SERVICE层模板
    public static boolean CREATE_SERVICE = false;



    public static void main(String[] args) {


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 生成文件的输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        // 是否打开输出目录
        gc.setOpen(false);
        // 是否在xml中添加二级缓存配置
        gc.setEnableCache(false);
        // 开发人员 默认null
        gc.setAuthor("");
        // 实体属性开启Swagger2 注解
//        gc.setSwagger2(true);
        // 生成通用查询映射结果
        gc.setBaseResultMap(true);
        // 生成通用查询结果列
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DATABASE_DRIVER);
        dsc.setUsername(USERNAME);
        dsc.setPassword(DATABASE_MIMA);
        mpg.setDataSource(dsc);


        dsc.setTypeConvert(new SqlServerTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig,
                                                  String fieldType) {
                if (fieldType.contains("tinyint")) {
                    return DbColumnType.BOOLEAN;
                }
                if (fieldType.contains("datetime")) {
                    return DbColumnType.DATE;
                }
                if (fieldType.contains("decimal")) {
                    return DbColumnType.BIG_DECIMAL;
                }
                // 注意！！processTypeConvert 存在默认类型转换，
                //如果不是你要的效果请自定义返回、非如下直接返回。
                GlobalConfig config = new GlobalConfig();
                return super.processTypeConvert(config, fieldType);
            }

        });

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 包名:如com.dollar,则会生成com.dollar.controller,com.dollar.entity,com.dollar.mapper... 下生成文件
        pc.setParent("com.szu." + PROJECT_NAME );
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库映射到实体的命名规则 underline_to_camel:驼峰 no_change:不变
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段 如果没有配置父类,会导致此字段不生成
        //strategy.setSuperEntityColumns("id");
        // 需要逆向生产的表,隐藏则逆向生成全库的表的实体,慎用!
        strategy.setInclude((CREATE_TABLES).split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("rt_");
        mpg.setStrategy(strategy);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("databasePackage", DATABASE_PACKAGE);
                this.setMap(map);
            }
        };



        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();


    if(controlSet.contains(xml)){
        /**
         * 生成xml
         */
        // 选择模板引擎freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            //设置XML文件路径以及命名
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"  + DATABASE_PACKAGE +
                        "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
    }



if(controlSet.contains(mapper)){

    /**
     * 生成mapper
     */
    // 选择模板引擎mapper java
    String mapperJavaPath = "/templates/mapper.java.ftl";
    focList.add(new FileOutConfig(mapperJavaPath) {
        //设置XML文件路径以及命名
        @Override
        public String outputFile(TableInfo tableInfo) {
            // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！

            return projectPath + "/src/main/java/com/"+ COMPANY_NAME+"/" + PROJECT_NAME  + "/mapper" +  "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
        }
    });


}





        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        /**
         * 实体类
         */
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        //扫描templates下的自定义模板引擎,因为以及选择了freemarker,所以不需要加.ftl
        templateConfig.setEntity("/templates/db_entity.java");









        //暂时用不到
        if (CREATE_CONTROLLER) {
            templateConfig.setController("/templates/controller.java");
        } else {
            templateConfig.setController("");
        }
        templateConfig.setMapper("");
        if (CREATE_SERVICE) {
            templateConfig.setService("/templates/service.java");
            templateConfig.setServiceImpl("/templates/serviceImpl.java");
        } else {
            templateConfig.setService("");
            templateConfig.setServiceImpl("");
        }
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
