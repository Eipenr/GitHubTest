package com.eipen.utils;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * 得到 ES 客户端的工具类
 */
public class ESClient {

    public static RestHighLevelClient getClient(){

        //获取到 客户机的 IP 和端口
        HttpHost httpHost=new HttpHost("10.9.12.200",9078);

        //创建 RestClientBuilder
        RestClientBuilder clientBuilder = RestClient.builder(httpHost);

        //创建 RestHighLeveClient
        RestHighLevelClient restHighLevelClient=new RestHighLevelClient(clientBuilder);

        return restHighLevelClient;
    }

}
