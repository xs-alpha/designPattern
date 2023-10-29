package com.cf.sqlTest.services.service;

import com.alibaba.fastjson.JSON;
import com.cf.sqlTest.dao.po.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsDataUpdate {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        UpdateRequest req = new UpdateRequest();
        req.index("user").id("10012");
        req.doc(XContentType.JSON,"name","xb");
        UpdateResponse res = esClient.update(req, RequestOptions.DEFAULT);
        System.out.println("创建索引：{}"+res.getResult());


        esClient.close();
    }
}
