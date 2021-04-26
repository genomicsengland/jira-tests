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
}
