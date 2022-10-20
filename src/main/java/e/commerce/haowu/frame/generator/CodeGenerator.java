package e.commerce.haowu.frame.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author bobo
 * @date 2021/04/19
 */
public class CodeGenerator {

    private static final String projectPath = System.getProperty("user.dir");

    private static final String URL ="jdbc:mysql://localhost:3306/e-commerce?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static final String author = "bobo";
    private static final String basePackage = "e.commerce.haowu";
    private static final String moduleName = "system";
    private static final String SuperEntityClass = "e.commerce.haowu.frame.dto.BaseEntity";
    private static final String templatePath = "/templates/mapper.xml.ftl";

    public static void main(String[] args) {
        String[] nameList = new String[]{"user_info","order_info","goods_info","delivery_addr_info"};
        for (int i = 0; i < nameList.length; i++) {
            codeGenerator(nameList[i]);
        }
    }

    private static void codeGenerator(String tableName){
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        //每个类的作者
        gc.setAuthor(author);
        // 是否打开输出目录
        gc.setOpen(false);
        //是否开启swagger注解
        gc.setSwagger2(true);
        // controller 命名方式，注意 %s 会自动填充表实体属性
        gc.setControllerName("%sController");
        // service 命名方式
        gc.setServiceName("%sService");
        // serviceImpl 命名方式
        gc.setServiceImplName("%sServiceImpl");
        // mapper 命名方式
        gc.setMapperName("%sMapper");
        // xml 命名方式
        gc.setXmlName("%sMapper");
        // 时间格式
        gc.setDateType(DateType.ONLY_DATE);
        // 是否覆盖已有文件
        gc.setFileOverride(false);
        // 是否在xml中添加二级缓存配置
        gc.setEnableCache(false);
        // 是否开启 BaseResultMap
        gc.setBaseResultMap(false);

        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        dsc.setDbType(DbType.MYSQL);

        mpg.setDataSource(dsc);

        //基础包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(basePackage);
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");

        mpg.setPackageInfo(pc);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("data1", "1.0.0");
                this.setMap(map);
            }
        };

        //自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        //自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输出文件名，如果Entity设置了前后缀，此处注意xml的名称会跟着发生变化！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        //要映射的表名
        strategy.setInclude(tableName);
        // 数据库表映射到实体的命名策略，驼峰原则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字数据库表字段映射到实体的命名策略，驼峰原则
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 实体类父类
        strategy.setSuperEntityClass(SuperEntityClass);
        //设置是否需要lombok
        strategy.setEntityLombokModel(true);
        // 实体是否生成 serialVersionUID
        strategy.setEntitySerialVersionUID(false);
        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 自动注入监听器配置
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("status", FieldFill.INSERT));
        strategy.setTableFillList(tableFills);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setStrategy(strategy);

        mpg.execute();
    }

}
