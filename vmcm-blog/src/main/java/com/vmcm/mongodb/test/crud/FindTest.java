package com.vmcm.mongodb.test.crud;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viccardo on 26/03/16.
 */
public class FindTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findTest");

        collection.drop();

        for(int i = 0; i<10; i++){
            collection.insertOne(new Document("x", i));
        }

        Document first = collection.find().first();
        Helpers.printJson(first);

        //find all with into
        List<Document> all = collection.find().into(new ArrayList<>());
        for(Document cur: all){
            Helpers.printJson(cur);
        }

        MongoCursor<Document> cursor = collection.find().iterator();
        try{
            while(cursor.hasNext()){
                Document doc = cursor.next();
                Helpers.printJson(doc);
            }
        } finally {
            cursor.close();
        }

        long count = collection.count();
        System.out.println("count:::"+count);

    }
}
