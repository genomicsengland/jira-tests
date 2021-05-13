package co.uk.gel.jira.util;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import com.google.common.base.Splitter;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TestUtils {

    public static final String PREFIX = "Tests";

    static String defaultDownloadLocation = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;

    public static String currentUser;

    public static String dateFormatReverserToYYYYMMDD(String dateInDDMMYYY) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInYYYYMMDD = LocalDate.parse(dateInDDMMYYY.trim(), formatter).format(formatter2);
        return dateInYYYYMMDD;
    }


    public static HashMap<String, String> splitAndGetParams(String combinedInput) {
        HashMap<String, String> paramNameValue = new HashMap<>();
        String[] allParams = combinedInput.split(":");
        for (String param : allParams) {
            paramNameValue.put(param.split("=")[0], param.split("=")[1]);
        }

        return paramNameValue;

    }

    public static String todayInDDMMYYYFormat() {
        SimpleDateFormat expectedFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = expectedFormat.format(new Date());
        return date;
    }

    public static String removeAWord(String sentence, String word) {
        String str = sentence;
        if (sentence.contains(word)) {
            str = sentence.replaceAll(word, "");
        }
        return str;
    }

    public static String[] getPatientSplitNames(String fullname) {//LastName,FirstName (TITLE)
        String[] names = new String[3];
        try {
            String lastName = fullname.substring(0, fullname.indexOf(","));
            String firstName = fullname.substring(fullname.indexOf(",") + 1, fullname.indexOf("("));
            String title = fullname.substring(fullname.indexOf("(") + 1, fullname.indexOf(")"));
            Debugger.println("First Name: " + firstName + ",LastName: " + lastName + ", Title: " + title);
            names[0] = firstName.trim();
            names[1] = lastName.trim();
            names[2] = title.trim();
        } catch (Exception exp) {
            Debugger.println("Ëxception in Splitting Patient Full name: " + exp);
            names[0] = "";
            names[1] = "";
            names[2] = "";
        }
        return names;
    }

    public static Map<String, String> splitStringIntoKeyValuePairs(String input) {
        Map<String, String> resultantStr = Splitter.on(':')
                .trimResults()
                .withKeyValueSeparator(
                        Splitter.on('=')
                                .limit(2)
                                .trimResults())
                .split(input);
        return resultantStr;
    }

    //Change month number to equivalent month form e.g 01 = Jan
    public static String convertMonthNumberToMonthForm(String monthBirthNumber) {
        String monthForm = null;
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        if (Integer.parseInt(monthBirthNumber) - 1 > 11 || Integer.parseInt(monthBirthNumber) - 1 < 0 || monthBirthNumber.isEmpty() || monthBirthNumber == null) {
            monthForm = "invalid number";
        } else {
            monthForm = months[Integer.parseInt(monthBirthNumber) - 1];
        }
        return monthForm;
    }

    public static String convertMonthToMonthNumberForm(String monthName) {

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] monthNumbers = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        for (int i = 0; i < months.length; i++) {
            if (monthName.equalsIgnoreCase(months[i])) {
                return monthNumbers[i];
            }
        }
        return null;
    }

    public static ArrayList<String> convertDOBNumbersToStrings(String dobInNumbers) {
        int strLength = dobInNumbers.length();
        String yearOfBirth = dobInNumbers.substring(0, 4);
        String monthOfBirth = dobInNumbers.substring(4, 6);
        String dayOfBirth = dobInNumbers.substring(6, strLength);

        ArrayList<String> dobInfoAsList = new ArrayList<String>();
        dobInfoAsList.add(0, dayOfBirth);
        dobInfoAsList.add(1, monthOfBirth);
        dobInfoAsList.add(2, yearOfBirth);

        return dobInfoAsList;
    }

    public static void deleteIfFilePresent(String title, String folder) {
        String downloadLocation = "";
        if (folder == null || folder.isEmpty()) {
            downloadLocation = defaultDownloadLocation;
        }
        File location = new File(downloadLocation);
        if (!location.exists()) {//Create the location, if not exists, first time may not be existing.
            location.mkdirs();
        }
        //Debugger.println("Deleting file if present from : " + downloadLocation);
        File[] files = location.listFiles();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains(title)) {
                    files[i].delete();
                    Debugger.println("File:" + files[i].getName() + " deleted from " + downloadLocation);
                }
            }
        }
        if (folder == null || folder.isEmpty()) {
            return;
        }
        //Delete from default location also
        //Debugger.println("Deleting ALSO file if present from : " + defaultDownloadLocation);
        File defLocation = new File(defaultDownloadLocation);
        File[] files_default = defLocation.listFiles();
        if (files_default != null && files_default.length > 0) {
            for (int i = 0; i < files_default.length; i++) {
                if (files_default[i].getName().contains(title)) {
                    files_default[i].delete();
                    Debugger.println("File:" + files_default[i].getName() + " deleted from " + defaultDownloadLocation);
                }
            }

        }
    }

    public static boolean isFilePresent(String title, String folder) {
        String downloadLocation = "";
        if (folder == null || folder.isEmpty()) {
            downloadLocation = defaultDownloadLocation;
        }
        File location = new File(downloadLocation);
        if (!location.exists()) {//Create the location, if not exists, first time may not be existing.
            Debugger.println("Specified download location: " + downloadLocation + " not exists.");
            return false;
        }
        boolean isPresent = false;
        File[] files = location.listFiles();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains(title)) {
                    isPresent = true;
                    break;
                }
            }
        }
        return isPresent;
    }

