package com.browserstack;

import java.util.Iterator;
import java.util.Arrays;

import io.cucumber.java.After;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(
        glue = "com.browserstack.stepdefs",
        features = "src/test/resources/features/test",
        plugin = {
                "pretty",
                "html:reports/tests/cucumber/html",
                "timeline:reports/tests/cucumber/timeline",
                "junit:reports/tests/cucumber/junit/cucumber.xml",
                "testng:reports/tests/cucumber/testng/cucumber.xml",
                "json:reports/tests/cucumber/json/cucumber.json"
        }
)
public class RunWebDriverCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;
//     private static final ThreadLocal<ManagedWebDriver> threadLocalWebDriver = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

//     @BeforeStep()
//     public void beforeStep() {
//         System.out.println("Inside the beforeStep step");
//     }

    @AfterStep
    public synchronized void testAfter(Scenario scenario) throws Throwable {
        System.out.println("Inside the afterStep step");
    }

//     private synchronized static void setThreadLocalWebDriver(ManagedWebDriver managedWebDriver) {
//         threadLocalWebDriver.set(managedWebDriver);
//     }

//     public synchronized static ManagedWebDriver getManagedWebDriver() {
//         return threadLocalWebDriver.get();
//     }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
    public void feature(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        // if(Utility.isLocal(managedWebDriver) && local==null){
        //     local = new Local();
        //     Utility.startLocal(local, managedWebDriver);
        // }
        // managedWebDriver.setTestName(pickleWrapper.getPickle().getName());
        // setThreadLocalWebDriver(managedWebDriver);
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider(name = "scenarios", parallel = true)
    public Object[][] scenarios() {
        // Object[][] scenarios = testNGCucumberRunner.provideScenarios();
        // //Get Iterator of Object arrays consisting PickleWrapper, FeatureWrapper and ManagedWebDriver
        // return Arrays.stream(scenarios).iterator();
        Object scenarios[][] = testNGCucumberRunner.provideScenarios();
        if (scenarios.length == 0) {
                testNGCucumberRunner = new io.cucumber.testng.TestNGCucumberRunner(this.getClass());
                scenarios = testNGCucumberRunner.provideScenarios();
        }
        return scenarios;
    }

    @BeforeMethod(alwaysRun = true)
        @Parameters(value = { "config" })
        public void setUp(String config_file) throws Throwable {
                // try {
                //         BaseUtil.setCurrentConfigFile(config_file);
                // } catch (Exception e) {
                //         logger.error(e.getMessage());
                //         e.printStackTrace();
                //         throw e;
                // }
        }
        @AfterMethod
        public synchronized void getResult(ITestResult result) throws Throwable {
                // AcatsRunnerMethods.getResults(result);
        }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        // if(local != null){
        //     try {
        //         local.stop();
        //     } catch (Exception e) {
        //         throw new Error("Unable to stop BrowserStack Local.");
        //     }
        // }
        if (testNGCucumberRunner == null) {
            return;
        }
        testNGCucumberRunner.finish();
    }

}
