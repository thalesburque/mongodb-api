package CRUDCollection;

import Connection.MongoConnection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class CRUDController {


    public CRUDController(String actionName, String pegaJson) {

        /* This is the sample code to get the Mongo DB Connection Testing started */
        MongoDatabase mongoDatabase;
        MongoClient mongoClient;
        MongoConnection mongoConnection = new MongoConnection();
        CRUDOperations crudOperations = new CRUDOperations();

        mongoClient = mongoConnection.getMongoClient();
        mongoDatabase = mongoConnection.getMongoDB(mongoClient);

        // Switch through the actions based on the action given in Main
        switch (actionName) {
            case "getAllDocuments":
                //   Get all Documents From MongoDB
                crudOperations.getAllDocuments(mongoDatabase);
                mongoConnection.closeMongoDBConn(mongoClient);
                break;
            case "getDocumentByID":
                // Get a specific document by ID
                crudOperations.getSpecificDocumentByID(mongoDatabase);
                mongoConnection.closeMongoDBConn(mongoClient);
                break;
            case "getFirstDocument":
                // Get the First Document
                crudOperations.getFirstDocument(mongoDatabase);
                mongoConnection.closeMongoDBConn(mongoClient);
                break;
            case "getDocumentsByQuery":
                //Get Documents by Query
                crudOperations.getDocumentsByQuery(mongoDatabase, "maximum_nights", "500");
                mongoConnection.closeMongoDBConn(mongoClient);
                break;
            case "updateDocumentsByQuery":
                // Update Documents by Query
                crudOperations.updateDocument(mongoDatabase, "_id", "10006546", "name", "Ribeira Charming Duplex Duplex");
                mongoConnection.closeMongoDBConn(mongoClient);
                break;
            case "getDocumentsByComplexOR":
                // Get Documents by Complex OR Query
                crudOperations.getDocumentsByComplexORQuery(mongoDatabase);
                mongoConnection.closeMongoDBConn(mongoClient);
                break;
            case "getDocumentsByComplexAND":
                // Get Documents by Complex AND Query
                crudOperations.getDocumentsByComplexANDQuery(mongoDatabase);
                mongoConnection.closeMongoDBConn(mongoClient);
                break;
            case "InsertDocument":
                // Get Documents by Complex AND Query
                crudOperations.createDocument(mongoDatabase, pegaJson);
                mongoConnection.closeMongoDBConn(mongoClient);
                break;

        }

    }

}
