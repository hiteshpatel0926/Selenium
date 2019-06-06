package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ApDefaultHomePage
{
	WebDriver driver;
	 
    public ApDefaultHomePage(WebDriver driver)
    { 
            this.driver=driver; 
    }
    
  //Using FindBy for locating elements
    @FindBy(how=How.XPATH, using="//a[@class='login']") WebElement signIn;
    
 // This method to click on SingIn
    
    public void clickOnSingin()
    {
    	signIn.click();
    }
    
}
