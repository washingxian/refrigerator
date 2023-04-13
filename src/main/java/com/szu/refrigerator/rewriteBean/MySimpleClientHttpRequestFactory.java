package com.szu.refrigerator.rewriteBean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;

@Configuration
//自定义一个SimpleClientHttpRequestFactory的子类
public  class MySimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory {
    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException, IOException {
        super.prepareConnection(connection, httpMethod);
        //设置connection.setInstanceFollowRedirects(true)来强制跟随重定向
        connection.setInstanceFollowRedirects(false);
    }
}