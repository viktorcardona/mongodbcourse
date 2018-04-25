package com.vmcm.sparkfreemaker;

import static spark.Spark.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import static com.vmcm.freemaker.service.FreemarkerService.*;

/**
 * Created by viccardo on 16/03/16.
 */
public class HelloWorldSparkFreemakerStyle {

    private static final String TEMPLATE_HELLO = "hello.ftl";
    private static final String TEMPLATE_FRUITS = "fruits-picker.ftl";

    public static void main(String[] args) {
        //http://localhost:4567/hello
        Spark.get("/", new Route() {

            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World on handle test!";
            }
        });

        Map<String, Object> map = new HashMap<>();
        map.put("name", "FreeMarker Template");
        get("/greeting", (req, res) -> processTemplate(TEMPLATE_HELLO, map));

        /*
        http://localhost:4567/greet/Isabella y Martin
        */
        get("/greet/:name", (req, res) -> {
            map.put("name", req.params("name"));
            return processTemplate(TEMPLATE_HELLO, map);
        });

        map.put("fruits", Arrays.asList("Orange", "Apple", "Banana", "Avocado", "Other"));
        get("/fruitsform", (req, res) -> processTemplate(TEMPLATE_FRUITS, map));
        post("/fruit_favorite", (req, res) -> String.format("Your favorite fruit is %s", req.queryParams("fruit")));

    }
}
