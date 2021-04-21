package co.uk.gel.jira.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.jira.pages.ConfluencePage;
import co.uk.gel.jira.pages.Pages;
import co.uk.gel.lib.SeleniumLib;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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

    @And("^User is able to view all the spaces$")
    public void userIsAbleToViewAllTheSpaces() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.allSpaces();
        if (!testResult) {
            Assert.fail("Failed to view all the spaces");
            SeleniumLib.takeAScreenShot("failedToSeeTheSpaceDirectories.jpg");
        }
    }

    @And("^User should click on the space directory \"([^\"]*)\"$")
    public void userShouldClickOnTheSpaceDirectory(String spaceDirectory) throws Throwable {
        boolean testResult = false;
        testResult = confluencePage.clickOnTheSpaceDirectory(spaceDirectory);
        if (!testResult) {
            Assert.fail("Failed to click on the space directory");
            SeleniumLib.takeAScreenShot("failedToClickOnTheSpaceDirectory.jpg");
        }

    }

    @And("^User should be able to select the required page tree \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectTheRequiredPageTree(String pageTree) throws Throwable {
        boolean testResult = false;
        testResult = confluencePage.clickOnThePageTree(pageTree);
        if (!testResult) {
            Assert.fail("Failed to click on the page tree");
            SeleniumLib.takeAScreenShot("failedToClickOnThePageTree.jpg");
        }
    }

    @And("^User should click on the Create a blank page button$")
    public void userShouldClickOnTheCreateABlankPageButton() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.clickOnTheCreatePageButton();
        if (!testResult) {
            Assert.fail("Failed to click on the create button");
            SeleniumLib.takeAScreenShot("failedToClickOnTheCreateButton.jpg");
        }
    }

    @And("^User should be able to provide the page description\"([^\"]*)\"$")
    public void userShouldBeAbleToProvideThePageDescription(String description) throws Throwable {
        boolean testResult = false;
        testResult = confluencePage.enterThePageDescription(description);
        if (!testResult) {
            Assert.fail("Failed to enter the page description");
            SeleniumLib.takeAScreenShot("failedToEnterThePageDescription.jpg");
        }

    }

    @And("^User should be able to save the page successfully$")
    public void userShouldBeAbleToSaveThePageSuccessfully() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.saveThePage();
        if (!testResult) {
            Assert.fail("Failed to save the page");
            SeleniumLib.takeAScreenShot("failedToSaveThePage.jpg");
        }
    }


    @And("^User should select the required sub page \"([^\"]*)\"$")
    public void userShouldSelectTheRequiredSubPage(String subPageName) throws Throwable {
        boolean testResult = false;
        testResult = confluencePage.selectTheSubPage(subPageName);
        if (!testResult) {
            Assert.fail("Failed to select the sub page");
            SeleniumLib.takeAScreenShot("failedToPublishSelectTheSubPage.jpg");
        }
    }

    @And("^User should click on the edit page link$")
    public void userShouldClickOnTheEditPageLink() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.clickOnTheEditPageLink();
        if (!testResult) {
            Assert.fail("Failed to click on edit page link");
            SeleniumLib.takeAScreenShot("failedToClickOnEditPage.jpg");
        }
    }

    @And("^User should be able to insert a table$")
    public void userShouldBeAbleToInsertATable() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.insertTable();
        if (!testResult) {
            Assert.fail("Failed to insert the table");
            SeleniumLib.takeAScreenShot("failedToInsertTable.jpg");
        }
    }
}




