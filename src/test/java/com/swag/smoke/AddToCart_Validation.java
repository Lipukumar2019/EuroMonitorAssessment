package com.swag.smoke;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.GenericLibrary.TestBase;
import com.ObjectRepository.DashBoardPage;
import com.ObjectRepository.Loginpage;
import com.ObjectRepository.ShoppingCartPage;

public class AddToCart_Validation extends TestBase{
	public static  String testCaseName;
	@BeforeSuite
	public void setUp() throws IOException, InterruptedException{
		super.setUp();
		testCaseName=this.getClass().getSimpleName();
		test = extent.createTest(testCaseName);
	}
	
	
	@Test(priority=1)
	public void appLogin()
	{ 
		try {
	test.info("Login to Application");
	Loginpage login=new Loginpage();
	login.logintoSwag();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Test(priority=2)
	public void additemToCartAndVerify()
	{ 
		try {
	    test.info("Add item to cart and verify");
	    DashBoardPage db=new DashBoardPage();
	    db.addItemToCart();
	    ShoppingCartPage cart=new ShoppingCartPage();
	    cart.navigateToCartPageAndVerify();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		driver.close();
	}
	
	//Execution report you can see under test-output folder -Extent report

}
