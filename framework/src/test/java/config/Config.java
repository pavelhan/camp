package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Config extends BaseConfig{
    private static Config INSTANCE;
    private final String  DEFAULT_ENV = "prod";

    private Config() throws Exception {
        String json_config_path =
                System.getenv("TARGET") != null ?
                        System.getenv("TARGET") :
                        String.format("src/test/resources/predefined/%s.json", this.DEFAULT_ENV);

        ConfigFromEnvProvider app_env = new ConfigFromEnvProvider();
        ConfigFromSimpleJsonProvider app_json_conf = new ConfigFromSimpleJsonProvider(json_config_path);
        ConfigFromDictProvider app_defaults = new ConfigFromDictProvider(
                new HashMap<String,String>() {{
                    put("DEFAULT_TOKEN_EXPIRE", "60 * 60 * 24");
                }});
        this.configProvider =
                new HierarchicalProvider(new ArrayList<Provider>(Arrays.asList(app_env, app_json_conf, app_defaults)));
        register("BASE_URL");
        register("_OS");
        register("EMAIL");
        register("PASSWORD");
        register("DEFAULT_TOKEN_EXPIRE");
        register("USERS_JSON_PATH");
        register("UI_TESTS_BASE_URL");
    }

    public static Config config() throws Exception {
        if(INSTANCE == null){
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

}
