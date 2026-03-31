package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentReportListener implements ITestListener {
	private ExtentReports extent = ExtentManager.getInstance(); 
	
//	Test Start
	@Override
	public void onTestStart(ITestResult result) {
		
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		
		test.assignCategory(result.getMethod().getGroups());
		
		ExtentTestManager.setTest(test);
	}
	
	//Pass
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
	}
	
	// Fail + Screenshot 
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
		
		try {
			String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
			ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//Skip
	@Override 
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}
	
	//Suite Finish -> Save Report
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		ExtentTestManager.unload();
	}
	
	//ScreenShot method (replace you old listner)
	public String captureScreenshot(String testName) throws Exception {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        File src = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.FILE);

        String path = "screenshots/" + testName + "_" + timeStamp + ".png";

        File dest = new File(path);
        FileUtils.copyFile(src, dest);

        return path;
    }
}
