package tools.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Readers {

    public static Object getObjFromJsonFile(String jsonPath) {
        JSONParser jsonParser = new JSONParser();
        Object object = new Object();
        try (FileReader reader = new FileReader(jsonPath)) {
            //Read JSON file
            object = jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static String getJsonValue(Object obj, String key) {
        JSONObject jsonObject = (JSONObject) obj;
        String paramValue = (String) jsonObject.get(key);
        return paramValue;
    }
}
