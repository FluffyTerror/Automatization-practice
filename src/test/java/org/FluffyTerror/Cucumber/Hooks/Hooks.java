package org.FluffyTerror.Cucumber.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.FluffyTerror.managers.DriverManager;
import org.FluffyTerror.managers.InitManager;

public class Hooks {
    public void sleep(long sec){
        try {
            Thread.sleep(sec);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Before
    public  void setupDriver(){
        InitManager.initFramework();
    }
}
