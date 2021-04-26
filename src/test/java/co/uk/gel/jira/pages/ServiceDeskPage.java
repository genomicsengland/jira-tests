package co.uk.gel.jira.pages;


import co.uk.gel.jira.config.AppConfig;
import co.uk.gel.jira.util.Debugger;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class ServiceDeskPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//div[@class='rw_table_cell rw_request_secondary_column']/div[@class='rw_request_status_container']/span")
    public WebElement ticketStatus;

    @FindBy(xpath = "//div[@class='rw_comment_editor']/textarea")
    public WebElement commentSection;

    @FindBy(xpath = "//div[@class='rw_comment_editor rw_active']/textarea")
    public WebElement inputComment;

    @FindBy(xpath = "//button[@class='aui-button aui-button-primary rw_themed_button rw_add_comment_button']")
    public WebElement addCommentButton;

    @FindBy(xpath = "//a[@class='rw_profile_link rw_has_item_count rw_inline_dialog_button']")
    public WebElement profileLink;

    @FindBy(xpath = "//a[@class='rw_logout_button']")
    public WebElement logout;





//    public boolean loginToServiceDesk() {
//        try {
//            driver.get(AppConfig.systemDashboard);
//            if (!seleniumLib.isElementPresent(username)) {
//                By loginOption = By.xpath("//li[@id='user-options']/a[contains(text(),'Log In')]");
//                if (seleniumLib.isElementPresent(loginOption)) {
//                    seleniumLib.clickOnElement(loginOption);
//                    SeleniumLib.sleepInSeconds(3);
//                }
//            }
//            seleniumLib.sendValue(username, AppConfig.jiraUsername);
//            seleniumLib.sendValue(password, AppConfig.jiraPassword);
//            seleniumLib.clickOnElement(signIn);
//            SeleniumLib.sleep(8);
//            //Verify whether Login is successful and user can see the Projects Tab
//            By projectLink = By.xpath(".//*[@id='browse_link']");
//            if (!seleniumLib.isElementPresent(projectLink)) {
//                SeleniumLib.sleepInSeconds(8);
//                if (!seleniumLib.isElementPresent(projectLink)) {
//                    Debugger.println("JIRA home page not loaded successfully. Project tab not present.");
//                }
//            }
//            Debugger.println("User is logged into the System Dashboard successfully");
//            return true;
//
//        } catch (Exception exp) {
//            Debugger.println("EXCEPTION: Jira Login Failed. Check whether site is up and the credentials are right.\nPlease do a manual login/logout and check for the message: You are now logged out. Any automatic login has also been stopped. Sometimes login from program is disabled from JIRA. Close if any open Firefox and then try again." + exp);
//            return false;
//        }

//    }
//    }


    public boolean ticketStatus(String expectedStatus) throws IOException {
        try {
            String actualStatus = ticketStatus.getText();
            Wait.seconds(2);
            if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
                Debugger.println("The actual status is not matching with the expected status");
                SeleniumLib.takeAScreenShot("actualAndExpectedStatusMismatch.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to check the ticket status");
            SeleniumLib.takeAScreenShot("actualAndExpectedStatusMismatch.jpg");
            return false;
        }
    }

    public boolean commentOnTicket(String comment) throws IOException {
        try {
            commentSection.isDisplayed();
            commentSection.click();
            Wait.seconds(2);
            inputComment.sendKeys(comment);
            addCommentButton.click();
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to add the comment");
            SeleniumLib.takeAScreenShot("commentNotAdded.jpg");
            return false;
        }


    }

    public boolean logoutFromServicedesk() {
        try {
           profileLink.click();
            logout.click();
            Debugger.println("Logged out successfully");
            return true;

        } catch (Exception exp) {
            Debugger.println("Failed to logout");
            return false;
        }
    }
}