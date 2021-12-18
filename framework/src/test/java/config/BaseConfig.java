package config;

import java.util.HashMap;

public class BaseConfig {

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
}
