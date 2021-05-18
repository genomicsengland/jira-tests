package co.uk.gel.jira.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.jira.pages.Pages;
import co.uk.gel.lib.SeleniumLib;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.junit.Assert;

import java.io.IOException;

public class ServiceDeskSteps extends Pages {


    public ServiceDeskSteps(SeleniumDriver driver) {
        super(driver);
    }

//    @Given("^User is logged in to the Service Desk Dashboard$")
//    public void UserIsLoggedInToTheServiceDeskDashboard() throws IOException {
//        boolean testResult = false;
//        testResult = serviceDeskPage.loginToServiceDesk();
//        if (!testResult) {
//            Assert.fail("Could not navigate Service Desk Dashboard");
//            SeleniumLib.takeAScreenShot("failedToNavigateToSeviceDesk.jpg");
//        }
//    }


    @And("^User should see the status of the ticket as \"([^\"]*)\"$")
    public void userShouldSeeTheStatusOfTheTicketAs(String status) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.ticketStatus(status);
        if (!testResult) {
            Assert.fail("Incorrect ticket status");
            SeleniumLib.takeAScreenShot("incorrectTicketStatus.jpg");
        }
    }

    @And("^User should be able to comment on the ticket \"([^\"]*)\"$")
    public void userShouldBeAbleToCommentOnTheTicket(String comment) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.commentOnTicket(comment);
        if (!testResult) {
            Assert.fail("Failed to comment on the ticket");
            SeleniumLib.takeAScreenShot("failedToCommentOnTicket.jpg");
        }

    }

    @And("^User should be able to logout from Service Desk$")
    public void userShouldBeAbleToLogoutFromServiceDesk() throws IOException {
        boolean testResult = false;
        testResult = serviceDeskPage.logoutFromServicedesk();
        if (!testResult) {
            Assert.fail("Could not logout from Service desk");
            SeleniumLib.takeAScreenShot("failedToLogoutFromServiceDesk.jpg");
        }
    }

    @Given("^User is logged in to the Service Desk portal Home page$")
    public void userIsLoggedInToTheServiceDeskPortalHomePage() throws IOException {
        boolean testResult = false;
        testResult = serviceDeskPage.loginToHomePage();
        if (!testResult) {
            Assert.fail("Could not log into service desk home page");
            SeleniumLib.takeAScreenShot("failedToNavigateLoginToServiceDesk.jpg");
        }
    }

    @And("^User should be able to provide the description \"([^\"]*)\"$")
    public void userShouldBeAbleToProvideTheDescription(String description) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.enterDescription(description);
        if (!testResult) {
            Assert.fail("Failed to enter the Description");
            SeleniumLib.takeAScreenShot("failedToEnterDescription.jpg");
        }
    }

    @And("^User should be able to click on \"([^\"]*)\" workflow$")
    public void userShouldBeAbleToClickOnWorkflow(String workFlow) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.updateTheWorkflow(workFlow);
        if (!testResult) {
            Assert.fail("Incorrect ticket workflow - " + workFlow);
            SeleniumLib.takeAScreenShot("incorrectWorkFlow" + workFlow + ".jpg");
        }
    }

    @And("^User should be able to add comment \"([^\"]*)\" for \"([^\"]*)\" workflow$")
    public void userShouldBeAbleToAddCommentForWorkflow(String comment, String workFlow) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.addComment(comment);
        if (!testResult) {
            Assert.fail("Not able to add the comment for workFlow " + workFlow);
            SeleniumLib.takeAScreenShot("Comment" + workFlow + ".jpg");
        }
    }

    @And("^User should be able to click workFlow Transition Button$")
    public void userShouldBeAbleToClickWorkFlowTransitionButton() throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.clickOnTransitionButton();
        if (!testResult) {
            Assert.fail("Not able to click on Transition Button");
            SeleniumLib.takeAScreenShot("TransitionButton.jpg");
        }
    }

    @And("^User should be able to select the linked issue as \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectTheLinkedIssueAs(String linkedIssue) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.selectLinkedIssue(linkedIssue);
        if (!testResult) {
            Assert.fail("Not able to select the Linked issue " + linkedIssue + " from dropdwn");
            SeleniumLib.takeAScreenShot("LinkedIssue.jpg");
        }
    }

    @And("^User should be able to select the pending reason \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectThePendingReason(String pendingReason) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.selectPendingReason(pendingReason);
        if (!testResult) {
            Assert.fail("Not able to select the Pending reasaon " + pendingReason + " from dropdwn");
            SeleniumLib.takeAScreenShot("PendingReason.jpg");
        }
    }

    @And("^User should be able to select the resolution as \"([^\"]*)\"$")
    public void userShouldBeAbleToSelectTheResolutionAs(String resolution) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.selectResolution(resolution);
        if (!testResult) {
            Assert.fail("Not able to select the resolution " + resolution + " from dropdwn");
            SeleniumLib.takeAScreenShot("Resolution.jpg");
        }
    }

    @And("^User should be able to select \"([^\"]*)\" from workflow dropdown$")
    public void userShouldBeAbleToSelectFromWorkflowDropdown(String workFlow) throws Throwable {
        boolean testResult = false;
        testResult = serviceDeskPage.selectWorkFlow(workFlow);
        if (!testResult) {
            Assert.fail("Not able to select the workFlow " + workFlow + " from dropdwn");
            SeleniumLib.takeAScreenShot("WorkFlow" + workFlow + ".jpg");
        }
    }
}
