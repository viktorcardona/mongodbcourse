package com.vmcm.mongodb.test.crud;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * Created by viccardo on 26/03/16.
 */
public class InsertTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTest");

        coll.drop();

        Document smith = new Document("name", "Smith")
                                    .append("age", 30)
                                    .append("profession", "programmer");

        coll.insertOne(smith);

        coll.drop();

        Document jones = new Document("name", "Jones")
                .append("age", 25)
                .append("profession", "hacker");

        Helpers.printJson(smith);
        Helpers.printJson(jones);

        coll.insertMany(Arrays.asList(smith, jones));

        Helpers.printJson(smith);
        Helpers.printJson(jones);

        BasicDBObject k;
    }
}
