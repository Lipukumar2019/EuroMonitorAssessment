package com.ObjectRepository;

import java.io.IOException;

import org.apache.http.util.Asserts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericLibrary.CommonUtils;

import junit.framework.Assert;

public class ShoppingCartPage extends CommonUtils {
	
	@FindBy(className="shopping_cart_link")
	public WebElement cartLink;
	
	@FindBy(xpath="//span[contains(text(),'Your Cart')]")
	public WebElement pageTitle;
	
	@FindBy(xpath="//div[contains(text(),'Sauce Labs Backpack')]")
	public WebElement cartItemtitle;
	
	
	
	
	public ShoppingCartPage() throws IOException, InterruptedException {
		super();
		PageFactory.initElements(driver, this);
	} 
	
	
	public void navigateToCartPageAndVerify()
	{
		test.info("Navigate to Cart page And Verify");
		ClickUsingjavaScript(cartLink);
		test.info("Verify Your cart page title");
		waitForElement(pageTitle);
		Assert.assertEquals(pageTitle.getText(), "YOUR CART");
		test.info("Your cart page title is"  +pageTitle.getText());
		Assert.assertEquals(cartItemtitle.getText(), "Sauce Labs Backpack");
		test.info("Verify added item in Cart"  +cartItemtitle.getText());
	}
	
	

}
