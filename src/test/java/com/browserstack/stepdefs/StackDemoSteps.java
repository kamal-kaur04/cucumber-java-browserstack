package com.browserstack.stepdefs;

import com.browserstack.pageobjects.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

public class StackDemoSteps {

    public HomePage homePage = new HomePage();

    @Given("^I am on the website '(.+)'$")
    public void I_am_on_the_website(String url) throws Throwable {
        DriverManager.getDriver().get(url);
        Thread.sleep(2000);
    }

    @When("^I select a product and click on 'Add to cart' button")
    public void I_select_a_product_and_add_to_cart() throws Throwable {
        homePage.selectFirstProductName();
        homePage.clickAddToCartButton();
        Thread.sleep(2000);
    }

    @Then("the product should be added to cart")
    public void product_should_be_added_to_cart() {
        homePage.waitForCartToOpen();
        Assert.assertEquals(homePage.getSelectedProductName(), homePage.getProductCartText());
    }

    @Then("the page title should contain '(.+)'$")
    public void page_title_should_contain(String expectedTitle) {
        Assert.assertTrue(DriverManager.getDriver().getTitle().contains(expectedTitle));
    }
}
