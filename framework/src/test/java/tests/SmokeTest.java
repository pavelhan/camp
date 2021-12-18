package tests;

import org.junit.Test;
import config.*;
import enums.CfgParams;

public class SmokeTest {

    @Test
    public void fullConfigTest() throws Exception {
        Config cfg = Config.getInstance();
        System.out.println("------");
        System.out.println(cfg.get(CfgParams.BASE_URL.toString()));
        System.out.println("------");
        System.out.println(cfg.get(CfgParams._OS.toString()));
    }
}
