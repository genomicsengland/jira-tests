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


    public ConfluencePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    public boolean loginToConfluence() {
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
            return false;
        }
    }


    public boolean createConfluencePageTitle(String value) {
        try{
            createPageButton.click();
            pageTitle.isDisplayed();
            pageTitle.sendKeys(value);
            pageTitle.sendKeys(Keys.ENTER);
            return true;
        } catch (Exception exp) {
            Debugger.println("EXCEPTION: Confluence page Login Failed. Check whether site is up and the credentials are right." + exp);
            return false;
        }
    }
}

