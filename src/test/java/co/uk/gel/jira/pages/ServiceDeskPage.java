package co.uk.gel.jira.pages;


import co.uk.gel.jira.config.AppConfig;
import co.uk.gel.jira.util.Debugger;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//div[@id='rw_my_menu']//a[@class='rw_profile_link rw_has_item_count rw_inline_dialog_button']")
    public WebElement profileLink;

    @FindBy(xpath = "//a[@class='rw_logout_button']")
    public WebElement logout;

    @FindBy(xpath = "//a[@class='rw_login_button']")
    public WebElement loginLink;

    @FindBy(xpath = "//input[@id='os_username']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='os_password']")
    public WebElement password;

    @FindBy(xpath = "//button[@id='js-login-submit']")
    public WebElement loginButton;

    public ServiceDeskPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean loginToHomePage() throws IOException {
        try {
            driver.get(AppConfig.serviceDeskHomePageURL);
            Wait.seconds(3);
            loginLink.isDisplayed();
            Debugger.println("The service desk home page URL is accessed");
            loginLink.click();
            Wait.seconds(3);
            username.sendKeys(AppConfig.serviceDeskUsername);
            password.sendKeys((AppConfig.serviceDeskPassword));
            loginButton.click();
            Wait.seconds(10);
            //Verify whether Login is successful and user can see the User profile link
            By menuOptions = By.xpath("//div[@id='rw_my_menu']");

            seleniumLib.scrollToElement(driver.findElement(menuOptions));
            By profileLink = By.xpath("//div[@id='rw_my_menu']//li[2]");
            WebElement profileLink1 = driver.findElement(profileLink);
            Wait.forElementToBeDisplayed(driver,profileLink1,30);

            if (!seleniumLib.isElementPresent(profileLink)) {

                Wait.seconds(5);
                Debugger.println("The service desk portal home page not loaded successfully. User profile link not found ");
                return false;
            }
                Debugger.println("User is logged into the service desk portal home page successfully");
                return true;

        } catch (Exception exp) {
            Debugger.println("EXCEPTION: Service desk portal home page Login Failed. Check whether site is up and the credentials are right." + exp);
            SeleniumLib.takeAScreenShot("failedToLogin.jpg");
            return false;
        }
    }



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
            By profileLink = By.xpath("//div[@id='rw_my_menu']//li[2]");
           seleniumLib.clickOnElement(profileLink);
           Wait.seconds(2);
            logout.click();
            Debugger.println("Logged out successfully");
            return true;

        } catch (Exception exp) {
            Debugger.println("Failed to logout");
            return false;
        }
    }

}