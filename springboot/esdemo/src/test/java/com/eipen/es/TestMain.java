package com.eipen.es;

import com.eipen.utils.ESClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import javax.print.Doc;
import java.io.IOException;

public class TestMain {

    @Test
    public void TestCreateIndex() throws IOException {
        IndexService indexService=new IndexService();

        if (indexService.isExist()){
            System.err.println("这个数据库已经存在了");
            return;
        }

        indexService.createIndex();
    }

    @Test
    public void TestAdd() throws IOException {
        DocService docService=new DocService();
        docService.add("1");
    }


    @Test
    public void TestGet() throws IOException {
        DocService docService=new DocService();
        docService.get("1");
    }

    @Test
    public void TestDelete() throws IOException {
        DocService docService=new DocService();
        docService.delete("1");
    }

    @Test
    public void TestUpdate() throws IOException {
        DocService docService=new DocService();
        docService.update("1");
    }




}
