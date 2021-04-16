package co.uk.gel.config;

import co.uk.gel.jira.util.Debugger;
import org.junit.Assert;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class BrowserConfig {

    static Properties config;
    public static String browser;

    public static void loadConfig() {
        if (browser != null) {  //Already loaded, need not load again.
            return;
        }
        config  = loadConfigProperties("BrowserConfig.properties");
        browser = config.getProperty("Browser");
    }

    public static Properties loadConfigProperties(String filename) {
        String configLocation = System.getProperty("user.dir") + File.separator + "config";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(configLocation + File.separator + filename));
        } catch (IOException exp) {
            Debugger.println("File: " + filename + " not present in location : " + configLocation + ". Exception: " + exp);
            Assert.assertFalse("File: " + filename + " not present in location : " + configLocation,true);
        }
        return prop;
    }
//
//    public static String getBrowser() {
//        browser = System.getProperty("selected_browser");
//        setCurrentBrowser((browser == null || browser.isEmpty()) ? "FROM_CONFIG" : browser);
//        if(browser == null || browser.isEmpty() || browser.equalsIgnoreCase("FROM_CONFIG")){
//            //browser = null;//To ensure that it reads from the config file
//            loadConfig();
//        }
//        return browser;
//    }


    public static String browserVersion;
    public static String serverType;
    public static String osName;
    public static String osVersion;
    public static String rerunOption;

    public static String getBrowser() {
        browser = System.getProperty("browser");
        if(browser == null || browser.isEmpty()){
            browser = "Chrome";
        }
        return browser;
    }
    public static String getServerType() {
        serverType = System.getProperty("serverType");
        if (serverType == null || serverType.isEmpty()) {
            serverType = "Local";
        }
        return serverType;
    }

    public static String getBrowserVersion() {
        browserVersion = System.getProperty("browserVersion");
        if (browserVersion == null || browserVersion.isEmpty()) {
            browserVersion = "68";
        }
        return browserVersion;
    }

    public static String getOsName() {
        osName = System.getProperty("osName");
        if (osName == null || osName.isEmpty()) {
            osName = "Windows";
        }
        return osName;
    }

    public static String getOsVersion() {
        osVersion = System.getProperty("osVersion");
        if (osVersion == null || osVersion.isEmpty()) {
            osVersion = "10";
        }
        return osVersion;
    }

    public static String ifRerunNeeded() {
        rerunOption = System.getProperty("rerunOption");
        if (rerunOption == null || rerunOption.isEmpty()) {
            rerunOption = "No";
        }
        return rerunOption;
    }

}//end
