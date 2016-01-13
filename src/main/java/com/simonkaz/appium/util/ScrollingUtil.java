package com.simonkaz.appium.util;

import com.simonkaz.appium.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by simonk on 8/25/15.
 */
@Component
public class ScrollingUtil {

    @Autowired
    private AppiumDriver driver;

    private static final double DEFAULT_SWIPE_DEADZONE = 0.20; //default swipe deadzone, set to 20%
    private static final int DEFAULT_SWIPE_DURATION = 1000; //default swipe duration, set to 0.5 second
    public static final int DEFAULT_SWIPE_COUNT = 3;

    public WebElement scrollToElement(WebElement elementToScrollTo) {
        return scrollToElement(elementToScrollTo, DEFAULT_SWIPE_COUNT);
    }

    public WebElement scrollToElement(WebElement elementToScrollTo, int maxSwipeCount) {
        if (isElementDisplayed(elementToScrollTo)) {
            return elementToScrollTo;
        } else {
            scrollUpScreenLength(maxSwipeCount);
            if (isElementDisplayed(elementToScrollTo)) {
                return elementToScrollTo;
            }
            for (int count = 0; count < maxSwipeCount; count++) {
                scrollDownScreenLength();
                if (isElementDisplayed(elementToScrollTo)) {
                    return elementToScrollTo;
                }
            }
            throw new NoSuchElementException(String.format("Cannot find element: %s)", elementToScrollTo));
        }
    }


    public void scrollDownScreenLength() {
        scrollDownScreenLength(1);
    }


    public void scrollDownScreenLength(int count) {

        int width = getScreenWidth();
        int height = getScreenHeight();

        //adjusting height to account for deadzone
        int swipeAreaAdjust = (int) (height * DEFAULT_SWIPE_DEADZONE);

        for (int i = 0; i < count; i++) {
            // width/2 - to get the center of the screen
            driver.swipe(width / 2, height - swipeAreaAdjust, width / 2, swipeAreaAdjust, DEFAULT_SWIPE_DURATION);
        }
    }

    public void scrollUpScreenLength() {
        scrollUpScreenLength(1);
    }

    public void scrollUpScreenLength(int count) {

        int width = getScreenWidth();
        int height = getScreenHeight();

        //adjusting height to account for deadzone
        int swipeAreaAdjust = (int) (height * DEFAULT_SWIPE_DEADZONE);

        for (int i = 0; i < count; i++) {
            // width/2 - to get the center of the screen
            driver.swipe(width / 2, swipeAreaAdjust, width / 2, height - swipeAreaAdjust, DEFAULT_SWIPE_DURATION);
        }
    }

    public boolean isElementDisplayed(WebElement element, long waitDuration) {
        TimeUnit tu = BasePage.duration.getTimeUnit();
        long time = BasePage.duration.getTime();

        BasePage.duration.setTime(waitDuration, TimeUnit.SECONDS);
        try {
            if (element.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            BasePage.duration.setTime(time, tu);
        }
        return false;
    }

    public void scrollToAndroidUISelector(String selector) {
        String selectorString = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(%s);", selector);

        driver.findElement(MobileBy.AndroidUIAutomator(selectorString));
    }

    public boolean isElementDisplayed(WebElement element) {
        return isElementDisplayed(element, 0L);
    }

    private int getScreenWidth() {
        return driver.manage().window().getSize().getWidth();
    }

    private int getScreenHeight() {
        return driver.manage().window().getSize().getHeight();
    }
}