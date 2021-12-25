package com.ObjectRepository;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GenericLibrary.*;

public class Loginpage extends CommonUtils {
       
	
		@FindBy(xpath="//input[@id='user-name']")
	    public WebElement accountName;
	    
	    @FindBy(xpath="//input[@id='password']")
	    public WebElement password;
	    
	    @FindBy(xpath="//input[@id='login-button']")
	    public WebElement signIn;
	    

	    
	    public Loginpage() throws IOException, InterruptedException {
			super();
			PageFactory.initElements(driver, this);
		} 
	    
	
/*Function to Login Using Admin credential*/    
	    public void logintoSwag()
	    {
	    	waitFindEnterText(accountName,"standard_user");
	    	waitFindEnterText(password,"secret_sauce");
	    	ClickUsingjavaScript(signIn);
	    }
	    
  
	    
	    
}
