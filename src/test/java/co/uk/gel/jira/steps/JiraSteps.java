package co.uk.gel.jira.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.jira.pages.Pages;
import co.uk.gel.lib.SeleniumLib;


import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

public class JiraSteps extends Pages {


    public JiraSteps(SeleniumDriver driver) {
        super(driver);
    }

    //    @Given("User is logged in to the Jira System Dashboard")
    @Given("^User is logged in to the Jira System Dashboard$")
    public void UserIsLoggedInToTheJiraSystemDashboard() throws IOException {
        boolean testResult = false;
        testResult = jiraPage.loginToJira();
        if (!testResult) {
            Assert.fail("Could not navigate Jira System Dashboard");
            SeleniumLib.takeAScreenShot("failedToNavigateToJira.jpg");
        }
    }


    @Then("^User should logout from the Jira$")
    public void userShouldLogoutFromTheJira() throws IOException {
        boolean testResult = false;
        testResult = jiraPage.logoutFromJira();
        if (!testResult) {
            Assert.fail("Could not logout from Jira System Dashboard");
            SeleniumLib.takeAScreenShot("failedToLogoutFromJira.jpg");
        }
    }


    @And("^User should click on the the Create button$")
    public void userShouldClickOnTheTheCreateButton() throws IOException {
        boolean testResult = false;
        testResult = jiraPage.clickOnCreateButton();
        if (!testResult) {
            Assert.fail("Could not find create button");
            SeleniumLib.takeAScreenShot("createButtonIsMissing.jpg");
        }
    }

    @And("^User should be able to select the project \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectTheProject(String projectName) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.selectDropDownProjectName(projectName);
        if (!testResult) {
            Assert.fail("Failed to select the project");
            SeleniumLib.takeAScreenShot("projectIsMissing.jpg");
        }
    }

    @And("^User should be able to select the Issue Type \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectTheIssueType(String issueType) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.selectDropDownIssueType(issueType);
        if (!testResult) {
            Assert.fail("Failed to select the issueType");
            SeleniumLib.takeAScreenShot("issueTypeIsMissing.jpg");
        }

    }

    @And("^User should be able to provide the Summary \"([^\"]*)\"$")
    public void userShouldBeAbleToProvideTheSummary(String summaryText) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.enterTheSummary(summaryText);
        if (!testResult) {
            Assert.fail("Failed to add the summary");
            SeleniumLib.takeAScreenShot("failedToAddSummary.jpg");
        }
    }

    @And("^User should be able to select the Application \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectTheApplication(String application) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.selectTheApplication(application);
        if (!testResult) {
            Assert.fail("Failed to select the application");
            SeleniumLib.takeAScreenShot("failedToSelectApplication.jpg");
        }
    }

    @And("^User should be able to select the Workstream \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectTheWorkstream(String workstream) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.selectTheWorkstream(workstream);
        if (!testResult) {
            Assert.fail("Failed to select the workstream");
            SeleniumLib.takeAScreenShot("failedToSelectWorkstream.jpg");
        }
    }

    @And("^User should be able to provide the ticket description \"([^\"]*)\"$")
    public void userShouldBeAbleToProvideTheTicketDescription(String description) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.enterTheDescription(description);
        if (!testResult) {
            Assert.fail("Failed to enter the description");
            SeleniumLib.takeAScreenShot("failedToEnterDescription.jpg");
        }
    }

    @And("^User should be able to select the priority \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectThePriority(String priority) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.selectPriority(priority);
        if (!testResult) {
            Assert.fail("Failed to select the Priority");
            SeleniumLib.takeAScreenShot("failedToSelectPriority.jpg");
        }
    }

    @And("^User should be able to assign the ticket to the assignee \"([^\"]*)\"$")
    public void userShouldBeAbleToAssignTheTicketToTheAssignee(String assignee) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.selectAssignee(assignee);
        if (!testResult) {
            Assert.fail("Failed to select the Assignee");
            SeleniumLib.takeAScreenShot("failedToSelectAssignee.jpg");
        }
    }

    @And("^User should be able to submit the ticket$")
    public void userShouldBeAbleToSubmitTheTicket() throws IOException {
        boolean testResult = false;
        testResult = jiraPage.submitTicket();
        if (!testResult) {
            Assert.fail("Failed to submit the ticket");
            SeleniumLib.takeAScreenShot("failedToSubmitTicket.jpg");
        }
    }


    @And("^User should be able to see the ticket is created successfully and store the ticketID$")
    public void userShouldBeAbleToSeeTheTicketIsCreatedSuccessfullyAndStoreTheTicketID() throws IOException {
        boolean testResult = false;
        testResult = jiraPage.accessTicket();
        if (!testResult) {
            Assert.fail("Failed to access the ticket");
            SeleniumLib.takeAScreenShot("failedToAccessTicket.jpg");
        }
    }

    @And("^User should see the ticket status as \"([^\"]*)\"$")
    public void userShouldSeeTheTicketStatusAs(String status) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.ticketStatus(status);
        if (!testResult) {
            Assert.fail("Incorrect ticket status");
            SeleniumLib.takeAScreenShot("incorrectTicketStatus.jpg");
        }
    }

    @And("^User should be able to update the workflow as \"([^\"]*)\"$")
    public void userShouldBeAbleToUpdateTheWorkflowAs(String workflow) throws Throwable {
        boolean testResult = false;
        testResult = jiraPage.updateTheWorkflow(workflow);
        if (!testResult) {
            Assert.fail("Incorrect ticket workflow");
            SeleniumLib.takeAScreenShot("incorrectTicketWorkflow.jpg");
        }
    }

    @And("^User should be able to see the test execution area$")
    public void userShouldBeAbleToSeeTheTestExecutionArea(DataTable columnNames) throws IOException {
        boolean testResult = false;
        List<List<String>> testExecutionPlaceholder = columnNames.raw();
        for (int i = 0; i < testExecutionPlaceholder.size(); i++) {
            String options = testExecutionPlaceholder.get(i).get(0);
            testResult = jiraPage.testExecution(options);
            if (!testResult) {
                Assert.fail("Test execution not found");
                SeleniumLib.takeAScreenShot("testExecutionNotFound.jpg");
            }
        }
    }

    @And("^User should access the link$")
    public void userShouldAccessTheLink() {
        boolean testResult = false;
        testResult = jiraPage.ticketLink();
        if (!testResult) {
            Assert.fail("ticket link not found");
        }
    }
}