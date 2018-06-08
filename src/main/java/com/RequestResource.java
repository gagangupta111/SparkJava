package com;

import com.google.gson.JsonObject;
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

    public static String postObject(Request request){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key1", "value1");
        jsonObject.addProperty("key2", "value2");

        return jsonObject.toString();

    }

}
