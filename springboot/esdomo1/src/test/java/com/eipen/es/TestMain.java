package com.eipen.es;

import com.eipen.es.index.DocService;
import com.eipen.es.index.IndexService;
import com.eipen.es.utils.ESClient;
import org.elasticsearch.action.index.IndexRequest;
import org.junit.Test;

import java.io.IOException;

public class TestMain {

    @Test
    public void TestCreateIndex() throws IOException {
        IndexService indexService=new IndexService();
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
