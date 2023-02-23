package com.browserstack.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverManager = new ThreadLocal<>();

    @Before
    public void setUp() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "cucumber-java:sample-sdk:v1.0");
        capabilities.setCapability("bstack:options", bstackOptions);
        driverManager.set(new RemoteWebDriver(
            new URL("https://hub.browserstack.com/wd/hub"), capabilities));
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
        Thread.sleep(2000);
        driverManager.get().quit();
    }

    public static WebDriver getDriver() {
        return driverManager.get();
    }
}
