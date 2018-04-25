package com.vmcm.mongodb.test.crud;

import static com.vmcm.mongodb.test.crud.Helpers.printJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

/**
 * Created by viccardo on 26/03/16.
 */
public class FindWithFilterTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        for(int i = 0; i<10; i++){
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100)));
        }

        Bson filter = new Document("x", 0);
        Bson filter2 = new Document("x", 0)
                .append("y", new Document("$gt", 10).append("$lt", 90));
        Bson filter3 = eq("x",0);
        Bson filter4 = and(eq("x",0),gt("y",10));
        Bson filter5 = and(eq("x",0),gt("y",10),lt("y", 70));

        //find all with into
        List<Document> all = collection.find(filter5).into(new ArrayList<>());
        for(Document cur: all){
            printJson(cur);
        }

        long count = collection.count(filter5);
        System.out.println("count:::"+count);

    }
}
