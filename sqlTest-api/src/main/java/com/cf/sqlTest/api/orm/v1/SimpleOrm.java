package com.cf.sqlTest.api.orm.v1;

import com.cf.sqlTest.api.orm.Member;
import com.cf.sqlTest.api.orm.SelfOrmTableField;
import com.cf.sqlTest.api.orm.SelfOrmTableName;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: lpy
 * @Date: 2023/12/04
 */
public class SimpleOrm {
    public static void main(String[] args) {
        Member member = new Member();
        member.setAge(34);
        List<Member> select = (List<Member>) select(member);
        System.out.println(select);
    }

    public static List<?> select(Object condition) {
        ArrayList<Object> resultList = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;

        Class<?> entityClass = condition.getClass();
        try {
            // 1.加载驱动类
//            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.建立连接
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/blog?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai", "root", "root");
            StringBuilder sql = new StringBuilder();
            // 缓存字段名和表字段的映射
            HashMap<String, String> getFieldNameByColumn = new HashMap<>();
            HashMap<String, String> getColumnByFieldName = new HashMap<>();
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (field.isAnnotationPresent(SelfOrmTableField.class)) {
                    SelfOrmTableField annotation = field.getAnnotation(SelfOrmTableField.class);
                    String value = annotation.value();

                    getFieldNameByColumn.put(fieldName, value);
                    getColumnByFieldName.put(value, fieldName);
                } else {
                    getFieldNameByColumn.put(fieldName, fieldName);
                    getColumnByFieldName.put(fieldName, fieldName);
                }

            }

            SelfOrmTableName annotation = entityClass.getAnnotation(SelfOrmTableName.class);
            String tableName = "";
            if (null != annotation) {
                tableName = annotation.value();
            } else {
                tableName = entityClass.getSimpleName().toLowerCase();
            }

            sql.append("select * from " + tableName + " where 1=1 ");


            // 拿到对象的字段，如果不为空则拼接，否则，不拼接
            for (Field field : fields) {
                Object val = field.get(condition);
                if (null != val) {
                    if (String.class == field.getType()) {
                        sql.append("and " + getFieldNameByColumn.get(field.getName()) + " = '" + val + "'");
                    } else {
                        sql.append("and " + getFieldNameByColumn.get(field.getName()) + " = " + val);
                    }
                }
            }

            System.out.println("sql语句：" + sql);

            // 3.创建语句
            pstm = con.prepareStatement(sql.toString());

            // 获取结果集
            rs = pstm.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object instance = entityClass.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    Field field = entityClass.getDeclaredField(getColumnByFieldName.get(columnName));
                    field.setAccessible(true);
                    field.set(instance, rs.getObject(columnName));
                }
                resultList.add(instance);
            }
            return resultList;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != con) {
                    con.close();
                }
                if (null != pstm) {
                    pstm.close();
                }
                if (null != rs) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return new ArrayList<>();
    }
}
/**
 * Class.forName("com.mysql.cj.jdbc.Driver") 是 Java 中加载驱动程序的方式之一，用于加载并注册 MySQL 数据库的 JDBC 驱动程序。
 * <p>
 * 具体地说，这行代码会触发以下几个步骤：
 * <p>
 * 类加载：
 * <p>
 * Class.forName() 方法用于加载指定名称的类。在这里，它加载并返回一个代表 com.mysql.cj.jdbc.Driver 类的 Class 对象。
 * 静态块执行：
 * <p>
 * 当调用 Class.forName() 方法加载类时，如果目标类中有静态块（static块），这些静态块会被执行。在 JDBC 驱动程序中，通常在静态块中进行一些初始化工作，如注册驱动程序到 DriverManager 中。
 * 注册驱动：
 * <p>
 * 在 MySQL 的 JDBC 驱动程序中，静态块中一般会包含驱动程序的注册，即调用 DriverManager.registerDriver() 方法注册数据库驱动程序。
 * 注册驱动的目的是将驱动程序加载到 DriverManager 中，使得后续可以通过 JDBC URL 连接到相应的数据库。
 */
