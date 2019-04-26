package com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleSearchPage {
	
	WebDriver driver;

	public GoogleSearchPage(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	// Using FindBy for locating elements
			@FindBy(how = How.XPATH, using = "//input[@name='q']")
			WebElement searchtag;

			public void search() 
			{
				searchtag.sendKeys("Hiteshkumar Patel");
				searchtag.sendKeys(Keys.ENTER);
			}		
}
