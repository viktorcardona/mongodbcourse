package com.vmcm.mongodb.test.replicaset;

import static java.util.Arrays.asList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by viccardo on 20/04/16.
 */
public class ReplicaSetTest {

    public static void main(String[] args) throws InterruptedException {

        //
        //27018 the primary, then: from the primary:
        //mongo --port 27018
        //rs.stepDown();
        //rs.status();
        //the last show that 27018 is not more the primary, now the primary is 27017
        //MongoClient client = new MongoClient(new ServerAddress("localhost", 27018));

        //new version to take advantage of the replicaset
        //this detects wich is the primary, even if it is not 27018, this is because 27018 is part of the replicaset
        //the problem is that if 27018 is down, then the connection can not be established
        //MongoClient client = new MongoClient(asList(new ServerAddress("localhost", 27018)));

        //new version to take advantage of the replicaset
        MongoClient client = new MongoClient(asList(new ServerAddress("localhost", 27017),
                new ServerAddress("localhost", 27018),
                new ServerAddress("localhost", 27019)),
                MongoClientOptions.builder()
                        .requiredReplicaSetName("m101")//name of the replica set
                        .build());

        MongoCollection<Document> collection = client.getDatabase("course").getCollection("replication");
        collection.drop();

        for(int i=0; i < Integer.MAX_VALUE; i++){
            collection.insertOne(new Document("_id", i));
            System.out.println("Inserted document: "+i);
            Thread.sleep(500);
        }

    }

}
