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
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class ServiceDeskPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//div[@class='rw_table_cell rw_request_secondary_column']/div[@class='rw_request_status_container']/span")
    public WebElement ticketStatus;

    @FindBy(xpath = "//body[@id='tinymce']")
    public WebElement workFlowCommentSection;

    @FindBy(xpath = "//textarea[@id='sd-comment-collapsed-textarea']")
    public WebElement commentSection;

    @FindBy(xpath = "//body[@id='tinymce']")
    public WebElement inputComment;

    @FindBy(xpath = "//button[text()='Comment internally']")
    public WebElement addCommentButton;

    @FindBy(xpath = "//div[@id='rw_my_menu']//a[@class='rw_profile_link rw_has_item_count rw_inline_dialog_button']")
    public WebElement profileLink;

    @FindBy(xpath = "//a[@id='log_out']")
    public WebElement logout;

    @FindBy(xpath = "//a[text()='Log In']")
    public WebElement loginLink;

    @FindBy(xpath = "//input[@id='login-form-username']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='login-form-password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id='login-form-submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//body[@id='tinymce']")
    public WebElement description;

    @FindBy(xpath = "//span[text()='Workflow']")
    public WebElement workFlowDropDown;

    @FindBy(xpath = "//a[text()='Internal comment']/..")
    public WebElement internalComment;

    @FindBy(xpath = "//input[@id='issue-workflow-transition-submit']")
    public WebElement workFlowTransitionButton;

    @FindBy(xpath = "//select[@id='resolution']")
    public WebElement resolutionField;

    @FindBy(xpath = "//select[@id='issuelinks-linktype']")
    public WebElement linkedIssueField;

    @FindBy(xpath = "//select[@id='customfield_10672']")
    public WebElement pendingReasonField;

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
            //Verify whether Login is successful and user can see the dashboard
            By dashBoardTitle = By.xpath("//h1[text()='Browse projects']");
            if (!seleniumLib.isElementPresent(dashBoardTitle)) {

                Wait.seconds(5);
                Debugger.println("The service desk portal home page not loaded successfully. Dashboard title not found ");
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
            commentSection.click();
            Wait.seconds(2);
            driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
            inputComment.sendKeys(comment);
            driver.switchTo().parentFrame();
            driver.switchTo().defaultContent();
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
            By profileLink = By.xpath("//li[@id='user-options']");
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

    public boolean enterDescription(String value) throws Exception {
        try {
            driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
            description.click();
            description.sendKeys(value);
            Wait.seconds(5);
            System.out.println("the description of the ticket is " + value);
            Debugger.println("The Description is provided");
            driver.switchTo().parentFrame();
            driver.switchTo().defaultContent();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in entering The Description: " + exp);
            SeleniumLib.takeAScreenShot("description.jpg");
            return false;
        }
    }

    public boolean updateTheWorkflow(String workFlow) throws IOException {
        try {
            By workflowButton = By.xpath("//span[contains(text(),'" + workFlow + "')]");
            WebElement workflowButton1 = driver.findElement(workflowButton);
            workflowButton1.click();
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to click on the " + workFlow + " workflow");
            SeleniumLib.takeAScreenShot("WorkFlow" + workFlow + ".jpg");
            return false;
        }
    }

    public boolean addComment(String comment) throws IOException {
        try {
            seleniumLib.clickOnWebElement(internalComment);
            driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
            workFlowCommentSection.click();
            workFlowCommentSection.sendKeys(comment);
            driver.switchTo().parentFrame();
            driver.switchTo().defaultContent();
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to add the comment");
            SeleniumLib.takeAScreenShot("Comment.jpg");
            return false;
        }
    }

    public boolean clickOnTransitionButton() throws IOException {
        try {
            workFlowTransitionButton.click();
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to Click on workFlow Transition Button");
            SeleniumLib.takeAScreenShot("WorkFlowTransitionButton.jpg");
            return false;
        }
    }

    public boolean selectLinkedIssue(String linkedIssue) throws IOException {
        try {
            seleniumLib.clickOnWebElement(linkedIssueField);
            seleniumLib.selectFromListByText(By.xpath("//select[@id='issuelinks-linktype']"), linkedIssue);
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to select the " + linkedIssue + " linked issue ");
            SeleniumLib.takeAScreenShot("LinkedIssue.jpg");
            return false;
        }
    }

    public boolean selectPendingReason(String pendingReason) throws IOException {
        try {
            seleniumLib.clickOnWebElement(pendingReasonField);
            seleniumLib.selectFromListByText(By.xpath("//select[@id='customfield_10672']"), pendingReason);
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to select the " + pendingReason + " pending reason");
            SeleniumLib.takeAScreenShot("PendingReason.jpg");
            return false;
        }
    }

    public boolean selectResolution(String resolution) throws IOException {
        try {
            seleniumLib.clickOnWebElement(resolutionField);
            seleniumLib.selectFromListByText(By.xpath("//select[@id='resolution']"), resolution);
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to select the " + resolution + " Resolution");
            SeleniumLib.takeAScreenShot("Resolution.jpg");
            return false;
        }
    }

    public boolean selectWorkFlow(String workFlow) throws IOException {
        try {
            seleniumLib.clickOnWebElement(workFlowDropDown);
            By workflowButton = By.xpath("//span[contains(text(),'" + workFlow + "')]");
            WebElement workflowButton1 = driver.findElement(workflowButton);
            workflowButton1.click();
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to select the " + workFlow + " WorkFlow");
            SeleniumLib.takeAScreenShot("WorkFlow.jpg");
            return false;
        }
    }
}