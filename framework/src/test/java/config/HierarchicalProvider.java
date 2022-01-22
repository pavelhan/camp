package config;

import java.util.ArrayList;

public class HierarchicalProvider extends Provider{
    /*Allows to create hierarchical override model,
    for ex:
    1. Env config (most priority)
    2. Json config (less priority)
    3. Dict config (with defaults)
    In this situation the get key will try tio return value from 1st,
    in case it not configured (None) => resolve from 2nd,
    in case it not configured from the last one in the passed list
    */

    ArrayList<Provider> providers = null;

    public HierarchicalProvider(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    @Override
    public String get(String key) {
        for (Provider provider: providers) {
            String result = provider.get(key);
            if (result != null){
                return result;
            }
        }
        return null;
    }
}
