package com.eipen.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {


    public static RestHighLevelClient getClient(){
        HttpHost httpHost=new HttpHost("10.9.12.200",9078);

        RestClientBuilder builder = RestClient.builder(httpHost);

        RestHighLevelClient client = new RestHighLevelClient(builder);

        return client;

    }

}
