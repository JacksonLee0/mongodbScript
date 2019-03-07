package Database;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Filter;

public class MongoDB {

    private MongoDatabase database;

    //connect to the local database
    public void connectToDB() {


        //create a mongo client
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("nlp_ner");
    }

    public void createCollection(String collectionName) {
        try {
            database.createCollection(collectionName);

            System.out.println("Collection created successfully");
        } catch (MongoCommandException e) {
            System.out.println("Collection already exists");
        }
    }


    //return the collection in database
    public MongoCollection retrieveCollection(String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        if (collection == null) {
            System.out.println("Collection doesn't exits");
            return null;
        } else {
            System.out.println("Collection retrieved successfully");
            return collection;
        }

    }


    //create the document and add it to the database
    public void createAndAddDocuments(MongoDocument mongoDocument, String collectionName) {

//        if(database.getCollection(collectionName) != null){
//            database.getCollection(collectionName).drop();
//            createCollection(collectionName);
//        }

        MongoCollection collection = retrieveCollection(collectionName);
        ArrayList<String> localesForGlobal = new ArrayList<>();
        Document alternativeTerm;
        ArrayList<Document> alternativeTerms;
        Document doc = new Document();
        localesForGlobal.add("global");

        for (MongoField field : mongoDocument.getFields()) {
            doc.append("_id", field.getFieldName());

            /**
             *
             */
            doc.append("locales", localesForGlobal);

            alternativeTerms = new ArrayList<>();
            for (AlternativeTerm alt : field.getAlternativeTerms()) {

                alternativeTerm = new Document();
                alternativeTerm.append("name", alt.getName());
                alternativeTerm.append("locales", alt.getLocales());
                alternativeTerm.append("_class", "nlp.ner.domain.terms.Term");
                alternativeTerms.add(alternativeTerm);

            }//loop through alternative terms and append them to the alternative term document

            doc.append("alternateTerms", alternativeTerms);
            doc.append("lastModifiedDate", field.getLastModifiedDate());
            insertDocument(collection, doc);
        }
    }

    //insert the document into database
    private void insertDocument(MongoCollection collection, Document document) {
        try {
            collection.insertOne(document);
            System.out.println("MongoField inserted successfully");
        } catch (MongoWriteException e) {
            System.out.println("MongoField with this id already exists");
        }
    }


    //drop the collection in database
    public void dropCollection(String collectionName) {
        if (retrieveCollection(collectionName) == null) {
            System.out.println("no collection to drop");
        } else {
            retrieveCollection(collectionName).drop();

            System.out.println("drop successfully");
        }

    }

}
