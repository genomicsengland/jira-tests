package co.uk.gel.jira.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.jira.pages.Pages;
import co.uk.gel.jira.util.Debugger;
import co.uk.gel.lib.SeleniumLib;
import com.jayway.restassured.RestAssured;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class TestHooks  extends Pages {

    SeleniumLib seleniumLib;
    public static String currentTagName = "";
    public static String currentTags = "";
    public static String currentFeature = "";
    public static String tempTagName = "";
    public static boolean newScenarioFeature = false;

    public TestHooks(SeleniumDriver driver){
        super(driver);
        seleniumLib = new SeleniumLib(driver);
    }

    @Before(order=0)
    public void beforeTestScenario(){
        SeleniumLib.clearCookies();
    }

    @Before(order=1)
    public void beginningOfTest(Scenario scenario){
        RestAssured.proxy("proxy-dmz.gel.zone", 80);
        currentTagName = scenario.getSourceTagNames().toString();
        currentTags = scenario.getSourceTagNames().toString();
        currentFeature = scenario.getId().split(";")[0];
        if (tempTagName.isEmpty() || !(tempTagName.equalsIgnoreCase(currentTagName))) {
            Debugger.println("\n"+scenario.getSourceTagNames()+" STARTED");
            tempTagName = currentTagName;
            newScenarioFeature = true;
            Debugger.println("FEATURE: " + currentFeature.replaceAll("_", " ").toUpperCase());
        } else {
            newScenarioFeature = false;
        }
    }

    @After(order=1)
    public void tearDown(Scenario scenario){
        String scenarioStatus =  scenario.getStatus();
        if (!scenarioStatus.equalsIgnoreCase("PASSED")) {
            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
        Debugger.println("STATUS: " + scenarioStatus.toUpperCase());
        // ResponseContainer.clearCacheInstance();
        // CacheService.clearCacheInstance();
    }
    @After(order=0)
    public void afterScenario(){
        // appHomePage.logOutFromPortal();
        SeleniumDriver.initDriver();
    }
}//end class
