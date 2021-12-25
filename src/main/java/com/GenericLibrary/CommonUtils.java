package com.GenericLibrary;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;


public class CommonUtils extends TestBase{

	
	
	public CommonUtils() throws IOException, InterruptedException {
		super();
		// TODO Auto-generated constructor stub
	}

	// Method to wait and enter text in a text field
	public void waitFindEnterText (WebElement we, String text){
		
			waitForPageLoad();
			we.sendKeys(text);
			test.info("Object found success "+we.toString() +" and entered the value " + text);
		
	}

	public static void ClickUsingjavaScript(WebElement we){
		try{

			if (we.isEnabled()){
				//javaScriptScrollUsingBy(by);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", we);
				test.info("The object "+we.toString()+" is clicked successfully using javascript");
			}else{
				test.info("The object "+we.toString()+" is is not enabled to be clicked using javascript");
			}

		}catch(Exception e){
			test.error(e);
		}		
	}

	public static void hoverControlOnElement(WebElement we){

		waitForPageLoad();
		Actions actions = new Actions(driver);
		actions.moveToElement(we).perform();
		test.info("The object was clicked successfuly");

	}

	//Method to wait for the page load (explicit wait, the Page time out is provided in the config.properties file)
	public static void waitForPageLoad(){

		int pageTimeOutSeconds = Integer.parseInt("30"); //Wait time flows from config.properties
		driver.manage().timeouts().pageLoadTimeout(pageTimeOutSeconds, TimeUnit.SECONDS);
		test.info("Page loaded successfully "+ driver.getTitle());
	}


	//Method to wait for an element (explicit wait, the object time out is provided in the config.properties file)
	public static boolean waitForElement(WebElement we) {

		int objectTimeOutSeconds = Integer.parseInt("30"); //Wait Time flows from config.properties
		WebDriverWait waitDriver = new WebDriverWait(driver, objectTimeOutSeconds);
		//waitDriver.until(ExpectedConditions.visibilityOf(we));
		waitDriver.until(ExpectedConditions.visibilityOf(we));
		return true;

	}
	
	
	
	
	//Method to clear the Textfields
		public static void clearTextfield(WebElement we)
		{
			try{
				waitForElement(we);
				we.clear();
				test.info("Clear the Textfield...");
				assert true;
			}catch(Exception e){
				test.error(e);
				assert(false);
			}
		}
		
		public static void selectDropDown(WebElement we)
		{
			Select sel=new Select(we);
			sel.selectByValue("core");
		}
 
	
	
	
	
	
	
	
	
	
	
}	





