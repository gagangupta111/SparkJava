package com.send;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

public class SendBinaryData {

    public static void main(String[] args) throws Exception{

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:4568/post");

        StringEntity jsonEntity = new StringEntity("{\n" +
                "\t\"username\":\"username\",\n" +
                "\t\"password\":\"password\"\n" +
                "}");
        jsonEntity.setContentType("application/json");

        BufferedReader br = null;
        FileReader fr = null;
        String FILENAME = "username.txt";
        fr = new FileReader(FILENAME);
        br = new BufferedReader(fr);

        String sCurrentLine = "";
        StringBuilder stringBuilder = new StringBuilder();

        while ((sCurrentLine = br.readLine()) != null) {
            stringBuilder.append(sCurrentLine);
        }
        StringEntity binaryEntity = new StringEntity(stringBuilder.toString());
        binaryEntity.setContentType("application/octet-stream");

        httppost.setEntity(binaryEntity);
        HttpResponse response = httpclient.execute(httppost);
        BufferedReader br1 = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br1.readLine()) != null) {
            System.out.println(output);
        }

}

}
