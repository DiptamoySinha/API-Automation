package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Properties;

public class Utils {

    public RequestSpecBuilder reqSpecBuilder;
    static PrintStream logStream;

    public RequestSpecBuilder getReqSpecification() throws IOException {
        if(logStream == null)
        {
            logStream = new PrintStream(new FileOutputStream("logging.txt"));
        }
        reqSpecBuilder = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseURI"))
                .setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter.logRequestTo(logStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(logStream));
        return reqSpecBuilder;
    }

    public String getGlobalProperty(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("F:\\Note_API_Auomation\\src\\test\\java\\resources\\global.properties");
        prop.load(file);
        return prop.getProperty(key);
    }
}
