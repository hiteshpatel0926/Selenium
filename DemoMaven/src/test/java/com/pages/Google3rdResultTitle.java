package com.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class Google3rdResultTitle {

	WebDriver driver;

	public Google3rdResultTitle(WebDriver driver) {
		this.driver = driver;
	}

	public void TitleVerification() {

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();

		String parentId = it.next();
		String childId = it.next();

		driver.switchTo().window(childId);
		String title = driver.getTitle();
		System.out.println("The 3rd Link Opened in New Tab's Title is ===>> " + title);

		driver.close();
		driver.switchTo().window(parentId);
		// System.out.println(driver.getTitle());
		driver.close();

	}

}
