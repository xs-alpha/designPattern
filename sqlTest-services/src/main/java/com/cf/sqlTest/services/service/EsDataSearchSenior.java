package com.cf.sqlTest.services.service;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import javax.naming.directory.SearchResult;
import java.io.IOException;

public class EsDataSearchSenior {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 1.查询索引中全部数据
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        req.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });

        // 2.条件查询termQuery
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        req.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age",13)));
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });

        // 3.分页查询
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        query.from(0);
//        query.size(3);
//        req.source(query);
//
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });

        // 4.查询排序
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        query.sort("age", SortOrder.DESC);
//        query.from(0);
//        query.size(3);
//        req.source(query);
//
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });

        // 5.过滤字段
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        String[] exc = {"address"};
//        String[] inc = {};
//        query.fetchSource(inc,exc);
//        req.source(query);
//
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });

        // 6.组合查询
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        boolQuery.must(QueryBuilders.matchQuery("age",13));
//        boolQuery.must(QueryBuilders.matchQuery("sex","男"));
//
//        builder.query(boolQuery);
//        req.source(builder);
//
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });

        // 7.范围查询
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        RangeQueryBuilder range = QueryBuilders.rangeQuery("age");
//        range.gt(11);
//        range.lt(30);
//        builder.query(range);
//        req.source(builder);
//
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });

        // 8.模糊查询
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        FuzzyQueryBuilder fuz = QueryBuilders.fuzzyQuery("name", "1").fuzziness(Fuzziness.ONE);
//        builder.query(fuz);
//        req.source(builder);
//
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });


        // 9.高亮查询
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        TermQueryBuilder termQuery = QueryBuilders.termQuery("name", "x1");
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<font color='red'>");
//        highlightBuilder.postTags("</font>");
//        highlightBuilder.field("name");
//        builder.highlighter(highlightBuilder);
//        builder.query(termQuery);
//        req.source(builder);
//
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });

        // 10.聚合查询
//        SearchRequest req = new SearchRequest();
//        req.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        MaxAggregationBuilder field = AggregationBuilders.max("maxAge").field("age");
//        builder.aggregation(field);
//
//        req.source(builder);
//
//        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
//        SearchHits hits = resp.getHits();
//        System.out.println("结果："+hits.getTotalHits());
//        System.out.println("耗时："+resp.getTook());
//        hits.forEach(o->{
//            System.out.println(o.getSourceAsString());
//        });


        // 11.分组查询
        SearchRequest req = new SearchRequest();
        req.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermsAggregationBuilder field = AggregationBuilders.terms("ageGroup").field("age");
        builder.aggregation(field);

        req.source(builder);

        SearchResponse resp = esClient.search(req, RequestOptions.DEFAULT);
        SearchHits hits = resp.getHits();
        System.out.println("结果："+hits.getTotalHits());
        System.out.println("耗时："+resp.getTook());
        hits.forEach(o->{
            System.out.println(o.getSourceAsString());
        });



        esClient.close();
    }
}
