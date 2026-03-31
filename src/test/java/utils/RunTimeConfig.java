package utils;

import org.testng.ITestContext;

public class RunTimeConfig {

	public static String getBrowser() {
		
		//CLI 
		String browser = System.getProperty("browser");
		
		//from XML Suite File -> used in old approach but as of today mvn cli is used to run the tests
//		if(browser == null) {
//			browser = context.getCurrentXmlTest().getParameter("browser");
//		}
		
		if(browser == null) {
			browser = ConfigReader.getProperty("browser");
		}
		
		return browser;
	}
	
	public static String getUrl() {
		String Url = System.getProperty("appUrl");
		
		//from XML Suite File
		//if(Url == null) {
			//Url = context.getCurrentXmlTest().getParameter("appUrl");
		//}
			
		if(Url == null) {
			Url = ConfigReader.getProperty("appUrl");
		}
				
		return Url;
	}

}
