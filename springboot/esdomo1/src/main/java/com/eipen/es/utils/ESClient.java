package com.eipen.es.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {

    public static RestHighLevelClient getESClient(){
        HttpHost httpHost=new HttpHost("10.9.12.200",9078);

        RestClientBuilder clientBuilder = RestClient.builder(httpHost);

        RestHighLevelClient restHighLevelClient=new RestHighLevelClient(clientBuilder);

        return restHighLevelClient;

    }

}
