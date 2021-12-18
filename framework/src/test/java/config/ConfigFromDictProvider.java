package config;

import java.util.HashMap;
import java.util.Map;

public class ConfigFromDictProvider implements Provider{
    /*Allows configuration through the simple dict object*/
    HashMap config;

    public ConfigFromDictProvider(HashMap config) {
        if (config == null || config.size() == 0){
            System.out.println("Dictionary config not set or empty!");
        }
        this.config = config;
    }

    @Override
    public String get(String key) {
        String paramValue = (String) config.get(key);
        //System.out.println("DICT PARAM VAL = " + paramValue);
        return paramValue;
    }
}
