package com.cf.sqlTest.api.sqlSource;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * @author: lpy
 * @Date: 2023/09/11
 */
@Component
@Slf4j
public class SelfMultiDataSourceProvider implements DynamicDataSourceProvider {
    @Autowired
    private DefaultDataSourceCreator defaultDataSourceCreator;
    @Override
    public Map<String, DataSource> loadDataSources() {


        Map<String, javax.sql.DataSource> dataSourceMap = new HashMap(32);
        // 这里查询db库
        List<DBEntity> dbEntities = this.loadDataSource();
        if (null == dbEntities){
            throw new RuntimeException("init datasource err");
        }
        log.info("加载到数据源配置信息：{}", JSON.toJSONString(dbEntities));
        Iterator<DBEntity> iterator = dbEntities.iterator();


        while(iterator.hasNext()) {
            DBEntity db = iterator.next();
//            Map.Entry<String, DataSourceProperty> item = (Map.Entry)iterator.next();
            String dsName = "druid";
            DataSourceProperty dataSourceProperty = new DataSourceProperty();
            dataSourceProperty.setUrl(db.getUrl());
            dataSourceProperty.setUsername(db.getUserName());
            dataSourceProperty.setPassword(db.getPassword());
            dataSourceProperty.setPoolName("druidDataSource");
            dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSourceMap.put(dsName, defaultDataSourceCreator.createDataSource(dataSourceProperty));
        }
        return dataSourceMap;
    }

    @SneakyThrows
    private List<DBEntity> loadDataSource()  {
        Statement statement = null;
        Connection connection=null;
        //1.加载一个Driver驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");//如果是8.0的版本请求com.mysql.cj.jdbc.Driver
            //2.使用DriverManager创建数据库连接（Connection）
            String conUrl = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai";
            connection = DriverManager.getConnection(conUrl, "root", "root");
            //3.使用connection创建SQL命令发送器Statement
            statement = connection.createStatement();
            //4.创建SQL
            String sql="select * from db ";
            //5.通过Statement发送SQL命令并得到结果
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            //6.处理SQL结果（select语句）
            ArrayList<DBEntity> list = new ArrayList<>(20);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String password= resultSet.getString("password");
                String url= resultSet.getString("url");
                list.add(
                        new DBEntity()
                                .setUserName(userName)
                                .setPassword(password)
                                .setUrl(url)
                );
            }
            return list;
        } catch (Exception e) {
        }finally {
            //7.关闭数据库资源
            if (null==statement) {
                statement.close();
            }
            if (null==connection) {
                connection.close();
            }
        }

        return null;
    }

}
