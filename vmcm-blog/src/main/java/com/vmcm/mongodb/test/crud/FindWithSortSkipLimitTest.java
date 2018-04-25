package com.vmcm.mongodb.test.crud;


import static com.mongodb.client.model.Filters.and;
import static com.vmcm.mongodb.test.crud.Helpers.printJson;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Sorts.*;

import static com.mongodb.client.model.Projections.*;

/**
 * Created by viccardo on 27/03/16.
 */
public class FindWithSortSkipLimitTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithSortTest");

        collection.drop();


        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                collection.insertOne(new Document()
                        .append("i", i)
                        .append("j", j));
            }
        }

        Bson projection = fields(include("i","j"), excludeId());
        Bson sort0 = new Document("i", 1).append("j", -1);//1 ascending, -1 descending
        Bson sort1 = ascending("i");
        Bson sort2 = orderBy(ascending("i"), descending("j"));
        Bson sort = descending("j", "i");

                //find all with into
        List<Document> all = collection.find()
                                        .sort(sort)
                                        .skip(20)
                                        .limit(50)
                                        .projection(projection)
                                        .into(new ArrayList<>());
        for(Document cur: all){
            printJson(cur);
        }

    }

}
