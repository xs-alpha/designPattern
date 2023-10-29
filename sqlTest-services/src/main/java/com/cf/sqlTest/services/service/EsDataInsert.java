package com.cf.sqlTest.services.service;

import com.alibaba.fastjson.JSON;
import com.cf.sqlTest.dao.po.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsDataInsert {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        IndexRequest req = new IndexRequest();
        req.index("user").id("10012");
        User user = new User(1, "xiaosheng", "china",12,"男","打牌");

        // es插入数据必须是json
        String s = JSON.toJSONString(user);
        req.source(s, XContentType.JSON);

        IndexResponse index = esClient.index(req, RequestOptions.DEFAULT);
        System.out.println("--------------------------");
        System.out.println("创建索引：{}"+index.getResult());
        System.out.println("--------------------------");


        esClient.close();
    }
}
