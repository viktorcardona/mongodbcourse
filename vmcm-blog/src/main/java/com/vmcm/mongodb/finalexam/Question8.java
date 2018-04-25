package com.vmcm.mongodb.finalexam;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/**
 * Created by viccardo on 28/04/16.
 */
public class Question8 {

    /**
     * Final: Question 8
     * Supposed we executed the following Java code.
     * How many animals will be inserted into the "animals" collection?
     *
     * @param args
     */
    public static void main(String[] args) {
        MongoClient c = new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> animals = db.getCollection("animals");

        animals.drop();

        final long countAnimalsBefore = animals.count();

        Document animal = new Document("animal", "monkey");

        try {
            animals.insertOne(animal);
        }
        catch (Exception exc) {
            System.out.println("Error animals.insertOne(animal) " + exc.getMessage());
        }

        try {
            animal.remove("animal");
        }
        catch (Exception exc) {
            System.out.println("Error animal.remove(\"animal\") " + exc.getMessage());
        }

        try {
            animal.append("animal", "cat");
        }
        catch (Exception exc) {
            System.out.println("Error animal.append(\"animal\", \"cat\") " + exc.getMessage());
        }

        try {
            animals.insertOne(animal);
        }
        catch (Exception exc) {
            System.out.println("Error animals.insertOne(animal) " + exc.getMessage());
        }

        try {
            animal.remove("animal");
        }
        catch (Exception exc) {
            System.out.println("Error animal.remove(\"animal\") " + exc.getMessage());
        }

        try {
            animal.append("animal", "lion");
        }
        catch (Exception exc) {
            System.out.println("Error animal.append(\"animal\", \"lion\") " + exc.getMessage());
        }

        try {
            animals.insertOne(animal);
        }
        catch (Exception exc) {
            System.out.println("Error animals.insertOne(animal) " + exc.getMessage());
        }

        final long countAnimalsAfter = animals.count();

        System.out.println("countAnimalsBefore:" + countAnimalsBefore);
        System.out.println("countAnimalsAfter:" + countAnimalsAfter);

    }

}
