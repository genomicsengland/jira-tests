package co.uk.gel.jira.pages;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.jira.config.AppConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages implements Navigable {

    protected WebDriver driver;
    protected JiraPage jiraPage;
    protected ConfluencePage confluencePage;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        jiraPage = PageFactory.initElements(driver, JiraPage.class);
        confluencePage =PageFactory.initElements(driver, ConfluencePage.class);
    }




    @Override
    public void NavigateTo(String pageToNavigate) {

    }

    @Override
    public void NavigateTo(String urlToNavigate, String pageToNavigate) {

    }

    @Override
    public void NavigateTo(String urlToNavigate, String pageToNavigate, String userType) {

    }

    @Override
    public void switchToURL(String currentURL) {

    }

    @Override
    public void switchToURL(String currentURL, String userType) {

    }
}
