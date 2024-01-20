package resources;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pojo.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataBuild {

    public static String getUserDetails(String scenario) throws IOException, ParseException {
        String template;
        Map<String, String> map= getUser();

        if(scenario.equals("login"))
        {
            template = "{ \"email\": \"%s\", \"password\": \"%s\"}";
            return template.formatted(map.get("email"), map.get("password"));
        }
        else if (scenario.equals("update"))
        {
            template = "{ \"name\": \"%s\", \"phone\": \"%s\", \"company\": \"%s\"}";
            return template.formatted(map.get("updateName"), map.get("phone"), map.get("company"));
        }
        template = "{\"name\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}";
        return template.formatted(map.get("name"), map.get("email"), map.get("password"));
    }

    public static Map<String, String> getUser() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader file = new FileReader("F:\\Note_API_Auomation\\src\\test\\java\\resources\\testData.json");
        JSONObject jobj = (JSONObject) jsonParser.parse(file);
        JSONObject user = (JSONObject) jobj.get("user");
        String name = (String) user.get("name");
        String updateName = (String) user.get("updateName");
        String email = (String) user.get("email");
        String password = (String) user.get("password");
        String phone = (String) user.get("phone");
        String company = (String) user.get("company");
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("updateName", updateName);
        map.put("email", email);
        map.put("password", password);
        map.put("phone", phone);
        map.put("company", company);
        return map;
    }

    public static String getNoteDetails(String title, String description, String category)
    {
        String template = "{\"title\": \"%s\", \"description\": \"%s\", \"category\": \"%s\"}";
        return template.formatted(title, description, category);
    }

    public static String getNoteDetails(String id, String title, String description, String completed, String category)
    {
        String template = "{\"id\": \"%s\", \"title\": \"%s\", \"description\": \"%s\", \"completed\": \"%s\", \"category\": \"%s\"}";
        return template.formatted(id, title, description, completed, category);
    }
}
