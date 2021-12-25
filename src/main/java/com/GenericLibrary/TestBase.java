package com.GenericLibrary;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Importing log4j



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.InputStreamReader;

//Importing the selenium webdriver related libraries
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.DOMConfiguration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.xml.*;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

//import org.apache.commons.io.FileUtils;
public class TestBase {
	//Create ojects for Extent reports
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
			
			//setting a variable as null
			public static WebDriver driver;

						
			protected static Properties prop=new Properties();
			public static String browser;
			
			// Method to load and read the property file
			public static void readPropFile(){
				test.info("Reading properties file");
				FileInputStream fis;
				try {
					fis = new FileInputStream(TestData.config);
					
					try {
						prop.load(fis);
						
					} catch (Exception e) {
						e.printStackTrace();
						test.error(e.toString());	
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					test.error(e1.toString());	
				}
				
				
			}
			
//			public static void setupReports(String testCaseName)
//			{
//				try {
//				
//				//DOMConfigurator.configure("log4j.xml");
//				LoggerConfig.cla
//				Log.startTestCase(testCaseName);
//				}catch(Exception e) {
//					Log.error(e.toString());	
//				}	
//			}
   
			
			//Create a setup method to run before start of every suite
			@BeforeSuite
			public void setUp() throws IOException, InterruptedException
			{   
				//TestBase.readPropFile();
				String filepath=System.getProperty("user.dir")+"./src/main/java/com/GenericLibrary/Config.properties";
				Properties prop=new Properties();
				FileInputStream  fis = new FileInputStream(filepath);
				prop.load(fis);
				browser=prop.getProperty("browser");
				//Extent reports Setup;
				htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/ExtentReport/AutomationReport.html");
				extent = new ExtentReports();
		        extent.attachReporter(htmlReporter);
		        extent.setSystemInfo("OS", "Windows");
		        extent.setSystemInfo("Host Name", "SwagLabs");
		        extent.setSystemInfo("Environment", "QA");
		       // extent.setSystemInfo("User Name", "iEval Team"); 
		        htmlReporter.config().setChartVisibilityOnOpen(true);
		        htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
		        htmlReporter.config().setReportName("Swaglabs Test Automation Report");
		        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		        htmlReporter.config().setTheme(Theme.STANDARD);
		        
				if (browser.equalsIgnoreCase("chrome"))
				{
					System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverpath")); 
					ChromeOptions options = new ChromeOptions();
					options.addArguments("start-maximized");
					options.addArguments("--disable-impl-side-painting");
					options.addArguments("--disable-notifications");
					driver = new ChromeDriver(options);

				}
				else if (browser.equalsIgnoreCase("Firefox"))
				{
					Thread.sleep(1000);
					System.setProperty("webdriver.gecko.driver", prop.getProperty("geckoDriverPath"));
					driver = new FirefoxDriver();
					driver.manage().window().maximize();
				}
				else if(browser.equalsIgnoreCase("safari"))
				{
					driver = new SafariDriver();
					driver.manage().window().maximize();		
				}
		
			}
			
			
			
			//Before each test load the url
			@BeforeTest
			public void loadUrl() throws InterruptedException{
				try{
					test.info("loadUrl() - execution start");
					//Calling the url through the driver object
					/*appleConnectSignOutThroughCommand(props.getProperty("username"));
					appleConnectSignInThroughCommand(props.getProperty("username"), props.getProperty("password"));*/
					driver.get("https://www.saucedemo.com/");
					Thread.sleep(5000);
					//Setting time out if the credentials fails
					test.info("loadUrl() - execution end");
					//Assert.assertEquals(props.getProperty("sitetitle"), driver.getTitle());
				}catch(Exception e){
					test.info(e.toString());
				}
			}

			/*@BeforeMethod
			public void startRecording(Method method) throws ATUTestRecorderException{
				logger.info("startRecording(Method method) - execution start");
				this.recorder= new ATUTestRecorder(System.getProperty("user.dir")+"/test-output/Recordings/", method.getName(), false);
				recorder.start();
				logger.info("startRecording(Method method) - execution end");
			}
			
			@AfterMethod
			public void takescreenshotonfail(ITestResult result, Method method) throws IOException, ATUTestRecorderException
			{
				recorder.stop();
				renameFileExtension(System.getProperty("user.dir")+"/test-output/Recordings/"+method.getName()+".mov", "mp4");
				String movie = test.addScreencast(System.getProperty("user.dir")+"/test-output/Recordings/"+method.getName()+".mp4");
				String image = test.addScreenCapture(System.getProperty("user.dir")+"/test-output/Screenshots/"+result.getName()+".png");

				if (ITestResult.SUCCESS==result.getStatus()){
					//delete the file
					FileUtils.forceDelete(new File(System.getProperty("user.dir")+"/test-output/Recordings/"+method.getName()+".mp4"));
					logger.info("The movie file deleted as the testcase passed");
				}

				// Here will compare if test is failing then only it capture the video and provide the link
				else if(ITestResult.FAILURE==result.getStatus()) 
				{
					// Call method to capture screenshot
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
					// Copy files to specific location here it will save all screenshot in our project home directory and
					// result.getName() will return name of test case so that screenshot name will be same
					String screenshotPath = props.getProperty("screenshotsPath")+result.getName()+".png";
					FileUtils.copyFile(scrFile, new File((screenshotPath)));
					logger.info("Screenshot taken"+result.getName());
					//Add the screenshot to the report
					test.log(LogStatus.INFO, result.getName(), movie);
					test.log(LogStatus.INFO, "Download the video file using right click->save videos as option");
					logger.info("The link to screen recording is captured in the report");
					test.log(LogStatus.FAIL,result.getName(), image);			
				}
				extent.endTest(test);
			}*/
			
		
			
		/*	@AfterTest
			public void closeAllWindows(){
				String homeWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();

				//Use Iterator to iterate over windows
				Iterator<String> windowIterator =  allWindows.iterator();

				//Verify next window is available
				while(windowIterator.hasNext()){

					//Store the child window id
					String childWindow = windowIterator.next();

					if (homeWindow.equals(childWindow)){
						driver.switchTo().window(childWindow);
						//driver.manage().deleteAllCookies(); //delete all cookies
						deleteAllCookiesOneByOne();
						driver.quit();
					}
				}
				extent.flush();
				//extent.close();
			}*/
			
			@AfterSuite
			public void quitDriver(){
				try{
					Thread.sleep(1000);
					//driver.quit();
					//driver.close();
					extent.flush();
					Thread.sleep(5000);
										
				}catch (Exception e) {
					Log.error(e.toString());			
				}
				
			}			
			//Method to delete all cookies
			public void deleteAllCookiesOneByOne() {
				int noOfCookies = driver.manage().getCookies().size();
				if (noOfCookies > 0) {
					System.out.println("Number of cookies found: " + Integer.toString(noOfCookies));
				}
				Set<Cookie> cookies = driver.manage().getCookies();
				for (Cookie cookie: cookies) {
					System.out.println("Cookie found with name: " + cookie.getName() + " and path: "
							+ cookie.getPath() + " and domain: " + cookie.getDomain());
					String javascriptCall = "document.cookie = \"" + cookie.getName() + " path="+ cookie.getPath() + "; expires=Thu, 01-Jan-1970 00:00:01 GMT;\"";
					System.out.println("Attempting to expire the cookie with the following script: " + javascriptCall);
					((JavascriptExecutor)driver).executeScript(javascriptCall);
				}
				System.out.println("Number of cookies is now: " + Integer.toString(driver.manage().getCookies().size()));
			}
			
			
			//To rename the extension of the file
			public static boolean renameFileExtension(String source, String newExtension) throws FileNotFoundException, NullPointerException
			{
				String target;
				String currentExtension = getFileExtension(source);

				if (currentExtension.equals("")){
					target = source + "." + newExtension;
				}
				else {
					target = source.replaceFirst(Pattern.quote("." +
							currentExtension) + "$", Matcher.quoteReplacement("." + newExtension));
				}
				return new File(source).renameTo(new File(target));
			}

			//to get the file extension
			public static String getFileExtension(String sourceFile) {
				String ext = "";
				int i = sourceFile.lastIndexOf('.');
				if (i > 0 &&  i < sourceFile.length() - 1) {
					ext = sourceFile.substring(i + 1);
				}
				return ext;
			}
			

}
