package com;

import com.dto.InnerDTORequest;
import com.dto.RequestDTO;
import com.google.gson.JsonObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.mapper.SimpleMapper;
import spark.Request;

import java.util.Map;

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

        JsonObject jsonObject = new JsonObject();

        try{

            String json = request.queryParams("json");
            Boolean aBoolean = new Boolean(json);

            if (aBoolean) {

                RequestDTO.Builder requestDTOBuilder = RequestDTO.newBuilder();
                JsonFormatClass.parser.merge(request.body(), requestDTOBuilder);
                RequestDTO dto = requestDTOBuilder.build();

                InnerDTORequest innerDTORequest = SimpleMapper.INSTANCE.sourceToDestination(dto);
                RequestDTO requestDTO = SimpleMapper.INSTANCE.destinationToSource(innerDTORequest).build();
                System.out.println(requestDTO);
                // RequestDTO to InnerDTORequest


                jsonObject.addProperty("key1", "value1");
                jsonObject.addProperty("key2", "value2");
                jsonObject.addProperty("user", dto.getUsername());
                jsonObject.addProperty("password", dto.getPassword());

            }else {

                RequestDTO requestDTO;
                requestDTO = RequestDTO.parseFrom(request.bodyAsBytes());
                jsonObject.addProperty("key1", "value1");
                jsonObject.addProperty("key2", "value2");
                jsonObject.addProperty("user", requestDTO.getUsername());
                jsonObject.addProperty("password", requestDTO.getPassword());

            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject.toString();

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