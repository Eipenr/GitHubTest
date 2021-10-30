package com.eipen.es.index;

import com.eipen.es.pojo.User;
import com.eipen.es.utils.ESClient;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DocService {

    private String index="ergou";
private ObjectMapper objectMapper=new ObjectMapper();

    public void add(String id) throws IOException {

        RestHighLevelClient levelClient = ESClient.getESClient();

        User user=new User();

        user.setName("zhangsan");

        String json = objectMapper.writeValueAsString(user);

        IndexRequest source = new IndexRequest(index).source(json, XContentType.JSON);

        if(id !=null){
            source.id(id);
        }

        IndexResponse response = levelClient.index(source, RequestOptions.DEFAULT);

        System.err.println(response);

    }


    public void get(String id) throws IOException {
        RestHighLevelClient highLevelClient = ESClient.getESClient();

        GetRequest getRequest = new GetRequest(index, id);

        GetResponse response = highLevelClient.get(getRequest, RequestOptions.DEFAULT);

        System.err.println(response);

    }


    public void delete(String id) throws IOException {
        RestHighLevelClient esClient = ESClient.getESClient();

        DeleteRequest deleteRequest = new DeleteRequest(index, id);

        DeleteResponse response = esClient.delete(deleteRequest, RequestOptions.DEFAULT);

        System.err.println(response);
    }


    public void update(String id) throws IOException {

        RestHighLevelClient esClient = ESClient.getESClient();

        Map map=new HashMap();
        map.put("name","zhangsansan");

        String josn = objectMapper.writeValueAsString(map);

        UpdateRequest updateRequest = new UpdateRequest(index, id).doc(josn,XContentType.JSON);

        UpdateResponse response = esClient.update(updateRequest, RequestOptions.DEFAULT);

        System.err.println(response);


    }
}
