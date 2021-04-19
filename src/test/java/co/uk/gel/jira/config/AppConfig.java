package co.uk.gel.jira.config;

import co.uk.gel.jira.util.Debugger;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;



public class AppConfig {
    static Properties appConfig;
    public static String browser;
    public static String device;
    public static RequestSpecification requestSpec;
    public static String systemDashboard, serviceDesk, confluencePage;
    public static String jiraUsername;
    public static String jiraPassword;
    public static String confluenceUsername;
    public static String confluencePassword;
  /*  private static String appUsername;
    private static String appPassword;*/

    public static void loadConfig() {
        if (device != null) {//Already loaded, need not load again.
            return;
        }
        appConfig = loadConfigProperties("AppConfig.properties");
        device = appConfig.getProperty("Device");
        systemDashboard = appConfig.getProperty("APP_URL_SYSTEM_DASHBOARD");
        serviceDesk = appConfig.getProperty("APP_URL_SERVICE_DESK");
        confluencePage = appConfig.getProperty("APP_URL_CNFL_PAGE");
       jiraUsername =appConfig.getProperty("APP_JIRA_USER_NAME");
       jiraPassword =appConfig.getProperty("APP_JIRA_PASSWORD");
       confluenceUsername=appConfig.getProperty("APP_CONF_USER_NAME");
       confluencePassword=appConfig.getProperty("APP_CONF_PASSWORD");

    }
    public static String getDevice() {
        if (device == null) {
            loadConfig();
        }
        return device;
    }
    public static Properties loadConfigProperties(String filename) {
        String configLocation = System.getProperty("user.dir") + File.separator + "config";
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(configLocation +File.separator + filename));
        } catch (IOException e) {
            Debugger.println("Exception while trying to find file " + filename + "  at location " + configLocation + ". Exception: " + e);
            Assert.assertFalse("Exception: " + e,true);
        }
        return props;
    }

    public static String getBrowser() {
        return browser;
    }{

    }

    public static synchronized String getLocalisedMandatoryPropValue(String property) {
        Properties props = new Properties();
        String propFileName = props.getProperty("AppConfig.properties");
        String result = getPropertyValue(property);
        if(StringUtils.isEmpty(result)){
            Assert.assertFalse("FAILED: Property value returned empty/null for property: " + property,true);
        }
        return result;
    }

    private static synchronized String getPropertyValue(String property) {
        String result;
        HashMap<String, Properties> propertyObjMap = new HashMap<String, Properties>();
        Properties prop = propertyObjMap.get("AppConfig.properties");
        if(null == prop){
            prop = loadConfigProperties("AppConfig.properties");
            propertyObjMap.put("AppConfig.properties", prop);
        }
        result = prop.getProperty(property);
        return result;
    }

}//end