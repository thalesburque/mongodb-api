package resources;

import java.util.Properties;
import java.io.*;


public class PropertiesObject {

    public Properties getProperties(String propFileName) {
        InputStream input = null;
        Properties prop = null;

        try {
            input = new FileInputStream(propFileName);
            prop = new Properties();
            prop.load(input);
        } catch (Exception var4) {
            System.out.println("Error !!" + var4.toString());
        }
        return prop;
    }
}
