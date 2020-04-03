package BrowsersAndListeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends Browser implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Error " + result.getName() + " test has failed");
        takeScreenOnFailure(result.getName());
    }

}
