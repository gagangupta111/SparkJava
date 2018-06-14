package com.send;

import com.dto.RequestDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import spark.utils.IOUtils;

import java.io.*;

public class SendBinaryData {

    public static void main(String[] args) throws Exception{

        boolean json = false;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = "http://localhost:4568/post?json=";
        // json entity
        StringEntity jsonEntity = new StringEntity("{\n" +
                "\t\"username\":\"username99\",\n" +
                "\t\"password\":\"password11\"\n" +
                "}");
        jsonEntity.setContentType("application/json");

        File file;
        file = new File("username.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        HttpEntity httpEntity = new ByteArrayEntity(IOUtils.toByteArray(fileInputStream));

        // HTTP POST IS BEING GENERATED.
        HttpPost httpPost;
        if (json) {

            httpPost = new HttpPost(url + "true");
            httpPost.setEntity(jsonEntity);

        }else {

            httpPost = new HttpPost(url + "false");
            httpPost.setEntity(httpEntity);

        }

        HttpResponse response = httpclient.execute(httpPost);
        BufferedReader br1 = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br1.readLine()) != null) {
            System.out.println(output);
        }

    }

}
