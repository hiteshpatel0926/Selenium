package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ApTopsTab {

	WebDriver driver;

	public ApTopsTab(WebDriver driver) {
		this.driver = driver;
	}

	// Using FindBy for locating elements
	@FindBy(how = How.XPATH, using = "//div[@class='block_content']//ul[@class='tree dynamized']//a[contains(text(),'T-shirts')]")
	WebElement Tshirts;

	public void clickOnTshirts() {
		Tshirts.click();
	}

}
