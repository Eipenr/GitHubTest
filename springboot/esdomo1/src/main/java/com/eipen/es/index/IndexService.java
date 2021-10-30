package com.eipen.es.index;

import com.eipen.es.utils.ESClient;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.json.JsonXContent;

import java.io.IOException;

public class IndexService {

    public void createIndex() throws IOException {

        RestHighLevelClient client = ESClient.getESClient();

        CreateIndexRequest createIndexRequest=new CreateIndexRequest("ergou");

        createIndexRequest.settings(Settings.builder()
                .put("number_of_shards",5)
                .put("number_of_replicas",1)
                .build())
                .mapping(
                        JsonXContent.contentBuilder()
                        .startObject()
                        .startObject("properties")
                        .startObject("name").field("type","text").endObject()
                        .endObject()
                        .endObject());
        CreateIndexResponse response = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.err.println(response);


    }

}
