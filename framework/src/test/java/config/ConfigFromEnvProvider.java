package config;

public class ConfigFromEnvProvider extends Provider {
    /*Allows configuration through the env variables.*/
    @Override
    public String get(String key) {
        String val = System.getenv(key);
        //System.out.println("ENV GET PARAM = " + val);
        return val;
    }
}
