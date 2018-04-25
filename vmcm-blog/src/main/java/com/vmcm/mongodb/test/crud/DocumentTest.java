package com.vmcm.mongodb.test.crud;

import java.util.Arrays;
import java.util.Date;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.vmcm.mongodb.test.crud.Helpers.printJson;

/**
 * Created by viccardo on 26/03/16.
 */
public class DocumentTest {

    public static void main(String[] args) {

        Document document = new Document()
                .append("str", "Hello Mongo DB")
                .append("int", 42)
                .append("l", 1L)
                .append("double", 1.1)
                .append("bool", true)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embededDoc", new Document("x",0))
                .append("list", Arrays.asList(1,2,3));

        printJson(document);

        BsonDocument bsonDocument = new BsonDocument("str", new BsonString("Hello Mongo DB"));
    }
}
