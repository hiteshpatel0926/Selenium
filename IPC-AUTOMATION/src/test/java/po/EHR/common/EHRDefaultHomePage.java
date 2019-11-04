package po.EHR.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EHRDefaultHomePage {

	WebDriver driver;

	public EHRDefaultHomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Using FindBy for locating elements
	@FindBy(how = How.ID, using = "imgOpenChart1")
	WebElement OpenChart;

	public void OpenChart() {

		OpenChart.click();
	}

}
