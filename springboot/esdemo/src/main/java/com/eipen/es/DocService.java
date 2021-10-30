package com.eipen.es;

import com.eipen.pojo.User;
import com.eipen.utils.ESClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.engine.Engine;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DocService {

    private String index="zhangsan";
    private ObjectMapper objectMapper=new ObjectMapper();

    //对 ES 数据库 的增删改查

    //增加
    public void add(String id) throws IOException {
        //拿到客户端
        RestHighLevelClient highLevelClient = ESClient.getClient();


        //创建了一个对象来保存我们 想要存的值
        User user=new User();
        user.setName("小张");
        user.setAge(18);
        user.setSex("男");
        user.setShengri(new Date());

        //将对象转化成 json 格式
        String json = objectMapper.writeValueAsString(user);

        //要添加的数据放在 indexRequest 的请求中
        IndexRequest indexRequest=new IndexRequest(index).source(json, XContentType.JSON);
        if (id != null){
            indexRequest.id(id);
        }
        IndexResponse response = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        System.err.println(response);


    }

    //查询
    public void get(String id) throws IOException {
        //拿到客户端
        RestHighLevelClient client = ESClient.getClient();

        //创建查询 get 请求
        GetRequest getRequest = new GetRequest(index, id);
        //根据客户端查询
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);

        System.err.println(response);

    }

    public void delete(String id) throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        DeleteRequest deleteRequest = new DeleteRequest(index,id);
        DeleteResponse response = client.delete(deleteRequest,RequestOptions.DEFAULT);
        System.err.println(response);
    }

    public void update(String id) throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        Map map=new HashMap();
        map.put("name","sdfsadfasdf");
        String json = objectMapper.writeValueAsString(map);
        UpdateRequest updateRequest = new UpdateRequest(index, id).doc(json,XContentType.JSON);
        UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
        System.err.println(response);
    }



}
