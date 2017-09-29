package common;

//import com.Scripts.Scripts;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TestListener extends com.Scripts.Scripts implements ITestListener {
    WebDriver Listener_driver = null;
    String filePath = "C:\\SCREENSHOTS\\";

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("***** Error " + result.getName() + " test has failed *****");
        String methodName = result.getName().toString().trim();

        Object currentClass = result.getInstance();
        Listener_driver = ((com.Scripts.Scripts) currentClass).getDriver();

        takeScreenShot(methodName);
    }

    public void takeScreenShot(String methodName) {

        System.out.println("***Driver.tostring " + Listener_driver.toString() + " ***");

        File scrFile = ((TakesScreenshot) Listener_driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name

        try {
            FileUtils.copyFile(scrFile, new File(getFullPath(methodName)));
            System.out.println("***Placed screen shot in " + filePath + " ***");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFullPath(String methodName) {
        String sFullPath;
        String sBrowserName;

        sBrowserName = Listener_driver.toString().substring(0, Listener_driver.toString().indexOf(":"));

        sFullPath = filePath + sBrowserName + "_" + methodName + "_" + getTimeStamp() + ".png";
        return sFullPath;
    }

    private String getTimeStamp() {
        String lTimeStamp;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        lTimeStamp = sdf.format(timestamp);

        return lTimeStamp;

    }

    public void onFinish(ITestContext context) {
    }

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }
}