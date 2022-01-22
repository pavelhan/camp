package config;

import org.json.simple.JSONObject;

import static tools.helpers.Readers.*;

public class ConfigFromSimpleJsonProvider extends Provider{
    /*Allows configuration through the JSON file*/
    Object obj;

    public ConfigFromSimpleJsonProvider(String jsonPath) {
        obj = getObjFromJsonFile(jsonPath);
    }

    @Override
    public String get(String key) {
        String paramValue = getJsonValue(obj, key);
        //System.out.println("JSON GET PARAM = " + paramValue);
        return paramValue;
    }


}
