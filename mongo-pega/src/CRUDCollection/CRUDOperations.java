package CRUDCollection;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Updates.set;

import java.io.File;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.JsonObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import resources.PropertiesObject;



public class CRUDOperations {
    Document obj;

    public String getCollectionName()
    {
        String collectionName = null;

        try {
            String filelocation = "src/resources/MongoConnection.properties";
            String jdbcPropertiesFileName = new File(filelocation).getAbsolutePath();
            PropertiesObject propObject= new PropertiesObject();
            Properties prop = propObject.getProperties(jdbcPropertiesFileName);
            collectionName = prop.get("mongo.collectionName").toString();

        }
        catch (Exception e)
        {
            System.out.println("Cannot get the Mongo Connection Properties File !!");
        }

        return collectionName;
    }

    public String getInsertCollectionName()
    {
        String collectionName = null;

        try {
            String filelocation = "src/resources/MongoConnection.properties";
            String jdbcPropertiesFileName = new File(filelocation).getAbsolutePath();
            PropertiesObject propObject= new PropertiesObject();
            Properties prop = propObject.getProperties(jdbcPropertiesFileName);
            collectionName = prop.get("mongo.InsertCollectionName").toString();

        }
        catch (Exception e)
        {
            System.out.println("Cannot get the Mongo Connection Properties File !!");
        }

        return collectionName;
    }

    public void getAllDocuments(MongoDatabase mongoDatabase) {

        MongoCollection<Document> mongoCollection;

        String collectionName = this.getCollectionName();

        mongoCollection = this.getMongoCollection(mongoDatabase,collectionName);

       FindIterable<Document> docs = mongoCollection.find(); //SELECT * FROM sample;

        for (Document doc : docs) {

            System.out.println("JSON"+doc.toJson());
        }

    }

    public void getFirstDocument (MongoDatabase mongoDatabase) {

        MongoCollection<Document> mongoCollection;

        String collectionName = this.getCollectionName();

        mongoCollection = this.getMongoCollection(mongoDatabase,collectionName);

        Document obj = (Document) mongoCollection.find().first();

        System.out.println(obj.toJson());

    }

    public void getSpecificDocumentByID (MongoDatabase mongoDatabase) {

        MongoCollection<Document> mongoCollection;
        String collectionName = this.getCollectionName();

        mongoCollection = this.getMongoCollection(mongoDatabase,collectionName);

        Document obj = (Document) mongoCollection.find(new Document("_id", "10006546")).first();

        System.out.println(obj.toJson());
    }

    public MongoCollection<Document> getMongoCollection(MongoDatabase mongoDatabase, String collectionName){

        MongoCollection<Document> mongoCollection;

        mongoCollection = mongoDatabase.getCollection(collectionName);
        return mongoCollection;
    }


    public void getDocumentsByQuery (MongoDatabase mongoDatabase, String Query_Key, String Query_Value) {

        @SuppressWarnings("rawtypes")
		MongoCollection<Document> mongoCollection;
        mongoCollection = this.getMongoCollection(mongoDatabase,"listingsAndReviews");

        FindIterable<Document> docs = mongoCollection.find(new Document(Query_Key, Query_Value));

        for (Document doc : docs) {
            System.out.println("JSON"+doc.toJson());
        }
    }

    public void updateDocument (MongoDatabase mongoDatabase, String Query_Key, String Query_Value, String Update_Key, String Update_Value) {

        MongoCollection<Document> mongoCollection;

        String collectionName = this.getCollectionName();
        mongoCollection = this.getMongoCollection(mongoDatabase,collectionName);

        Bson filter;

        filter = eq("_id", "10006546");
        Bson updateOperation = set("name", "Ribeira Charming Duplex Duplex");

        obj = (Document) mongoCollection.findOneAndUpdate(filter,updateOperation);
    }

    public void getDocumentsByComplexORQuery (MongoDatabase mongoDatabase) {

        MongoCollection<?> mongoCollection;

        String collectionName = this.getCollectionName();

        FindIterable<Document> docs = mongoDatabase.getCollection(collectionName).find(or
                (
                        eq("minimum_nights","2"),
                        eq("maximum_nights", "30"),
                        eq("_id", "10006546")
                )
        );

        for (Document doc : docs) {
            System.out.println("JSON"+doc.toJson());
        }
    }

    public void getDocumentsByComplexANDQuery (MongoDatabase mongoDatabase) {

        String collectionName = this.getCollectionName();

        FindIterable<Document> docs = mongoDatabase.getCollection(collectionName).find(and
                (
                        eq("minimum_nights","2"),
                        eq("maximum_nights", "30"),
                        eq("_id", "10006546")
                )
        );

        for (Document doc : docs) {
            System.out.println("JSON"+doc.toJson());
        }
    }

    public void createDocument(MongoDatabase mongoDatabase, String pegaJson)
    {

       String collectionName = this.getInsertCollectionName();
        
        var pegaDocument = Document.parse(pegaJson);
        
        System.out.println(pegaDocument.toJson());
        
        try {
        mongoDatabase.getCollection(collectionName).insertOne(pegaDocument);
        } catch (Exception e) {
        	e.printStackTrace();
        }

    }

    }
