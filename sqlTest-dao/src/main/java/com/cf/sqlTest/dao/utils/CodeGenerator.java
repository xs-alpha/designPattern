package com.cf.sqlTest.dao.utils;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


/**
 * 自定义模板 代码生成器
 */
@Slf4j

public class CodeGenerator {

    public static final String[] tableArray = {"user_nums"};
    public static final String[] tablePrefixArray = {""};
    public static final String packageName = "com.cf";
    public static final String moduleName = "sqlTest";

    public static final String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai";
    public static final String userName = "root";
    public static final String password = "root";

    public static void main(String[] args) {
        log.info("==========================准备生成文件...==========================");
        for (String tableName : tableArray) {
            create(tableName);
        }
        log.info("==========================文件生成完成！！！==========================");
    }


    static void create(String tableName) {
        // 生产文件的项目相对位置
        StringBuilder projectPath = new StringBuilder();
        // 获取系统路径
        String systemPath = System.getProperty("user.dir");
        // 将反斜杠全部替换为双斜杠 并拼接项目路径
        projectPath.append(systemPath.replaceAll("\\\\", "//"));

        // 1 配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url, userName, password).build();
        AutoGenerator generator = new AutoGenerator(dataSourceConfig);

        // 2 全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .fileOverride() // 覆盖已生成文件
                .disableOpenDir() // 禁止打开输出目录	默认值:true
                .outputDir(systemPath) // 指定输出目录	/opt/baomidou/ 默认值: windows:D:// linux or mac : /tmp
                .author("whx") // TODO 作者名	默认值:作者
                .enableSwagger() // 开启 swagger 模式	默认值:false
//                .enableKotlin() // 开启 kotlin 模式	默认值:false
//                .dateType(DateType.TIME_PACK) // 时间策略	DateType.ONLY_DATE 默认值: DateType.TIME_PACK
//                .commentDate("yyyy-MM-dd") // 注释日期	默认值: yyyy-MM-dd
                .build();
        generator.global(globalConfig);

        // 3 包配置
        // 3.1 自定义包名
        PackageConfig.Builder packageConfig = new PackageConfig.Builder()
                .parent("") // 父包名	默认值:com.baomidou
                .moduleName("") // 父包模块名	默认值:无
                .entity("sqlTest-dao.src.main.java.com.cf.sqlTest.dao.po") // Entity 包名	默认值:entity
                .service("sqlTest-services.src.main.java.com.cf.sqlTest.services.service") // Service 包名	默认值:service
                .mapper("sqlTest-dao.src.main.java.com.cf.sqlTest.dao.mapper") // Mapper 包名	默认值:mapper
                .xml("sqlTest-dao.src.main.resources.mapping") // Mapper XML 包名	默认值:mapper.xml
                ;

        // 3.2 自定义路径，多种情况自行添加 pathInfo.put
        Map<OutputFile, String> pathInfo = new HashMap<>();
        //pathInfo.put(OutputFile.mapperXml, projectPath + "//resources//mapper"); // 单独配置xml路径，可覆盖全局配置。
        packageConfig.pathInfo(pathInfo);

        generator.packageInfo(packageConfig.build());

        // 4 自定义模板配置，配置模板路径
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY) // 禁用模板	TemplateType.ENTITY
                .entity("/templates/PO.java")// 设置实体模板路径(JAVA)	/templates/entity.java
                .service("/templates/service.java")
                .controller(null)
                .serviceImpl(null)
                .mapper("/templates/mapper.java")
                .mapperXml("/templates/mapper.xml")
                .build();
        generator.template(templateConfig);

        // 6 设置自定义属性
        Map<String, Object> map = new HashMap<>();

        setAttr(tableName, dataSourceConfig, map);

        generator.injection(injectionConfig().customMap(map).build());

        // 7 配置策略，策略非常多，如有其他需求，请参考官网
        // 7.1 添加表、表名过滤、字段过滤
        StrategyConfig.Builder strategyConfig = new StrategyConfig.Builder()
                .addInclude(tableName) // 设置需要生成的表名，多个逗号分开
                .addTablePrefix(tablePrefixArray); // 过滤表前缀，多个逗号分开
//                .addFieldSuffix("_202201", "202001"); // 过滤表后缀

        // 7.2 格式化文件名称，以下都是默认值
        // 7.3 Entity 策略配置，如果有需要，mapper、service、controller都可以配置
        strategyConfig
                .entityBuilder().formatFileName("%sPO")
                .addSuperEntityColumns() // 基于数据库字段，忽略某个字段，【好像不管用】
                .mapperBuilder().formatMapperFileName("%sMapper").formatXmlFileName("%sMapper")
                .serviceBuilder().formatServiceFileName("%sService")
                .controllerBuilder().formatFileName("%sController");

        // 策略非常多，所以将.build()放到最后
        generator.strategy(strategyConfig.build());

