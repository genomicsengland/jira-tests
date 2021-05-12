package co.uk.gel.jira.pages;

import co.uk.gel.jira.config.AppConfig;
import co.uk.gel.jira.util.Debugger;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class JiraPage {
    WebDriver driver;
    SeleniumLib seleniumLib;
    private By username = By.xpath("//input[@id='login-form-username']");
    private By password = By.xpath("//input[@id='login-form-password']");
    private By signIn = By.xpath("//input[@id='login']");

    private By userOptions = By.xpath("//li[@id='user-options']/a");
    private By logout = By.xpath("//a[@id='log_out']");
    private By create = By.xpath("//li[@id='create-menu']");

    @FindBy(xpath = "//input[@id='project-field']")
    public WebElement selectProjectDropDownValue;

    @FindBy(xpath = "//input[@id='issuetype-field']")
    public WebElement selectIssueTypeDropDownValue;

    @FindBy(xpath = "//input[@id='summary']")
    public WebElement summary;

    @FindBy(xpath = "//select[@id='customfield_13407']")
    public WebElement application;

    @FindBy(xpath = "//select[@id='customfield_12600']")
    public WebElement workstream;

    @FindBy(xpath = "//textarea[@id='description']")
    public WebElement description;

    @FindBy(xpath = "//input[@id='priority-field']")
    public WebElement priority;

    @FindBy(xpath = "//input[@id='assignee-field']")
    public WebElement assignee;

    @FindBy(xpath = "//input[@id='create-issue-submit']")
    public WebElement submitTheTicket;

    @FindBy(xpath = "//input[@id='quickSearchInput']")
    public WebElement searchTheTicket;

    @FindBy(xpath = "//a[@class='issue-created-key issue-link']")
    public WebElement accessTheTicket;

    @FindBy(xpath = "//span[@id='status-val']")
    public WebElement ticketStatus;

    @FindBy(xpath = "(//div[@id='unfreezedGridHeader'])[2]")
    public WebElement testExecutionPlaceHolder;

    @FindBy(xpath = "//h4[contains(text(),'Test Executions')]")
    public WebElement testExecutionTitle;

    @FindBy(xpath = "//span[contains(text(),'Workflow')]")
    public WebElement workFlowDropDown;


    private By createIssueTitle = By.xpath("//h2[contains(text(),'Create Issue')]");

    public JiraPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean loginToJira() {
        try {
            driver.get(AppConfig.systemDashboard);
            if (!seleniumLib.isElementPresent(username)) {
                By loginOption = By.xpath("//li[@id='user-options']/a[contains(text(),'Log In')]");
                if (seleniumLib.isElementPresent(loginOption)) {
                    seleniumLib.clickOnElement(loginOption);
                    SeleniumLib.sleepInSeconds(3);
                }
            }
            seleniumLib.sendValue(username, AppConfig.jiraUsername);
            seleniumLib.sendValue(password, AppConfig.jiraPassword);
            seleniumLib.clickOnElement(signIn);
            SeleniumLib.sleep(8);
            //Verify whether Login is successful and user can see the Projects Tab
            By projectLink = By.xpath(".//*[@id='browse_link']");
            if (!seleniumLib.isElementPresent(projectLink)) {
                SeleniumLib.sleepInSeconds(8);
                if (!seleniumLib.isElementPresent(projectLink)) {
                    Debugger.println("JIRA home page not loaded successfully. Project tab not present.");
                }
            }
            Debugger.println("User is logged into the System Dashboard successfully");
            return true;

        } catch (Exception exp) {
            Debugger.println("EXCEPTION: Jira Login Failed. Check whether site is up and the credentials are right.\nPlease do a manual login/logout and check for the message: You are now logged out. Any automatic login has also been stopped. Sometimes login from program is disabled from JIRA. Close if any open Firefox and then try again." + exp);
            return false;
        }

    }

    public boolean logoutFromJira() {
        try {
            seleniumLib.clickOnElement(userOptions);
            seleniumLib.clickOnElement(logout);
            Debugger.println("Logged out successfully");
            return true;

        } catch (Exception exp) {
            Debugger.println("Failed to logout");
            return false;
        }
    }

    public boolean clickOnCreateButton() throws IOException {
        try {
            seleniumLib.clickOnElement(create);
            SeleniumLib.sleepInSeconds(8);
            seleniumLib.isElementPresent(createIssueTitle);
            Debugger.println("The Create Issue window has opened");
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to open the create issue window");
            SeleniumLib.takeAScreenShot("createIssueWindowNotOpened.jpg");
            return false;
        }
    }

    public boolean selectDropDownProjectName(String value) throws IOException {
        try {

            selectProjectDropDownValue.click();
            selectProjectDropDownValue.sendKeys(Keys.BACK_SPACE);
            Wait.seconds(5);
            selectProjectDropDownValue.sendKeys(value);
            selectProjectDropDownValue.sendKeys(Keys.ENTER);
            Wait.seconds(5);
            System.out.println("the drop down value is selected as " + value);
            Debugger.println("The Project value is selected");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selectProjectDropDownValue: " + exp);
            SeleniumLib.takeAScreenShot("projectDropDown.jpg");
            return false;

        }
    }

    public boolean selectDropDownIssueType(String value) throws IOException {
        try {

            selectIssueTypeDropDownValue.click();
            selectIssueTypeDropDownValue.sendKeys(Keys.BACK_SPACE);
            Wait.seconds(5);
            selectIssueTypeDropDownValue.sendKeys(value);
            selectIssueTypeDropDownValue.sendKeys(Keys.ENTER);
            Wait.seconds(5);
            System.out.println("the drop down value is selected as " + value);
            Debugger.println("The Issue type is selected");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selectIssueTypeDropDownValue: " + exp);
            SeleniumLib.takeAScreenShot("issueTypeDropDown.jpg");
            return false;

        }
    }

    public boolean enterTheSummary(String value) throws IOException {
        try {
            Wait.seconds(2);
            int randomNumber = seleniumLib.getRandomNumber();
            value = value + "_" + randomNumber;
            summary.sendKeys(value);
            Wait.seconds(2);
            System.out.println("the summary of the ticket is " + value);
            Debugger.println("The Summary is provided as " + value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in enteringTheSummary: " + exp);
            SeleniumLib.takeAScreenShot("summary.jpg");
            return false;

        }

    }

    public boolean selectTheApplication(String value) throws IOException {
        try {
            application.isSelected();
            application.click();
            application.sendKeys(value);
            application.sendKeys(Keys.ENTER);
            Wait.seconds(5);
            System.out.println("the application is selected as " + value);
            Debugger.println("The application is provided");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selectingTheApplication: " + exp);
            SeleniumLib.takeAScreenShot("selectApplication.jpg");
            return false;
        }
    }

    public boolean selectTheWorkstream(String value) throws IOException {
        try {
            workstream.isSelected();
            workstream.click();
            workstream.sendKeys(value);
            workstream.sendKeys(Keys.ENTER);
            Wait.seconds(5);
            System.out.println("the workstream is selected as " + value);
            Debugger.println("The workstream is provided");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selectingTheWorkstream: " + exp);
            SeleniumLib.takeAScreenShot("selectWorkstream.jpg");
            return false;
        }

    }


    public boolean enterTheDescription(String value) throws IOException {
        try {
            Wait.seconds(5);
            description.sendKeys(value);
            Wait.seconds(5);
            System.out.println("the description of the ticket is " + value);
            Debugger.println("The Description is provided");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in entering The Description: " + exp);
            SeleniumLib.takeAScreenShot("description.jpg");
            return false;
        }
    }

    public boolean selectPriority(String value) throws IOException {
        try {
            priority.click();
            priority.sendKeys(Keys.BACK_SPACE);
            Wait.seconds(5);
            priority.sendKeys(value);
            priority.sendKeys(Keys.ENTER);
            Wait.seconds(5);
            System.out.println("the priority drop down value is selected as " + value);
            Debugger.println("The priority value is selected");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selectPriorityDropDownValue: " + exp);
            SeleniumLib.takeAScreenShot("priority.jpg");
            return false;
        }
    }

    public boolean selectAssignee(String value) throws IOException {
        try {

            assignee.click();
            assignee.sendKeys(Keys.BACK_SPACE);
            Wait.seconds(5);
            assignee.sendKeys(value);
            Wait.seconds(5);
            assignee.sendKeys(Keys.ENTER);
            Wait.seconds(5);
            System.out.println("the assignee drop down value is selected as " + value);
            Debugger.println("The assignee is selected");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selectAssigneeDropDownValue: " + exp);
            SeleniumLib.takeAScreenShot("assignee.jpg");
            return false;
        }
    }

    public boolean submitTicket() throws IOException {
        try {
            submitTheTicket.click();
            Wait.seconds(3);
            Debugger.println("The ticket has been submitted");
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to submit the ticket");
            SeleniumLib.takeAScreenShot("submitTicket.jpg");
            return false;
        }
    }

    public boolean accessTicket() throws IOException {
        try {
            if (seleniumLib.isElementPresent(accessTheTicket)) {
                System.out.println("The element is  displayed ");
            }
            String ticketID = accessTheTicket.getText();
            Debugger.println("The ticket ID is " + ticketID);
            accessTheTicket.click();
            Wait.seconds(5);
            Debugger.println("The ticket has been accessed");
            String[] linkText = ticketID.split(" ");
            String ticketId = linkText[0];
            Debugger.println("The ticket Id is " + ticketId);

            String ticketURL = driver.getCurrentUrl();
            if (!ticketURL.contains(ticketId)) {
                Debugger.println("URL does not contains the ticket ID");
                SeleniumLib.takeAScreenShot("IDinURLNotExist.jpg");
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to access the ticket");
            SeleniumLib.takeAScreenShot("IDinURLNotExist.jpg");
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


    public boolean updateTheWorkflow(String workflow) throws IOException {
        try {
            if (seleniumLib.isElementPresent(workFlowDropDown)) {
                workFlowDropDown.click();
                By workflowButton = By.xpath("//span[contains(text(),'" + workflow + "')]");
                WebElement workflowButton1 = driver.findElement(workflowButton);
                workflowButton1.click();
            }
            else{
                By workflowButton = By.xpath("//span[contains(text(),'" + workflow + "')]");
                WebElement workflowButton1 = driver.findElement(workflowButton);
                workflowButton1.click();
            }
            Wait.seconds(3);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to check the ticket status");
            SeleniumLib.takeAScreenShot("ticketStatus.jpg");
            return false;
        }
    }

    public boolean testExecution(String options) {
        try {
            testExecutionTitle.isDisplayed();
            String columnNames = testExecutionPlaceHolder.getText();
            String[] optionName = columnNames.split("\n");
            Wait.seconds(2);
            boolean isPresent = false;
            for (int i = 0; i < optionName.length; i++) {
                Debugger.println("The column names are " + optionName[i]);
                if (optionName[i].equalsIgnoreCase(options)) {
                    Debugger.println("The column names are matching with the provided options");
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("The options are not matching");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to check the test execution column names");
            return false;
        }
    }

    public boolean ticketLink() {
        try {
            driver.get(AppConfig.ticketLink);
            Wait.seconds(2);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to check the ticket link");
            return false;
        }
    }
}//end class


