package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ApClickonProduct {

	WebDriver driver;

	public ApClickonProduct(WebDriver driver) {
		this.driver = driver;
	}

	// Using FindBy for locating elements
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Faded Short Sleeve T-shirts')]")
	WebElement Product;

	public void clickOnProduct() {
		Product.click();
	}

}
