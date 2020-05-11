package BrowsersAndListeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomListener extends Browser implements ITestListener {

    public void takeScreenOnFailure(String nameOfMethod) {
        Date dateNew = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String fileName = nameOfMethod + format.format(dateNew) + ".png";
        File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot1, new File("D:\\Screenshots\\" + fileName));
            System.out.println("Screenshot with " + fileName + " was created in D:\\Screenshots\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Error " + result.getName() + " test has failed");
        takeScreenOnFailure(result.getName());
    }

}
