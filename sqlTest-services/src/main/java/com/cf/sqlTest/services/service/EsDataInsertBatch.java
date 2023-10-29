package com.cf.sqlTest.services.service;

import com.alibaba.fastjson.JSON;
import com.cf.sqlTest.dao.po.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsDataInsertBatch {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        BulkRequest req = new BulkRequest();
        User user = new User(1, "xiaosheng", "china",12,"男","打牌");
        req.add(new IndexRequest().index("user").id("100123").source(JSON.toJSONString(user.setName("x1").setAge(13)),XContentType.JSON));
        req.add(new IndexRequest().index("user").id("100132").source( JSON.toJSONString(user.setName("x2").setAge(178)),XContentType.JSON));
        req.add(new IndexRequest().index("user").id("100145").source( JSON.toJSONString(user.setName("x3").setAge(10)),XContentType.JSON ));
        req.add(new IndexRequest().index("user").id("100146").source( JSON.toJSONString(user.setName("x4").setAge(17)),XContentType.JSON ));
        req.add(new IndexRequest().index("user").id("100147").source( JSON.toJSONString(user.setName("x5").setAge(16)),XContentType.JSON ));
        req.add(new IndexRequest().index("user").id("100148").source( JSON.toJSONString(user),XContentType.JSON ));
        BulkResponse bulk = esClient.bulk(req, RequestOptions.DEFAULT);
        System.out.println("创建索引：{}"+bulk.getTook());


        esClient.close();
    }
}