        // 8 执行
        generator.execute();
    }

    /**
     * 注入配置
     */
    private static InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作在执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> System.out.println("------------------tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size()));
    }


    /**
     * 组装模板属性
     *
     * @param tableName        表名
     * @param dataSourceConfig 数据源
     * @param map              模板里面 自定义的属性
     */
    private static void setAttr(String tableName, DataSourceConfig dataSourceConfig, Map<String, Object> map) {
        List<Map<String, Object>> columns = new ArrayList<>();

        // 获取表信息sql
        String tableSql = "select table_name , table_comment from information_schema.tables " +
                "where table_schema = (select database()) and table_name = '" + tableName + "'";
        // 获取字段信息sql
        String columnSql = "select column_name , data_type , column_comment ,column_key,extra from information_schema.columns " +
                "where table_name = '" + tableName + "' and table_schema = (select database()) and column_name != 'id_new'";
        // 利用现有的dataSourceConfig来获取数据库连接，查询表字段及备注等信息
        try (Connection conn = dataSourceConfig.getConn();
             PreparedStatement psTable = conn.prepareStatement(tableSql);
             ResultSet rsTable = psTable.executeQuery();
             PreparedStatement ps = conn.prepareStatement(columnSql);
             ResultSet rs = ps.executeQuery()
        ) {
            if (rsTable.next()) {
                String table_name = rsTable.getString("table_name");
                map.put("tableName", table_name);
                map.put("comment", rsTable.getString("table_comment"));

                // 过滤表前缀
                for (String s : CodeGenerator.tablePrefixArray) {
                    table_name = table_name.replaceAll(s, "");
                }
                // 类名 大驼峰
                map.put("upperClassName", StrUtil.upperFirst(StrUtil.toCamelCase(table_name)));
                // 对象名 小驼峰
                map.put("lowerClassName", StrUtil.toCamelCase(table_name));
            }
            while (rs.next()) {
                Map<String, Object> columnMap = new HashMap<>();
                // 列名字、数据类型、java属性名字、java属性类型、备注
                columnMap.put("column_name", rs.getString("column_name"));
                columnMap.put("data_type", rs.getString("data_type"));
                columnMap.put("javaLowerAttrName", StrUtil.toCamelCase(rs.getString("column_name")));
                columnMap.put("javaAttrType", columnTypeToJavaType(rs.getString("data_type")));
                columnMap.put("column_comment", rs.getString("column_comment"));
                columnMap.put("column_key", rs.getString("column_key"));
                columnMap.put("extra", rs.getString("extra"));
                columns.add(columnMap);
            }
        } catch (Exception e) {
            log.info("----------sql执行出错");
            e.printStackTrace();
        }

        map.put("columns", columns);
        map.put("datetime", DateUtil.now());
        map.put("packageName", packageName);
        map.put("moduleName", moduleName);
    }

    /**
     * 数据库类型转换为java类型
     *
     * @param columnType 数据库类型
     * @return java类型
     */
    private static String columnTypeToJavaType(String columnType) {
        if (StrUtil.isNotEmpty(columnType)) {
            if (Arrays.asList(COLUMN_TYPE_STR).contains(columnType)) {
                return TYPE_STRING;
            }
            if (Arrays.asList(COLUMN_TYPE_TEXT).contains(columnType)) {
                return TYPE_STRING;
            }
            if (Arrays.asList(COLUMN_TYPE_TIME).contains(columnType)) {
                return TYPE_DATE;
            }
            if (Arrays.asList(COLUMN_TYPE_NUMBER).contains(columnType)) {
                return TYPE_INTEGER;
            }
            if (Arrays.asList(COLUMN_TYPE_BIGINT).contains(columnType)) {
                return TYPE_LONG;
            }
            if (Arrays.asList(COLUMN_TYPE_FLOAT).contains(columnType)) {
                return TYPE_DOUBLE;
            }
            if (Arrays.asList(COLUMN_TYPE_DOUBLE).contains(columnType)) {
                return TYPE_DOUBLE;
            }
            if (Arrays.asList(COLUMN_TYPE_DECIMAL).contains(columnType)) {
                return TYPE_BIGDECIMAL;
            }
        }
        return null;
    }

    /**
     * 数据库字符串类型
     */
    public static final String[] COLUMN_TYPE_STR = {"char", "varchar", "nvarchar", "varchar2"};

    /**
     * 数据库文本类型
     */
    public static final String[] COLUMN_TYPE_TEXT = {"tinytext", "text", "mediumtext", "longtext"};

    /**
     * 数据库时间类型
     */
    public static final String[] COLUMN_TYPE_TIME = {"datetime", "time", "date", "timestamp"};

    /**
     * 数据库数字类型
     */
    public static final String[] COLUMN_TYPE_NUMBER = {"tinyint", "smallint", "mediumint", "int", "number", "integer", "bit"};

    /**
     * 数据库bigint类型
     */
    public static final String[] COLUMN_TYPE_BIGINT = {"bigint"};
    /**
     * 数据库float类型
     */
    public static final String[] COLUMN_TYPE_FLOAT = {"float"};
    /**
     * 数据库double类型
     */
    public static final String[] COLUMN_TYPE_DOUBLE = {"double"};
    /**
     * 数据库decimal类型
     */
    public static final String[] COLUMN_TYPE_DECIMAL = {"decimal"};

    /**
     * 字符串类型
     */
    public static final String TYPE_STRING = "String";

    /**
     * 整型
     */
    public static final String TYPE_INTEGER = "Integer";

    /**
     * 长整型
     */
    public static final String TYPE_LONG = "Long";

    /**
     * 浮点型
     */
    public static final String TYPE_DOUBLE = "Double";

    /**
     * 高精度计算类型
     */
    public static final String TYPE_BIGDECIMAL = "BigDecimal";

    /**
     * 时间类型
     */
    public static final String TYPE_DATE = "Date";
}
