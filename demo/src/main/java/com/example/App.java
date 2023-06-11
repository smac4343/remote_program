package com.example;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class App {

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
                .setConnectTimeout(5000)    // maximum time of waiting for a connection to the server
                .setSocketTimeout(30000)    // maximum time of waiting to get data
                .setRedirectsEnabled(false) // ability to follow redirect in the response
                .build())
            .build()) {

            HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts/1");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(result);
                System.out.println(json.toString(2));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
