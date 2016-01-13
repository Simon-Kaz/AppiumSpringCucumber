package com.simonkaz.appium.steps;

import com.simonkaz.appium.config.TestConfiguration;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by simonk on 8/25/15.
 */

@ContextConfiguration(classes = {TestConfiguration.class})
public class InitSteps {

    @Autowired
    private AppiumDriver driver;

    @Before
    public void beforeMethod(){
        System.out.println("Before Method");
    }

    @After
    public void afterMethod(Scenario scenario){
        if(scenario.isFailed()) {
            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

}
