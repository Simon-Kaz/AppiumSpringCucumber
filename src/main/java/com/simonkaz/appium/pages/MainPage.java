package com.simonkaz.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by simonk on 8/25/15.
 */
@Component
public class MainPage extends BasePage {

    @Autowired
    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    public void switchToActivity(String activity){
        //switch to required activity
        ((AndroidDriver<WebElement>)driver).startActivity("io.appium.android.apis", activity);
    }

}
