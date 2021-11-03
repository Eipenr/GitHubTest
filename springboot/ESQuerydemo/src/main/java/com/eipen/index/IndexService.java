package com.eipen.index;


import com.eipen.utils.ESClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.json.JsonXContent;

import java.io.IOException;

public class IndexService {

    public boolean isExist() throws IOException {
        RestHighLevelClient client = ESClient.getClient();
        GetIndexRequest request = new GetIndexRequest("erpang3");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }


    public void creatIndex() throws IOException {

        RestHighLevelClient client = ESClient.getClient();

        CreateIndexRequest indexRequest=new CreateIndexRequest("erpang3");
        indexRequest.settings(Settings.builder()
                .put("number_of_shards",5)//分5片
                .put("number_of_replicas",1)//每片一个备份
                .build())
                .mapping(JsonXContent.contentBuilder()
                    .startObject()
                        .startObject("properties")
                        .startObject("name").field("type","text").endObject()
                        .startObject("createDate").field("type","date").field("format","yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis").endObject()
                        .endObject().endObject());

        CreateIndexResponse response = client.indices().create(indexRequest, RequestOptions.DEFAULT);

        System.err.println(response);

    }



}
