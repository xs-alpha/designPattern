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
            Class.forName("com.mysql.cj.jdbc.Driver");
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
