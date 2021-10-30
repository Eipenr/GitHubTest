package com.eipen.es;

import com.eipen.utils.ESClient;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.json.JsonXContent;

import java.io.IOException;

public class IndexService {


    public Boolean isExist() throws IOException {
        RestHighLevelClient highLevelClient = ESClient.getClient();
        GetRequest getRequest=new GetRequest("zhangsan");
        boolean exists = highLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        return exists;
    }


    /**
     * 创建库
     */
    public void createIndex() throws IOException {

        // 拿到 es 客户端
        RestHighLevelClient client = ESClient.getClient();

        //创建库
        CreateIndexRequest createIndexRequest=new CreateIndexRequest("zhangsan");

        //设置库的参数
        createIndexRequest.settings(Settings.builder()
                .put("number_of_shards",5)
                .put("number_of_replicas",1)
                .build())
                .mapping(
                        JsonXContent.contentBuilder()
                        .startObject()
                        .startObject("properties")
                        .startObject("name").field("type","keyword").endObject()
                        .startObject("age").field("type","integer").endObject()
                        .startObject("sex").field("type","text").endObject()
                        .startObject("shengri").field("type","date").field("format","yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis").endObject()
                        .endObject()
                        .endObject());
        CreateIndexResponse response = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.err.println(response);


    }

}
