package co.uk.gel.jira.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.jira.pages.ConfluencePage;
import co.uk.gel.jira.pages.Pages;
import co.uk.gel.lib.SeleniumLib;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.io.IOException;

public class ConfluenceSteps extends Pages {
    public ConfluenceSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Given("^User is logged in to the confluence page$")
    public void UserIsLoggedInToTheConfluencePage() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.loginToConfluence();
        if (!testResult) {
            Assert.fail("Could not log into confluence page");
            SeleniumLib.takeAScreenShot("failedToNavigateLoginToConfluence.jpg");
        }
    }


    @Then("^User should be able to provide a page title \"([^\"]*)\"$")
    public void userShouldBeAbleToProvideAPageTitle(String pageTitle) throws Throwable {
        boolean testResult = false;
        testResult = confluencePage.createConfluencePageTitle(pageTitle);
        if (!testResult) {
            Assert.fail("Could not create the confluence page title");
            SeleniumLib.takeAScreenShot("failedToCreateConfluencePageTitle.jpg");
        }
    }
}





