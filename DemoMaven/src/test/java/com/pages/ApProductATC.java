package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ApProductATC {
	
	WebDriver driver;

	public ApProductATC(WebDriver driver) 
	{
		this.driver = driver;
	}

	// Using FindBy for locating elements
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add to cart')]")
		WebElement addTocart;
	
		@FindBy(how = How.XPATH, using = "//div[@class='layer_cart_product col-xs-12 col-md-6']//h2[1]")
		WebElement successmessage;
	
		@FindBy(how = How.XPATH, using = "//span[@id='layer_cart_product_title']")
		WebElement pname;
		
		@FindBy(how = How.XPATH, using = "//span[@id='layer_cart_product_attributes']")
		WebElement pcolor;
		
		@FindBy(how = How.XPATH, using = "//span[@id='layer_cart_product_quantity']")
		WebElement qty;
		
		@FindBy(how = How.XPATH, using = "//span[@id='layer_cart_product_price']")
		WebElement baseprice;
		
		@FindBy(how = How.XPATH, using = "//span[@class='ajax_block_cart_total']")
		WebElement totalbilling;
		
		public void ATC() throws InterruptedException 
		{
			Thread.sleep(5000);
			addTocart.click();
			Thread.sleep(5000);
		}
		
		public String verification() 
		{
			String s1= successmessage.getText();
			System.out.println(s1);
			successmessage.getText().contains("Product successfully added to your shopping cart");
			
			pname.getText().contains("Faded Short Sleeve T-shirts");
			pcolor.getText().contains("Orange");
			qty.getText().contains("1");
			baseprice.getText().contains("16.51");
			totalbilling.getText().contains("18.51");
			return s1;
			
		}
}
