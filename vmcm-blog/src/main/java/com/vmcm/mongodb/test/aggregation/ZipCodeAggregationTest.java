package com.vmcm.mongodb.test.aggregation;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.gte;
import static java.util.Arrays.asList;
import static org.bson.Document.parse;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by viccardo on 14/04/16.
 */
public class ZipCodeAggregationTest {

    /*

    https://docs.mongodb.org/manual/tutorial/aggregation-zip-code-data-set/

    to import the data:
    cd /Users/viccardo/Documents/courses/mongodb/src/vmcm-blog/src/main/java/com/vmcm/mongodb/test/aggregation
    mongoimport -d course -c zipcodes < zips.json

    2016-04-14T17:59:20.846-0500	connected to: localhost
    2016-04-14T17:59:21.533-0500	imported 29353 documents

    * */

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("zipcodes");

        /*
        db.zipcodes.aggregate( [
           { $group: { _id: "$state", totalPop: { $sum: "$pop" } } },
           { $match: { totalPop: { $gte: 10*1000*1000 } } }
        ] )
        */

        /* total population by state */
        List<Document> pipeLine0 = asList( new Document(("$group"),
                new Document("_id", "$state")
        .append("totalPop", new Document("$sum","$pop"))));

        /* state with population greater than 10 million */
        List<Document> pipeLine1 = asList( new Document(("$group"),
                new Document("_id", "$state")
                        .append("totalPop", new Document("$sum","$pop"))),
        new Document("$match", new Document("totalPop", new Document("$gte", 10000000))));

        /* state with population greater than 10 million: better less verbose! */
        List<Bson> pipeLine2 = asList(group("$state", sum("totalPop","$pop")),match(gte("totalPop",10000000 )));

        /* SAME: state with population greater than 10 million: better less verbose! */
        List<Document> pipeLine = asList(
                parse("{ $group: { _id: \"$state\", totalPop: { $sum: \"$pop\" } } },"),
                parse("{ $match: { totalPop: { $gte: 10000000 } } }"));

        List<Document> results = collection.aggregate(pipeLine).into(new ArrayList<>());

        for(Document cur: results){
            System.out.println(cur.toJson());
        }

    }
}
