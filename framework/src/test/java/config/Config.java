package config;

import enums.CfgParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Config extends BaseConfig{
    private static Config INSTANCE;
    private final String  DEFAULT_ENV = "prod";

    private Config() {
        String json_config_path =
                System.getenv("TARGET") != null ?
                        System.getenv("TARGET") :
                        String.format("src/test/resources/predefined/%s.json", this.DEFAULT_ENV);

        ConfigFromEnvProvider app_env = new ConfigFromEnvProvider();
        ConfigFromSimpleJsonProvider app_json_conf = new ConfigFromSimpleJsonProvider(json_config_path);
        ConfigFromDictProvider app_defaults = new ConfigFromDictProvider(
                new HashMap<String,String>() {{
                    put(CfgParams.DEFAULT_TOKEN_EXPIRE.toString(), "60 * 60 * 24");
                }});
        this.configProvider =
                new HierarchicalProvider(new ArrayList<Provider>(Arrays.asList(app_env, app_json_conf, app_defaults)));
        super.register(CfgParams.BASE_URL.toString());
        super.register(CfgParams._OS.toString());
        super.register(CfgParams.EMAIL.toString());
        super.register(CfgParams.PASSWORD.toString());
        super.register(CfgParams.DEFAULT_TOKEN_EXPIRE.toString());
    }

    public static Config getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Config();
        }
        return INSTANCE;
    }
}
