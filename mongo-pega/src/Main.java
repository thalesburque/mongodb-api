import CRUDCollection.CRUDController;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        /* Prevent the default mongoDB Logger levels by setting the log level to SEVERE */
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("enter json: ");
        String pegaJson = sc.nextLine();
        
        System.out.println(pegaJson);

        // Trigger the MongoDB action
        CRUDController crudController = new CRUDController("InsertDocument", pegaJson);
        
        sc.close();

    }
}