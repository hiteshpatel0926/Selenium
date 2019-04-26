package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ApLoginPage {
	WebDriver driver;

	public ApLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Using FindBy for locating elements
	@FindBy(how = How.XPATH, using = "//input[@id='email']")
	WebElement email;
	@FindBy(how = How.XPATH, using = "//input[@id='passwd']")
	WebElement password;
	@FindBy(how = How.XPATH, using = "//p[@class='submit']//span[1]")
	WebElement login;

	public void setEmail(String strEmail) {
		email.sendKeys(strEmail);
	}

	// This method is to set Password in the password text box
	public void setPassword(String strPassword) {
		password.sendKeys(strPassword);
	}

	// This method is to click on Login Button
	public void clickOnLoginButton() {
		login.click();
	}
}
