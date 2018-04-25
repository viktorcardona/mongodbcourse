package com.vmcm.mongodb.test.crud;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.vmcm.mongodb.test.crud.Helpers.printJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.*;

/**
 * Created by viccardo on 26/03/16.
 */
public class FindWithProjectionTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithProjectionTest");

        collection.drop();

        for(int i = 0; i<10; i++){
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("i", i));
        }

        Bson filter5 = and(eq("x",0),gt("y",10),lt("y", 90));
        Bson projection0 = new Document("x", 1).append("y",1).append("_id",0);//0:exclude, 1:include
        Bson projection1 = exclude("x", "_id");
        Bson projection = fields(include("y","i"), excludeId());

                //find all with into
        List<Document> all = collection.find(filter5).projection(projection).into(new ArrayList<>());
        for(Document cur: all){
            printJson(cur);
        }

        long count = collection.count(filter5);
        System.out.println("count:::"+count);

    }
}
