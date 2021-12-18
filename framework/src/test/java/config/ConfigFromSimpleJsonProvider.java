package config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigFromSimpleJsonProvider implements Provider{
    /*Allows configuration through the JSON file*/
    JSONObject jsonObject;
    Object obj;

    public ConfigFromSimpleJsonProvider(String jsonPath) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(jsonPath)) {
            //Read JSON file
            obj = jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String key) {
        jsonObject = (JSONObject) obj;
        String paramValue = (String) jsonObject.get(key);
        //System.out.println("JSON GET PARAM = " + paramValue);
        return paramValue;
    }
}
