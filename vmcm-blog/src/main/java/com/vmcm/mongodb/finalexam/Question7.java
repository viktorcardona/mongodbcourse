package com.vmcm.mongodb.finalexam;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Created by viccardo on 28/04/16.
 */
public class Question7 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("photo-sharing");
        MongoCollection<Document> collectionAlbums = db.getCollection("albums");
        MongoCollection<Document> collectionImages = db.getCollection("images");


        final long countImagesBefore = collectionImages.count();

        int counterImagesOrphan = 0;

        try(MongoCursor<Document> cursor = collectionImages.find().iterator()){
            int counter = 0;

            while(cursor.hasNext()){
                counter++;
                Document image = cursor.next();
                final boolean isOrphanImage = isOrphanImage(image, collectionAlbums, counter);
                if(isOrphanImage){
                    counterImagesOrphan++;
                    collectionImages.deleteOne(image);
                }

            }
        }

        System.out.println("collectionImages.count.before:"+countImagesBefore);
        System.out.println("collectionImages.count.after:"+collectionImages.count());
        System.out.println("collectionImages.count.ImagesOrphan:"+counterImagesOrphan);

    }

    private static boolean isOrphanImage(Document image, MongoCollection<Document> collectionAlbums, int counter) {
        Integer imageId = image.getInteger("_id");
        Bson filter = eq("images",imageId);
        List<Document> albumsWithTheImage = collectionAlbums.find(filter).into(new ArrayList<>());
        final boolean isOrphanImage = albumsWithTheImage==null || albumsWithTheImage.isEmpty();
        System.out.println(+counter+".Image._id:"+imageId+":isOrphanImage:"+isOrphanImage);
        return isOrphanImage;
    }

}
