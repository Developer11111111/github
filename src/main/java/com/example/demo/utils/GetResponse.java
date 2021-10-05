package com.example.demo.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class GetResponse {
    public MyHttpResponse sendPostRequest(String postData) throws Exception {

        String endpoint = "http://172.25.0.43:8080/rest";

        CloseableHttpClient httpclient = HttpClients.custom().build();
        HttpPost httpPost = new HttpPost(endpoint);
        StringEntity myEntity = new StringEntity(postData);
        httpPost.setEntity(myEntity);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(35000)
                .setConnectTimeout(35000)
                .setConnectionRequestTimeout(35000)
                .setAuthenticationEnabled(true)
                .build();
        httpPost.setConfig(requestConfig);

        httpPost.setHeader("Content-Type", "application/pkcs7-mime");
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        MyHttpResponse result = new MyHttpResponse();
        int statuscode = response2.getStatusLine().getStatusCode();
        try {
            HttpEntity entity2 = (HttpEntity) response2.getEntity();
            result.setHttpStatus(statuscode);
            result.setResponse(EntityUtils.toString((org.apache.http.HttpEntity) entity2));
        } finally {
            response2.close();
        }
        return result;
    }

    public String getResponseFromService(String request) throws Exception{
//            URL url = new URL("http://195.158.16.25:8083/api/getPersonData");
        URL url = new URL("http://172.25.0.43:8080/rest");
        String response = "";
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.12.16.202", 8081));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        conn.setRequestProperty("Content-Type","application/json");
        /* Send post request */
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(request);
        wr.flush();
        wr.close();
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        System.out.println("Output from Server .... \n");
        String output;
        while ((output = br.readLine()) != null) {
            response+=output;
        }
        conn.disconnect();
        return response;
    }
}
