package com.simonkaz.appium.steps;

import com.simonkaz.appium.config.TestConfiguration;
import com.simonkaz.appium.pages.ContextMenuPage;
import com.simonkaz.appium.pages.MainPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by simonk on 8/25/15.
 */
@ContextConfiguration(classes = {TestConfiguration.class})
public class MyStepdefs {

    @Autowired
    AppiumDriver driver;

    @Autowired
    private MainPage mainPage;

    @Autowired
    private ContextMenuPage contextMenuPage;

    @Given("^I am on the main screen$")
    public void I_am_on_the_main_screen() throws Throwable {

    }

    @When("^I open Context Menu Activity$")
    public void I_open_Context_Menu_Activity() throws Throwable {
        mainPage.switchToActivity(".app.FragmentContextMenu");
    }

    @And("^I long press on the element$")
    public void I_long_press_on_the_element() throws Throwable {
        contextMenuPage.longPress();
    }

    @Then("^the context menu is displayed$")
    public void the_context_menu_is_displayed() throws Throwable {
    }
}
