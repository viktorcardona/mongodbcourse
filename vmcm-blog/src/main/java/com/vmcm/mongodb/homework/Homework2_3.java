package com.vmcm.mongodb.homework;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.vmcm.mongodb.test.crud.Helpers.printJson2;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by viccardo on 27/03/16.
 */
public class Homework2_3 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> collection = db.getCollection("grades");

        System.out.println("count:before::"+collection.count());

        Bson filter = new Document("type", "homework");
        Bson sort = ascending("student_id","score");

        int studentId = -1;
        for(Document doc: collection.find().sort(sort).filter(filter).into(new ArrayList<>())){
            int newStudentId = doc.getInteger("student_id");
            printJson2(doc);
            if(newStudentId!=studentId){
                studentId=newStudentId;
                System.out.println("objectId.2.remove:"+doc.getObjectId("_id").toString());
                collection.deleteOne(doc);
            }

        }

        System.out.println("count:after::"+collection.count());

    }

}
