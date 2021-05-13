package co.uk.gel.jira.pages;

import co.uk.gel.jira.config.AppConfig;
import co.uk.gel.jira.util.Debugger;
import co.uk.gel.jira.util.TestUtils;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import cucumber.api.java.hu.De;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.io.IOException;
import java.util.List;

public class ConfluencePage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//a[@id='login-link']")
    public WebElement loginlink;

    @FindBy(xpath = "//input[@id='os_username']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='os_password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id='loginButton']")
    public WebElement loginButton;

    @FindBy(xpath = "//a[@id='quick-create-page-button']")
    public WebElement createPageButton;

    @FindBy(xpath = "//input[@id='content-title']")
    public WebElement pageTitle;

    @FindBy(xpath = "//a[@class='all-spaces-link']")
    public WebElement allSpacesLink;


    @FindBy(xpath = "//tr/td[@class='entity-attribute space-name']/a")
    public List<WebElement> spaceDirectoryNames;

    @FindBy(xpath = "//div[@class='ia-secondary-container tipsy-enabled']/div[@class='ia-secondary-content']//li")
    public List<WebElement> pageTreeLinks;

    @FindBy(xpath = "//div[@class='plugin_pagetree_children_container']/ul/li//a")
    public List<WebElement> subPageTreeLink;


    @FindBy(xpath = "//a[@id='editPageLink']")
    public WebElement editPageLink;

    @FindBy(xpath = "//a[@id='rte-button-insert-table']")
    public WebElement insertTableLink;

    @FindBy(xpath = "//a[@id='rte-button-insert-table']/following-sibling::div/div/div/div[4]")
    public WebElement selectTable;

    @FindBy(xpath = "//div[@id='navigation']//a[@id='action-menu-link']")
    public WebElement clickOnPageOptions;

    @FindBy(xpath = "//span[contains(text(),'Export to PDF')]")
    public WebElement exportPDF;

    @FindBy(xpath = "//a[@id='k15t-exp-word-export-dialog-web-item']//span[contains(text(),'Export to Word')]")
    public WebElement exportWord;

    @FindBy(xpath = "//span[contains(text(),'Exporting Word File')]")
    public WebElement exportWordTitle;

    @FindBy(xpath = "//button[@id='dialog-submit-button']")
    public WebElement exportWordButton;

    @FindBy(xpath = "//button[contains(text(),'Ok')]")
    public WebElement exportOkButton;

    @FindBy(xpath = "//button[@class='aui-button aui-button-subtle']/span[@class='icon aui-icon aui-icon-small aui-iconfont-insert-row-before']")
    public WebElement insertRowAboveButton;

    @FindBy(xpath = "//button[@class='aui-button aui-button-subtle']/span[@class='icon aui-icon aui-icon-small aui-iconfont-insert-column-after']")
    public WebElement insertColumnRightButton;


    @FindBy(xpath = "//button[@class='aui-button aui-button-subtle']/span[@class='icon aui-icon aui-icon-small aui-iconfont-remove-table']")
    public WebElement deleteTableButton;

    @FindBy(xpath = "//a[@id='page-addWorkflow']/span[contains(text(),'Add Workflow...')]")
    public WebElement addWorkflowButton;

    @FindBy(xpath = "//a[@id='user-menu-link']")
    public WebElement userOptions;

    @FindBy(xpath = "//a[@id='logout-link']")
    public WebElement logout;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    public WebElement deletePageOptionButton;

    @FindBy(xpath = "//section[@id='delete-page-dialog']")
    public WebElement deletePagePopUp;

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    public WebElement deletePageSectionButton;

    @FindBy(xpath = "//strong[text()='Page deleted']")
    public WebElement pageDeleteInfoMessage;

    @FindBy(xpath = "//strong[text()='Page deleted']/../following-sibling::span")
    public WebElement pageDeleteInfoCrossButton;

    public ConfluencePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    public boolean loginToConfluence() throws IOException {
        try {
            driver.get(AppConfig.confluencePage);
            loginlink.isDisplayed();
            loginlink.click();
            username.sendKeys(AppConfig.confluenceUsername);
            password.sendKeys((AppConfig.confluencePassword));
            loginButton.click();
            Wait.seconds(3);
            //Verify whether Login is successful and user can see the Create button
            if (!createPageButton.isDisplayed()) {
                Wait.seconds(3);
                Debugger.println("The confluence home page not loaded successfully. Create button not present ");
            }
            Debugger.println("User is logged into the confluence page successfully");
            return true;

        } catch (Exception exp) {
            Debugger.println("EXCEPTION: Confluence page Login Failed. Check whether site is up and the credentials are right." + exp);
            SeleniumLib.takeAScreenShot("failedToLogin.jpg");
            return false;
        }
    }


    public boolean createConfluencePageTitle(String title) throws IOException {
        try {
            Wait.seconds(2);
            pageTitle.isDisplayed();
            int randomNumber = seleniumLib.getRandomNumber();
            title = title + "_" + randomNumber;
            pageTitle.sendKeys(title);
            Debugger.println("Page Title is " + title);
            pageTitle.sendKeys(Keys.ENTER);
            return true;
        } catch (Exception exp) {
            Debugger.println("Page Title not found" + exp);
            SeleniumLib.takeAScreenShot("titleNotFound.jpg");
            return false;
        }
    }

    public boolean allSpaces() throws IOException {
        try {
            allSpacesLink.isDisplayed();
            allSpacesLink.click();
            Debugger.println("Clicked on the All spaces link");
            return true;
        } catch (Exception exp) {
            Debugger.println("All spaces link not found" + exp);
            SeleniumLib.takeAScreenShot("allSpacesLinkNotFound.jpg");
            return false;
        }
    }

    public boolean clickOnTheSpaceDirectory(String spaceDirectory) throws IOException {

        try {
            By nextButton = By.xpath("//a[contains(text(),'Next')]");
            By spaceDirectoryLink = By.xpath("(//a[contains(text(),'" + spaceDirectory + "')])[1]");
            boolean isPresent = false;
            for (int i = 0; i < spaceDirectoryNames.size(); i++) {
                String directoryName = spaceDirectoryNames.get(i).getText();
                if (directoryName.equalsIgnoreCase(spaceDirectory)) {
                    Wait.seconds(2);
                    seleniumLib.clickOnWebElement(spaceDirectoryNames.get(i));
                    Wait.seconds(3);
                    Debugger.println("Selected space directory is " + directoryName);
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                seleniumLib.clickOnElement(nextButton);
                seleniumLib.clickOnElement(spaceDirectoryLink);
                Debugger.println("Selected space directory is " + spaceDirectory);
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Space directory not found" + exp);
            SeleniumLib.takeAScreenShot("spaceDirectoryNotFound.jpg");
            return false;
        }
    }

    public boolean clickOnThePageTree(String pageTree) throws IOException {
        try {
            boolean isPresent = false;
            for (int i = 0; i < pageTreeLinks.size(); i++) {
                String pageTreeName = pageTreeLinks.get(i).getText();
                if (pageTreeName.equalsIgnoreCase(pageTree)) {
//                    pageTreeLinks.get(i).click();

//                    seleniumLib.moveMouseAndClickOnWebElement(pageTreeLinks.get(i));
//                    seleniumLib.clickOnWebElement(pageTreeLinks.get(i));
                    seleniumLib.clickonLink(pageTreeName);
                    Wait.seconds(3);
                    Debugger.println("Selected page tree is " + pageTreeName);
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Page Tree Not Found");
                return false;

            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Page tree link not found" + exp);
            SeleniumLib.takeAScreenShot("pageTreeNotFound.jpg");
            return false;
        }

    }

    public boolean clickOnTheCreatePageButton() throws IOException {
        try {
            createPageButton.click();
            Wait.seconds(5);
            pageTitle.isDisplayed();
            return true;
        } catch (Exception exp) {
            Debugger.println("Create a blank page button not found" + exp);
            SeleniumLib.takeAScreenShot("createPageButtonNotFound.jpg");
            return false;
        }
    }

    public boolean enterThePageDescription(String description) throws IOException {
        try {
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='wysiwygTextarea_ifr']")));
            By pageDescription = By.xpath("//body[@id='tinymce']/p");
            seleniumLib.clickOnElement(pageDescription);
            seleniumLib.sendValue(pageDescription, description);
            seleniumLib.moveMouseAndClickOnElement(pageDescription);

            Wait.seconds(3);
            driver.switchTo().parentFrame();
            driver.switchTo().defaultContent();
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to enter the description" + exp);
            SeleniumLib.takeAScreenShot("pageDescriptionFound.jpg");
            return false;
        }
    }

    public boolean saveThePage() throws IOException {
        try {

            By savePageButton = By.xpath("//button[@name='confirm']");

            seleniumLib.isElementPresent(savePageButton);
            seleniumLib.clickOnElement(savePageButton);

            Wait.seconds(5);
            Debugger.println("The page has been saved successfully");
            return true;
        } catch (Exception exp) {
            Debugger.println("Publish/Update button not found" + exp);
            SeleniumLib.takeAScreenShot("saveButtonNotFound.jpg");
            return false;

        }
    }

    public boolean selectTheSubPage(String subPageName) throws IOException {
        try {
            Wait.seconds(3);
            boolean isPresent = false;
            for (int i = 0; i < subPageTreeLink.size(); i++) {
                String subPageTreeName = subPageTreeLink.get(i).getText();
                if (subPageTreeName.equalsIgnoreCase(subPageName)) {
                    Wait.seconds(3);
                    subPageTreeLink.get(i).isDisplayed();
                    subPageTreeLink.get(i).click();
//                    seleniumLib.clickOnWebElement(subPageTreeLink.get(i));
                    Wait.seconds(3);
                    Debugger.println("Selected sub page tree is " + subPageTreeName);
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
//                By pageTree = By.xpath("//div[@class='ia-secondary-content']//a[contains(text(),'"+treeName+"')]");
                Debugger.println("Sub Page Not Found");
                return false;

            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Sub Page tree link not found" + exp);
            SeleniumLib.takeAScreenShot("subPageTreeNotFound.jpg");
            return false;
        }
    }

    public boolean clickOnTheEditPageLink() throws IOException {
        try {
            editPageLink.isDisplayed();
            Wait.seconds(2);
            editPageLink.click();
            Wait.seconds(5);
            Debugger.println("Edit link has been clicked");
            return true;
        } catch (Exception exp) {
            Debugger.println("Edit page link not found" + exp);
            SeleniumLib.takeAScreenShot("editPageLinkNotFound.jpg");
            return false;
        }
    }

    public boolean insertTable() throws IOException {
        try {
            insertTableLink.isDisplayed();
            insertTableLink.click();
            Wait.seconds(2);
            selectTable.click();
            insertRowAboveButton.click();
            insertRowAboveButton.click();
            Wait.seconds(3);
            insertColumnRightButton.click();
            insertColumnRightButton.click();
            return true;
        } catch (Exception exp) {
            Debugger.println("Table is not inserted" + exp);
            SeleniumLib.takeAScreenShot("failedToInsertTable.jpg");
            return false;
        }
    }

    public boolean clickOnThePageOptions() throws IOException {
        try {
            clickOnPageOptions.isDisplayed();
            clickOnPageOptions.click();
            Wait.seconds(3);
            Debugger.println("The page options are displayed");
            return true;
        } catch (Exception exp) {
            Debugger.println("The page options are not displayed" + exp);
            SeleniumLib.takeAScreenShot("failedToDisplayOptions.jpg");
            return false;
        }
    }

    public boolean exportToPDF() throws IOException {
        try {

            String title = pageTitle.getAttribute("value");
            Debugger.println("The title is " + title);
            TestUtils.deleteIfFilePresent(title, "");

            if (!exportPDF.isDisplayed()) {
                Debugger.println("The export PDF option is not displayed");
                SeleniumLib.takeAScreenShot("ExportPDFNotFound.jpg");
                return false;
            }
            exportPDF.click();
            Wait.seconds(15);//Wait for 15 seconds to ensure file got downloaded, large file taking time to download
            Debugger.println("The exported PDF contains the title " + title);
            return true;
//            exportPDF.isDisplayed();
//            exportPDF.click();

        } catch (Exception exp) {
            Debugger.println("Failed to export to PDF" + exp);
            SeleniumLib.takeAScreenShot("failedToExportToPDF.jpg");
            return false;
        }

    }

    public boolean exportToWord() throws IOException {

        try {
            String title = pageTitle.getAttribute("value");
            Debugger.println("The title is " + title);
            TestUtils.deleteIfFilePresent(title, "");

            exportWord.isDisplayed();
            exportWord.click();
            Wait.seconds(10);
            By exportWindow = By.xpath("//iframe[@class='spark-fullscreen-iframe']");
            driver.switchTo().frame(driver.findElement(exportWindow));


            if (!exportWordTitle.isDisplayed()) {
                Debugger.println("The export to word window is not opened");
                return false;
            } else {
                exportWordButton.click();
                Wait.seconds(10);
                Debugger.println("The export button is clicked");
                exportOkButton.isDisplayed();
                exportOkButton.click();
                Debugger.println("The Ok button is clicked");
                Wait.seconds(5);
                driver.switchTo().parentFrame();
                driver.switchTo().defaultContent();
                return true;
            }
        } catch (Exception exp) {
            Debugger.println("Failed to export to Word document" + exp);
            SeleniumLib.takeAScreenShot("failedToExportToWord.jpg");
            return false;
        }

    }

    public boolean deleteTheTable() throws IOException {
        try {
            By table = By.xpath("//iframe[@id='wysiwygTextarea_ifr']");
            driver.switchTo().frame(driver.findElement(table));
            //table[@class='confluenceTable wrapped']/tbody/tr[1]
            //table[@class='confluenceTable']/tbody/tr[1]/td[1]
            //body[@id='tinymce']/table/tbody/tr[1]/th[1]
            By displayedTable = By.xpath("//body[@id='tinymce']/table/tbody/tr[1]");

            seleniumLib.isElementPresent(displayedTable);
            seleniumLib.clickOnElement(displayedTable);


            Wait.seconds(2);
            driver.switchTo().parentFrame();
            driver.switchTo().defaultContent();
            deleteTableButton.click();
            Wait.seconds(2);
            if (!seleniumLib.isElementPresent(displayedTable)) {
                Debugger.println("The table has been deleted");
                return true;
            } else {
                Debugger.println("The table is not deleted");
                return false;
            }

        } catch (Exception exp) {
            Debugger.println("Failed to delete the table" + exp);
            SeleniumLib.takeAScreenShot("failedToDeleteTable.jpg");
            return false;
        }
    }

    public boolean addWorkflow() throws IOException {
        try {
            addWorkflowButton.click();
            Wait.seconds(2);
            By workflowWindow = By.xpath("//iframe[@id='cw-addWorkflowDialog_content_iframe']");
            driver.switchTo().frame(driver.findElement(workflowWindow));
            By displayedWorkflowName = By.xpath("//div[@class='WorkflowList_workflowsListContainer__u_26E']/div/section/div[1]/h5[contains(text(),'Simple approval workflow')]");
            By workflowWindowApplyButton = By.xpath("//div[@class='css-vxcmzt']/div/button[@class='css-1l4j2co']");
            By successMessage = By.xpath("//div[@id='aui-flag-container']//div[@class='aui-message closeable aui-message-success aui-will-close']");


            seleniumLib.isElementPresent(displayedWorkflowName);
            seleniumLib.clickOnElement(workflowWindowApplyButton);
            driver.switchTo().parentFrame();
            driver.switchTo().defaultContent();
            Wait.seconds(3);
            seleniumLib.isElementPresent(successMessage);
            String displayedMessage = seleniumLib.getText(successMessage);
            Debugger.println("The success message is " + displayedMessage);
            return true;

        } catch (Exception exp) {
            Debugger.println("Failed to add a simple workflow" + exp);
            SeleniumLib.takeAScreenShot("failedToAddWorkflow.jpg");
            return false;
        }
    }

    public boolean workflowStatus(String workflowStatus) throws IOException {
        try {
            By workflowStatusLink = By.xpath("//span[@class='cw-byline__description']/a[contains(text(),'" + workflowStatus + "')]");
            seleniumLib.isElementPresent(workflowStatusLink);
            String status = seleniumLib.getText(workflowStatusLink);
            Debugger.println("The status is " + status);
            seleniumLib.clickOnElement(workflowStatusLink);
            return true;
        } catch (Exception exp) {
            Debugger.println("Failed to see the workflow status" + exp);
            SeleniumLib.takeAScreenShot("failedToSeeWorkflowStatus.jpg");
            return false;
        }
    }

    public boolean changeWorkflowStatus(String changeStatus) throws IOException {
        try {
            By reviewWindow = By.xpath("//iframe[@id='cw-state-byline_dialog_iframe_iframe']");
            driver.switchTo().frame(driver.findElement(reviewWindow));
            By newStatus = By.xpath("//div[@class='index_buttons__3O9P8']//span[contains(text(),'" + changeStatus + "')]");
            seleniumLib.isElementPresent(newStatus);
            seleniumLib.clickOnElement(newStatus);
            Wait.seconds(2);
            driver.switchTo().parentFrame();
            driver.switchTo().defaultContent();
            return true;

        } catch (Exception exp) {
            Debugger.println("Failed to change the workflow status" + exp);
            SeleniumLib.takeAScreenShot("failedToChangeWorkflowStatus.jpg");
            return false;
        }
    }

    public boolean logoutFromConfluence() {
        try {
          userOptions.isDisplayed();
          userOptions.click();
          Wait.seconds(2);
          logout.isDisplayed();
          logout.click();
            Debugger.println("Logged out successfully");
            return true;

        } catch (Exception exp) {
            Debugger.println("Failed to logout");
            return false;
        }
    }

    public boolean deletePage() {
        try {
            if (!deletePageOptionButton.isDisplayed()) {
                Debugger.println("Delete button in page options is not present.");
                SeleniumLib.takeAScreenShot("DeleteButton.jpg");
                return false;
            }
            deletePageOptionButton.click();
            Debugger.println("page section Delete button selected successfully");
            if (!deletePagePopUp.isDisplayed()){
                Debugger.println("Delete page pop up section is not present.");
                SeleniumLib.takeAScreenShot("DeletePageSection.jpg");
                return false;
            }
            deletePageSectionButton.click();
            if (pageDeleteInfoMessage.isDisplayed()) {
                Debugger.println("page deleted successfully");
            }
            pageDeleteInfoCrossButton.click();
             return true;
        } catch (Exception exp) {
            Debugger.println("Failed to delete the created page");
            return false;
        }
    }
}