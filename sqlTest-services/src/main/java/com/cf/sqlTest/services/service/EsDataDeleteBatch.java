package com.cf.sqlTest.services.service;

import com.alibaba.fastjson.JSON;
import com.cf.sqlTest.dao.po.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsDataDeleteBatch {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        BulkRequest req = new BulkRequest();
        User user = new User(1, "xiaosheng", "china",12,"男","打牌");
        req.add(new DeleteRequest().index("user").id("100121"));
        req.add(new DeleteRequest().index("user").id("10013"));
        req.add(new DeleteRequest().index("user").id("10014"));
        BulkResponse bulk = esClient.bulk(req, RequestOptions.DEFAULT);
        System.out.println("创建索引：{}"+bulk.getTook());


        esClient.close();
    }
}
