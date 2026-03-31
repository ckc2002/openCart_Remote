package listeners;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import utils.DriverFactory;
import utils.ExtentManager;
import utils.ExtentReportListener;
import utils.ExtentTestManager;

public class TestListener implements ITestListener {

	@Override 
	public void onTestStart(ITestResult result) {
		
		ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
		ExtentTestManager.setTest(test);
	}
	
	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.getInstance().flush();
	}
	
	
	
	// --- Empty implementations (required) ---
    //@Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(org.testng.ITestContext context) {}
    //@Override public void onFinish(org.testng.ITestContext context) {}
    
    
    
	// it is use for the screenshot listner on every test failure
//	@Override
//	public void onTestFailure(ITestResult result) {
//		try {
//			File src = ((TakesScreenshot) DriverFactory.getDriver())
//                    .getScreenshotAs(OutputType.FILE);
//			
//			 File dest = new File("screenshots/" + result.getName() + ".png");
//			 FileUtils.copyFile(src, dest);
//		} catch(Exception e) {
//			 e.printStackTrace();
//		}
//	}
	
}