//    public static void clearAllSnapShots() {
//        try {
//            File location = new File(defaultSnapshotLocation);
//            if (!location.exists()) {//Create the location, if not exists, first time may not be existing.
//                location.mkdirs();
//                return;
//            }
//            File[] files = location.listFiles();
//            if (files == null || files.length < 1) {
//                return;
//            }
//            String[] today = getCurrentDay();
//            String prefix = today[0] + today[1];
//            for (int i = 0; i < files.length; i++) {
//                if (!(files[i].getName().startsWith("T" + prefix))) {
//                    files[i].delete();
//                } else {
//                    if (files[i].getName().startsWith("NTS")) {
//                        files[i].delete();
//                    }
//                }
//            }
//        } catch (Exception exp) {
//            Debugger.println("Exception in deleting all existing snapshots." + exp);
//        }
//    }

    public static void moveDownloadedFileTo(String fileName, String targetFolder, String extension) {
        String downloadLocation = defaultDownloadLocation;
        String target = defaultDownloadLocation + targetFolder + File.separator;
        File targetPath = new File(target);
        if (!targetPath.exists()) {//Create the location, if not exists, first time may not be existing.
            targetPath.mkdirs();
        }
        File defaultPath = new File(downloadLocation);
        File[] files = defaultPath.listFiles();
        if (files == null || files.length < 1) {
            Debugger.println("No files present in the download folder:" + downloadLocation);
            return;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().startsWith(fileName)) {
                try {
                    Path result = null;
                    result = Files.move(Paths.get(downloadLocation + files[i].getName()), Paths.get(target + fileName + extension));
                } catch (Exception exp) {
                    //Continue to next file, as if the file  is already open , it wont allow to move.
                    continue;
                }
            }
        }
    }

    public static String getDOBInMonthFormat(String dob) {//as dd-mm-yyyy
        try {
            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String[] dobs = dob.split("-");
            return dobs[0] + "-" + months[(Integer.parseInt(dobs[1]) - 1)] + "-" + dobs[2];
        } catch (Exception exp) {
            Debugger.println("Exception DOB IN MONTH format: " + dob);
            return dob;
        }
    }

    public static String getNHSDisplayFormat(String nhs) {//as dd-mm-yyyy
        try {//Assume length of NHS is 10 digit
            return nhs.substring(0, 3) + " " + nhs.substring(3, 6) + " " + nhs.substring(6, 10);
        } catch (Exception exp) {
            Debugger.println("Exception getNHSInSplitFormat: " + nhs);
            return nhs;
        }
    }

    public static String insertWhiteSpaceAfterEveryNthCharacter(String textToBeModified, String position) {
        String in = textToBeModified;
        String val = position;
        String result = in.replaceAll("(.{" + val + "})", "$1 ").trim();
        return result;
    }


    //Added new method to get Current day in String array (used in file upload section for patient choice)
    public static String[] getCurrentDay() {
        Calendar today = Calendar.getInstance();
        String year = "";
        String month = "";
        String day = "";
        int iyear = today.get(Calendar.YEAR);
        int imonth = today.get(Calendar.MONTH) + 1;
        int iday = today.get(Calendar.DATE);

        if (imonth < 10) {
            month = "0" + imonth;
        } else {
            month = "" + imonth;
        }
        year = "" + iyear;
        if (iday < 10) {
            day = "0" + iday;
        } else {
            day = "" + iday;
        }
        return new String[]{day, month, year};
    }

    public static String getAgeInYearsAndMonth(String dob) {
        try {
            //Debugger.println("Date of Birth: "+dob);
            String[] dobs = dob.split("-");
            LocalDate dob_date = LocalDate.of(Integer.parseInt(dobs[2]), Integer.parseInt(dobs[1]), Integer.parseInt(dobs[0]));
            LocalDate today = LocalDate.of(Integer.parseInt(getCurrentDay()[2]), Integer.parseInt(getCurrentDay()[1]), Integer.parseInt(getCurrentDay()[0]));
            Period diff = Period.between(dob_date, today);
            //Debugger.println("Age in Years and months: "+diff.getYears()+",months:"+diff.getMonths());
            if (diff.getMonths() > 1) {
                return "(" + diff.getYears() + "y " + diff.getMonths() + "m)";
            } else {
                return "(" + diff.getYears() + "y)";
            }
        } catch (Exception exp) {
            Debugger.println("Exception from finding Age in Years: " + exp);
            return null;
        }
    }



    public static boolean extractZipFile(String fileName) {

        try {
            Wait.seconds(5);
            String pathToFile = defaultDownloadLocation + fileName;
            Debugger.println("Extraction of file starting: " + pathToFile);
            File zipfile = new File(pathToFile);
            if (!zipfile.exists()) {
                Debugger.println("Zipped file does not exist in location " + pathToFile);
                return false;
            }
            ZipFile zippedFile = new ZipFile(pathToFile);
            Enumeration<? extends ZipEntry> entries = zippedFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                String name = defaultDownloadLocation + (zipEntry.getName());
                //Debugger.println("Zipped filename- " + name);
                //Create directory and sub-directories to extract the zip file
                File file = new File(name);
                if (name.endsWith("/")) {//If it is a directory
                    file.mkdirs();
                    continue;
                }
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                InputStream inputStream = zippedFile.getInputStream(zipEntry);
                FileOutputStream fileOutStream = new FileOutputStream(file);
                byte[] bytes = new byte[2096];
                int length;
                while ((length = inputStream.read(bytes)) >= 0) {
                    fileOutStream.write(bytes, 0, length);
                }
                inputStream.close();
                fileOutStream.close();
            }//while
            zippedFile.close();
            return true;
        } catch (IOException exp) {
            Debugger.println("Exception from Extracting the Zip file: " + fileName + " : " + exp);
            return false;
        }
    }





    public static String downloadFile(String url, String fileName, String folder) {
        try {
            String downLocation = "";
            if (folder == null || folder.isEmpty()) {
                downLocation = defaultDownloadLocation;
            } else {
                downLocation = defaultDownloadLocation + folder + File.separator;
            }
            InputStream inputStream = new URL(url).openStream();
            Wait.seconds(10);
            Files.copy(inputStream, Paths.get(downLocation + fileName), StandardCopyOption.REPLACE_EXISTING);
            Wait.seconds(5);
            return "Success";
        } catch (Exception exp) {
            return "Exception from downloadFile:" + exp;
        }
    }


    public static String getNtsTag(String fullTagName) {

        String ntsTag = "";
        String[] tags = fullTagName.split(",");
        if (tags != null) {
            for (int i = 0; i < tags.length; i++) {
                //Debugger.println("Tag:"+tags[i]);
                if (tags[i].contains("NTS")) {
                    ntsTag = tags[i].replaceAll("@", "");
                    ntsTag = ntsTag.replaceAll("]", "");
                    //Debugger.println("Here is NTS:"+ntsTag);
                    return ntsTag.trim();
                }
            }
        }
        return "T";
    }

    public static void printTheFullLogs(WebDriver driver, String NTStag) throws IOException {
        try {
            List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
            Debugger.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
            File file = new File("APIStatus-" + NTStag + ".html");
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append(String.format("<h1 style=\"text-align:center\">NGIS API - URL Test Results</h1>" +
                    "<table>" +
                    "<thead>" +
                    "<tr>" +
                    "<th style=\"text-align:left\">API-URL</th>" +
                    "<th style=\"text-align:center; text-indent: 5em;\">STATUS-CODE</th>" +
                    "</tr>" +
                    "</thead>" +
                    "<tbody>"));

            for (LogEntry entry : entries) {
                Object document = Configuration.defaultConfiguration().jsonProvider().parse(entry.getMessage());
                try {
                    JSONArray response = JsonPath.read(document, "$.*.method");
                    JSONArray url = JsonPath.read(document, "$.*.*.*.url");
                    if (response.get(0).equals("Network.responseReceived") && !url.toString().contains("static") &&
                            !url.toString().contains("logws") && !url.toString().contains("microsoft") && !url.toString().contains("msftauth") &&
                            url.toString().contains("https") && !url.get(0).toString().endsWith(".js") && !url.get(0).toString().endsWith(".ico")
                            && !url.get(0).toString().endsWith(".png") && !url.get(0).toString().endsWith(".svg") && !url.toString().contains("msauth")
                            && !url.toString().contains("login.live")) {

                        JSONArray statusCode = JsonPath.read(document, "$.*.*.*.status");
                        htmlBuilder.append(String.format("<tr>" +
                                        "<td style=\"text-align:left\">%s</td>" +
                                        "<td style=\"text-align:center; text-indent: 5em;\">%s</td>" +
                                        "</tr>",
                                url.get(0), statusCode.get(0)));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            htmlBuilder.append("</tbody>" +
                    "</table>");

            String html = htmlBuilder.toString();
            FileUtils.writeStringToFile(file, html, "UTF-8");
        }catch(Exception exp){

        }
    }

}
