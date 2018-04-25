package com.vmcm.mongodb.homework;

import static com.vmcm.mongodb.test.crud.Helpers.printJson2;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by viccardo on 2/04/16.
 */
public class Homework3_1 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> collection = db.getCollection("students");

        System.out.println("count:before::"+collection.count());

        List<Document> docs = collection.find().into(new ArrayList<>());

        for(Document doc: docs){
            printJson2(doc);
            List<Document> scores = (List<Document>) doc.get("scores");
            Document homeWorkTypeWithLowestScore = scores.stream().filter(score -> score.getString("type").equalsIgnoreCase("homework")).reduce((i, j) -> i.getDouble("score")<j.getDouble("score")?i:j).get();
            scores.remove(homeWorkTypeWithLowestScore);
            BasicDBObject updateQuery = new BasicDBObject("_id", doc.getInteger("_id"));
            BasicDBObject updateCommand = new BasicDBObject("$set", new Document("scores", scores));
            collection.updateOne(updateQuery,updateCommand);

        }

        System.out.println("count:after::"+collection.count());

    }

}
/*
INITIAL DOC:
{
	"_id" : 17,
	"name" : "Fletcher Mcconnell",
	"scores" : [
		{
			"type" : "exam",
			"score" : 39.41011069729274
		},
		{
			"type" : "quiz",
			"score" : 81.13270307809924
		},
		{
			"type" : "homework",
			"score" : 31.15090466987088
		},
		{
			"type" : "homework",
			"score" : 97.70116640402922
		}
	]
}

FINAL DOC:
{
	"_id" : 17,
	"name" : "Fletcher Mcconnell",
	"scores" : [
		{
			"type" : "exam",
			"score" : 39.41011069729274
		},
		{
			"type" : "quiz",
			"score" : 81.13270307809924
		},
		{
			"type" : "homework",
			"score" : 97.70116640402922
		}
	]
}

*/