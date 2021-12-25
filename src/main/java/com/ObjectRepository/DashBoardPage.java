package com.ObjectRepository;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericLibrary.CommonUtils;

public class DashBoardPage extends CommonUtils {
	
	@FindBy(xpath="//div[contains(text(),'Sauce Labs Backpack')]/../../following-sibling::div/button[contains(text(),'Add to cart')]")
	public WebElement saucelabBackpack_addtocartBtn;
	
	
	public DashBoardPage() throws IOException, InterruptedException {
		super();
		PageFactory.initElements(driver, this);
	} 
	
	
	public void addItemToCart()
	{
		test.info("Add Sauce lab Backpack to cart");
		ClickUsingjavaScript(saucelabBackpack_addtocartBtn);
	}
	

}
