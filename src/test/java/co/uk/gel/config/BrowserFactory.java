package co.uk.gel.config;

import co.uk.gel.jira.config.AppConfig;
import co.uk.gel.jira.util.Debugger;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BrowserFactory {
    WebDriver driver;

    public WebDriver getDriver() {
        return getDriver(AppConfig.getDevice(), BrowserConfig.getBrowser(), true);
    }

    public WebDriver getDriver(String device, String browser,
                               boolean javascriptEnabled) {
        DeviceType deviceType = DeviceType.valueOf(device.toUpperCase());
        BrowserEnum browserEnum = BrowserEnum.valueOf(browser.toUpperCase());
        switch (deviceType) {
            case DESKTOP:
                switch (browserEnum) {
                    case CHROME:
                        WebDriverManager.chromedriver().clearPreferences();
                        WebDriverManager.chromedriver().setup();
                        driver = getChromeDriver(null, javascriptEnabled);
                        break;
                    case FIREFOX:
                        driver = getFirefoxDriver(null, javascriptEnabled);
                        break;

                    case SAFARI:
                        driver = getSafariDriver(null, javascriptEnabled);
                        break;
                    default:
                      Debugger.println("Invalid Browser information");
                        Assert.fail();
                        break;
                }
                break;
            default:
                Debugger.println("Invalid Browser information");
                break;

        }

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        if (driver instanceof RemoteWebDriver) {
            ((RemoteWebDriver) driver).setLogLevel(Level.INFO);
        }
        if (AppConfig.device.equalsIgnoreCase("DESKTOP")) {
            driver.manage().window().maximize();
            return driver;
        }
        if (driver instanceof RemoteWebDriver && !(driver instanceof Rotatable)) {
            Dimension dim = new Dimension(480, 800);
            driver.manage().window().setSize(dim);
        }
        return driver;
    }

    private WebDriver getSafariDriver(Object object,
                                      boolean javascriptEnabled) {
        DesiredCapabilities safariCaps = DesiredCapabilities.safari();
        safariCaps.setCapability("safari.cleanSession", true);
        return new SafariDriver(safariCaps);
    }


    private WebDriver getFirefoxDriver(String userAgent,
                                       boolean javascriptEnabled) {
        String osName = System.getProperty("os.name");
        String osArchitecture = System.getProperty("os.arch");
        if (osName.toLowerCase().contains("windows")) {
            if (osArchitecture.contains("64")) {
                System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
            } else {
                System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver-v0.23.0-win32.exe");
            }
        } else {
            if (osArchitecture.contains("64")) {
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver-v0.23.0-linux64");
            } else {
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver-v0.23.0-linux32");
            }
        }
        //FirefoxDriverService ffDriverService = FirefoxDriverService.CreateDefaultService(<driver path>);
        return new FirefoxDriver(getFirefoxOptions(userAgent, javascriptEnabled));

    }

    private FirefoxOptions getFirefoxOptions(String userAgent,
                                             boolean javascriptEnabled) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates (true);
//			profile.setEnableNativeEvents(true);
        profile.shouldLoadNoFocusLib();
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("javascript.enabled", javascriptEnabled);
        String downloadFilepath = System.getProperty("user.dir") + File.separator +"downloads"+File.separator;
        Debugger.println("PATH: "+downloadFilepath);
        try{
            File download_loc = new File(downloadFilepath);
            if(!download_loc.exists()){
                download_loc.mkdirs();
            }
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir",downloadFilepath);
            // profile.setPreference( "layout.css.devPixelsPerPx", "0.6" );
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/json, application/ris, participant_id/csv, image/png, application/pdf, participant_id/html, participant_id/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet ");
            if (null != userAgent) {
                profile.setPreference("general.useragent.override", userAgent);
            }
        }catch(Exception exp){
            System.out.println("Exception in creating download directory..."+exp);
        }

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        firefoxOptions.setCapability("marionette", true);
        return firefoxOptions;
    }



    private ChromeOptions getChromeOptions(String userAgent,
                                           boolean javascriptEnabled) {
        ChromeOptions opts = new ChromeOptions();
        if (null != userAgent) {
            opts.addArguments("user-agent=" + userAgent);
        }
        if (!javascriptEnabled) {
            opts.addArguments("disable-javascript");
        }
        return opts;
    }


    private WebDriver getChromeDriver(String userAgent, boolean javascriptEnabled) {
        return new ChromeDriver(getChromeLocalOptions(userAgent, javascriptEnabled));
    }

    private ChromeOptions getChromeLocalOptions(String userAgent,
                                                boolean javascriptEnabled) {
        ChromeOptions chromeLocalOptions = new ChromeOptions();
        if (null != userAgent) {
            chromeLocalOptions.addArguments("user-agent=" + userAgent);
        }
        chromeLocalOptions.setExperimentalOption("prefs", downloadPathsetup());
        if (!javascriptEnabled) {
            chromeLocalOptions.addArguments("disable-javascript");
        }
        return chromeLocalOptions;
    }

    private HashMap downloadPathsetup() {
        String downloadFilePath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
        File location = new File(downloadFilePath);
        if (!location.exists()) {
            location.mkdirs();
        }
        HashMap<String, Object> pathPrefs = new HashMap<String, Object>();
        pathPrefs.put("profile.default_content_settings.popups", 0);
        pathPrefs.put("download.default_directory", downloadFilePath);
        return pathPrefs;
    }

}//end
