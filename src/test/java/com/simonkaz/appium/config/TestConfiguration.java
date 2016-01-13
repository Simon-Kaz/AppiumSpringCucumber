package com.simonkaz.appium.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@ComponentScan(basePackages = {"com.simonkaz.appium.configuration", "com.simonkaz.appium.steps", "com.simonkaz.appium.util", "com.simonkaz.appium.pages"})
public class TestConfiguration {

    private static String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    private static String SAUCE_KEY = System.getenv("SAUCE_KEY");

    @Bean(destroyMethod = "quit")
    public AppiumDriver androidAppiumDriver() throws MalformedURLException {

        AppiumDriver driver = null;

        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("build","Appium Spring Cucumber JVM Test Suite");
        capabilities.setCapability("name", "Samsung Galaxy S4");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy S4 Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, "sauce-storage:ApiDemos-debug.apk");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "ApiDemos");
        capabilities.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY, "ApiDemos");
        capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT, 40);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.4.16");
        capabilities.setCapability("deviceOrientation", "portrait");

        driver = new AndroidDriver(new URL("http://" + SAUCE_USERNAME + ":" + SAUCE_KEY + "@ondemand.saucelabs.com:80/wd/hub")
                ,capabilities);

        return driver;
    }
}