package co.uk.gel.jira.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.jira.pages.ConfluencePage;
import co.uk.gel.jira.pages.Pages;
import co.uk.gel.jira.util.TestUtils;
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
    public void userShouldBeAbleToProvideAPageTitle(String title) throws Throwable {
        boolean testResult = false;
        testResult = confluencePage.createConfluencePageTitle(title);
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

    @And("^User should be able to click on the Page options displayed on the right corner$")
    public void userShouldBeAbleToClickOnThePageOptionsDisplayedOnTheRightCorner() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.clickOnThePageOptions();
        if (!testResult) {
            Assert.fail("Failed to click on the page options");
            SeleniumLib.takeAScreenShot("failedToClickOptions.jpg");
        }
    }

    @Then("^User should be able to export the page into PDF$")
    public void userShouldBeAbleToExportThePageIntoPDF() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.exportToPDF();
        Assert.assertTrue(testResult);
        testResult = TestUtils.isFilePresent("", "");
        Assert.assertTrue(testResult);
    }

    @Then("^User should be able to export the page into a Word document$")
    public void userShouldBeAbleToExportThePageIntoAWordDocument() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.exportToWord();
        Assert.assertTrue(testResult);
        testResult = TestUtils.isFilePresent("", "");
        Assert.assertTrue(testResult);
    }

    @And("^User should be able to delete the table$")
    public void userShouldBeAbleToDeleteTheTable() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.deleteTheTable();
        if (!testResult) {
            Assert.fail("Failed to delete the table");
            SeleniumLib.takeAScreenShot("failedToDeleteTable.jpg");
        }
    }

    @And("^User should be able to add a simple workflow$")
    public void userShouldBeAbleToAddASimpleWorkflow() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.addWorkflow();
        if (!testResult) {
            Assert.fail("Failed to add a workflow");
            SeleniumLib.takeAScreenShot("failedToAddWorkflow.jpg");
        }
    }

    @And("^User should see the workflow status as\"([^\"]*)\"$")
    public void userShouldSeeTheWorkflowStatusAs(String workflowStatus) throws Throwable {
        boolean testResult = false;
        testResult = confluencePage.workflowStatus(workflowStatus);
        if (!testResult) {
            Assert.fail("Failed to see the workflow status");
            SeleniumLib.takeAScreenShot("failedToSeeWorkflowStatus.jpg");
        }
    }


    @And("^User should be able to change the status as \"([^\"]*)\"$")
    public void userShouldBeAbleToChangeTheStatusAs(String changeStatus) throws Throwable {
        boolean testResult = false;
        testResult = confluencePage.changeWorkflowStatus(changeStatus);
        if (!testResult) {
            Assert.fail("Failed to change the workflow status");
            SeleniumLib.takeAScreenShot("failedToChangeWorkflowStatus.jpg");
        }
    }

    @Then("^User should logout from confluence$")
    public void userShouldLogoutFromConfluence() throws IOException {
        boolean testResult = false;
        testResult = confluencePage.logoutFromConfluence();
        if (!testResult) {
            Assert.fail("Could not logout from confluence");
            SeleniumLib.takeAScreenShot("failedToLogoutFromConfluence.jpg");
        }
    }
}




