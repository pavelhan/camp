package config;

import enums.CfgParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Config extends BaseConfig{
    private static Config INSTANCE;
    private final String  DEFAULT_ENV = "dev";

    private Config() {
        String json_config_path =
                System.getenv("TARGET") != null ?
                        System.getenv("TARGET") :
                        String.format("src/test/resources/predefined/%s.json", this.DEFAULT_ENV);

        ConfigFromEnvProvider app_env = new ConfigFromEnvProvider();
        ConfigFromSimpleJsonProvider app_json_conf = new ConfigFromSimpleJsonProvider(json_config_path);
        ConfigFromDictProvider app_defaults = new ConfigFromDictProvider(
                new HashMap<String,String>() {{
                    put(CfgParams.BASE_URL.toString(), "http://selenium-grid:4444/wd/hub");
                    put(CfgParams._OS.toString(), "OS_DICT");
                }});
        this.configProvider =
                new HierarchicalProvider(new ArrayList<Provider>(Arrays.asList(app_env, app_json_conf, app_defaults)));
        super.register(CfgParams.BASE_URL.toString());
        super.register(CfgParams._OS.toString());
    }

    public static Config getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Config();
        }
        return INSTANCE;
    }
}
