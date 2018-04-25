package com.vmcm.mongodb.test.crud;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;
import static com.vmcm.mongodb.test.crud.Helpers.printJson2;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Updates.*;

/**
 * Created by viccardo on 27/03/16.
 */
public class UpdateTest {

    public static void main(String[] args) {

        test02();
    }

    private static void test01(){
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("test");

        collection.drop();


        for(int i = 0; i<8; i++){
            collection.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i)
                    .append("y", true));
        }

        //collection.replaceOne(eq("x",5), new Document("x", 20).append("updated", true));
        //collection.updateOne(eq("x",5), new Document("$set", new Document("x", 20).append("updated", true)));
        //collection.updateOne(eq("x",5), Updates.set("x", 20));
        //collection.updateOne(eq("x",5), combine(set("x", 20), set("updated",true)));
        //collection.updateOne(eq("_id",9), combine(set("x", 20), set("updated",true)));//does not match any doc
        //collection.updateOne(eq("_id",9), combine(set("x", 20), set("updated",true)), new UpdateOptions().upsert(true));//does not match, but with upsert true the doc is created if does not exist
        collection.updateMany(gte("x",5), inc("x", 1));

        //find all with into
        List<Document> all = collection.find().into(new ArrayList<>());

        for(Document cur: all){
            printJson2(cur);
        }
    }

    private static void test02(){
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("blog");
        MongoCollection<Document> collection = db.getCollection("posts");

        final String permalink = "mxwnnnqaflufnqwlekfd";
        final int ordinal = 1;

        collection.updateOne(eq("permalink",permalink), inc("comments."+ordinal+".num_likes", 1));

        System.out.println("Document updated");
    }

}
