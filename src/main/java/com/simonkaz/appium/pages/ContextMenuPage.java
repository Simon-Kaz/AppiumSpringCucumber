package com.simonkaz.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static junit.framework.TestCase.assertTrue;


@Component
public class ContextMenuPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/long_press")
    private WebElement longPressButton;

    @AndroidFindBy(id = "android:id/select_dialog_listview")
    private WebElement contextMenu;

    @Autowired
    public ContextMenuPage(AppiumDriver driver) {
        super(driver);
    }

    public void longPress() {
        wait.until(ExpectedConditions.visibilityOf(longPressButton));
        TouchAction longPress_action = new TouchAction(driver);
        longPress_action.longPress(longPressButton, 2000).perform();
    }

    public void verifyContextMenuDisplayed(){
        //assert that the dialog menu is displayed after the long press
        assertTrue(contextMenu.isDisplayed());
    }
}
