package com.browserstack.stepdefs;

import com.browserstack.pageobjects.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

public class StackDemoSteps {
    private static WebDriver driver;
    private static HomePage homePage;

    @Before
    public void setUp() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.2");
        capabilities.setCapability("bstack:options", bstackOptions);
        driver = new RemoteWebDriver(
                new URL("https://hub.browserstack.com/wd/hub"), capabilities);
        homePage = new HomePage(driver);
    }

    @Given("User Set the required data maps for test")
    public void I_am_on_the_requirede() throws Throwable {
        // driver.get(url);
        Thread.sleep(2000);
    }

    @Given("I should have {int} cucumbers")
    public void I_am_on_the_website(Integer left) throws Throwable {
        // driver.get(url);
        System.out.println(left);
        Thread.sleep(2000);
    }

    @When("User updates Soap request payload with dynamic attribute")
    public void user_updates_soap_request_payload_with_dynamic_attribute(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> dynamicAttributes = dataTable.asMap(String.class, String.class);
        System.out.println(dynamicAttributes);
    }

    @Then("the product should be added to cart")
    public void product_should_be_added_to_cart() {
        homePage.waitForCartToOpen();
        Assert.assertEquals(homePage.getSelectedProductName(), homePage.getProductCartText());
    }

    @Then("the page title should contain '(.+)'$")
    public void page_title_should_contain(String expectedTitle) {
        // Assert.assertTrue(driver.getTitle().contains(expectedTitle));
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
