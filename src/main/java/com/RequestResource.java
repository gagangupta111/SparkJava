package com;

import com.dto.RequestDTO;
import com.google.gson.JsonObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import spark.Request;
import spark.Response;
import spark.Spark.*;
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

    public static String postObject(Request request) throws InvalidProtocolBufferException {

        RequestDTO.Builder requestDTOBuilder = RequestDTO.newBuilder();
        JsonFormatClass.parser.merge(request.body(), requestDTOBuilder);
        RequestDTO dto = requestDTOBuilder.build();

        // RequestDTO to InnerDTORequest


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key1", "value1");
        jsonObject.addProperty("key2", "value2");
        jsonObject.addProperty("user", dto.getUsername());
        jsonObject.addProperty("password", dto.getPassword());

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