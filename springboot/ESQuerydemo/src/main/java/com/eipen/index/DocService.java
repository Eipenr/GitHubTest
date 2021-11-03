package com.eipen.index;

import com.eipen.user.User;
import com.eipen.utils.ESClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.util.Date;

public class DocService {

    private String index="erpang3";
    private ObjectMapper objectMapper=new ObjectMapper();


    public void add(String id) throws IOException {

        RestHighLevelClient client = ESClient.getClient();

        User user=new User();

        user.setName("eipen2");
        user.setCreateDate(new Date());

        String json = objectMapper.writeValueAsString(user);
        IndexRequest indexRequest = new IndexRequest(index).source(json, XContentType.JSON);
        if(id!=null){
            indexRequest.id(id);
        }
        IndexResponse response = client.index(indexRequest,RequestOptions.DEFAULT);

        System.err.println(response);

    }

    //完全匹配查询
    public void termQuery(String field,String value) throws IOException {
        baseQuery(QueryBuilders.termQuery(field, value));
    }

    //完全匹配查询（多个词）
    public void termsQuery(String field,String... value) throws IOException {
        baseQuery(QueryBuilders.termsQuery(field,value));
    }


    //match 查询（分词查询）
    public void matchQuery(String field,String value) throws IOException {
        baseQuery(QueryBuilders.matchQuery(field,value));
    }

    //查询所有内容，不指定查询条件
    public void matchAllQuery() throws IOException {
        baseQuery(QueryBuilders.matchAllQuery());
    }

    //查询匹配的内容， 采用or 或 and 的方式
    public void booleanMatchQuery(String field,String value) throws IOException {
        baseQuery(QueryBuilders.matchQuery(field,value).operator(Operator.AND));
    }

    //查询匹配的内容， 在多个列 进行查询
    public void multiMatch(String value,String... filed) throws IOException {
        baseQuery(QueryBuilders.multiMatchQuery(value,filed));
    }

    //根据id 查
    public void idQuery(String id) throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        GetRequest getRequest = new GetRequest(index, id);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        System.err.println(response);
    }

    //查询多个id 的数据
    public void idsQuery(String... ids) throws IOException {
        baseQuery(QueryBuilders.idsQuery().addIds(ids));
    }

    //TODO 前缀查询(但测试的时候 只要包含就能查出来)
    public void prefix(String filed,String value) throws IOException {
        baseQuery(QueryBuilders.prefixQuery(filed,value));
    }

    //模糊 查询， 只要输入大概有的字和因 比如盒马鲜生 输入成 盒马先生 音大概就行
    //TODO 测试的时候发现 查一个汉字的时候可以查出来， 两个字以及两个字以上就不行了
    public void fuzzy(String filed,String value) throws IOException {
        baseQuery(QueryBuilders.fuzzyQuery(filed,value).prefixLength(2));
    }

    //通配查询  比如 中国河南  搜中国*
    //TODO 测试的时候发现 查一个汉字的时候可以查出来， 两个字以及两个字以上就不行了
    public void wildcard(String filed,String value) throws IOException {
        baseQuery(QueryBuilders.wildcardQuery(filed, value));
    }

    //范围查询 只针对数字，在摸个区间范围内的数据
    public void range(String filed) throws IOException {
        //搜索 5-10 之间的数据
        baseQuery(QueryBuilders.rangeQuery(filed).lte(10).gte(5));
    }




    public SearchResponse baseQuery(QueryBuilder queryBuilder) throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        SearchRequest searchRequest=new SearchRequest(index);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(queryBuilder);
        searchRequest.source(builder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.err.println(response);
        return response;
    }



}
