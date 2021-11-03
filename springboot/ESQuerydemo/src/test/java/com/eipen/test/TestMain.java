package com.eipen.test;

import com.eipen.index.DocService;
import com.eipen.index.IndexService;
import com.eipen.utils.ESClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import javax.print.Doc;
import java.io.IOException;

public class TestMain {


    @Test
    public void testCreatIndex() throws IOException {

        IndexService indexService=new IndexService();

        if(indexService.isExist()){
            System.err.println("这个库已经存在了");
            return;
        }

        indexService.creatIndex();

    }

    @Test
    public void testAdd() throws IOException {
        DocService docService=new DocService();
        docService.add("2");
    }

    @Test
    public void testTermQuery() throws IOException {
        DocService docService=new DocService();
        docService.termQuery("name","eipen1");
    }

    @Test
    public void testTerms() throws IOException {
        DocService docService=new DocService();
        docService.termsQuery("name","eipen","eipen1");
    }

    @Test
    public void testMatch() throws IOException {
        DocService docService=new DocService();
        docService.matchQuery("name","eipen");
    }

    @Test
    public void testMatchAll() throws IOException {
        DocService docService=new DocService();
        docService.matchAllQuery();
    }

    @Test
    public void testBooleanMatch() throws IOException {
        DocService docService=new DocService();
        docService.booleanMatchQuery("name","eipen 啥地方");
    }

    @Test
    public void testMultiMatch() throws IOException {
        DocService docService=new DocService();
        docService.multiMatch("name","name");
    }

    @Test
    public void testIdQuery() throws IOException {
        DocService docService=new DocService();
        docService.idQuery("1");
    }

    @Test
    public void testIdsQuery() throws IOException {
        DocService docService=new DocService();
        docService.idsQuery("1","2");
    }

    @Test
    public void testPrefix() throws IOException {
        DocService docService=new DocService();
        docService.prefix("name","eipen");
    }

    @Test
    public void testFuzzy() throws IOException {
        DocService docService=new DocService();
        docService.fuzzy("name","中");
    }

    @Test
    public void wildCard() throws IOException {
        DocService docService=new DocService();
        docService.wildcard("name","中*");
    }


}
