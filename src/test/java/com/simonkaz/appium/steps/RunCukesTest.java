package com.simonkaz.appium.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by simonk on 8/25/15.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty",
        "html:target/test-report",
        "json:target/test-report.json"}, tags = {"~@wip"})
public class RunCukesTest {
}
