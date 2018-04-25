package com.vmcm.mongodbsparkfreemaker;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.vmcm.freemaker.service.FreemarkerService.processTemplate;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.Spark;

/**
 * Created by viccardo on 27/03/16.
 */
public class HelloWorldMongoDBSparkFreemakerStyle {

    private static final String TEMPLATE_HELLO = "hello.ftl";

    public static void main(String[] args) {

        //MongoDB
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        final MongoCollection<Document> collection = db.getCollection("hello");
        collection.drop();
        collection.insertOne(new Document("name", "MongoDB"));
        Document document = collection.find().first();
        //Spark
        //Freemaker
        get("/", (req, res) -> processTemplate(TEMPLATE_HELLO, document));

    }

}
