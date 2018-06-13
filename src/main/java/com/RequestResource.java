package com;

import com.dto.InnerDTORequest;
import com.dto.RequestDTO;
import com.google.gson.JsonObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.TextFormat;
import com.mapper.SimpleMapper;
import org.apache.http.entity.StringEntity;
import spark.Request;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class RequestResource {

    public static void main(String[] args){

        port(4568);
        get("/get", (request, response) -> jsonObject(request));
        post("/post", (request, response) -> postObject(request));

    }

    public static String jsonObject(Request request){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key1", "value1");
        jsonObject.addProperty("key2", "value2");

        return jsonObject.toString();

    }

    public static String postObject(Request request) {

        RequestDTO dto = null;
        try{

            RequestDTO.Builder requestDTOBuilder = RequestDTO.newBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(getDataFromFile().getBytes(StandardCharsets.US_ASCII));

            RequestDTO received = RequestDTO.parseFrom(input);
            System.out.println(received);

            dto = requestDTOBuilder.mergeFrom(input).build();

            InnerDTORequest innerDTORequest = SimpleMapper.INSTANCE.sourceToDestination(dto);
            dto = SimpleMapper.INSTANCE.destinationToSource(innerDTORequest).build();

        }catch (Exception e){
            e.printStackTrace();
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key1", "value1");
        jsonObject.addProperty("key2", "value2");
        jsonObject.addProperty("user", dto.getUsername());
        jsonObject.addProperty("password", dto.getPassword());

        return jsonObject.toString();

    }

    public static String getDataFromFile() throws Exception {

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

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();

    }

}

class JsonFormatClass {

    public static final com.google.protobuf.util.JsonFormat.Printer printer =
            com.google.protobuf.util.JsonFormat.printer()
                    .includingDefaultValueFields();
    public static final com.google.protobuf.util.JsonFormat.Parser parser =
            com.google.protobuf.util.JsonFormat.parser()
                    .ignoringUnknownFields();

}