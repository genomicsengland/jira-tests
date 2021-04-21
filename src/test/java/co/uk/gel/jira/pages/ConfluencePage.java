package co.uk.gel.jira.pages;

import co.uk.gel.jira.config.AppConfig;
import co.uk.gel.jira.util.Debugger;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import gherkin.lexer.De;
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

    @FindBy(xpath = "//a[contains(text(),'All')]")
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


    public boolean createConfluencePageTitle(String value) throws IOException {
        try {
            Wait.seconds(2);
            pageTitle.isDisplayed();
            pageTitle.sendKeys(value);
            Debugger.println("Page Title is " + value);
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
            seleniumLib.sendValue(pageDescription,description);
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
            for (int i = 0; i <subPageTreeLink.size(); i++) {
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
try{
    insertTableLink.isDisplayed();
    insertTableLink.click();
    Wait.seconds(2);
    selectTable.click();
    Wait.seconds(3);
    return true;
}catch (Exception exp) {
    Debugger.println("Table is not inserted" + exp);
    SeleniumLib.takeAScreenShot("failedToInsertTable.jpg");
    return false;
}
    }
}