package Connection;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import resources.PropertiesObject;
import java.io.File;
import java.util.Properties;


public class MongoConnection {

    public MongoDatabase getMongoDB(MongoClient mongoClient) {
        MongoDatabase database = null;
        String databaseName = null;
        String connectionURI;
        String collectionName ;


        try {
            String filelocation = "src/resources/MongoConnection.properties";
            String jdbcPropertiesFileName = new File(filelocation).getAbsolutePath();
            PropertiesObject propObject= new PropertiesObject();
            Properties prop = propObject.getProperties(jdbcPropertiesFileName);
            databaseName = prop.get("mongo.dbName").toString();
            collectionName = prop.get("mongo.collectionName").toString();

        }
        catch (Exception e)
        {
            System.out.println("Cannot get the Mongo Connection Properties File !!");
        }


        try {
            database = mongoClient.getDatabase(databaseName);

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Fail !!");
        }
        return database;
    }

    public MongoClient getMongoClient(){
        MongoClient mongoClient = null;
        MongoClientURI uri;
        String connectionURI = null;

        try {
            String filelocation = "src/resources/MongoConnection.properties";
            String jdbcPropertiesFileName = new File(filelocation).getAbsolutePath();
            PropertiesObject propObject= new PropertiesObject();
            Properties prop = propObject.getProperties(jdbcPropertiesFileName);
            connectionURI = prop.get("mongo.connectionURI").toString();

        }
        catch (Exception e)
        {
            System.out.println("Cannot get the Mongo Connection Properties File !!");
        }

        uri = new MongoClientURI(connectionURI);
        try {
            mongoClient = new MongoClient(uri);

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Fail !!");
        }
        return mongoClient;
    }

    public String closeMongoDBConn(MongoClient mongoClient)
    {
        String returnStatus;
        if (mongoClient != null) {
            mongoClient.close();
            returnStatus = "Success";
        }
        else
        returnStatus= "Failure";

        return returnStatus;
    }

}
