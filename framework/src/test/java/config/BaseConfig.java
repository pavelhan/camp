package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class BaseConfig {

    private static Properties endpoints;
    protected HashMap<String, String> configParams = new HashMap<>();
    HierarchicalProvider configProvider;

    public void register(String item){
        this.configParams.put(item, configProvider.get(item));
    }

    public String get(String item) throws Exception {
        if (configParams.containsKey(item)) {
            return configParams.get(item);
        }
        throw new Exception("Please register var before usage");
    }

    public static String getEndpoint(String name){
        String endpointValue = "none";
        try {
            endpoints = new Properties();
            endpoints.load(new FileInputStream("src\\test\\resources\\endpoints.properties"));
            //TODO - Add check for not found property
            endpointValue = endpoints.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return endpointValue;
    }
}
