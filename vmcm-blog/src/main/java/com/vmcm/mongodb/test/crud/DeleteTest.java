package com.vmcm.mongodb.test.crud;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Updates.inc;
import static com.vmcm.mongodb.test.crud.Helpers.printJson2;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by viccardo on 27/03/16.
 */
public class DeleteTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("test");

        collection.drop();


        for(int i = 0; i<8; i++){
            collection.insertOne(new Document()
                    .append("_id", i));
        }

        //collection.deleteMany(gt("_id",4));
        //collection.deleteOne(gt("_id",4));
        collection.deleteOne(eq("_id",4));

        for(Document cur: collection.find().into(new ArrayList<>())){
            printJson2(cur);
        }

    }

}
