package com.eipen.springcloud.zuul.zuulfallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class ZuulFallback implements FallbackProvider {


    /**
     * 当前的 fallback 给那个服务用
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     *
     * @param route 具体出现问题的服务
     * @param cause 出现问题的原因
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 404;
            }

            @Override
            public String getStatusText() throws IOException {
                return "你出错了";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("你们把网络都干废了".getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Content-Type","text/html;charset=utf-8");
                return httpHeaders;
            }
        };
    }
}
